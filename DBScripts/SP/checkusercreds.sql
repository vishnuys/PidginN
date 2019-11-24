CREATE DEFINER=`root`@`localhost` PROCEDURE `checkusercreds`(
IN VARusername VARCHAR(100),
IN VARpassword VARCHAR(100)
)
BEGIN
declare outputval INT;
    IF EXISTS(SELECT 1 FROM userdetails WHERE  UserName =VARusername AND PasswordHash = VARpassword) THEN
		set outputval = 1;
		SELECT UserID,UserName,FirstName,LastName,PreferedLanguage,EmailID FROM userdetails  WHERE  UserName =VARusername AND PasswordHash = VARpassword;
    else
		set outputval = 0;
        SELECT outputval,'a','b','c','d','e' from userdetails;
    END IF;

END