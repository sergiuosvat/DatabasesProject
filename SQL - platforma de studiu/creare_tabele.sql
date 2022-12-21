drop database if exists platformaStudiu;
create database if not exists platformaStudiu;
use platformaStudiu;

drop table if exists superAdministrator;
drop table if exists administrator;
drop table if exists profesor;
drop table if exists student;
drop table if exists materie; # 6 materii in total
drop table if exists activitate; # curs / seminar / laborator / grup de studiu
-- drop table if exists testare; # examen / colocviu
drop table if exists grupStudiu;
drop table if exists situatie; # media = 15% seminar + 25% laborator + 60% examen
drop table if exists inscriereActivitate; # pot fi facute de studenti si profesori / cele facute de profesori pot fi modificate de profesori / administratori
-- drop table if exists inscriereTestare;
drop table if exists inscriereGrupStudiu;

create table if not exists superAdministrator(
idSuperAdmin int primary key unique not null auto_increment,
email varchar(254)
);

create table if not exists administrator(
idAdmin int primary key unique not null auto_increment,
CNP varchar(13),
numeAdmin varchar(50),
prenumeAdmin varchar(50),
adresa varchar(50),
nrTelefon varchar(15),
email varchar(254),
IBAN varchar(34),
nrContract int
);

create table if not exists profesor(
idProfesor int primary key unique not null auto_increment,
CNP varchar(13),
numeProfesor varchar(50),
prenumeProfesor varchar(50),
adresa varchar(50),
nrTelefon varchar(15),
email varchar(254),
IBAN varchar(34),
nrContract int
);

create table if not exists student(
idStudent int primary key unique not null auto_increment,
CNP varchar(13),
numeStudent varchar(50),
prenumeStudent varchar(50),
adresa varchar(50),
nrTelefon varchar(15),
email varchar(254),
IBAN varchar(34),
nrContract int,
anDeStudiu int,
serieAn int,
grupaSerie int
);

create table if not exists materie(
idMaterie int primary key unique not null auto_increment,
numeMaterie varchar(50),
nrCredite int
);

create table if not exists activitate(
idActivitate int primary key unique not null auto_increment,
tipActivitate varchar(20),
dataActivitate datetime,
nrMinStudenti int default 10,
nrCrtStudenti int,
nrMaxStudenti int default 150,
durataOre int default 2,
frecventa int default 1,
idMaterie int,
foreign key (idMaterie) references materie (idMaterie) on delete cascade,
idProfesor int,
foreign key (idProfesor) references profesor (idProfesor) on delete cascade
);

-- create table if not exists testare(
-- idTestare int primary key unique not null auto_increment,
-- tipTestare varchar(10),
-- dataTestare date,
-- nrCrtParticipanti int,
-- nrMaxParticipanti int default 150,
-- idMaterie int,
-- foreign key (idMaterie) references materie (idMaterie),
-- idProfesor int,
-- foreign key (idProfesor) references profesor (idProfesor)
-- );

create table if not exists grupStudiu(
idGrup int primary key unique not null auto_increment,
dataGrup datetime,
nrMinParticipanti int default 5,
nrCrtParticipanti int,
nrMaxParticipanti int default 30,
idMaterie int, 
foreign key (idMaterie) references materie (idMaterie) on delete cascade
);

create table if not exists inscriereActivitate(
idStudent int,
foreign key (idStudent) references student (idStudent) on delete cascade,
idActivitate int,
foreign key (idActivitate) references activitate (idActivitate) on delete cascade
);

-- create table if not exists inscriereTestare(
-- idStudent int,
-- foreign key (idStudent) references student (idStudent),
-- idTestare int,
-- foreign key (idTestare) references testare (idTestare)
-- );

create table if not exists inscriereGrupStudiu(
idStudent int,
foreign key (idStudent) references student (idStudent) on delete cascade,
idGrup int,
foreign key (idGrup) references grupStudiu (idGrup) on delete cascade
);

create table if not exists situatie(
idStudent int unique,
foreign key (idStudent) references student (idStudent) on delete cascade,
notaMaterie1 decimal (4,2) default 0.00,
notaMaterie2 decimal (4,2) default 0.00,
notaMaterie3 decimal (4,2) default 0.00,
notaMaterie4 decimal (4,2) default 0.00,
notaMaterie5 decimal (4,2) default 0.00,
notaMaterie6 decimal (4,2) default 0.00,
nrCrediteDisponibile int default 30,
nrCrediteObtinute int default 0.00,
medie decimal(4,2) default 0.00
);

#tabela grup de studii
#procedura pentru medii
#eliminare tabel testari / inlocuire cu note