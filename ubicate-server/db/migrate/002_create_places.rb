class CreatePlaces < ActiveRecord::Migration
  def self.up
    create_table :places do |t|
      t.belongs_to :user
      t.string :reference
      t.timestamps
    end
  end

  def self.down
    drop_table :places
  end
end
