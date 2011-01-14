(ns clj-drmaa.core
  (use [clojure.contrib
         [def :only [defn-memo defnk]]])
   (import [org.ggf.drmaa DrmaaException Session SessionFactory JobTemplate]))

(defn at-exit
  "Similar to Ruby's at_exit hook. The given f will be ran will the process closes."
  [f]
  (.addShutdownHook (Runtime/getRuntime) (Thread. f)))

(defn-memo get-session
  "Returns an initialized session for DRMAA.
   From what I can tell you are only allowed one session per process (singleton).
   Additionaly, you need to ensure that the session resources are properly
   cleaned up.  This function handles all of this for you."
  []
  (let [session (doto (.getSession (SessionFactory/getFactory)) (.init ""))]
    (at-exit #(.exit session))
    session))

(defnk block-on-jobs
  "Blocks until the given job ids complete or fail.
   Options and defaults:

   - :timeout, like it sounds, but it is unclear from the DRMAA docs if this is really supported.
     default: Will run until completion.
  
   - :dispose, removes the jobs from the system once it is complete, meaning you can not query about them.
     default: true"
  [job-ids :timeout (Session/TIMEOUT_WAIT_FOREVER) :dispose true]
  (.synchronize (get-session) job-ids timeout dispose))

(defnk run-jobs
  "Starts multiple jobs with cmd and for each args vector found in job-args.
   Options and defaults:

   - :block?, boolean indicating if this fn should block until jobs are complete
     default: false

   - :block-options, options passed to block-on-jobs if block? is true
     default: {}

   Example:

   (run-jobs \"sleep\" [[\"5\"] [\"10\"]] :block? true)"
  [cmd job-args :block? false :block-options {}]
  (let [session (get-session)
        jt (doto (.createJobTemplate session) (.setRemoteCommand cmd))
        job-ids (doall (for [args job-args]
                         (do (.setArgs jt args)
                             (.runJob session jt))))]
    (.deleteJobTemplate session jt)
    (when block? (apply block-on-jobs job-ids block-options))
    job-ids))
