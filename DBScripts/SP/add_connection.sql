CREATE DEFINER=`root`@`localhost` PROCEDURE `add_connection`(
IN UserID INT,
IN ConnectionUserID INT
)
BEGIN
	INSERT INTO UserConnections(UserID,ConnectionUserID) values(UserID,ConnectionUserID);
END