use platformaStudiu;

insert into superAdministrator(email) values
("email1@superadmin.ro");

insert into administrator(CNP, numeAdmin, prenumeAdmin, adresa, nrTelefon, email, IBAN, nrContract) values
('1950702367140', 'Marian', 'Adrian', 'Frunzisului 33', '0712345789', 'marian.adrian@admin.ro', 'RO34RZBR8936476961633725', 1),
('2930215315643', 'Andrei', 'Larisa', 'Zorilor 11', '0798346538', 'andrei.larisa@admin.ro', 'RO69RZBR8838371474462751', 2);

insert into profesor(CNP, numeProfesor, prenumeProfesor, adresa, nrTelefon, email, IBAN, nrContract) values
('1870116134210', 'Cret', 'Lucian', 'Florilor 12', '0770707077', 'cret.lucian@profesor.ro', 'RO28RZBR8759811474736883', 19),
('1790219183746', 'Bogdan', 'Ioan', 'Gradinii 5', '0712321232', 'bogdan.ioan@profesor.ro', 'RO44RZBR4725688321132875', 22),
('2680811039870', 'Petrescu', 'Larisa', 'Lunii 5', '0765676864', 'petrescu.larisa@profesor.ro', 'RO34RZBR4344793811139476', 145),
('2831120393970', 'Popescu', 'Georgeta', 'Fermelor 444', '0734198224', 'popescu.georgeta@profesor.ro', 'RO57RZBR1748782176146992', 226),
('1811203216927', 'Alexandrescu', 'Marian', 'Bogata 31', '0745353543', 'alexandrescu.marian@profesor.ro', 'RO78PORL3798369211552294', 67),
('1780425073354', 'Pop', 'Andrei', 'Comorilor 66', '0789249399', 'pop.andrei@profesor.ro', 'RO31RZBR5214649999315361', 68),
('1971107330460', 'Popa', 'Calin', 'Ciucas 2', '0711111188', 'popa.calin@profesor.ro', 'RO22PORL5392135138325516', 89),
('2860114510828', 'Popa', 'Antonia', 'Lemnului 2', '0725160904', 'popa.antonia@profesor.ro', 'RO36PORL4475856377474395', 110);

insert into student(CNP, numeStudent, prenumeStudent, adresa, nrTelefon, email, IBAN, nrContract, anDeStudiu, serieAn, grupaSerie) values
('2970623522350', 'Popa', 'Daria', 'Caisilor 11', '0754637487', 'popa.daria@student.ro', 'RO24PORL4421117162268749', 754, 3, 1, 4),
('1970611420712', 'Amarandei', 'Alin', 'Strugurilor 31', '0778265738', 'amarandei.alin@student.ro', 'RO14RZBR6238818566275456', 755, 3, 1, 4),
('1950708217904', 'Vaida', 'Filip', 'Viilor 36', '0756372856', 'vaida.filip@student.ro', 'RO10RZBR5661745241958648', 756, 3, 1, 4),
('2960326300117', 'Morar', 'Alexandra', 'Piersicilor 13', '07832622837', 'morar.alexandra@student.ro', 'RO38PORL4284373496728463', 556, 4, 2, 5),
('1970205413326', 'Bordea', 'Alin', 'Zeilor 11', '0726473837', 'bordea.alin@student.ro', 'RO02PORL6738913629237297', 557, 4, 2, 6),
('1990719067304', 'Cordoneanu', 'Stefan', 'Zeilor 12', '0738471928', 'cordoneanu.stefan@student.ro', 'RO82PORL1262176229327944', 1002, 2, 1, 1),
('1960506217039', 'Popescu', 'Daniel', 'Izvorului 88', '0792847561', 'popescu.daniel@student.ro', 'RO03RZBR2577593545988199', 1003, 2, 1, 1),
('2981106344163', 'Cosmin', 'Daniela', 'Izvorului 77', '0732876576', 'cosmin.daniela@student.ro', 'RO47RZBR4843198332971373', 1004, 2, 1, 2),
('1940203211700', 'Iosif', 'Alin', 'Memorandumului 1102', '0789233988', 'iosif.alin@student.ro', 'RO93PORL5319851744397568', 1005, 2, 2, 7),
('1960215195303', 'Avram', 'Mihai', 'Eroilor 554', '0712349876', 'avram.mihai@student.ro', 'RO02PORL8624899495153687', 1300, 1, 2, 8);

insert into materie(numeMaterie, nrCredite) values 
('Algebra', 4), ('Analiza', 5), ('Baze De Date', 5), ('Algoritmica', 6), ('Programare', 5), ('Proiectare', 5);

insert into activitate(tipActivitate, dataActivitate, nrMinStudenti, nrCrtStudenti, nrMaxStudenti, frecventa, idMaterie, idProfesor) values
('Curs', concat(date(now()), ' 12:00:00'), default, 150, default, default, 1, 2),
('Curs', '2024-12-16 12:00:00', default, 0, default, default, 2, 3),
('Laborator', '2023-01-17 11:30:00', default, 0, 24, default, 5, 8),
('Seminar', '2023-02-10 16:00:00', default, 0, 30, default, 4, 4),
('Seminar', '2023-02-01 14:00:00', default, 0, 30, default, 2, 2);

-- insert into testare(tipTestare, dataTestare, nrCrtParticipanti, nrMaxParticipanti, idMaterie, idProfesor) values
-- ('Examen', '2022-12-30', 99, 111, 1, 2),
-- ('Examen', '2022-12-29', 100, 111, 2, 3),
-- ('Colocviu', '2023-01-15', 24, 24, 5, 8);

insert into grupStudiu(dataGrup, nrMinParticipanti, nrCrtParticipanti, nrMaxParticipanti, idMaterie, idProfesor) values
('2023-12-30 18:00:00', 5, 30, 30, 2, null), ('2023-01-15 16:00:00', 5, 0, 30, 4, null);

insert into inscriereActivitate(idStudent, idActivitate) values
(1, 2), (1, 3), (4, 1), (4, 3), (5, 1), (5, 2), (5, 4), (6, 4), (7, 4), (9, 3);

-- insert into inscriereTestare(idStudent, idTestare) values
-- (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1);

insert into inscriereGrupStudiu(idStudent, idGrup) values
(1,1), (4,1), (5,1), (7,1), (8,1), (4,2), (5, 2), (6,2), (8,2), (10,2);


insert into situatie(idStudent, notaMaterie1, notaMaterie2, notaMaterie3, notaMaterie4, notaMaterie5, notaMaterie6, nrCrediteDisponibile, nrCrediteObtinute, medie) values
(1, default, default, default, default, default, default, default, default, default),
(2, default, default, default, default, default, default, default, default, default),
(3, default, default, default, default, default, default, default, default, default),
(4, default, default, default, default, default, default, default, default, default),
(5, default, default, default, default, default, default, default, default, default),
(6, default, default, default, default, default, default, default, default, default),
(7, default, default, default, default, default, default, default, default, default),
(8, default, default, default, default, default, default, default, default, default),
(9, default, default, default, default, default, default, default, default, default),
(10, default, default, default, default, default, default, default, default, default);
 
