class Place < ActiveRecord::Base

	has_many :checkins
has_many :users, through: :checkins
has_one :user



end
