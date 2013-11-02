class User < ActiveRecord::Base

has_many :checkins
has_many :places, through: :checkins

validates_presence_of :email, message: "Email cannot be blank."
validates_presence_of :password, message: "Password cannot be blank."
 # validates_uniqueness_of :email, message: "This user account already registered."
end
