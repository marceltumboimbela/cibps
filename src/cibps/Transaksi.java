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
public class Transaksi {

    private String noTransaksi;
    private Date waktuTransaksi;
    private int jumlahTransaksi;
    private String idBarang;

    public Transaksi() {
    }

    /**
     * @return the noTransaksi
     */
    public String getNoTransaksi() {
        return noTransaksi;
    }

    /**
     * @return the waktuTransaksi
     */
    public Date getWaktuTransaksi() {
        return waktuTransaksi;
    }

    /**
     * @return the jumlahTransaksi
     */
    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    /**
     * @return the idBarang
     */
    public String getIdBarang() {
        return idBarang;
    }

    /**
     * @param noTransaksi the noTransaksi to set
     */
    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    /**
     * @param waktuTransaksi the waktuTransaksi to set
     */
    public void setWaktuTransaksi(Date waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
    }

    /**
     * @param jumlahTransaksi the jumlahTransaksi to set
     */
    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }

    /**
     * @param idBarang the idBarang to set
     */
    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public Object[] toObjects() {
        Object[] objects = new Object[5];
        objects[0] = (Object) noTransaksi;
        objects[1] = (Object) waktuTransaksi;
        objects[2] = (Object) jumlahTransaksi;
        objects[3] = (Object) idBarang;
        return objects;
    }
}
