package OOAD.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserMessageMapping {

//attributes
	@ManyToOne
    @JoinColumn
    private UserDetails userMessageMapping_TranslatedMessageId;
	
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

	public UserMessageMapping(UserDetails userMessageMapping_TranslatedMessageId,
			UserDetails userMessageMapping_SenderUserId, UserDetails userMessageMapping_RecieverUserId,
			Date messageSentTimestamp, Date messageRecievedTimestamp, Boolean messageReadByReciever,
			Boolean isDirectMessage, UserDetails userMessageMapping_GroupId, Boolean isDeleted) {
		super();
		this.userMessageMapping_TranslatedMessageId = userMessageMapping_TranslatedMessageId;
		this.userMessageMapping_SenderUserId = userMessageMapping_SenderUserId;
		this.userMessageMapping_RecieverUserId = userMessageMapping_RecieverUserId;
		this.messageSentTimestamp = messageSentTimestamp;
		this.messageRecievedTimestamp = messageRecievedTimestamp;
		this.messageReadByReciever = messageReadByReciever;
		this.isDirectMessage = isDirectMessage;
		this.userMessageMapping_GroupId = userMessageMapping_GroupId;
		this.isDeleted = isDeleted;
	}

	public UserDetails getUserMessageMapping_TranslatedMessageId() {
		return userMessageMapping_TranslatedMessageId;
	}

	public void setUserMessageMapping_TranslatedMessageId(UserDetails userMessageMapping_TranslatedMessageId) {
		this.userMessageMapping_TranslatedMessageId = userMessageMapping_TranslatedMessageId;
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
