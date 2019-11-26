package OOAD.Entities;

import java.util.Date;

public class UserMessageMapping {
	
    public int SenderUserId;
	
    public int RecieverUserId;
	
	private Date messageSentTimestamp;
	
	private Date messageRecievedTimestamp;
	
	private Boolean messageReadByReciever;
	
	private Boolean isDirectMessage;
	
    public int GroupId;
    
	private Boolean isDeleted;

	
    public UserMessageMapping(int senderUserId, int recieverUserId) {
    	this.SenderUserId = senderUserId;
    	this.RecieverUserId = recieverUserId;
    	this.GroupId = 0;
    }
    
	public UserMessageMapping(int senderUserId, int recieverUserId, Date messageSentTimestamp,
			Date messageRecievedTimestamp, Boolean messageReadByReciever, Boolean isDirectMessage, int groupId,
			Boolean isDeleted) {
		super();
		SenderUserId = senderUserId;
		RecieverUserId = recieverUserId;
		this.messageSentTimestamp = messageSentTimestamp;
		this.messageRecievedTimestamp = messageRecievedTimestamp;
		this.messageReadByReciever = messageReadByReciever;
		this.isDirectMessage = isDirectMessage;
		GroupId = groupId;
		this.isDeleted = isDeleted;
	}

	public int getSenderUserId() {
		return SenderUserId;
	}



	public void setSenderUserId(int senderUserId) {
		SenderUserId = senderUserId;
	}



	public int getRecieverUserId() {
		return RecieverUserId;
	}



	public void setRecieverUserId(int recieverUserId) {
		RecieverUserId = recieverUserId;
	}



	public Date getMessageSentTimestamp() {
		return messageSentTimestamp;
	}



	public void setMessageSentTimestamp(Date messageSentTimestamp) {
		this.messageSentTimestamp = messageSentTimestamp;
	}



	public Date getMessageRecievedTimestamp() {
		return messageRecievedTimestamp;
	}



	public void setMessageRecievedTimestamp(Date messageRecievedTimestamp) {
		this.messageRecievedTimestamp = messageRecievedTimestamp;
	}



	public Boolean getMessageReadByReciever() {
		return messageReadByReciever;
	}



	public void setMessageReadByReciever(Boolean messageReadByReciever) {
		this.messageReadByReciever = messageReadByReciever;
	}



	public Boolean getIsDirectMessage() {
		return isDirectMessage;
	}



	public void setIsDirectMessage(Boolean isDirectMessage) {
		this.isDirectMessage = isDirectMessage;
	}



	public int getGroupId() {
		return GroupId;
	}



	public void setGroupId(int groupId) {
		GroupId = groupId;
	}



	public Boolean getIsDeleted() {
		return isDeleted;
	}



	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	
	
	
	public UserMessageMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
