require_recipe "apt"
require_recipe "git"
require_recipe "vim"
require_recipe "emacs"
require_recipe "emacs.d"
require_recipe "screen"
node["java"]["install_flavor"] = "sun"
require_recipe "java"

require_recipe "lein"
require_recipe "sge-standalone"

