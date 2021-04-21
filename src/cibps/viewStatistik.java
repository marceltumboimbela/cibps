/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * viewStatistik.java
 *
 * Created on 03 Des 09, 22:20:35
 */

package cibps;

import java.sql.Connection;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Mr Zorro
 */
public class viewStatistik extends javax.swing.JFrame {

    private Connection koneksi;
    private Vector<Transaksi> dbTransaksi;
    private Vector<Statistik> dbStats;
    
    /** Creates new form viewStatistik */
    public viewStatistik() {
        initComponents();
        dbTransaksi = new Vector();
        dbStats = new Vector();
        koneksi = Database.openConnection(koneksi);
        if (koneksi != null) {
            dbTransaksi = Database.loadDBTransaksi(koneksi);
            dbStats = Database.loadStatistik(koneksi);
            loadTanggal();
            loadIDBarang();
        }
    }

    private void loadTanggal() {
        Vector<java.util.Date> dates = new Vector();
        for (int i = 0; i < dbStats.size(); i++) {
            if (dates.contains(dbStats.get(i).getTanggal()) == false) {
                dates.add(dbStats.get(i).getTanggal());
                cbxTanggal.addItem(dbStats.get(i).getTanggal());
            }
        }
    }

    private void loadIDBarang() {
        if (cbxTanggal.getSelectedIndex() == 0)
            return;
        cbxIDBarang.removeAllItems();
        cbxIDBarang.addItem("NONE");
        for (int i = 0; i < dbStats.size(); i++) {
            if (((Date) cbxTanggal.getSelectedItem()).compareTo(dbStats.get(i).getTanggal()) == 0) {
                cbxIDBarang.addItem(dbStats.get(i).getIDBarang());
            }
        }
    }

    private void showSelectedItem() {
        if (cbxTanggal.getSelectedIndex() != 0) {
            if (cbxIDBarang.getSelectedIndex() != 0) {
                java.util.Date date = (Date) cbxTanggal.getSelectedItem();
                String IDBarang = (String) cbxIDBarang.getSelectedItem();
                for (int i = 0; i < dbStats.size(); i++) {
                    if (date.compareTo(dbStats.get(i).getTanggal()) == 0) {
                        if (IDBarang.compareTo(dbStats.get(i).getIDBarang()) == 0) {
                            txtNamaBarang.setText(dbStats.get(i).getNamaBarang());
                            txtJumlahTerjual.setText(String.valueOf(dbStats.get(i).getJumlahTerjual()));
                            txtTotalTransaksi.setText(String.valueOf(dbStats.get(i).getJumlahTransaksi()));
                            break;
                        }
                    }
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbxTanggal = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtJumlahTerjual = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTotalTransaksi = new javax.swing.JTextField();
        cbxIDBarang = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnPeroleh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CIBPS [Statistik]");

        cbxTanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE" }));
        cbxTanggal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTanggalItemStateChanged(evt);
            }
        });

        jLabel1.setText("Tanggal Transaksi");

        jLabel2.setText("Nama Barang");

        jLabel3.setText("Jumlah Terjual");

        jLabel4.setText("Total Transaksi");

        cbxIDBarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE" }));
        cbxIDBarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxIDBarangItemStateChanged(evt);
            }
        });

        jLabel5.setText("ID Barang");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJumlahTerjual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(cbxIDBarang, 0, 248, Short.MAX_VALUE)
                            .addComponent(cbxTanggal, 0, 248, Short.MAX_VALUE)
                            .addComponent(txtTotalTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxIDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtJumlahTerjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTotalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Keluar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnPeroleh.setText("Peroleh");
        btnPeroleh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerolehActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnPeroleh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnPeroleh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxTanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTanggalItemStateChanged
        loadIDBarang();
    }//GEN-LAST:event_cbxTanggalItemStateChanged

    private void cbxIDBarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxIDBarangItemStateChanged
        //showSelectedItem();
    }//GEN-LAST:event_cbxIDBarangItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPerolehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerolehActionPerformed
        showSelectedItem();
    }//GEN-LAST:event_btnPerolehActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPeroleh;
    private javax.swing.JComboBox cbxIDBarang;
    private javax.swing.JComboBox cbxTanggal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtJumlahTerjual;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtTotalTransaksi;
    // End of variables declaration//GEN-END:variables

}
