# clj-drmaa

Thin Clojure wrappers for DRMAA to be used with <strike>Sun</strike> [Oracle Grid Engine][sge].
For more information about the underlying Java DRMAA libraries start [here][java-example].

## Usage
  
  TODO

## How to test and/or experiment with clj-drmma

To test (just manually so far) clj-drmma I'm using [vagrant](http://vagrantup.com) to create a VM running ubuntu and SGE standalone.
You can do the same, after you setup vagrant, with the following commands:


    $ vagrant box add lucid64 http://files.extraneous.org/lucid64.box 
    $ git submodule update --init
    $ vagrant up
    $ vagrant ssh
    $ qsub -V -b y -cwd hostname
    $ qstat ; you may see the job if it hasn't been processed
    $ cat hostname.o1 ; to see the jobs output, assuming it worked correctly
    
Once you have verified that SGE is working properly fire up a repl to play with `clj-drmaa`:

    guest-box $ cd /vagrant && lein deps && ./with-sge lein repl
    REPL started; server listening on localhost:58079.
    user=> (use 'clj-drmaa.core)
    nil
    user=> (run-jobs "/vagrant/test-resources/sleeper" (map #(list (str %)) (range 5)) :block? true)

The above command will block until all the jobs have been processed.  Once done you will find that the homedir (`~/`)
has been littered with all of the jobs output from above.

To use slime on your local (host) box you can connect to the guest's swank using SSH to forward the port:

    guest-box $ cd /vagrant
    guest-box $ ./with-sge lein swank

    host-box $ vagrant ssh_config >> ~/.ssh/config # only need to do once
    host-box $ ssh -2 -N -f -L 4005:localhost:4005 vagrant

  

## License

Copyright (C) 2010 LeadTune and Ben Mabey

Released under the MIT License: <http://www.opensource.org/licenses/mit-license.php>

[sge]: http://en.wikipedia.org/wiki/Oracle_Grid_Engine
[java-example]: http://wikis.sun.com/display/GridEngine/Automating+Grid+Engine+Functions+Through+DRMAA#AutomatingGridEngineFunctionsThroughDRMAA-JavaApplicationExamples
