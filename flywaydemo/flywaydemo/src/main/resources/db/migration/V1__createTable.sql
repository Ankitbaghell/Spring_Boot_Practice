CREATE TABLE UserDetails(
   id int NOT NULL AUTO_INCREMENT,
   name varchar(30) NOT NULL,
   email varchar(50) NOT NULL,
   password varchar(55) NOT NULL,
   age int NOT NULL,
   PRIMARY KEY(id),
   UNIQUE KEY username(email)
)