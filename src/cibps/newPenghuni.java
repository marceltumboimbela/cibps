/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * newPenghuni.java
 *
 * Created on 03 Des 09, 21:57:16
 */

package cibps;

import java.sql.Connection;
import java.util.Vector;

/**
 *
 * @author Mr Zorro
 */
public class newPenghuni extends javax.swing.JFrame {

    private Connection koneksi;
    private Vector dbNoKamar;
    private Vector dbIDPenghuni;
    private String defaultDate = "2009-12-09";
    //public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    /** Creates new form penghuniBaru2 */
    public newPenghuni() {
        initComponents();
        initFirst();
    }

    private void initFirst() {
        koneksi = null;
        dbNoKamar = new Vector();
        dbIDPenghuni = new Vector();
        koneksi = Database.openConnection(koneksi);
        if (koneksi != null) {
            Vector dbKamar = Database.loadDBKamar(koneksi);
            if (dbKamar.isEmpty() == false) {
                for (int i = 0; i < dbKamar.size(); i++) {
                    if (((Kamar)dbKamar.get(i)).getStatus().compareTo("AVAILABLE") == 0)
                        dbNoKamar.add(((Kamar)dbKamar.get(i)).getNoKamar());
                }
            }
            else
                return;
            loadNoKamar();
            Vector dbPenghuni = Database.loadDBPenghuni(koneksi);
            if (dbPenghuni.isEmpty() == false) {
                for (int i = 0; i < dbPenghuni.size(); i++)
                    dbIDPenghuni.add(((Penghuni)dbPenghuni.get(i)).getIdPenghuni());
            }
            else
                return;
        }
    }

    private void loadNoKamar() {
        if (dbNoKamar.isEmpty())
            return;
        for (int i = 0; i < dbNoKamar.size(); i++) {
            cbxNoKamar.addItem(dbNoKamar.get(i));
        }
    }

    private String generateID() {
        String genID = null;
        if (dbIDPenghuni.isEmpty()) {
            genID = "GC0001";
        }
        else {
            genID = (String) dbIDPenghuni.get(dbIDPenghuni.size()-1);
            int tID = Integer.parseInt(genID.substring(2));
            do {
                tID++;
                genID = String.format("GC%04d", tID);
            } while (dbIDPenghuni.contains(genID));
        }
        return genID;
    }

    private boolean simpan() {
        if (txtIDPenghuni.getText().compareTo("") == 0)
            return false;
        if (txtNama.getText().compareTo("") == 0)
            return false;
        if (spnBayarMulai.getValue() == null)
            return false;
        if (spnBayarSampai.getValue() == null)
            return false;
        if (((String) cbxHuni.getSelectedItem()).compareTo("") == 0)
            return false;
        if (cbxNoKamar.getSelectedIndex() == 0)
            return false;

        Penghuni penghuni = new Penghuni();
        penghuni.setIdPenghuni(txtIDPenghuni.getText());
        penghuni.setNama(txtNama.getText());
        penghuni.setAlamat(txtAlamat.getText());
        penghuni.setPekerjaan(txtPekerjaan.getText());
        penghuni.setNoHP(txtNoHP.getText());
        penghuni.setKendaraan(txtKendaraan.getText());
        penghuni.setHuni((String) cbxHuni.getSelectedItem());
        penghuni.setBayarMulai((java.util.Date) spnBayarMulai.getValue());
        penghuni.setBayarSampai((java.util.Date) spnBayarSampai.getValue());

        Kamar kamar = new Kamar();
        int noKamar = (Integer) cbxNoKamar.getSelectedItem();
        kamar.setNoKamar(noKamar);

        return Database.insertPenghuniBaru(koneksi, penghuni, kamar);
    }

    public void close() {
        this.setVisible(false);
        this.dispose();
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
        txtIDPenghuni = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        lblIDPenghuni = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPekerjaan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNoHP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbxNoKamar = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        btnGenerateID = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtKendaraan = new javax.swing.JTextField();
        cbxHuni = new javax.swing.JComboBox();
        spnBayarSampai = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        spnBayarMulai = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CIBPS [Tambah Penghuni]");

        txtIDPenghuni.setEditable(false);

        lblIDPenghuni.setText("ID Penghuni");

        lblNama.setText("Nama");

        lblAlamat.setText("Alamat");

        jLabel4.setText("Pekerjaan");

        jLabel5.setText("No. HP");

        jLabel6.setText("Bayar Sampai");

        jLabel7.setText("Huni");

        jLabel1.setText("No. Kamar");

        cbxNoKamar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtAlamat.setLineWrap(true);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        btnGenerateID.setText("Generate");
        btnGenerateID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateIDActionPerformed(evt);
            }
        });

        jLabel2.setText("Kendaraan");

        cbxHuni.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TETAP", "INSIDENTAL" }));

        spnBayarSampai.setModel(new javax.swing.SpinnerDateModel());
        spnBayarSampai.setEditor(new javax.swing.JSpinner.DateEditor(spnBayarSampai, "yyyy-MM-dd"));

        jLabel3.setText("Bayar Mulai");

        spnBayarMulai.setModel(new javax.swing.SpinnerDateModel());
        spnBayarMulai.setEditor(new javax.swing.JSpinner.DateEditor(spnBayarMulai, "yyyy-MM-dd"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblIDPenghuni)
                        .addGap(12, 12, 12)
                        .addComponent(txtIDPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnGenerateID))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNama)
                        .addGap(43, 43, 43)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAlamat)
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(22, 22, 22)
                        .addComponent(txtPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(37, 37, 37)
                        .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtKendaraan, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(49, 49, 49)
                        .addComponent(cbxHuni, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(cbxNoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(15, 15, 15)
                        .addComponent(spnBayarMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(15, 15, 15)
                        .addComponent(spnBayarSampai, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIDPenghuni)
                    .addComponent(txtIDPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerateID))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNama)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlamat)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtKendaraan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbxHuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbxNoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(spnBayarMulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(spnBayarSampai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateIDActionPerformed
        // TODO add your handling code here:
        txtIDPenghuni.setText(generateID());
}//GEN-LAST:event_btnGenerateIDActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        close();
}//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (simpan() == false)
            System.err.println("Error: can not insert new Penghuni data");
        else
            close();
}//GEN-LAST:event_btnSimpanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnGenerateID;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cbxHuni;
    private javax.swing.JComboBox cbxNoKamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblIDPenghuni;
    private javax.swing.JLabel lblNama;
    private javax.swing.JSpinner spnBayarMulai;
    private javax.swing.JSpinner spnBayarSampai;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtIDPenghuni;
    private javax.swing.JTextField txtKendaraan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHP;
    private javax.swing.JTextField txtPekerjaan;
    // End of variables declaration//GEN-END:variables

}
