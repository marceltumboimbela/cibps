/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cibps;

/**
 *
 * @author Mr Zorro
 */
public class Barang {

    private String idBarang;
    private String namaBarang;
    private int harga;
    private int jumlahStok;

    public Barang() {
    }

    /**
     * @return the idBarang
     */
    public String getIdBarang() {
        return idBarang;
    }

    /**
     * @return the namaBarang
     */
    public String getNamaBarang() {
        return namaBarang;
    }

    /**
     * @return the harga
     */
    public int getHarga() {
        return harga;
    }

    /**
     * @return the jumlahStok
     */
    public int getJumlahStok() {
        return jumlahStok;
    }

    /**
     * @param idBarang the idBarang to set
     */
    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    /**
     * @param namaBarang the namaBarang to set
     */
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(int harga) {
        this.harga = harga;
    }

    /**
     * @param jumlahStok the jumlahStok to set
     */
    public void setJumlahStok(int jumlahStok) {
        this.jumlahStok = jumlahStok;
    }

    public Object[] toObjects() {
        Object[] t = new Object[4];
        t[0] = (Object)  idBarang;
        t[1] = (Object)  namaBarang;
        t[2] = (Object)  harga;
        t[3] = (Object)  jumlahStok;
        return t;
    }
    
}
