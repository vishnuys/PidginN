CREATE DEFINER=`root`@`localhost` PROCEDURE `fetchmessagesforuser`(
IN VARSenderUserId INT)
BEGIN
  
    
    select 
    TM.SenderMessage, 
    TM.TranslatedMessage,
    TM.CultureCode,
    UMM.MessageSentTimestamp,
    USENDER.Username as SenderUserName,
    USENDER.Firstname as SenderFirstName,
    UREC.UserName as RecieverUserName,
    UREC.FirstName as RecieverFirstName,
    ThreadID,
    ULOGIN.UserName as LoginUserName
    FROM UserMessageMapping UMM INNER JOIN TranslatedMessage TM ON UMM.TranslatedMessageID = TM.TranslatedMessageID
    LEFT JOIN userconnections UC ON (UMM.SenderUserId = UC.UserID AND UMM.RecieverUserId = ConnectionUserID )
    INNER JOIN USERDETAILS USENDER ON(USENDER.UserID = UMM.SenderUserId)
    INNER JOIN USERDETAILS UREC ON(UREC.UserID = UMM.RecieverUserId)
    INNER JOIN USERDETAILS ULOGIN ON (ULOGIN.UserID = VARSenderUserId)
    WHERE (UMM.SenderUserId = VARSenderUserId OR UMM.RecieverUserId = VARSenderUserId) AND (UC.UserID IS NOT NULL AND UC.ConnectionUserID IS NOT NULL)
    order by ThreadID, MessageSentTimestamp;
    

END