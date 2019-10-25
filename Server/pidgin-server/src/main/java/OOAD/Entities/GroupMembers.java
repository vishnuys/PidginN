package OOAD.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GroupMembers {
	
	//attributes
	@Id
	private int groupMemebersId;
	@ManyToOne
    @JoinColumn
    private UserDetails groupMembers_GroupId;

	@ManyToOne
    @JoinColumn
    private UserDetails userDetails_MappedUserId;
	
	private Boolean isAdmin;
	
	@ManyToOne
    @JoinColumn
    private UserDetails userDetails_UserAddedBy;
	
	private Date userAddedOn;

	
	public GroupMembers() {}

	public GroupMembers(int groupMemebersId, UserDetails groupMembers_GroupId, UserDetails userDetails_MappedUserId,
			Boolean isAdmin, UserDetails userDetails_UserAddedBy, Date userAddedOn) {
		super();
		this.groupMemebersId = groupMemebersId;
		this.groupMembers_GroupId = groupMembers_GroupId;
		this.userDetails_MappedUserId = userDetails_MappedUserId;
		this.isAdmin = isAdmin;
		this.userDetails_UserAddedBy = userDetails_UserAddedBy;
		this.userAddedOn = userAddedOn;
	}



	public UserDetails getGroupMembers_GroupId() {
		return groupMembers_GroupId;
	}
	public int getGroupMemebersId() {
		return groupMemebersId;
	}
	public void setGroupMemebersId(int groupMemebersId) {
		this.groupMemebersId = groupMemebersId;
	}

	public void setGroupMembers_GroupId(UserDetails groupMembers_GroupId) {
		this.groupMembers_GroupId = groupMembers_GroupId;
	}

	public UserDetails getUserDetails_MappedUserId() {
		return userDetails_MappedUserId;
	}

	public void setUserDetails_MappedUserId(UserDetails userDetails_MappedUserId) {
		this.userDetails_MappedUserId = userDetails_MappedUserId;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public UserDetails getUserDetails_UserAddedBy() {
		return userDetails_UserAddedBy;
	}

	public void setUserDetails_UserAddedBy(UserDetails userDetails_UserAddedBy) {
		this.userDetails_UserAddedBy = userDetails_UserAddedBy;
	}

	public Date getUserAddedOn() {
		return userAddedOn;
	}

	public void setUserAddedOn(Date userAddedOn) {
		this.userAddedOn = userAddedOn;
	}


	
}
