CREATE DEFINER=`root`@`localhost` PROCEDURE `fetch_all_users`(
)
BEGIN



	select UserID,UserName,FirstName,LastName,PreferedLanguage,EmailID
    from UserDetails;
-- Set outUserId =1;
END