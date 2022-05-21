
-- SELECT * from Borrower where Id in (SELECT DISTINCT BorrowerId FROM BorrowingRecord WHERE Returned=false);
-- INSERT INTO DonationRecord( DonorId ,BookId,Quantity,DonationDate  ) VALUES
-- (1,"t202",0,"2022-05-03");



INSERT INTO Book(Title ,Author ,Copies ,BorrowedCount )
VALUES 
("title1","a1",10,0),
("t2","a2",1,0)
;


INSERT INTO User(UserName, Password,FullName ,Email ,PhoneNumber ,isAdmin )
VALUES ("un1","12345678","fn1","email1@test.com","123456789",true),
("un2","12345678","fn2","email2@test.com","223456789",false)
;

INSERT INTO Donor(Name,PhoneNumber   ) VALUES 
("d1","123134759"),
("d2","123134759")
;

INSERT INTO Borrower(Name,Email ,PhoneNumber ) VALUES 
("bn1","email81@test.com","323456789"),
("bn2","email82@test.com","423456782")
;



INSERT INTO Donation (Quantity,DonorId,BookId)
VALUES 
(5,1,1),
(4,2,1)
;

INSERT INTO BorrowingRecord(IssuedDate ,ExpectedReturn , Returned, BorrowerId ,BookId ) VALUES 
("2022-04-23 00:00:00","2022-06-23 00:00:00",false,1,1),
 ("2022-02-23 00:00:00","2022-05-21 00:00:00",false,1,2),
 ("2022-03-12 00:00:00","2022-05-23 00:00:00",false,2,1)
;

-- a)	add new books.

-- exsit  then

-- else

INSERT INTO Book(Title ,Author ,Copies,BorrowedCount )
VALUES 
("t1","a2",0,0)
;

INSERT INTO Donation(Quantity,DonorId,BookId)
VALUES 
(3,1,1),
(2,1,1)
;

UPDATE Book
SET Copies= 5 
WHERE  Id= 1;


-- b)	enter details of a borrower.
INSERT INTO Borrower(Name,Email ,PhoneNumber ) VALUES 
("bn3","email23@test.com","423456789");
-- c)	issue books to a borrower.
INSERT INTO BorrowingRecord(IssuedDate ,ExpectedReturn ,Returned,BorrowerId ,BookId ) VALUES 
("2022-04-12 00:00:00","2022-06-15 00:00:00",false,2,2);

UPDATE Book
SET BorrowedCount= 9 
WHERE  Id= 1;

-- d)	return borrowed books.
UPDATE Book
SET BorrowedCount= 10 
WHERE  Id= 2;

UPDATE BorrowingRecord
SET Returned=true
Where BookId=1


-- e)	search for a book using the book title, or author.
-- f)	list all issued books.
-- g)	list all outstanding returns (overdue returns).
-- h)	list all books donated by a donor.
-- i)	list all books issued to a borrower.




