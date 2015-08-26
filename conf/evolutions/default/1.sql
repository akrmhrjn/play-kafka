# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table notification (
  id                        varchar(255) not null,
  msg                       varchar(255),
  constraint pk_notification primary key (id))
;

create table register_url (
  id                        varchar(255) not null,
  url                       varchar(255),
  constraint pk_register_url primary key (id))
;

create sequence notification_seq;

create sequence register_url_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists notification;

drop table if exists register_url;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists notification_seq;

drop sequence if exists register_url_seq;

