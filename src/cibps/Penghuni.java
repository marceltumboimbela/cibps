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
public class Penghuni {

    private String idPenghuni;
    private String nama;
    private String alamat;
    private String pekerjaan;
    private String noHP;
    private String kendaraan;
    private String huni;
    private Date bayarMulai;
    private Date bayarSampai;

    public Penghuni() {
    }

    /**
     * @return the idPenghuni
     */
    public String getIdPenghuni() {
        return idPenghuni;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @return the pekerjaan
     */
    public String getPekerjaan() {
        return pekerjaan;
    }

    /**
     * @return the noHP
     */
    public String getNoHP() {
        return noHP;
    }

    /**
     * @return the kendaraan
     */
    public String getKendaraan() {
        return kendaraan;
    }
    
    /**
     * @return the huni
     */
    public String getHuni() {
        return huni;
    }

    /**
     * @return the bayarMulai
     */
    public Date getBayarMulai() {
        return bayarMulai;
    }

    /**
     * @return the bayarSampai
     */
    public Date getBayarSampai() {
        return bayarSampai;
    }

    /**
     * @param idPenghuni the idPenghuni to set
     */
    public void setIdPenghuni(String idPenghuni) {
        this.idPenghuni = idPenghuni;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * @param pekerjaan the pekerjaan to set
     */
    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    /**
     * @param noHP the noHP to set
     */
    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    /**
     * @param kendaraan the kendaraan to set
     */
    public void setKendaraan(String kendaraan) {
        this.kendaraan = kendaraan;
    }

    /**
     * @param huni the huni to set
     */
    public void setHuni(String huni) {
        this.huni = huni;
    }

    /**
     * @param bayarMulai the bayarMulai to set
     */
    public void setBayarMulai(Date bayarMulai) {
        this.bayarMulai = bayarMulai;
    }

    /**
     * @param bayarSampai the bayarSampai to set
     */
    public void setBayarSampai(Date bayarSampai) {
        this.bayarSampai = bayarSampai;
    }

    public Object[] toObjects() {
        Object[] t = new Object[9];
        t[0] = (Object) idPenghuni;
        t[1] = (Object) nama;
        t[2] = (Object) alamat;
        t[3] = (Object) pekerjaan;
        t[4] = (Object) noHP;
        t[5] = (Object) kendaraan;
        t[6] = (Object) huni;
        t[7] = (Object) bayarMulai;
        t[8] = (Object) bayarSampai;
        return t;
    }
    
}
