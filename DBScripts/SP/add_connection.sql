CREATE DEFINER=`root`@`localhost` PROCEDURE `add_connection`(
IN VARUserID INT,
IN VARConnectionUserID INT
)
BEGIN

	IF EXISTS (SELECT 1 FROM USERCONNECTIONS WHERE USERID = VARUserID AND ConnectionUserID = VARConnectionUserID)
    THEN
		SELECT 1; -- ALREADY EXITS
	
    ELSEIF EXISTS (SELECT THREADID FROM USERCONNECTIONS WHERE USERID = VARConnectionUserID AND ConnectionUserID = VARUserID)
    THEN
			SELECT ThreadID INTO @VARTHREADID  FROM USERCONNECTIONS WHERE USERID = VARConnectionUserID AND ConnectionUserID = VARUserID;
			INSERT INTO UserConnections(UserID,ConnectionUserID,ThreadID) values(VARUserID,VARConnectionUserID,@VARTHREADID);
    ELSE
			SELECT MAX(ThreadID) into @VARTHREADID FROM USERCONNECTIONS;
			set @VARTHREADID = @VARTHREADID +1;
			INSERT INTO UserConnections(UserID,ConnectionUserID, ThreadID) values(VARUserID,VARConnectionUserID,@VARTHREADID);
            INSERT INTO UserConnections(UserID,ConnectionUserID, ThreadID) values(VARConnectionUserID,VARUserID,@VARTHREADID);
    END IF;
END