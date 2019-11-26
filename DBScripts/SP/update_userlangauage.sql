CREATE PROCEDURE `update_userlangauage` (
IN VARuserID INT, 
IN VARLanguage VARCHAR(50)
)
BEGIN

	UPDATE UserDetails SET PreferedLanguage = VARLanguage WHERE UserID = VARuserID;
END
