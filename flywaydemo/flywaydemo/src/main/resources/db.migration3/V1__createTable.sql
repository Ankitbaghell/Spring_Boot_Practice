CREATE TABLE UserDetails(
   user_id int NOT NULL AUTO_INCREMENT,
   username varchar(30) NOT NULL,
   useremail varchar(50) NOT NULL,
   userpassword varchar(55) NOT NULL,
   age int NOT NULL,
   PRIMARY KEY(user_id),
   UNIQUE KEY username(useremail)
)