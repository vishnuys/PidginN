CREATE DEFINER=`root`@`localhost` PROCEDURE `fetchloggedinuserdetails`(
IN username1 VARCHAR(100)
)
BEGIN

	select UserID,UserName,FirstName,LastName,PreferedLanguage,EmailID
    from UserDetails WHERE UserName = username1; 

END