/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cibps;

import java.util.Date;

/**
 *
 * @author Mr Zorro
 */
public class Statistik {

    private Date tanggal;
    private String IDBarang;
    private String NamaBarang;
    private int JumlahTerjual;
    private int JumlahTransaksi;

    public Statistik() {
        tanggal = new Date();
        IDBarang = null;
        NamaBarang = null;
        JumlahTerjual = 0;
        JumlahTransaksi = 0;
    }

    public String getIDBarang() {
        return IDBarang;
    }

    public void setIDBarang(String IDBarang) {
        this.IDBarang = IDBarang;
    }

    public int getJumlahTerjual() {
        return JumlahTerjual;
    }

    public void setJumlahTerjual(int JumlahTerjual) {
        this.JumlahTerjual = JumlahTerjual;
    }

    public int getJumlahTransaksi() {
        return JumlahTransaksi;
    }

    public void setJumlahTransaksi(int JumlahTransaksi) {
        this.JumlahTransaksi = JumlahTransaksi;
    }

    public String getNamaBarang() {
        return NamaBarang;
    }

    public void setNamaBarang(String NamaBarang) {
        this.NamaBarang = NamaBarang;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
