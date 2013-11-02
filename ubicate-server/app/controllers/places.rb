Ubicate::App.controllers :places do
  
  get '/' do
      places = Place.all(:order => 'created_at desc')
    
      if places.nil? then
        status 404
      else
        status 200
        places.to_json
    end
  end


  #create new place
  put '/' do

    data = JSON.parse(request.body.string)
    puts data.to_json
    if data.nil? or !data.key?('user_id') or !data.key?('reference') then
      status 400
    else
      place = Place.create(
                  :user_id => data['user_id'],
                  :reference => data['reference'], 
                  :created_at => Time.now
                  # :update_at => Time.now
        )
      place.save
      status 200
      body(place.id.to_json)
      
    end
  end


  #update place

  # post '/:id' do
  #   puts " update place #{params[:id]}"
  #   data = JSON.parse(request.body.string)

  #   if data.nil? then
  #     status 400
  #   else
  #     updated = false
  #     place = Place.find(params[:id])
  #     place.update_attributes(:name => data['name'],:address => data['address'], :longitude => data['longitude'],:latitude =>data['latitude']) 
      
  #   end

  # end
  

end
