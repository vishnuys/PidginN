CREATE DEFINER=`root`@`localhost` PROCEDURE `add_connection`(
IN UserID INT,
IN ConnectionUserID INT
)
BEGIN

    IF EXISTS (SELECT THREADID FROM USERCONNECTIONS WHERE USERID = ConnectionUserID AND ConnectionUserID = UserID)
    THEN
			SELECT ThreadID INTO @VARTHREADID  FROM USERCONNECTIONS WHERE USERID = ConnectionUserID AND ConnectionUserID = UserID;
			INSERT INTO UserConnections(UserID,ConnectionUserID,ThreadID) values(UserID,ConnectionUserID,@VARTHREADID);
    ELSE
			SELECT MAX(ThreadID) into @VARTHREADID FROM USERCONNECTIONS;
			set @VARTHREADID = @VARTHREADID +1;
			INSERT INTO UserConnections(UserID,ConnectionUserID, ThreadID) values(UserID,ConnectionUserID,@VARTHREADID);
    END IF;
END