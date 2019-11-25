package OOAD.Entities;


public class TranslatedMessage {
	
	private int messageId;
	
	private String cultureCode;
	
	private String senderMessage;
	
	private String translatedMessage;
	
	public TranslatedMessage() {
		
	}
	
	public TranslatedMessage(String cultureCode, String senderMessage, String translatedMessage) {
		this.cultureCode = cultureCode;
		this.senderMessage = senderMessage;
		this.translatedMessage = translatedMessage;
	}
	public TranslatedMessage(int messageId, String cultureCode, String senderMessage,
			String translatedMessage) {
		super();
		this.messageId = messageId;
		this.cultureCode = cultureCode;
		this.senderMessage = senderMessage;
		this.translatedMessage = translatedMessage;
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
