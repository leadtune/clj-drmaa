Vagrant::Config.run do |config|
  config.vm.box = "lucid64"
  config.vm.box_url = "http://files.extraneous.org/lucid64.box"

  # Boot with a GUI so you can see the screen. (Default is headless)
  # config.vm.boot_mode = :gui

  # Assign this VM to a host only network IP, allowing you to access it
  # via the IP.
  # config.vm.network "33.33.33.10"

  # Forward a port from the guest to the host, which allows for outside
  # computers to access the VM, whereas host only networking does not.
  config.vm.forward_port "swank", 4005, 5050

  # Share an additional folder to the guest VM. The first argument is
  # an identifier, the second is the path on the guest to mount the
  # folder, and the third is the path on the host to the actual folder.
  # config.vm.share_folder("v-data", "/vagrant_data", "../data")

  # Enable provisioning with chef solo, specifying a cookbooks path (relative
  # to this Vagrantfile), and adding some recipes and/or roles.
  #
  config.vm.provisioner = :chef_solo
  config.chef.cookbooks_path = %w[cookbooks opscode-cookbooks]
  # config.chef.add_recipe "mysql"
  # config.chef.add_role "web"
  #
  # You may also specify custom JSON attributes:
  # Java runtime hack to avoid bugs in opscode's tomcat6 cookbook, and ohai..
  config.chef.json = {:languages => 
    {:java => 
      {:runtime => {:name => "Java(TM) SE Runtime Environment", :build => "1.6.0_20-b02"},
       :hotspot => {:name => "Java HotSpot(TM) 64-Bit Server VM", :build => "16.3-b01, mixed mode"}
      }}}
  config.chef.add_recipe "vagrant_main"
end
