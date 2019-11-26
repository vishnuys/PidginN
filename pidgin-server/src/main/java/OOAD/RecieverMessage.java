package OOAD;

public class RecieverMessage extends SenderMessage{
	public String translatedMessage;
	
	RecieverMessage(SenderMessage sender,String translatedMessage) {
		super(sender);
		this.translatedMessage = translatedMessage; 
	}
}