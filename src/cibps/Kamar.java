/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cibps;

/**
 *
 * @author Mr Zorro
 */
public class Kamar {

    private int noKamar;
    private String status;
    private String idPenghuni;

    public Kamar() {
        status = "AVAILABLE";
    }
    
    /**
     * @return the noKamar
     */
    public int getNoKamar() {
        return noKamar;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the idPenghuni
     */
    public String getIdPenghuni() {
        return idPenghuni;
    }

    /**
     * @param noKamar the noKamar to set
     */
    public void setNoKamar(int noKamar) {
        this.noKamar = noKamar;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param idPenghuni the idPenghuni to set
     */
    public void setIdPenghuni(String idPenghuni) {
        this.idPenghuni = idPenghuni;
    }

    public Object[] toObjects() {
        Object[] t = new Object[3];
        t[0] = (Object)  noKamar;
        t[1] = (Object)  status;
        t[2] = (Object)  idPenghuni;
        return t;
    }
}
