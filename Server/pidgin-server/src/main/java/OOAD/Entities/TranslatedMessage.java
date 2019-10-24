package OOAD.Entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TranslatedMessage {

//attributes
	@Id
	private int translatedMessageId;
	
	private int messageId;
	
	private String cultureCode;
	
	private String senderMessage;
	
	private String translatedMessage;
	
	@OneToMany(mappedBy = "UserMessageMapping_TranslatedMessageId", cascade = CascadeType.ALL)
	private Set<UserMessageMapping> UserMessageMapping_TranslatedMessageId;
	
	
	
	public TranslatedMessage() {}
	public TranslatedMessage(int translatedMessageId, int messageId, String cultureCode, String senderMessage,
			String translatedMessage, Set<UserMessageMapping> userMessageMapping_TranslatedMessageId) {
		super();
		this.translatedMessageId = translatedMessageId;
		this.messageId = messageId;
		this.cultureCode = cultureCode;
		this.senderMessage = senderMessage;
		this.translatedMessage = translatedMessage;
		UserMessageMapping_TranslatedMessageId = userMessageMapping_TranslatedMessageId;
	}

	public int getTranslatedMessageId() {
		return translatedMessageId;
	}

	public void setTranslatedMessageId(int translatedMessageId) {
		this.translatedMessageId = translatedMessageId;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getCultureCode() {
		return cultureCode;
	}

	public void setCultureCode(String cultureCode) {
		this.cultureCode = cultureCode;
	}

	public String getSenderMessage() {
		return senderMessage;
	}

	public void setSenderMessage(String senderMessage) {
		this.senderMessage = senderMessage;
	}

	public String getTranslatedMessage() {
		return translatedMessage;
	}

	public void setTranslatedMessage(String translatedMessage) {
		this.translatedMessage = translatedMessage;
	}

	public Set<UserMessageMapping> getUserMessageMapping_TranslatedMessageId() {
		return UserMessageMapping_TranslatedMessageId;
	}

	public void setUserMessageMapping_TranslatedMessageId(Set<UserMessageMapping> userMessageMapping_TranslatedMessageId) {
		UserMessageMapping_TranslatedMessageId = userMessageMapping_TranslatedMessageId;
	}

	
}
