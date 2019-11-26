package OOAD.Entities;

public class UserConnections {
	
	private int userID;
	
	private int connectionUserID;

	
	
	public UserConnections(int userID, int connectionUserID) {
		super();
		this.userID = userID;
		this.connectionUserID = connectionUserID;
	}

	public UserConnections() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getConnectionUserID() {
		return connectionUserID;
	}

	public void setConnectionUserID(int connectionUserID) {
		this.connectionUserID = connectionUserID;
	}
	
	

}
