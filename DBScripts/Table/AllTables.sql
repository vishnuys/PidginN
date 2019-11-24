-- create database pidgin;
-- drop table UserDetails; 
--  drop table UserConnections
USE pidgin;
CREATE TABLE  UserDetails (
  UserID INT AUTO_INCREMENT PRIMARY KEY,
  UserName VARCHAR(500) NOT NULL,
  FirstName VARCHAR(50) NOT NULL,
  LastName VARCHAR(50),
  PasswordHash VARCHAR(500) NOT NULL,
  PreferedLanguage VARCHAR(50) NOT NULL,
  EmailID  VARCHAR(200) NOT NULL,
  CreatedOn timestamp,
  UpdatedOn timestamp
);

USE pidgin;

CREATE TABLE UserConnections (
	ConnectionId INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NULL,
    ConnectionUserID INT NULL,
    ThreadID INT,
     FOREIGN KEY (UserID) REFERENCES UserDetails(UserID)
);
insert into UserConnections(UserID,ConnectionUserID,ThreadID) values(null,NULL,0);


CREATE TABLE Group1 (
	GroupId INT AUTO_INCREMENT PRIMARY KEY,
    GroupName VARCHAR(100) NOT NULL,
    CreatedBy INT,
    CreatedOn TIMESTAMP,
    UpdatedBy INT,
    UpdatedOn TIMESTAMP
--    FOREIGN KEY (CreatedBy) REFERENCES UserDetails(UserID),
-- FOREIGN KEY (UpdatedBy) REFERENCES UserDetails(UserID)
);

CREATE TABLE GroupMembers (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    GroupId INT,
    MappedUserIds INT,
    IsAdmin BOOL,
    UserAddedBy INT,
    UserAddedOn TIMESTAMP,
     FOREIGN KEY (GroupId) REFERENCES Group1(GroupId)
-- FOREIGN KEY (MappedUserIds) REFERENCES UserDetails(UserID)
);

CREATE TABLE UserMessageMapping(
	TranslatedMessageID INT AUTO_INCREMENT PRIMARY KEY,
    SenderUserId INT,
    RecieverUserId INT,
    MessageSentTimestamp TIMESTAMP,
    MessageRecieivedTimestamp TIMESTAMP,
    MessageReadByReceiver BOOL,
    IsDirectMessage BOOL,
    GroupId INT,
    IsDeleted BOOL,
    FOREIGN KEY (SenderUserId) REFERENCES UserDetails(UserID)  
);

CREATE TABLE  TranslatedMessage(
    MessageID INT AUTO_INCREMENT PRIMARY KEY,
    CultureCode VARCHAR(10),
    SenderMessage NVARCHAR(5000),
    TranslatedMessage NVARCHAR(5000),
	TranslatedMessageID INT,
	FOREIGN KEY (TranslatedMessageID) REFERENCES UserMessageMapping(TranslatedMessageID) 
);




