/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cibps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mr Zorro
 */
public class Database {

    public static Connection openConnection(Connection koneksi) {
        koneksi = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return koneksi;
        }
        try {
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/cibpsdb", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return koneksi;
        }
        return koneksi;
    }

    public static Connection closeConnection(Connection koneksi) {
        try {
            koneksi.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return koneksi;
    }

    public static boolean isAlive(Connection koneksi) {
        return (koneksi != null);
    }

    public boolean executeDBScripts(Connection koneksi, String aSQLScriptFilePath) {
        boolean isScriptExecuted = false;
        try {
            BufferedReader in = new BufferedReader(new FileReader(aSQLScriptFilePath));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = in.readLine()) != null) {
                sb.append(str + "\n ");
            }
            in.close();
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate(sb.toString());
            isScriptExecuted = true;
        } catch (Exception e) {
            System.err.println("Failed to Execute" + aSQLScriptFilePath +". The error is"+ e.getMessage());
        }
        return isScriptExecuted;
    }
    
    public static Vector loadDBPenghuni(Connection koneksi) {
        Vector dbPenghuni = new Vector();
        if (koneksi == null)
            return dbPenghuni;
        ResultSet hasilQuery = null;
        try {
            Statement stm = koneksi.createStatement();
            hasilQuery = stm.executeQuery("SELECT * FROM penghuni");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbPenghuni;
        }
        try {
            while (hasilQuery.next()) {
                Penghuni record = new Penghuni();
                record.setIdPenghuni(hasilQuery.getString("id_penghuni"));
                record.setNama(hasilQuery.getString("nama"));
                record.setAlamat(hasilQuery.getString("alamat"));
                record.setPekerjaan(hasilQuery.getString("pekerjaan"));
                record.setNoHP(hasilQuery.getString("no_hp"));
                record.setKendaraan(hasilQuery.getString("kendaraan"));
                record.setHuni(hasilQuery.getString("huni"));
                record.setBayarMulai(hasilQuery.getDate("bayar_mulai"));
                record.setBayarSampai(hasilQuery.getDate("bayar_sampai"));
                dbPenghuni.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbPenghuni;
        }
        return dbPenghuni;
    }

    public static Vector loadDBKamar(Connection koneksi) {
        Vector dbKamar = new Vector();
        if (koneksi == null)
            return dbKamar;
        ResultSet hasilQuery = null;
        try {
            Statement stm = koneksi.createStatement();
            hasilQuery = stm.executeQuery("SELECT * FROM kamar");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbKamar;
        }
        try {
            while (hasilQuery.next()) {
                Kamar record = new Kamar();
                record.setNoKamar(hasilQuery.getShort("no_kamar"));
                record.setStatus(hasilQuery.getString("status"));
                record.setIdPenghuni(hasilQuery.getString("id_penghuni"));
                dbKamar.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbKamar;
        }
        return dbKamar;
    }

    public static Vector loadDBBarang(Connection koneksi) {
        Vector dbBarang = new Vector();
        if (koneksi == null)
            return dbBarang;
        ResultSet hasilQuery = null;
        try {
            Statement stm = koneksi.createStatement();
            hasilQuery = stm.executeQuery("SELECT * FROM barang");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbBarang;
        }
        try {
            while (hasilQuery.next()) {
                Barang record = new Barang();
                record.setIdBarang(hasilQuery.getString("id_barang"));
                record.setNamaBarang(hasilQuery.getString("nama_barang"));
                record.setHarga(hasilQuery.getInt("harga"));
                record.setJumlahStok(hasilQuery.getInt("jumlah_stok"));
                dbBarang.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbBarang;
        }
        return dbBarang;
    }

    public static Vector loadDBTransaksi(Connection koneksi) {
        Vector dbTransaksi = new Vector();
        if (koneksi == null)
            return dbTransaksi;
        ResultSet hasilQuery = null;
        try {
            Statement stm = koneksi.createStatement();
            hasilQuery = stm.executeQuery("SELECT * FROM transaksi");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbTransaksi;
        }
        try {
            while (hasilQuery.next()) {
                Transaksi record = new Transaksi();
                record.setNoTransaksi(hasilQuery.getString("no_transaksi"));
                record.setWaktuTransaksi(hasilQuery.getDate("waktu_transaksi"));
                record.setJumlahTransaksi(hasilQuery.getInt("jumlah_transaksi"));
                record.setIdBarang(hasilQuery.getString("id_barang"));
                dbTransaksi.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbTransaksi;
        }
        return dbTransaksi;
    }

    public static Vector loadDBKas(Connection koneksi) {
        Vector dbKas = new Vector();
        if (koneksi == null)
            return dbKas;
        ResultSet hasilQuery = null;
        try {
            Statement stm = koneksi.createStatement();
            hasilQuery = stm.executeQuery("SELECT * FROM kas;");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbKas;
        }
        try {
            while (hasilQuery.next()) {
                Kas record = new Kas();
                record.setTanggal(hasilQuery.getDate("tanggal"));
                record.setSaldo(hasilQuery.getInt("saldo"));
                dbKas.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbKas;
        }
        return dbKas;
    }

    public static Vector loadDBUser(Connection koneksi) {
        Vector dbUser = new Vector();
        if (koneksi == null)
            return dbUser;
        ResultSet hasilQuery = null;
        try {
            Statement stm = koneksi.createStatement();
            hasilQuery = stm.executeQuery("SELECT * FROM user;");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbUser;
        }
        try {
            while (hasilQuery.next()) {
                User record = new User();
                record.setUsername(hasilQuery.getString("username"));
                record.setPassword(hasilQuery.getString("password"));
                record.setNamaUser(hasilQuery.getString("nama_user"));
                record.setDivisi(hasilQuery.getString("divisi"));
                dbUser.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbUser;
        }
        return dbUser;
    }

    public static boolean insertPenghuniBaru(Connection koneksi, Penghuni penghuni, Kamar kamar) {
        try {
            String sql = "INSERT INTO penghuni(" +
                "id_penghuni," +
                "nama," +
                "alamat," +
                "pekerjaan," +
                "no_hp," +
                "kendaraan," +
                "huni," +
                "bayar_mulai," +
                "bayar_sampai)" +
                "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, penghuni.getIdPenghuni());
            pstmt.setString(2, penghuni.getNama());
            pstmt.setString(3, penghuni.getAlamat());
            pstmt.setString(4, penghuni.getPekerjaan());
            pstmt.setString(5, penghuni.getNoHP());
            pstmt.setString(6, penghuni.getKendaraan());
            pstmt.setString(7, penghuni.getHuni());
            java.util.Date date = penghuni.getBayarMulai();
            pstmt.setDate(8, new java.sql.Date(date.getTime()));
            date = penghuni.getBayarSampai();
            pstmt.setDate(9, new java.sql.Date(date.getTime()));
            pstmt.executeUpdate();

            sql = null;
            pstmt = null;
            sql = "UPDATE kamar " +
                    "SET status='OCCUPIED' " +
                    "WHERE no_kamar=?";
            pstmt = koneksi.prepareStatement(sql);
            pstmt.setInt(1, kamar.getNoKamar());
            pstmt.executeUpdate();

            sql = null;
            pstmt = null;
            sql = "UPDATE kamar " +
                    "SET id_penghuni=? " +
                    "WHERE no_kamar=?";
            pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, penghuni.getIdPenghuni());
            pstmt.setInt(2, kamar.getNoKamar());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean updateDataPenghuni(Connection koneksi, Penghuni penghuni) {
        try {
            String sql = "UPDATE penghuni SET " +
                "nama=?, " +
                "alamat=?, " +
                "pekerjaan=?, " +
                "no_hp=?, " +
                "kendaraan=?, " +
                "huni=?, " +
                "bayar_mulai=?, " +
                "bayar_sampai=? " +
                "WHERE id_penghuni=?";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, penghuni.getNama());
            pstmt.setString(2, penghuni.getAlamat());
            pstmt.setString(3, penghuni.getPekerjaan());
            pstmt.setString(4, penghuni.getNoHP());
            pstmt.setString(5, penghuni.getKendaraan());
            pstmt.setString(6, penghuni.getHuni());
            java.util.Date date = penghuni.getBayarMulai();
            pstmt.setDate(7, new java.sql.Date(date.getTime()));
            date = penghuni.getBayarSampai();
            pstmt.setDate(8, new java.sql.Date(date.getTime()));
            pstmt.setString(9, penghuni.getIdPenghuni());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean removeDataPenghuni(Connection koneksi, String IDPenghuni) {
        try {
            String sql =
                    "UPDATE kamar " +
                    "SET status='AVAILABLE' " +
                    "WHERE id_penghuni=?";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, IDPenghuni);
            pstmt.executeUpdate();

            sql = null;
            pstmt = null;
            sql =   "DELETE FROM penghuni " +
                    "WHERE id_penghuni=?";
            pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, IDPenghuni);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean insertKamarBaru(Connection koneksi) {
        try {
            String sql = "INSERT INTO kamar(status) " +
                "VALUES('AVAILABLE')";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean updateKamar(Connection koneksi, Kamar kamar) {
        try {
            String sql = "UPDATE kamar SET " +
                "status=?, " +
                "id_penghuni=? " +
                "WHERE no_kamar=?";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, kamar.getStatus());
            pstmt.setString(2, kamar.getIdPenghuni());
            pstmt.setInt(3, kamar.getNoKamar());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean removeKamar(Connection koneksi, int noKamar) {
        try {
            String sql = "DELETE FROM kamar WHERE no_kamar=?";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setInt(1, noKamar);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean insertBarangBaru(Connection koneksi, Barang barang) {
        try {
            String sql = "INSERT INTO barang VALUES(?,?,?,?)";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, barang.getIdBarang());
            pstmt.setString(2, barang.getNamaBarang());
            pstmt.setInt(3, barang.getHarga());
            pstmt.setInt(4, barang.getJumlahStok());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean updateBarang(Connection koneksi, Barang barang) {
        try {
            String sql = "UPDATE barang SET " +
                "nama_barang=?, " +
                "harga=?, " +
                "jumlah_stok=? " +
                "WHERE id_barang=?";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, barang.getNamaBarang());
            pstmt.setInt(2, barang.getHarga());
            pstmt.setInt(3, barang.getJumlahStok());
            pstmt.setString(4, barang.getIdBarang());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean removeBarang(Connection koneksi, String IDBarang) {
        try {
            String sql = "DELETE FROM barang " +
                    "WHERE id_barang=?";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, IDBarang);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean insertTransaksiBaru(Connection koneksi, Transaksi transaksi, Barang barang) {
        try {
            String sql = "INSERT INTO transaksi VALUES(?,?,?,?)";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, transaksi.getNoTransaksi());
            java.util.Date date = transaksi.getWaktuTransaksi();
            pstmt.setDate(2, new java.sql.Date(date.getTime()));
            pstmt.setInt(3, transaksi.getJumlahTransaksi());
            pstmt.setString(4, transaksi.getIdBarang());
            pstmt.executeUpdate();
            sql = "UPDATE barang SET jumlah_stok=? WHERE id_barang=?";
            pstmt = koneksi.prepareStatement(sql);
            pstmt.setInt(1, barang.getJumlahStok());
            pstmt.setString(2, barang.getIdBarang());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean insertKasBaru(Connection koneksi, Kas kas) {
        try {
            String sql = "INSERT INTO kas VALUES(?,?)";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            java.util.Date date = kas.getTanggal();
            pstmt.setDate(1, new java.sql.Date(date.getTime()));
            pstmt.setInt(2, kas.getSaldo());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean insertUserBaru(Connection koneksi, User user) {
        try {
            String sql = "INSERT INTO user VALUES(?,PASSWORD(?),?,?)";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNamaUser());
            pstmt.setString(4, user.getDivisi());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean updateUser(Connection koneksi, User user) {
        try {
            String sql = "UPDATE user SET " +
                "password=PASSWORD(?), " +
                "nama_user=?, " +
                "divisi=? " +
                "WHERE username=?";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getNamaUser());
            pstmt.setString(3, user.getDivisi());
            pstmt.setString(4, user.getUsername());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean removeUser(Connection koneksi, String username) {
        try {
            String sql = "DELETE FROM user " +
                    "WHERE username=?";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static Vector loadStatistik(Connection koneksi) {
        Vector<Statistik> dbStats = new Vector();
        if (koneksi == null)
            return dbStats;
        ResultSet hasilQuery = null;
        try {
            Statement stm = koneksi.createStatement();
            hasilQuery = stm.executeQuery(
                    "SELECT waktu_transaksi as w, transaksi.id_barang as i, barang.nama_barang as n, " +
                    "SUM(transaksi.jumlah_transaksi) as j, " +
                    "COUNT(transaksi.id_barang) as c " +
                    "FROM barang, transaksi " +
                    "WHERE transaksi.id_barang=barang.id_barang " +
                    "GROUP BY w, i");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbStats;
        }
        try {
            while (hasilQuery.next()) {
                Statistik record = new Statistik();
                record.setTanggal(hasilQuery.getDate("w"));
                record.setIDBarang(hasilQuery.getString("i"));
                record.setNamaBarang(hasilQuery.getString("n"));
                record.setJumlahTransaksi(hasilQuery.getInt("j"));
                record.setJumlahTerjual(hasilQuery.getInt("c"));
                dbStats.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return dbStats;
        }
        return dbStats;
    }
}
