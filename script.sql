CREATE TABLE RECEIPT 
(
  ID NUMBER NOT NULL 
, CLIENT_NAME VARCHAR2(20) 
, DESCRIPTION VARCHAR2(200) 
, CONSTRAINT RECEIPT_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
)

CREATE SEQUENCE SEQ_RECEIPT START WITH 1;
ALTER TABLE RECEIPT MODIFY (ID DEFAULT SEQ_RECEIPT.NEXTVAL); 

INSERT INTO "SYSTEM"."RECEIPT" ( CLIENT_NAME, DESCRIPTION) VALUES ( 'client1', 'laptop')
INSERT INTO "SYSTEM"."RECEIPT" ( CLIENT_NAME, DESCRIPTION) VALUES ('client2', 'tv')
INSERT INTO "SYSTEM"."RECEIPT" ( CLIENT_NAME, DESCRIPTION) VALUES ('client3', 'play station')
INSERT INTO "SYSTEM"."RECEIPT" ( CLIENT_NAME, DESCRIPTION) VALUES ('client4', 'xbox')


  /*exec select_reciept()*/
    CREATE  OR REPLACE PROCEDURE system.select_reciept
    AS
       c1 sys_refcursor;
    Begin
       open c1 for
       SELECT * from RECEIPT;
  
       dbms_sql.return_result(c1);
    End;



/************************************************************/

CREATE OR REPLACE PROCEDURE insertReceipt(
	  
	   p_clientname IN receipt.client_name%TYPE,
	   p_description IN receipt.description%TYPE)
IS
BEGIN

  INSERT INTO receipt ( "CLIENT_NAME", "DESCRIPTION") 
  VALUES ( p_clientname, p_description);

  COMMIT;

END;

/************************************************************/
CREATE OR REPLACE PROCEDURE updateReceipt(
	  p_id IN receipt.id%TYPE,
	   p_clientname IN receipt.client_name%TYPE,
	   p_description IN receipt.description%TYPE)
IS
BEGIN

  UPDATE receipt SET receipt.client_name = p_clientname, receipt.description=p_description where receipt.id = p_id;
  
  COMMIT;

END;

/************************************************************/
CREATE OR REPLACE PROCEDURE deleteReceipt(p_id IN receipt.id%TYPE)
IS
BEGIN

  DELETE Receipt where Receipt.ID = p_id;
  
  COMMIT;

END;
/