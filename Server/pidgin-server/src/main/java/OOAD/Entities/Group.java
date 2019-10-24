package OOAD.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.Set;

@Entity
public class Group {
	
	//attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupId;
	
	private String groupName;

	@ManyToOne
    @JoinColumn
    private UserDetails createdBy;
	
	@ManyToOne
    @JoinColumn
    private UserDetails updatedBy;
	
	private Date createdOn;
	
	private Date updatedOn;
	
	//for group Members
	@OneToMany(mappedBy = "groupMembers_GroupId", cascade = CascadeType.ALL)
	private Set<GroupMembers> groupMembers_GroupId;
	

	@OneToMany(mappedBy = "userMessageMapping_GroupId", cascade = CascadeType.ALL)
	private Set<UserMessageMapping> userMessageMapping_GroupId;
	
	
	public Group(int groupId, String groupName, UserDetails createdBy, UserDetails updatedBy, Date createdOn,
			Date updatedOn, Set<GroupMembers> groupMembers_GroupId,
			Set<UserMessageMapping> userMessageMapping_GroupId) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.groupMembers_GroupId = groupMembers_GroupId;
		this.userMessageMapping_GroupId = userMessageMapping_GroupId;
	}
	
	public Group() {}


	public int getGroupId() {
		return groupId;
	}


	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public UserDetails getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(UserDetails createdBy) {
		this.createdBy = createdBy;
	}


	public UserDetails getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(UserDetails updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public Date getUpdatedOn() {
		return updatedOn;
	}


	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}


	public Set<GroupMembers> getGroupMembers_GroupId() {
		return groupMembers_GroupId;
	}


	public void setGroupMembers_GroupId(Set<GroupMembers> groupMembers_GroupId) {
		this.groupMembers_GroupId = groupMembers_GroupId;
	}


	public Set<UserMessageMapping> getUserMessageMapping_GroupId() {
		return userMessageMapping_GroupId;
	}


	public void setUserMessageMapping_GroupId(Set<UserMessageMapping> userMessageMapping_GroupId) {
		this.userMessageMapping_GroupId = userMessageMapping_GroupId;
	}


	
}
