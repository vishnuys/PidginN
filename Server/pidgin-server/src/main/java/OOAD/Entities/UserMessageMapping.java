package OOAD.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserMessageMapping {

//attributes
	
	@Id
	private int userMessageMappingId;

	@ManyToOne
    @JoinColumn
    private UserDetails UserMessageMapping_TranslatedMessageId;
	
	@ManyToOne
    @JoinColumn
    private UserDetails userMessageMapping_SenderUserId;
	
	@ManyToOne
    @JoinColumn
    private UserDetails userMessageMapping_RecieverUserId;
	
	private Date messageSentTimestamp;
	
	private Date messageRecievedTimestamp;
	
	private Boolean messageReadByReciever;
	
	private Boolean isDirectMessage;
	
	@ManyToOne
    @JoinColumn
    private UserDetails userMessageMapping_GroupId;
	
	private Boolean isDeleted;
	
	
	
	public UserMessageMapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserMessageMapping(int userMessageMappingId, UserDetails UserMessageMapping_TranslatedMessageId,
			UserDetails userMessageMapping_SenderUserId, UserDetails userMessageMapping_RecieverUserId,
			Date messageSentTimestamp, Date messageRecievedTimestamp, Boolean messageReadByReciever,
			Boolean isDirectMessage, UserDetails userMessageMapping_GroupId, Boolean isDeleted) {
		super();
		this.userMessageMappingId = userMessageMappingId;
		this.UserMessageMapping_TranslatedMessageId = UserMessageMapping_TranslatedMessageId;
		this.userMessageMapping_SenderUserId = userMessageMapping_SenderUserId;
		this.userMessageMapping_RecieverUserId = userMessageMapping_RecieverUserId;
		this.messageSentTimestamp = messageSentTimestamp;
		this.messageRecievedTimestamp = messageRecievedTimestamp;
		this.messageReadByReciever = messageReadByReciever;
		this.isDirectMessage = isDirectMessage;
		this.userMessageMapping_GroupId = userMessageMapping_GroupId;
		this.isDeleted = isDeleted;
	}


	public int getUserMessageMappingId() {
		return userMessageMappingId;
	}

	public void setUserMessageMappingId(int userMessageMappingId) {
		this.userMessageMappingId = userMessageMappingId;
	}


	public UserDetails getUserMessageMapping_TranslatedMessageId() {
		return UserMessageMapping_TranslatedMessageId;
	}

	public void setUserMessageMapping_TranslatedMessageId(UserDetails UserMessageMapping_TranslatedMessageId) {
		this.UserMessageMapping_TranslatedMessageId = UserMessageMapping_TranslatedMessageId;
	}

	public UserDetails getUserMessageMapping_SenderUserId() {
		return userMessageMapping_SenderUserId;
	}

	public void setUserMessageMapping_SenderUserId(UserDetails userMessageMapping_SenderUserId) {
		this.userMessageMapping_SenderUserId = userMessageMapping_SenderUserId;
	}

	public UserDetails getUserMessageMapping_RecieverUserId() {
		return userMessageMapping_RecieverUserId;
	}

	public void setUserMessageMapping_RecieverUserId(UserDetails userMessageMapping_RecieverUserId) {
		this.userMessageMapping_RecieverUserId = userMessageMapping_RecieverUserId;
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

	public UserDetails getUserMessageMapping_GroupId() {
		return userMessageMapping_GroupId;
	}

	public void setUserMessageMapping_GroupId(UserDetails userMessageMapping_GroupId) {
		this.userMessageMapping_GroupId = userMessageMapping_GroupId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	
}
