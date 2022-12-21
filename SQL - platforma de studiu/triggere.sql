use platformaStudiu;

drop trigger if exists inserareParticipantiActivitate; # ok
drop trigger if exists stergereParticipantiActivitate; # ok
drop trigger if exists inserareParticipantiGrupStudiu; # ok
drop trigger if exists stergereParticipantiGrupStudiu; # ok

delimiter //
create trigger inserareParticipantiActivitate 
after insert on inscriereActivitate for each row
begin
	update activitate
	set nrCrtStudenti = nrCrtStudenti+1
	where idActivitate = new.idActivitate and nrCrtStudenti < nrMaxStudenti;
end //
delimiter ;

delimiter //
create trigger stergereParticipantiActivitate
after delete on inscriereActivitate for each row
begin
	update activitate
	set nrCrtStudenti = nrCrtStudenti-1
	where idActivitate = old.idActivitate and nrCrtStudenti > 0;
end //
delimiter ;

delimiter //
create trigger inserareParticipantiGrupStudiu
after insert on inscriereGrupStudiu for each row
begin
	update grupStudiu
	set nrCrtParticipanti = nrCrtParticipanti+1
	where idGrup = new.idGrup and nrCrtParticipanti < nrMaxParticipanti;
end //
delimiter ;

delimiter //
create trigger stergereParticipantiGrupStudiu
after delete on inscriereGrupStudiu for each row
begin
	update grupStudiu
	set nrCrtParticipanti = nrCrtParticipanti-1
	where idGrup = old.idGrup and nrCrtParticipanti > 0;
end //
delimiter ;
