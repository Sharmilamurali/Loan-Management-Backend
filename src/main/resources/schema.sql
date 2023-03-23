Create TABLE ROLES (
						Role_Id INT PRIMARY KEY,
						Role_Name varchar(20));

Create TABLE USERDATA (
						user_id varchar(20) NOT NULL,
						user_password varchar(20) NOT NULL,
						user_name varchar(20) PRIMARY KEY,
						Role_Id INT,
					    FOREIGN KEY (Role_Id) REFERENCES ROLES(Role_Id));
						
Create TABLE LOANDATA (
						Loan_No INT primary key,
						Loan_Type varchar(20),
						Loan_Term varchar(20),
						Loan_Amount varchar(20),
						First_Name varchar(20) NOT NULL,
						Last_Name varchar(20) NOT NULL,
						Property_Address varchar(20) NOT NULL,
						user_name varchar(20),
						FOREIGN KEY (user_name) REFERENCES USERDATA(user_name) ON UPDATE CASCADE ON DELETE CASCADE);
						
						

									
						