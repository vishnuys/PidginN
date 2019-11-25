CREATE DEFINER=`root`@`localhost` PROCEDURE `fetchmessagesforuser`(
IN VARSenderUserId INT)
BEGIN
  
    
    select 
    TM.SenderMessage, -- 0
    TM.TranslatedMessage, -- 1
    TM.CultureCode, -- 2
    UMM.MessageSentTimestamp, -- 3
    USENDER.Username as SenderUserName, -- 4
    USENDER.Firstname as SenderFirstName,-- 5
    UREC.UserName as RecieverUserName, -- 6
    UREC.FirstName as RecieverFirstName, -- 7
    ThreadID, -- 8
    ULOGIN.UserName as LoginUserName, -- 9
    UREC.UserID AS URECUID, -- 10
    UREC.PreferedLanguage AS URECLANG, -- 11
    USENDER.UserID AS USENDERUID, -- 12
    USENDER.PreferedLanguage AS USENDERLANG -- 13
    FROM UserMessageMapping UMM INNER JOIN TranslatedMessage TM ON UMM.TranslatedMessageID = TM.TranslatedMessageID
    LEFT JOIN userconnections UC ON (UMM.SenderUserId = UC.UserID AND UMM.RecieverUserId = ConnectionUserID )
    INNER JOIN USERDETAILS USENDER ON(USENDER.UserID = UMM.SenderUserId)
    INNER JOIN USERDETAILS UREC ON(UREC.UserID = UMM.RecieverUserId)
    INNER JOIN USERDETAILS ULOGIN ON (ULOGIN.UserID = VARSenderUserId)
    WHERE (UMM.SenderUserId = VARSenderUserId OR UMM.RecieverUserId = VARSenderUserId) AND (UC.UserID IS NOT NULL AND UC.ConnectionUserID IS NOT NULL)
    order by ThreadID, MessageSentTimestamp;
    

END