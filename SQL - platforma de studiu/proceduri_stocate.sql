use platformaStudiu;

set global event_scheduler = ON;
# show processlist;

drop procedure if exists medieAleasa; # ok
drop procedure if exists adaugaActivitate; # ok
drop procedure if exists eliminaActivitate; # ok
drop procedure if exists adaugaParticipantActivitate; # ok
drop procedure if exists eliminaParticipantActivitate; # ok
drop procedure if exists adaugaGrupStudiu; # ok
drop procedure if exists eliminaGrupStudiu; # ok
drop procedure if exists adaugaParticipantGrupStudiu; # ok
drop procedure if exists eliminaParticipantGrupStudiu; # ok
drop procedure if exists verificareCalendarActivitate; # ok
drop procedure if exists verificareCalendarGrupStudiu; # ok
drop event if exists updateCalendar; # ok


delimiter //
create procedure medieAleasa(procSem int, procCol int, procExam int, notaSem int, notaCol int, notaExam int, givenStudentId int, givenSubjectId int)
begin
	if givenSubjectId = 1 then
		update situatie
        set notaMaterie1 = (procSem * notaSem + procCol * notaCol + procExam * notaExam)/100
        where situatie.idStudent = givenStudentId;
	end if;
    if givenSubjectId = 2 then
		update situatie
        set notaMaterie2 = (procSem * notaSem + procCol * notaCol + procExam * notaExam)/100
        where situatie.idStudent = givenStudentId;
	end if;
    if givenSubjectId = 3 then
		update situatie
        set notaMaterie3 = (procSem * notaSem + procCol * notaCol + procExam * notaExam)/100
        where situatie.idStudent = givenStudentId;
	end if;
    if givenSubjectId = 4 then
		update situatie
        set notaMaterie4 = (procSem * notaSem + procCol * notaCol + procExam * notaExam)/100
        where situatie.idStudent = givenStudentId;
	end if;
    if givenSubjectId = 5 then
		update situatie
        set notaMaterie5 = (procSem * notaSem + procCol * notaCol + procExam * notaExam)/100
        where situatie.idStudent = givenStudentId;
	end if;
    if givenSubjectId = 6 then
		update situatie
        set notaMaterie6 = (procSem * notaSem + procCol * notaCol + procExam * notaExam)/100
        where situatie.idStudent = givenStudentId;
	end if;
end //
delimiter ;

delimiter //
create procedure adaugaActivitate(tip varchar(20), dataAct datetime, nrMin int, nrMax int, durata int, frecv int, idMat int, idProf int)
begin
	if((select count(*) from activitate where (idProfesor = idProf and dataActivitate = dataAct)) = 0 and now() < dataAct) then
		insert into activitate(tipActivitate, dataActivitate, nrMinStudenti, nrCrtStudenti, nrMaxStudenti, durataOre, frecventa, idMaterie, idProfesor) values 
        (tip, dataAct, nrMin, 0, nrMax, durata, frecv, idMat, idProf);
    end if;
end //
delimiter ;

delimiter //
create procedure eliminaActivitate(idAct int)
begin
		delete from activitate
        where idActivitate = idAct;
end //
delimiter ;

delimiter //
create procedure adaugaParticipantActivitate(givenStudentId int, givenActivityId int)
begin
	if ((select count(*) from inscriereActivitate where (idStudent = givenStudentId and idActivitate = givenActivityId)) = 0
		and (select count(*) from activitate where (idActivitate = givenActivityId and nrCrtStudenti < nrMaxStudenti) != 0)
        and (select count(*) from activitate inner join inscriereActivitate using (idActivitate) where 
        (idStudent = givenStudentId and idActivitate != givenActivityId and dataActivitate in (select dataActivitate from activitate where idActivitate = givenActivityId))) = 0) then
		insert into inscriereActivitate(idStudent, idActivitate) values
		(givenStudentId, givenActivityId);
    end if;
end //
delimiter ;

delimiter //
create procedure eliminaParticipantActivitate(givenStudentId int, givenActivityId int)
begin
		delete from inscriereActivitate
        where idStudent = givenStudentId and idActivitate = givenActivityId;
end //
delimiter ;

delimiter //
create procedure adaugaGrupStudiu(dataGr datetime, nrMin int, nrMax int, idMat int)
begin
	if((select count(*) from grupStudiu where (dataGrup = dataGr and idMaterie = idMat)) = 0 and now() < dataGr) then
    insert into grupstudiu (dataGrup, nrMinParticipanti, nrCrtParticipanti, nrMaxParticipanti, idMaterie) values
    (dataGr, nrMin, 0, nrMax, idMat);
    end if;
end //
delimiter ;

delimiter //
create procedure eliminaGrupStudiu(givenGroupId int)
begin
    delete from grupStudiu
    where idGrup = givenGroupId;
end //
delimiter ;

delimiter //
create procedure adaugaParticipantGrupStudiu(givenStudentId int, givenGroupId int)
begin 
	if ((select count(*) from inscriereGrupStudiu where (idStudent = givenStudentId and idGrup = givenGroupId)) = 0
		and (select count(*) from grupStudiu where (idGrup = givenGroupId and nrCrtParticipanti < nrMaxParticipanti) != 0)
		and (select count(*) from grupStudiu inner join inscriereGrupStudiu using (idGrup) where 
        (idStudent = givenStudentId and idGrup != givenGroupId and dataGrup in (select dataGrup from grupStudiu where idGrup = givenGroupId))) = 0) then
		insert into inscriereGrupStudiu(idStudent, idGrup) values
		(givenStudentId, givenGroupId);
    end if;
end //
delimiter ;

delimiter //
create procedure eliminaParticipantGrupStudiu(givenStudentId int, givenGroupId int)
begin
		delete from inscriereGrupStudiu
        where idStudent = givenStudentId and idGrup = givenGroupId;
end //
delimiter ;

delimiter //
create procedure verificareCalendarActivitate()
begin 
	update activitate
    set dataActivitate = date_add(dataActivitate, interval frecventa week)
    where now() > date_add(dataActivitate, interval durataOre hour);
end //
delimiter ;

delimiter //
create procedure verificareCalendarGrupStudiu()
begin 
	delete from grupStudiu
    where now() > dataGrup;
end //
delimiter ;

create event updateCalendar
on schedule every 1 week
starts '2022-01-01 00:00:00'
ends '2025-01-01 00:00:00'
    do
    call verificareCalendarActivitate();
    call verificareCalendarGrupStudiu();