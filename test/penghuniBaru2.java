/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * penghuniBaru2.java
 *
 * Created on 03 Des 09, 12:10:35
 */



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;

/**
 *
 * @author Mr Zorro
 */
public class penghuniBaru2 extends javax.swing.JPanel {

    private Connection koneksi;
    private Vector dbKamar;
    private Vector dbPenghuni;
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    /** Creates new form penghuniBaru2 */
    public penghuniBaru2() {
        initComponents();
        initFirst();
    }

    private void initFirst() {
        koneksi = null;
        dbKamar = new Vector();
        dbPenghuni = new Vector();
        openDatabase();
        readDatabase();
        loadNoKamar();
        spiBayarSampai.setEditor(new JSpinner.DateEditor(spiBayarSampai, "yyyy-MM-dd"));
        //spiBayarSampai.addChangeListener(new SpinnerListener());
    }
    
    private void loadNoKamar() {
        if (dbKamar.isEmpty())
            return;
        for (int i = 0; i < dbKamar.size(); i++)
            cbxNoKamar.addItem(dbKamar.get(i));
    }

    public boolean openDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(penghuniBaru2.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/cibpsdb", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(penghuniBaru2.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void readDatabase() {
        ResultSet hasilQuery = null;
        try {
            Statement stm = koneksi.createStatement();
            hasilQuery = stm.executeQuery("SELECT id_penghuni FROM penghuni;");
        } catch (SQLException ex) {
            Logger.getLogger(penghuniBaru2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        try {
            while (hasilQuery.next()) {
                String record = hasilQuery.getString("id_penghuni");
                dbPenghuni.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(penghuniBaru2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        hasilQuery = null;
        try {
            Statement stm = koneksi.createStatement();
            hasilQuery = stm.executeQuery("SELECT no_kamar FROM kamar WHERE status='AVAILABLE'");
        } catch (SQLException ex) {
            Logger.getLogger(penghuniBaru2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        try {
            while (hasilQuery.next()) {
                String record = hasilQuery.getString("no_kamar");
                dbKamar.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(penghuniBaru2.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }

    private String generateID() {
        String genID = null;
        genID = (String) dbPenghuni.get(dbPenghuni.size()-1);
        int tID = Integer.parseInt(genID.substring(2));
        do {
            tID++;
            genID = String.format("GC%04d", tID);
        } while (dbPenghuni.contains(genID));
        return genID;
    }

    private boolean simpan() {
        if (txtIDPenghuni.getText().equals(""))
            return false;
        if (txtNama.getText().equals(""))
            return false;
        if (spiBayarSampai.getValue() == null)
            return false;
        if (cbxHuni.getSelectedItem().toString().equals(""))
            return false;
        if (cbxNoKamar.getSelectedIndex() == 0)
            return false;
        
        try {
            String sql = "INSERT INTO penghuni(" +
                "id_penghuni," +
                "nama," +
                "alamat," +
                "pekerjaan," +
                "no_hp," +
                "kendaraan," +
                "bayar_sampai," +
                "huni)" +
                "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, txtIDPenghuni.getText());
            pstmt.setString(2, txtNama.getText());
            pstmt.setString(3, txtAlamat.getText());
            pstmt.setString(4, txtPekerjaan.getText());
            pstmt.setString(5, txtNoHP.getText());
            pstmt.setString(6, txtKendaraan.getText());
            java.sql.Date dt1;
            java.util.Date dt2 = (java.util.Date) spiBayarSampai.getValue();
            dt1 = new Date(dt2.getTime());
            pstmt.setDate(7, dt1);
            pstmt.setString(8, cbxHuni.getSelectedItem().toString());
            pstmt.executeUpdate();

            sql = null;
            pstmt = null;
            sql = "UPDATE kamar " +
                    "SET status='OCCUPIED' " +
                    "WHERE no_kamar=?";
            pstmt = koneksi.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(cbxNoKamar.getSelectedItem().toString()));
            pstmt.executeUpdate();
            
            sql = "UPDATE kamar " +
                    "SET id_penghuni=? " +
                    "WHERE no_kamar=?";
            pstmt = koneksi.prepareStatement(sql);
            pstmt.setString(1, txtIDPenghuni.getText());
            pstmt.setInt(2, Integer.parseInt(cbxNoKamar.getSelectedItem().toString()));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(penghuniBaru2.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void clearAll() {
        dbKamar.clear();
        dbPenghuni.clear();
        dbKamar = null;
        dbPenghuni = null;
        koneksi = null;
        txtIDPenghuni.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtPekerjaan.setText("");
        txtNoHP.setText("");
        txtKendaraan.setText("");
        cbxHuni.setSelectedIndex(0);
        cbxNoKamar.setSelectedIndex(0);
        initFirst();
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
        spiBayarSampai = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        txtKendaraan = new javax.swing.JTextField();
        cbxHuni = new javax.swing.JComboBox();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        txtIDPenghuni.setEditable(false);

        lblIDPenghuni.setText("ID Penghuni");

        lblNama.setText("Nama");

        lblAlamat.setText("Alamat");

        jLabel4.setText("Pekerjaan");

        jLabel5.setText("No. HP");

        jLabel6.setText("Bayar Hingga");

        jLabel7.setText("Huni");

        jLabel1.setText("No. Kamar");

        cbxNoKamar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));

        txtAlamat.setColumns(20);
        txtAlamat.setLineWrap(true);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        btnGenerateID.setText("Generate");
        btnGenerateID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateIDActionPerformed(evt);
            }
        });

        spiBayarSampai.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(), null, java.util.Calendar.MONTH));
        spiBayarSampai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spiBayarSampaiMouseClicked(evt);
            }
        });

        jLabel2.setText("Kendaraan");

        cbxHuni.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TETAP", "INSIDENTAL" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDPenghuni)
                            .addComponent(lblNama)
                            .addComponent(lblAlamat)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(txtKendaraan, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(txtNoHP, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtIDPenghuni, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGenerateID))
                            .addComponent(txtPekerjaan, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbxNoKamar, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxHuni, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(spiBayarSampai, javax.swing.GroupLayout.PREFERRED_SIZE, 110, Short.MAX_VALUE)))
                            .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDPenghuni)
                    .addComponent(txtIDPenghuni)
                    .addComponent(btnGenerateID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(txtNama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlamat)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPekerjaan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNoHP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKendaraan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(spiBayarSampai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbxHuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxNoKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateIDActionPerformed
        // TODO add your handling code here:
        txtIDPenghuni.setText(generateID());
    }//GEN-LAST:event_btnGenerateIDActionPerformed

    private void spiBayarSampaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spiBayarSampaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_spiBayarSampaiMouseClicked

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        simpan();
        clearAll();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        clearAll();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        penghuniBaru2.this.remove(this);
    }//GEN-LAST:event_btnBatalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnGenerateID;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cbxHuni;
    private javax.swing.JComboBox cbxNoKamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblIDPenghuni;
    private javax.swing.JLabel lblNama;
    private javax.swing.JSpinner spiBayarSampai;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtIDPenghuni;
    private javax.swing.JTextField txtKendaraan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHP;
    private javax.swing.JTextField txtPekerjaan;
    // End of variables declaration//GEN-END:variables

}
