package OOAD;


public class SenderMessage {
	public String senderLang;
	public String recieverLang;
	public String userMessage;
	public String senderUserID;
	public String senderUserName;
	public String recieverUserID;
	public String recieverUserName;
	
	SenderMessage() {}
	SenderMessage(SenderMessage m) {
		this.senderLang = m.senderLang;
		this.recieverLang = m.recieverLang;
		this.userMessage = m.userMessage;
		this.senderUserID = m.senderUserID;
		this.senderUserName = m.senderUserName;
		this.recieverUserID = m.recieverUserID;
		this.recieverUserName = m.recieverUserName;
	}
}