-- --------create schema
create schema ass3db;
use ass3db;

-- --------drop table
-- DROP TABLE `ass3db`.`donationrecord`,`ass3db`.`borrowingrecord`,`ass3db`.`borrower`,   `ass3db`.`donor`, `ass3db`.`user`,`ass3db`.`book`,`ass3db`.`book_seq`;

CREATE TABLE User
(
	UserName VARCHAR (20) NOT NULL PRIMARY KEY,
	Password VARCHAR (8) NOT NULL,
	FullName VARCHAR (40) NOT NULL,
	Email VARCHAR (20) NOT NULL,
	PhoneNumber VARCHAR (9) NOT NULL,
	isAdmin Boolean NOT NULL
);
CREATE TABLE Donor
(
	Id INT NOT NULL PRIMARY KEY  AUTO_INCREMENT,
	Name VARCHAR (20) NOT NULL,
	FullName VARCHAR (40) NOT NULL,
	Email VARCHAR (20) NOT NULL,
    PhoneNumber VARCHAR (9) NOT NULL
);
CREATE TABLE Borrower
(
	Id INT NOT NULL PRIMARY KEY  AUTO_INCREMENT,
	Name VARCHAR (40) NOT NULL,
	Email VARCHAR (20) NOT NULL,
	PhoneNumber VARCHAR (9) NOT NULL
);

CREATE TABLE Book_seq
(
  Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Book
(
	Id VARCHAR (10) NOT NULL PRIMARY KEY  DEFAULT '0',
	Title VARCHAR (30) NOT NULL,
	Author VARCHAR (30) NOT NULL,
	Copies integer  NOT NULL,
	BorrowedCount integer  NOT NULL 
);

-- A large number preceded with 3 letters from the book title which can be auto-generated
delimiter //
CREATE TRIGGER prefix_id_insert
BEFORE INSERT ON Book
FOR EACH ROW
begin
INSERT INTO Book_seq VALUES (NULL);
SET NEW.Id = CONCAT(RPAD(NEW.Title, 3, '0'), LAST_INSERT_ID());
end;//
delimiter ;

CREATE TABLE DonationRecord
(
	Id INT NOT NULL PRIMARY KEY  AUTO_INCREMENT,
    Quantity integer NOT NULL,
	DonationDate Date NOT NULL,
    DonorId integer NOT NULL,
    CONSTRAINT FK_DonationDonor  FOREIGN KEY (DonorId) REFERENCES Donor(Id),
    BookId varchar(10) NOT NULL,
    CONSTRAINT FK_DonationBook FOREIGN KEY (BookId) REFERENCES Book(Id)
);



CREATE TABLE BorrowingRecord
(
	Id INT NOT NULL PRIMARY KEY  AUTO_INCREMENT,
	IssuedDate Date NOT NULL,
	ExpectedReturn DATE NOT NULL,
	Returned Boolean NOT NULL,
	BorrowerId integer NOT NULL,
    CONSTRAINT FK_BorrowingRecordBorrower FOREIGN KEY (BorrowerId) REFERENCES Borrower(Id),
    BookId varchar(10) NOT NULL,
    CONSTRAINT FK_BorrowingRecordBook FOREIGN KEY (BookId) REFERENCES Book(Id)
);

-- ------create users to login in
INSERT INTO User(UserName, Password,FullName ,Email ,PhoneNumber ,isAdmin )
VALUES ("admin","1234567a","fn1","email1@test.com","123456789",true),
("user1","1234567b","fn2","email2@test.com","223456876",false)
;


