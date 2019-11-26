package OOAD;

public class UserIdentityInfo {
	private String userID;
	private String username;
	
	UserIdentityInfo(String userID, String username) {
		this.userID = userID;
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
}
