# clj-drmaa

## How to test

To test clj-drmma I'm using [vagrant](http://vagrantup.com) to create a VM running ubuntu and SGE standalone.
You can do the same, after you setup vagrant, with the following commands:


    $ vagrant box add lucid64 http://files.extraneous.org/lucid64.box 
    $ git submodule update --init
    $ vagrant up
    $ vagrant ssh
    $ qsub -V -b y -cwd hostname
    $ qstat ; you may see the job if it hasn't been processed
    $ cat hostname.o1 ; to see the jobs output, assuming it worked correctly
