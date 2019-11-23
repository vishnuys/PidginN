CREATE DEFINER=`root`@`localhost` PROCEDURE `fetchmessagesforuser`(
IN SenderUserId INT,
IN RecieverUserId INT)
BEGIN

	SELECT UDSender.Username, UDRec.UserName,TM.SenderMEssage,TM.TranslatedMessage FROM UserMessageMapping UMM INNER JOIN TranslatedMessage TM ON UMM.TranslatedMessageID = TM.TranslatedMessageID
    INNER JOIN UserDetails UDSender on UMM.SenderUserId = UDSender.UserID
    INNER JOIN UserDetails UDRec on UMM.SenderUserId = UDRec.UserID 
    WHERE UMM.SenderUserId = SenderUserId AND UMM.RecieverUserId = RecieverUserId ORDER BY MessageSentTimestamp;

END