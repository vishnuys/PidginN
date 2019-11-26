package OOAD.Entities;

import java.util.Date;

public class GroupMembers {
	
	private int groupMemebersId;

    private int MappedUserIds;

	
	private Boolean isAdmin;
	
    private int userAddedBy;
	
	private Date userAddedOn;

	
	public GroupMembers() {}

	public GroupMembers(int groupMemebersId, int MappedUserIds, Boolean isAdmin, int userAddedBy, Date userAddedOn) {
		super();
		this.groupMemebersId = groupMemebersId;
		this.MappedUserIds = MappedUserIds;
		this.isAdmin = isAdmin;
		this.userAddedBy = userAddedBy;
		this.userAddedOn = userAddedOn;
	}


	public int getGroupMemebersId() {
		return groupMemebersId;
	}
	public void setGroupMemebersId(int groupMemebersId) {
		this.groupMemebersId = groupMemebersId;
	}

	public void setMappedUserIds(int MappedUserIds) {
		this.MappedUserIds = MappedUserIds;
	}

	public int getMappedUserIds() {
		return MappedUserIds;
	}



	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int userAddedBy() {
		return userAddedBy;
	}

	public void setUserDetails_userAddedBy(int userAddedBy) {
		this.userAddedBy = userAddedBy;
	}

	public Date getUserAddedOn() {
		return userAddedOn;
	}

	public void setUserAddedOn(Date userAddedOn) {
		this.userAddedOn = userAddedOn;
	}


	
}
