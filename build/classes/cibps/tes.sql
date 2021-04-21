select * from barang;
select * from transaksi;
--select distinct waktu_transaksi, id_barang
--from transaksi
--where waktu_transaksi in (select waktu_transaksi
--from transaksi);
select waktu_transaksi as w, transaksi.id_barang as id, barang.nama_barang as n,
sum(transaksi.jumlah_transaksi) as j, count(transaksi.id_barang) as t
from barang, transaksi
where transaksi.id_barang=barang.id_barang
group by w, id;