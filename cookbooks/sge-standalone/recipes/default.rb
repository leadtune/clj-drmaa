#remote_file "/usr/local/bin/lein" do
#  source "http://github.com/technomancy/leiningen/raw/stable/bin/lein"
#  mode 0755
#end
%w[gridengine-master gridengine-exec gridengine-client gridengine-qmon gridengine-common].each do |p|
 package p
end


cookbook_file "/tmp/foo.q" do
  source "foo.q" # this is the value that would be inferred from the path parameter
  mode "0644"
end

execute "qconf -as vagrantup.com && qconf -Aq /tmp/foo.q" do
  not_if "qconf -sql | grep foo -q"
end
