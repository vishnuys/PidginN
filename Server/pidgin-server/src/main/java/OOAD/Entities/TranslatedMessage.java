package OOAD.Entities;


public class TranslatedMessage {

	private int translatedMessageId;
	
	private int messageId;
	
	private String cultureCode;
	
	private String senderMessage;
	
	private String translatedMessage;
	
	
	public TranslatedMessage() {}
	public TranslatedMessage(int translatedMessageId, int messageId, String cultureCode, String senderMessage,
			String translatedMessage) {
		super();
		this.translatedMessageId = translatedMessageId;
		this.messageId = messageId;
		this.cultureCode = cultureCode;
		this.senderMessage = senderMessage;
		this.translatedMessage = translatedMessage;
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
	
}
