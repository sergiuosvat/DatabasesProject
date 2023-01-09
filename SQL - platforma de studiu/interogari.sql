use platformaStudiu;

# show variables like "secure_file_priv";

drop view if exists orarStudenti; # ok
drop procedure if exists procOrarStudenti; # ok - trebuie sters mereu fisierul creat anterior
drop view if exists noteStudent; # ok 
drop procedure if exists programStudent; # ok
drop procedure if exists programProfesor; # ok

create view orarStudenti as select 
group_concat(distinct s.numeStudent,' ', s.prenumeStudent, ' '  order by s.idStudent separator '\n') as 'Student', 
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
si.notaMaterie1 as 'Nota 1', si.notaMaterie2 as 'Nota 2', si.notaMaterie3 as 'Nota 3',
si.notaMaterie4 as 'Nota 4', si.notaMaterie5 as 'Nota 5', si.notaMaterie6 as 'Nota 6',
si.medie as 'Medie'
from student s
inner join situatie si using (idStudent);

delimiter //
create procedure programStudent(givenStudentId int, ziCurenta varchar(2), toateActivitatile varchar(2) ,fisierZiCurenta varchar(2), fisierToateActivitatile varchar(2)) 
begin
	if(ziCurenta = 'DA') then
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


