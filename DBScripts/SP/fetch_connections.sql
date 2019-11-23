CREATE DEFINER=`root`@`localhost` PROCEDURE `fetch_connections`(
IN inuserID INT
)
BEGIN

	SELECT UserID,UserName,FirstName,LastName,PreferedLanguage,ContactNo FROM UserDetails WHERE UserID IN (SELECT ConnectionUserID FROM UserConnections WHERE UserID=inuserID);

END