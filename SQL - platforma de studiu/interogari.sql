use platformaStudiu;

# show variables like "secure_file_priv";

drop view if exists orarStudenti; # ok
drop procedure if exists procOrarStudenti; # ok - trebuie sters mereu fisierul creat anterior
drop view if exists noteStudent; # ok 
drop procedure if exists programStudent; # ok
drop procedure if exists programProfesor; # ok
drop procedure if exists sugestiiParticipantiGrupStudiu;

create view orarStudenti as select 
group_concat(s.numeStudent,' ', s.prenumeStudent, ' '  order by s.idStudent separator ',\n') as 'Student', 
concat(a.tipActivitate, ' ', m.numeMaterie) as 'Activitate' ,
a.dataActivitate as 'Data'
from student s
inner join inscriereActivitate ia using (idStudent)
inner join activitate a using (idActivitate)
inner join materie m using (idMaterie)
group by a.idActivitate;

delimiter //
create procedure procOrarStudenti()
begin
	select * from orarStudenti
	into outfile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/calendar.txt'
	fields enclosed by '"' terminated by '\t' escaped by '"' 
	lines terminated by '\r\n';
end //
delimiter ;

create view noteStudent as select
concat(s.numeStudent, ' ', s.prenumeStudent) as 'Student', 
si.notaMaterie1 as 'Nota Algebra', si.notaMaterie2 as 'Nota Analiza', si.notaMaterie3 as 'Nota Baze De Date',
si.notaMaterie4 as 'Nota Algoritmica', si.notaMaterie5 as 'Nota Programare', si.notaMaterie6 as 'Nota Proiectare',
si.medie as 'Medie'
from student s
inner join situatie si using (idStudent);

delimiter //
create procedure programStudent(givenStudentId int, ziCurenta varchar(2), toateActivitatile varchar(2) ,fisierZiCurenta varchar(2), fisierToateActivitatile varchar(2)) 
begin
	if(ziCurenta = 'DA') then
		drop table if exists tabelZiCurentaStud;
		create table tabelZiCurentaStud
		select concat(a.tipActivitate, ' ', m.numeMaterie) as 'Activitate',
		time(a.dataActivitate) as 'Ora' 
		from inscriereActivitate ia
		inner join activitate a using (idActivitate)
		inner join materie m using (idMaterie)
		where ia.idStudent = givenStudentId and date(a.dataActivitate) = date(now())
		group by a.idActivitate
		order by a.dataActivitate;
    end if;
    
	if(fisierZiCurenta = 'DA') then
		select concat(a.tipActivitate, ' ', m.numeMaterie) as 'Activitate',
		time(a.dataActivitate) as 'Ora' 
		from inscriereActivitate ia
		inner join activitate a using (idActivitate)
		inner join materie m using (idMaterie)
		where ia.idStudent = givenStudentId and date(a.dataActivitate) = date(now())
		group by a.idActivitate
		order by a.dataActivitate
		into outfile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/crt_zi_std.txt'
		fields enclosed by '"' terminated by '\t' escaped by '"' 
		lines terminated by '\r\n';
    end if;
    
    if(toateActivitatile = 'DA') then
		drop table if exists tabelToateActivitatileStud;
        create table tabelToateActivitatileStud
		select concat(a.tipActivitate, ' ', m.numeMaterie) as 'Activitate',
		a.dataActivitate as 'Data' 
		from inscriereActivitate ia
		inner join activitate a using (idActivitate)
		inner join materie m using (idMaterie)
		where ia.idStudent = givenStudentId
		group by a.idActivitate
		order by a.dataActivitate;
    end if;
    
    if(fisierToateActivitatile = 'DA') then
		select concat(a.tipActivitate, ' ', m.numeMaterie) as 'Activitate',
		a.dataActivitate as 'Data' 
		from inscriereActivitate ia
		inner join activitate a using (idActivitate)
		inner join materie m using (idMaterie)
		where ia.idStudent = givenStudentId
		group by a.idActivitate
		order by a.dataActivitate
		into outfile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/crt_std.txt'
		fields enclosed by '"' terminated by '\t' escaped by '"' 
		lines terminated by '\r\n';
    end if;
end //
delimiter ;

delimiter //
create procedure programProfesor(givenProfessorId int, ziCurenta varchar(2), toateActivitatile varchar(2) ,fisierZiCurenta varchar(2), fisierToateActivitatile varchar(2)) 
begin
	if(ziCurenta = 'DA') then
		drop table if exists tabelZiCurentaProf;
		create table tabelZiCurentaProf
		select concat(a.tipActivitate, ' ', m.numeMaterie) as 'Activitate',
		time(a.dataActivitate) as 'Ora' 
		from activitate a
		inner join materie m using (idMaterie)
		where a.idProfesor = givenProfessorId and date(a.dataActivitate) = date(now())
		group by a.idActivitate
		order by a.dataActivitate;
    end if;
    
	if(fisierZiCurenta = 'DA') then
		select concat(a.tipActivitate, ' ', m.numeMaterie) as 'Activitate',
		time(a.dataActivitate) as 'Ora' 
		from activitate a
		inner join materie m using (idMaterie)
		where a.idProfesor = givenProfessorId and date(a.dataActivitate) = date(now())
		group by a.idActivitate
		order by a.dataActivitate
		into outfile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/crt_zi_prf.txt'
		fields enclosed by '"' terminated by '\t' escaped by '"' 
		lines terminated by '\r\n';
    end if;
    
    if(toateActivitatile = 'DA') then
		drop table if exists tabelToateActivitatileProf;
        create table tabelToateActivitatileProf
		select concat(a.tipActivitate, ' ', m.numeMaterie) as 'Activitate',
		a.dataActivitate as 'Data' 
		from activitate a
		inner join materie m using (idMaterie)
		where a.idProfesor = givenProfessorId
		group by a.idActivitate
		order by a.dataActivitate;
    end if;
    
    if(fisierToateActivitatile = 'DA') then
		select concat(a.tipActivitate, ' ', m.numeMaterie) as 'Activitate',
		a.dataActivitate as 'Data'
		from activitate a
		inner join materie m using (idMaterie)
		where a.idProfesor = givenProfessorId
		group by a.idActivitate
		order by a.dataActivitate
		into outfile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/crt_prf.txt'
		fields enclosed by '"' terminated by '\t' escaped by '"' 
		lines terminated by '\r\n';
    end if;
end //
delimiter ;

delimiter //
create procedure sugestiiParticipantiGrupStudiu(givenGroupId int)
begin
	drop table if exists sugestii;
    create table sugestii as
	select distinct s.email as 'Student'
    from student s where 
    s.idStudent not in (select idStudent from inscriereActivitate where idActivitate not in 
    (select idActivitate from activitate where dataActivitate not in (select dataGrup from grupStudiu where idGrup = givenGroupId))) and
    s.idStudent not in (select idStudent from inscriereGrupStudiu where idGrup not in 
    (select idGrup from grupStudiu where dataGrup not in (select dataGrup from grupStudiu where idGrup = givenGroupId)))
    order by s.idStudent;
end //
delimiter ;

