class Checkin < ActiveRecord::Base
 	
 	belongs_to :user  # foreign key - programmer_id
  	belongs_to :place 

end
