Ubicate::App.controllers :comments do
  
  #create new comment
  put '/' do

    data = JSON.parse(request.body.string)
    if data.nil? or !data.key?('checkin_id') or !data.key?('comment') then
      status 400
    else
      comment = Comment.create(
                  :checkin_id => data['checkin_id'],
                  :comment => data['comment'],
                  :created_at => Time.now
                  # :update_at => Time.now
        )
      comment.save
      status 200
      body(comment.id.to_json)
      
    end
  end

end
