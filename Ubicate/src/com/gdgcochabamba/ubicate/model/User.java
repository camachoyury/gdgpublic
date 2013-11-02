package com.gdgcochabamba.ubicate.model;

import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("id")	
private long id;

@SerializedName("email")
private String email;

@SerializedName("password")
private String password;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "User [id=" + id + ", email=" + email
			+ "]";
}


}
