CREATE DEFINER=`root`@`localhost` PROCEDURE `create_user`(
IN UserName varchar(100), 
IN FirstName VARCHAR(50),
IN LastName VARCHAR(50),
IN PasswordHash VARCHAR(500),
IN PreferedLanguage VARCHAR(10),
IN ContactNo VARCHAR(10)
)
BEGIN

INSERT INTO UserDetails(UserName,FirstName,LastName,PasswordHash,PreferedLanguage,ContactNo,CreatedOn,UpdatedOn) VALUES
(UserName,FirstName,LastName,PasswordHash,PreferedLanguage,ContactNo,CURRENT_TIME(),CURRENT_TIME());

END