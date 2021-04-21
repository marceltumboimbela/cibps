USE CIBPSDB;

INSERT INTO kamar(status) VALUES('AVAILABLE');
INSERT INTO kamar(status) VALUES('AVAILABLE');
INSERT INTO kamar(status) VALUES('AVAILABLE');
INSERT INTO kamar(status) VALUES('AVAILABLE');
INSERT INTO kamar(status) VALUES('AVAILABLE');
INSERT INTO kamar(status) VALUES('AVAILABLE');

INSERT INTO penghuni(id_penghuni, nama, huni, bayar_mulai, bayar_sampai)
VALUES ('GC0001', 'Sibghatullah Mujaddid', 'TETAP', '2009-12-09', '2011-12-06');
UPDATE kamar SET id_penghuni='GC0001' WHERE no_kamar=3;
INSERT INTO penghuni(id_penghuni, nama, huni, bayar_mulai, bayar_sampai)
VALUES ('GC0002', 'Andi Muhammad Anwar', 'TETAP', '2009-12-19', '2012-04-27');
UPDATE kamar SET id_penghuni='GC0002' WHERE no_kamar=4;
INSERT INTO penghuni(id_penghuni, nama, huni, bayar_mulai, bayar_sampai)
VALUES ('GC0003', 'Andi Muhammad Amil Siddik', 'TETAP', '2009-12-29', '2013-02-16');
UPDATE kamar SET id_penghuni='GC0003' WHERE no_kamar=1;
INSERT INTO penghuni(id_penghuni, nama, huni, bayar_mulai, bayar_sampai)
VALUES ('GC0004', 'Aswar Sandi', 'INSIDENTAL', '2009-12-30', '2010-01-31');
UPDATE kamar SET id_penghuni='GC0004' WHERE no_kamar=5;

UPDATE kamar SET status='OCCUPIED' WHERE id_penghuni IN (SELECT id_penghuni FROM penghuni);

INSERT INTO barang VALUES('MG00001', 'Sabun mandi', 1400, 24);
INSERT INTO barang VALUES('MG00002', 'Pasta gigi', 2500, 16);
INSERT INTO barang VALUES('MG00003', 'Aqua botol', 2000, 45);
INSERT INTO barang VALUES('MG00004', 'Chitos', 1500, 57);
INSERT INTO barang VALUES('MG00005', 'Apa aja deh', 3400, 42);

INSERT INTO transaksi VALUES('TG00001', '2009-12-15', 4, 'MG00005');

DELETE FROM user;
INSERT INTO user VALUES('owner', 'owner', 'siOwner', 'OWNER');
INSERT INTO user VALUES('office', 'office', 'siFrontOffice', 'FRONT OFFICE');
INSERT INTO user VALUES('manajer', 'manajer', 'siManajer', 'MANAJER');
