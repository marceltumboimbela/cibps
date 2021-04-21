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
public class Kas {

    private Date tanggal;
    private int saldo;
    
    public Kas() {
        saldo = 0;
    }

    /**
     * @return the tanggal
     */
    public Date getTanggal() {
        return tanggal;
    }

    /**
     * @return the saldo
     */
    public int getSaldo() {
        return saldo;
    }

    /**
     * @param tanggal the tanggal to set
     */
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Object[] toObjects() {
        Object[] t = new Object[2];
        t[0] = (Object)  tanggal;
        t[1] = (Object)  saldo;
        return t;
    }
}
