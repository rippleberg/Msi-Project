CREATE TABLE TRADER (
		LID NUMBER(10) PRIMARY KEY NOT NULL,
		FIRST_NAME VARCHAR2(30) NOT NULL,
		LAST_NAME VARCHAR2(30) NOT NULL,
		PHONE VARCHAR2(20),
		EMAIL VARCHAR2(30) UNIQUE NOT NULL,
		ADDRESS VARCHAR2(40),
		HOME_STATE CHAR(2),
		CITY VARCHAR2(20),
		ZIPCODE VARCHAR2 (10),
		BALANCE NUMBER(20, 6),
		ACTIVE CHAR(1)		
);

CREATE TABLE LOGIN (
		LID NUMBER(10) PRIMARY KEY REFERENCES TRADER(LID) NOT NULL,
		USERNAME VARCHAR2(20) UNIQUE NOT NULL,
		PASSWORD VARCHAR2(40) NOT NULL,
		AUTHORITY VARCHAR2(20) NOT NULL
);

CREATE TABLE CREDITCARD (
		CID NUMBER(10) PRIMARY KEY NOT NULL,
		LID NUMBER(10) REFERENCES TRADER (LID) NOT NULL,
		CARD_HOLDER VARCHAR2(40) NOT NULL,
		CARD_NUMBER BLOB NOT NULL,
		EXPIRE_MONTH NUMBER(2) NOT NULL,
		EXPIRE_YEAR NUMBER(4) NOT NULL,
		CODE NUMBER(3) NOT NULL
);

CREATE TABLE TRANS (
		TID NUMBER(10) PRIMARY KEY NOT NULL,
		LID NUMBER (10) REFERENCES TRADER (LID) NOT NULL,
		SID VARCHAR2(20) NOT NULL,
		T_TIME DATE NOT NULL,
		PRICE NUMBER(10,2) NOT NULL,
		QUANTITY NUMBER(10) NOT NULL,
		T_TYPE CHAR(1) NOT NULL,
		T_STATUS CHAR(1) NOT NULL 
);

CREATE TABLE STOCKS(
		SID VARCHAR2(20) NOT NULL PRIMARY KEY
);

 CREATE TABLE TRADER_STOCKS(
 		LID NUMBER(10) NOT NULL REFERENCES TRADER(LID),
		SID VARCHAR2(20) NOT NULL PRIMARY KEY,
		PRICE NUMBER(10, 2),
		QUANTITY NUMBER(10)
 );
