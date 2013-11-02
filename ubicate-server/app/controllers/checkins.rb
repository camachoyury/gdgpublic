Ubicate::App.controllers :checkins do
 require 'DTOCheckin'
  
get '/:id' do
  puts " update user #{params[:id]}"
      checkins = Checkin.find(:user_id => '#params[:id]')
    # checkins = Checkin.where(user_id: params[:id]).order('created_at DESC').limit(1)
      if checkins.nil? then
        status 404
      else
        status 200
        checkins.to_json
    end
  end

get '/' do
  
    @last_checkins = []
    users = User.all()
      if users.nil? then
          last_checkins
        else
          users.each do |user|
            puts "userss"+user.to_json
            checkin = Checkin.where(user_id: user.id).order('created_at DESC').limit(1)
            puts "checkin   "+checkin[0].place_id.to_s
            place = Place.where(id: checkin[0].place_id)
            puts "Place   "+place.to_json
            user_name = email_to_name(user.email)
            @last_checkins << DTOCheckin.new(user_name, place[0].name, place[0].longitude,  place[0].latitude, checkin[0].created_at)
          end
      end
          
      if @last_checkins.nil? then
        status 404
      else
        status 200
        @last_checkins.to_json
    end
  end

  def email_to_name(email)
      name = email[/[^@]+/]
      name.split(".").map {|n| n.capitalize }.join(" ")
  end

  #create new user
  put '/' do

    data = JSON.parse(request.body.string)
    puts " this is the data " + data.to_s 
    comment_request = data['comment']
    reference = data['place_id']
    if data.nil? or !data.key?('user_id') or !data.key?('place_id') then
      status 400
    else
      checkin = Checkin.create(
                  :user_id => data['user_id'],
                  :place_id => reference, 
                  :created_at => Time.now
        )

      if checkin.save
        if !comment_request.blank? then
         comment = Comment.create(
                    :checkin_id => checkin.id.to_s,
                    :comments => comment_request 
            )
          comment.save
        else
          status 400
        end
      end
      status 200
      body(checkin.to_json)
      
    end
  end

  #update password of user

  post '/:id' do
    puts " update user #{params[:id]}"
    data = JSON.parse(request.body.string)

    if data.nil? then
      status 400
    else
      updated = false
      user = User.find(params[:id])
      user.update_attributes(:password => data['password']) 
    end

  end



end

