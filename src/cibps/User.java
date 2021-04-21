/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cibps;

/**
 *
 * @author Mr Zorro
 */
public class User {

    private String username;
    private String password;
    private String namaUser;
    private String divisi;

    public User() {
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the namaUser
     */
    public String getNamaUser() {
        return namaUser;
    }

    /**
     * @return the divisi
     */
    public String getDivisi() {
        return divisi;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param namaUser the namaUser to set
     */
    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    /**
     * @param divisi the divisi to set
     */
    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

}
