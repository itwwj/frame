CREATE TABLE  tb_generator (
  id int(20) unsigned NOT NULL auto_increment,
  gen_name varchar(255) NOT NULL,
  gen_value int(20) NOT NULL,
  PRIMARY KEY  (id)
);


INSERT INTO tb_generator ( gen_name ,gen_value ) VALUES ( 'CUSTOMER_PK',1);

INSERT INTO tb_generator ( gen_name ,gen_value ) VALUES ( 'CONTACT_PK',100);