# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table register_urlclass (
  id                        varchar(255) not null,
  url                       varchar(255),
  constraint pk_register_urlclass primary key (id))
;

create table register_url (
  id                        varchar(255) not null,
  url                       varchar(255),
  constraint pk_register_url primary key (id))
;

create sequence register_urlclass_seq;

create sequence register_url_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists register_urlclass;

drop table if exists register_url;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists register_urlclass_seq;

drop sequence if exists register_url_seq;

