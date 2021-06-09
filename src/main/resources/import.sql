INSERT INTO user (user_id,name,email,password,created,modified,last_login,is_active) VALUES ('b78dcbcc-c7db-11eb-b8bc-0242ac130003','Claudio','claudio@algo.com','password',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,true);

INSERT INTO phone (id,number,city_code,contry_code,user_id) VALUES (10,'1234567','1','57','b78dcbcc-c7db-11eb-b8bc-0242ac130003');