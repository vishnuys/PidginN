package OOAD.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.Set;

@Entity
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String password; //Hash
	
	private String preferredLanguage;
	
	private Long contactNo;
	
	private Date CreatedOn;
	
	private Date UpdatedOn;
	
	//relationships
	@OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
	private Set<Group> groupCreatedBy;
	
	@OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
	private Set<Group> groupUpdatedBy;
	
	@OneToMany(mappedBy = "groupMembers_GroupId", cascade = CascadeType.ALL)
	private Set<GroupMembers> groupMembers_GroupId;
	
	@OneToMany(mappedBy = "userDetails_MappedUserId", cascade = CascadeType.ALL)
	private Set<GroupMembers> userDetails_MappedUserId;
	
	
	@OneToMany(mappedBy = "userMessageMapping_SenderUserId", cascade = CascadeType.ALL)
	private Set<UserMessageMapping> userMessageMapping_SenderUserId;
	
	@OneToMany(mappedBy = "userMessageMapping_RecieverUserId", cascade = CascadeType.ALL)
	private Set<UserMessageMapping> userMessageMapping_RecieverUserId;
	
//	private String profilePicture;
	
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDetails(int userID, String username, String firstName, String lastName, String password,
			String preferredLanguage, Long contactNo, Date createdOn, Date updatedOn, Set<Group> groupCreatedBy,
			Set<Group> groupUpdatedBy, Set<GroupMembers> groupMembers_GroupId,
			Set<GroupMembers> userDetails_MappedUserId, Set<UserMessageMapping> userMessageMapping_SenderUserId,
			Set<UserMessageMapping> userMessageMapping_RecieverUserId) {
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
		this.groupCreatedBy = groupCreatedBy;
		this.groupUpdatedBy = groupUpdatedBy;
		this.groupMembers_GroupId = groupMembers_GroupId;
		this.userDetails_MappedUserId = userDetails_MappedUserId;
		this.userMessageMapping_SenderUserId = userMessageMapping_SenderUserId;
		this.userMessageMapping_RecieverUserId = userMessageMapping_RecieverUserId;
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
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
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
	public Set<Group> getGroupCreatedBy() {
		return groupCreatedBy;
	}
	public void setGroupCreatedBy(Set<Group> groupCreatedBy) {
		this.groupCreatedBy = groupCreatedBy;
	}
	public Set<Group> getGroupUpdatedBy() {
		return groupUpdatedBy;
	}
	public void setGroupUpdatedBy(Set<Group> groupUpdatedBy) {
		this.groupUpdatedBy = groupUpdatedBy;
	}
	public Set<GroupMembers> getGroupMembers_GroupId() {
		return groupMembers_GroupId;
	}
	public void setGroupMembers_GroupId(Set<GroupMembers> groupMembers_GroupId) {
		this.groupMembers_GroupId = groupMembers_GroupId;
	}
	public Set<GroupMembers> getUserDetails_MappedUserId() {
		return userDetails_MappedUserId;
	}
	public void setUserDetails_MappedUserId(Set<GroupMembers> userDetails_MappedUserId) {
		this.userDetails_MappedUserId = userDetails_MappedUserId;
	}
	public Set<UserMessageMapping> getUserMessageMapping_SenderUserId() {
		return userMessageMapping_SenderUserId;
	}
	public void setUserMessageMapping_SenderUserId(Set<UserMessageMapping> userMessageMapping_SenderUserId) {
		this.userMessageMapping_SenderUserId = userMessageMapping_SenderUserId;
	}
	public Set<UserMessageMapping> getUserMessageMapping_RecieverUserId() {
		return userMessageMapping_RecieverUserId;
	}
	public void setUserMessageMapping_RecieverUserId(Set<UserMessageMapping> userMessageMapping_RecieverUserId) {
		this.userMessageMapping_RecieverUserId = userMessageMapping_RecieverUserId;
	}
	
}
