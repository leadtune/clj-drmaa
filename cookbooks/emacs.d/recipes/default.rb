execute "git clone git://github.com/leadtune/emacs-starter-kit.git ~/.emacs.d" do
  user "vagrant"
  not_if "test -d ~/.emacs.d"
end
