(defproject clj-drmaa "0.1.0"
  :description "drmaa client"
  :repositories {"leadtune-repo" "http://c0026236.cdn1.cloudfiles.rackspacecloud.com/repo"}
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [lt/drmaa "ubuntu-version"]
                 [lt/jgdi "ubuntu-version"]
                 [lt/juti "ubuntu-version"]]
  :dev-dependencies [[swank-clojure "1.2.1"]
                     [midje "1.0.1"]])
