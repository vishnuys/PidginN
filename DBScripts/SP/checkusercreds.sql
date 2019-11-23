CREATE DEFINER=`root`@`localhost` PROCEDURE `checkusercreds`(
IN username VARCHAR(100),
IN password VARCHAR(100),
OUT isValid BOOLEAN
)
BEGIN

	SET isValid = FALSE;
    IF EXISTS(SELECT 1 FROM userdetails WHERE  UserName =username AND PasswordHash = password) THEN
		SET isValid = TRUE;
    END IF;

END