drop table if exists cocktails;
drop table if exists employes;
drop table if exists roles;
drop table if exists clients;

drop sequence if exists seq;

create sequence seq as integer start with 100 increment by 1;

create table clients (
	id integer primary key,
	nom varchar(30),
	email varchar(50),
	date_naissance date,
	titre integer
);

create table roles (
	id integer primary key,
	libelle varchar(30)
);

create table employes (
	id integer primary key,
	nom varchar(30),
	idrole integer
);
alter table employes add foreign key (idrole) references roles(id);

create table cocktails (
	id integer primary key,
	nom varchar(200),
	norm varchar(200),
	prix integer
);