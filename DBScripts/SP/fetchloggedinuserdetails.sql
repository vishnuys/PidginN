CREATE DEFINER=`root`@`localhost` PROCEDURE `fetchloggedinuserdetails`(
IN username VARCHAR(100)
)
BEGIN

	select UserID,UserName,FirstName,LastName,PreferedLanguage,ContactNo
    from UserDetails WHERE UserName = username; 

END