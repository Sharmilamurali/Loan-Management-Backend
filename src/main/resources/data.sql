INSERT INTO
 	ROLES (Role_Id, Role_Name)
 VALUES
 	(1, 'Admin'),
 	(2, 'User');

INSERT INTO 
	USERDATA (user_id, user_password, user_name, Role_Id) 
VALUES
  	('1005', 'pwd1', 'User1', 2),
  	('1006', 'pwd2', 'User2', 2),
  	('1007', 'pwd3', 'User3', 2),
  	('1008', 'pwd4', 'Admin1', 1),
  	('1009', 'pwd5', 'Admin2', 1);
  	
INSERT INTO 
	LOANDATA (Loan_No, Loan_Type, Loan_Term, Loan_Amount, First_Name, Last_Name, Property_Address, user_name) 
VALUES
  	('101', 'Business Loan ', '2yrs', '3 lakh' , 'Sham', 'Laa', 'Vellore','User1'),
  	('102', 'Education Loan', '4yrs', '8 lakh', 'Sham', 'Laa', 'Vellore','User1'),
  	('103', 'Gold Loan ', '2yrs', '3 lakh', 'Sham', 'Laa', 'Vellore','User1'),
  	('104', 'House Loan', '3yrs', '5 lakh', 'Shabo', 'Ross','Bangalore','User2'),
  	('201', 'Gold Loan', '3yrs', '5 lakh', 'Shabo', 'Ross','Bangalore','User2'),
  	('202', 'Car Loan', '4yrs', '9 lakh', 'Ram', 'Ross','Coimbatore','User3'),
  	('203', 'Business Loan', '2yrs', '4 lakh', 'Ram', 'Ross','Coimbatore','User3'); 
  	
 
