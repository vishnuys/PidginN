CREATE DEFINER=`root`@`localhost` PROCEDURE `savemessage`(
IN SenderUserId INT,
IN RecieverUserId INT,
IN IsDirectMessage BOOL,
IN GroupId INT,
IN CultureCode VARCHAR(10),
IN SenderMessage VARCHAR(500),
IN TranslatedMessage VARCHAR(500)
)
BEGIN
    DECLARE LASTID INT;
	INSERT INTO UserMessageMapping(SenderUserId,RecieverUserId,MessageSentTimestamp,MessageRecieivedTimestamp,MessageReadByReceiver,IsDirectMessage,GroupId,IsDeleted)
    VALUES (SenderUserId,RecieverUserId,CURRENT_TIME(),CURRENT_TIME(),0,IsDirectMessage,GroupId,0);
    
	SELECT LAST_INSERT_ID() INTO LASTID;
    
    INSERT INTO TranslatedMessage(CultureCode,SenderMessage,TranslatedMessage,TranslatedMessageID)
    VALUES (CultureCode,SenderMessage,TranslatedMessage,LASTID);
    
END