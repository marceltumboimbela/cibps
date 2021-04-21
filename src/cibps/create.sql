DROP DATABASE IF EXISTS CIBPSDB;
CREATE DATABASE IF NOT EXISTS CIBPSDB;
USE CIBPSDB;

CREATE TABLE penghuni(
	id_penghuni VARCHAR(6) NOT NULL,
	nama VARCHAR(26) NOT NULL,
	alamat VARCHAR(40),
	pekerjaan VARCHAR(26),
	no_hp VARCHAR(16),
	kendaraan VARCHAR(26),
        huni VARCHAR(16) NOT NULL,
        bayar_mulai DATE NOT NULL,
        bayar_sampai DATE NOT NULL,
	PRIMARY KEY(id_penghuni)
)Engine=InnoDB;

CREATE TABLE kamar(
	no_kamar SMALLINT NOT NULL AUTO_INCREMENT,
	status VARCHAR(16) NOT NULL,
        id_penghuni VARCHAR(6),
	PRIMARY KEY(no_kamar),
        FOREIGN KEY(id_penghuni)
	REFERENCES penghuni(id_penghuni)
	ON UPDATE CASCADE ON DELETE SET NULL
)Engine=InnoDB;

CREATE TABLE barang(
	id_barang VARCHAR(7) NOT NULL,
	nama_barang VARCHAR(26) NOT NULL,
	harga INT NOT NULL,
	jumlah_stok INT NOT NULL,
	PRIMARY KEY(id_barang)
)Engine=InnoDB;

CREATE TABLE transaksi(
	no_transaksi VARCHAR(7) NOT NULL,
	waktu_transaksi DATE NOT NULL,
	jumlah_transaksi INT NOT NULL,
	id_barang VARCHAR(7),
	PRIMARY KEY(no_transaksi),
	FOREIGN KEY(id_barang)
	REFERENCES barang(id_barang)
	ON UPDATE CASCADE ON DELETE SET NULL
)Engine=InnoDB;

CREATE TABLE kas(
        id INT NOT NULL AUTO_INCREMENT,
        tanggal DATE,
        saldo INT,
        PRIMARY KEY(id)
)Engine=InnoDB;

CREATE TABLE user(
        username CHAR(26) NOT NULL,
        password CHAR(26) NOT NULL,
        nama_user CHAR(26),
        divisi VARCHAR(16) NOT NULL,
        PRIMARY KEY(username)
)Engine=InnoDB;

INSERT INTO user VALUES('owner', 'owner', 'siOwner', 'OWNER');
INSERT INTO user VALUES('office', 'office', 'siFrontOffice', 'FRONT OFFICE');
INSERT INTO user VALUES('manajer', 'manajer', 'siManajer', 'MANAJER');
