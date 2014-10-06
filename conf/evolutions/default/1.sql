# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table session (
  id                        bigint auto_increment not null,
  token                     varchar(255),
  user_id                   bigint,
  timestamp                 bigint,
  constraint pk_session primary key (id))
;

create table transaction (
  id                        bigint auto_increment not null,
  timestamp                 bigint,
  price                     double,
  user_id                   bigint,
  constraint pk_transaction primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  last_name                 varchar(255),
  first_name                varchar(255),
  gender                    tinyint(1) default 0,
  birthday                  integer,
  password                  varchar(255),
  spouse_user_id            bigint,
  constraint pk_user primary key (id))
;

create table user_credential (
  id                        bigint auto_increment not null,
  password                  varchar(255),
  user_id                   bigint,
  constraint pk_user_credential primary key (id))
;

alter table session add constraint fk_session_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_session_user_1 on session (user_id);
alter table transaction add constraint fk_transaction_user_2 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_transaction_user_2 on transaction (user_id);
alter table user_credential add constraint fk_user_credential_user_3 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_credential_user_3 on user_credential (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table session;

drop table transaction;

drop table user;

drop table user_credential;

SET FOREIGN_KEY_CHECKS=1;

