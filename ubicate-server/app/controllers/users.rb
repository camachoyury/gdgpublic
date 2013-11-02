Ubicate::App.controllers :users do
  require 'json'
  require 'sinatra'
  require 'rubygems'
  
    
  #User list

  get '/user' do
      users = User.all(:order => 'created_at desc')
    
      if users.nil? then
        status 404
      else
        status 200
        users.to_json
    end
  end


  get '/user' do
      users = User.all(:order => 'created_at desc')
    
      if users.nil? then
        status 404
      else

        
        status 200
        users.to_json
    end
  end

  #create new user
  put '/user' do

    data = JSON.parse(request.body.string)
    if data.nil? or !data.key?('email') or !data.key?('password') then
      status 400
    else
      if User.exists?(email: data['email'])

          status 200
          user = User.find_by_email data['email']
          body(user.to_json)
      else  
          user = User.create(
                      :email => data['email'],
                      :password => data['password'], 
                      :created_at => Time.now
                      # :update_at => Time.now
            )
            if !user.save        
                status 406
                body(user.errors.to_json)
              
            else
              status 200
              body(user.to_json)
            end
      end  
    end
  end

  #update password of user

  post 'user/:id' do
    puts " update user #{params[:id]}"
    data = JSON.parse(request.body.string)

    if data.nil? then
      status 400
    else
      updated = false
      user = User.find(params[:id])
      user.update_attributes(:password => data['password']) 
      # body(user.to_json)
    end

  end

end
