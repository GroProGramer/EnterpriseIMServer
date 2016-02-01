
use im;

drop table if exists tb_user;
drop table if exists tb_friendship;
drop table if exists tb_groupship;
drop table if exists tb_group;


create table tb_user
(
  id int(10) unsigned not null AUTO_INCREMENT,
  user_id varchar(50) unique not null,
  password varchar(256) not null,
  nickname varchar(50),
  department varchar(50),
  job varchar(50),
  photo mediumblob,
  contactway varchar(20),
  avatar varchar (200),
  description varchar(500),
  location varchar(500),
  sex varchar(10),
  device_id varchar(40),
  create_time datetime,
  PRIMARY KEY (id)
);

create table tb_group
(
   id int(10) unsigned not null AUTO_INCREMENT,
   group_id varchar(50) unique not null,
   group_name varchar(20) not null,
   creator_id varchar(40) not null,
   group_member_count int(10) unsigned,
   photo MEDIUMBLOB,
   description varchar(500),
   create_time datetime,
   PRIMARY KEY (id)
)


create table tb_friendship
(
  id int(10) unsigned not null AUTO_INCREMENT,
  user_id varchar(40) not null,
  friend_id varchar(40) not null,tb_usertb_user
  PRIMARY KEY (id)
)


CREATE table tb_groupship
(
   id int(10) unsigned not null AUTO_INCREMENT,
   groupid varchar(20) not null,
   user_id varchar(40) not null,
   PRIMARY key(id)
)



















