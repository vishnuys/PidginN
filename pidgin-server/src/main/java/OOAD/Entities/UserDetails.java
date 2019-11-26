package OOAD.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.Set;

public class UserDetails {

	private int userID;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String password; //Hash
	
	private String preferredLanguage;
	
	private String contactNo;
	
	private Date CreatedOn;
	
	private Date UpdatedOn;
	

	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDetails(int userID, String username, String firstName, String lastName, String password,
			String preferredLanguage, String contactNo, Date createdOn, Date updatedOn) {
		super();
		this.userID = userID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.preferredLanguage = preferredLanguage;
		this.contactNo = contactNo;
		CreatedOn = createdOn;
		UpdatedOn = updatedOn;
	}
	
	public UserDetails(int userID, String username, String firstName, String lastName, String preferredLanguage, String contactNo){
		super();
		this.userID = userID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.preferredLanguage = preferredLanguage;
		this.contactNo = contactNo;
	}
	

	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPreferredLanguage() {
		return preferredLanguage;
	}
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Date getCreatedOn() {
		return CreatedOn;
	}
	public void setCreatedOn(Date createdOn) {
		CreatedOn = createdOn;
	}
	public Date getUpdatedOn() {
		return UpdatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		UpdatedOn = updatedOn;
	}
}
