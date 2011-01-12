remote_file "/usr/local/bin/lein" do
  source "http://github.com/technomancy/leiningen/raw/stable/bin/lein"
  mode 0755
end

execute "lein self-install" do
  user "vagrant"
end