CREATE DEFINER=`root`@`localhost` PROCEDURE `create_user`(
IN varUserName varchar(100), 
IN varFirstName VARCHAR(50),
IN varLastName VARCHAR(50),
IN varPasswordHash VARCHAR(500),
IN varPreferedLanguage VARCHAR(10),
IN varEmailID VARCHAR(200)
)
BEGIN

INSERT INTO UserDetails(UserName,FirstName,LastName,PasswordHash,PreferedLanguage,EmailID,CreatedOn,UpdatedOn) VALUES
(varUserName,varFirstName,varLastName,varPasswordHash,varPreferedLanguage,varEmailID,CURRENT_TIME(),CURRENT_TIME());

END