package models;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;

import javax.persistence.Id;

import play.db.ebean.Model;

public class User extends Model {
	
	@Id
	private int Id;

	@Required(message="Please enter the user name field and it should not be blank")
	@Formats.NonEmpty
	private String userName;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private String location;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
