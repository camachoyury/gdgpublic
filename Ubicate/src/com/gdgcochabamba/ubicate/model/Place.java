package com.gdgcochabamba.ubicate.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;



public class Place implements Serializable {
	 
    @SerializedName("id")
    public String id;
   
    @SerializedName("name")
    public String name;
     
    @SerializedName("reference")
    public String reference;
     
    @SerializedName("icon")
    public String icon;
     
    @SerializedName("vicinity")
    public String vicinity;
     
    @SerializedName("geometry")
    public Geometry geometry;
     
    @SerializedName("formatted_address")
    public String formatted_address;
     
    @SerializedName("formatted_phone_number")
    public String formatted_phone_number;
    
    @SerializedName("user_id")
    public String userId;
    
    
     
    
    
    public Place(String name, String reference, 
			 Geometry geometry, String formatted_address,
			String formatted_phone_number) {
		super();
		this.id = id;
		this.name = name;
		this.reference = reference;
		this.icon = icon;
		this.vicinity = vicinity;
		this.geometry = geometry;
		this.formatted_address = formatted_address;
		this.formatted_phone_number = formatted_phone_number;
	}


    
	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getVicinity() {
		return vicinity;
	}


	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}


	public Geometry getGeometry() {
		return geometry;
	}


	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}


	public String getFormatted_address() {
		return formatted_address;
	}


	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}


	public String getFormatted_phone_number() {
		return formatted_phone_number;
	}


	public void setFormatted_phone_number(String formatted_phone_number) {
		this.formatted_phone_number = formatted_phone_number;
	}


	public static class Geometry implements Serializable
    {
        @SerializedName("location")
        public Location location;

        
		public Location getLocation() {
			return location;
		}


		public void setLocation(Location location) {
			this.location = location;
		}


		@Override
		public String toString() {
			return "Geometry [location=" + location.lat +" "+location.lng+ "]";
		}
        
        
    }
     
    public static class Location implements Serializable
    {
        @SerializedName("lat")
        public double lat;
         
        @SerializedName("lng")
        public double lng;

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLng() {
			return lng;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}
        
        
    }
    

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", reference="
				+ reference + ", formatted_address=" + formatted_address + "]";
	}
	
	
    
    
    
}