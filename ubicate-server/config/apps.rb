##
# This file mounts each app in the Padrino project to a specified sub-uri.
# You can mount additional applications using any of these commands below:
#
#   Padrino.mount('blog').to('/blog')
#   Padrino.mount('blog', :app_class => 'BlogApp').to('/blog')
#   Padrino.mount('blog', :app_file =>  'path/to/blog/app.rb').to('/blog')
#
# You can also map apps to a specified host:
#
#   Padrino.mount('Admin').host('admin.example.org')
#   Padrino.mount('WebSite').host(/.*\.?example.org/)
#   Padrino.mount('Foo').to('/foo').host('bar.example.org')
#
# Note 1: Mounted apps (by default) should be placed into the project root at '/app_name'.
# Note 2: If you use the host matching remember to respect the order of the rules.
#
# By default, this file mounts the primary app which was generated with this project.
# However, the mounted app can be modified as needed:
#
#   Padrino.mount('AppName', :app_file => 'path/to/file', :app_class => 'BlogApp').to('/')
#

##
# Setup global project settings for your apps. These settings are inherited by every subapp. You can
# override these settings in the subapps as needed.
#
Padrino.configure_apps do
  enable :sessions
  set :session_secret, '7b669d5751701c14c3312d4076aea219fcdd67b570c38e14cc8ae6bf86453f58'
  # set :protection, true
#   set :protect_from_csrf, true
#   set :protection, :except => [:json_csrf]
# set :environment, :production
  enable :cross_origin
  set :bind, '0.0.0.0'
  set :allow_origin, :any
#   # HTTP methods allowed
  set :allow_methods, [:get, :post, :put, :options]
#   # Allow cookies to be sent with the requests
  set :allow_credentials, true   

  # set :protection, :except => [:http_origin]
#   set :protection, false
#   # set :protect_from_csrf, true
#   # set :allow_disabled_csrf, true
#   # set :protection, :except => :ip_spoofing
  set :protect_from_csrf, false

end

# Mounts the core application for this project
Padrino.mount('Ubicate::App', :app_file => Padrino.root('app/app.rb')).to('/')
