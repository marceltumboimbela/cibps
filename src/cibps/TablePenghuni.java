/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TablePenghuni.java
 *
 * Created on Dec 2, 2009, 10:54:31 PM
 */

package cibps;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author marcel
 */
public class TablePenghuni extends JPanel {

    public final int sizeWidth = 640;
    public final int sizeHeight = 480;

    private Connection koneksi;
    private Vector dbPenghuni;

    /** Creates new form TablePenghuni */
    public TablePenghuni() {
        initComponents();
        initFirst();
    }

    private void initFirst() {
        dbPenghuni = new Vector();
        koneksi = Database.openConnection(koneksi);
        if (koneksi != null) {
            dbPenghuni = Database.loadDBPenghuni(koneksi);
            showDatabase();
        }
    }

    private void simpan() {
        if (masterTable.getSelectedRow() != -1) {
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
            Database.updateDataPenghuni(koneksi, penghuni);
        }
    }

    private void showDatabase() throws IllegalArgumentException {
        if (dbPenghuni.isEmpty())
            return;
        try {
            DefaultTableModel dtm = (DefaultTableModel) masterTable.getModel();
            for (int i = 0; i < dbPenghuni.size(); i++) {
                int idx = -1;
                Penghuni record = (Penghuni) dbPenghuni.get(i);
                dtm.addRow(new Object[dtm.getColumnCount()]);
                TableColumnModel tcm = masterTable.getColumnModel();
                for (int j = 0; j < masterTable.getColumnCount(); j++) {
                    if (masterTable.getColumnName(j).compareTo("ID Penghuni") == 0)
                        if ((idx = tcm.getColumnIndex("ID Penghuni")) > -1)
                            dtm.setValueAt(record.getIdPenghuni(), i, idx);
                    if (masterTable.getColumnName(j).compareTo("Nama") == 0)
                        if ((idx = tcm.getColumnIndex("Nama")) > -1)
                            dtm.setValueAt(record.getNama(), i, idx);
                    if (masterTable.getColumnName(j).compareTo("Alamat") == 0)
                        if ((idx = tcm.getColumnIndex("Alamat")) > -1)
                            dtm.setValueAt(record.getAlamat(), i, idx);
                    if (masterTable.getColumnName(j).compareTo("Pekerjaan") == 0)
                        if ((idx = tcm.getColumnIndex("Pekerjaan")) > -1)
                            dtm.setValueAt(record.getPekerjaan(), i, idx);
                    if (masterTable.getColumnName(j).compareTo("No HP") == 0)
                        if ((idx = tcm.getColumnIndex("No HP")) > -1)
                            dtm.setValueAt(record.getNoHP(), i, idx);
                    if (masterTable.getColumnName(j).compareTo("Kendaraan") == 0)
                        if ((idx = tcm.getColumnIndex("Kendaraan")) > -1)
                            dtm.setValueAt(record.getKendaraan(), i, idx);
                    if (masterTable.getColumnName(j).compareTo("Huni") == 0)
                        if ((idx = tcm.getColumnIndex("Huni")) > -1)
                            dtm.setValueAt(record.getHuni(), i, idx);
                    if (masterTable.getColumnName(j).compareTo("Bayar Mulai") == 0)
                        if ((idx = tcm.getColumnIndex("Bayar Mulai")) > -1)
                            dtm.setValueAt(record.getBayarMulai(), i, idx);
                    if (masterTable.getColumnName(j).compareTo("Bayar Sampai") == 0)
                        if ((idx = tcm.getColumnIndex("Bayar Sampai")) > -1)
                            dtm.setValueAt(record.getBayarSampai(), i, idx);
                    if (masterTable.getColumnName(j).compareTo("Status") == 0) {
                        Calendar calendar = new GregorianCalendar();
                        java.util.Date now = calendar.getTime();
                        if (record.getBayarSampai().after(now) || record.getBayarSampai().equals(now)) {
                            if ((idx = tcm.getColumnIndex("Status")) > -1)
                                dtm.setValueAt("Sudah Bayar", i, idx);
                        }
                        else {
                            if ((idx = tcm.getColumnIndex("Status")) > -1)
                                dtm.setValueAt("Berakhir", i, idx);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TablePenghuni.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }

    private void showSelectedElement() {
        int selIdx = masterTable.getSelectedRow();
        try {
            if (selIdx > -1) {
                Penghuni record = (Penghuni) dbPenghuni.get(selIdx);
                txtIDPenghuni.setText((String) record.getIdPenghuni());
                txtNama.setText((String) record.getNama());
                txtAlamat.setText((String) record.getAlamat());
                txtNoHP.setText((String) record.getNoHP());
                txtPekerjaan.setText((String) record.getPekerjaan());
                txtKendaraan.setText((String) record.getKendaraan());
                cbxHuni.setSelectedItem(record.getHuni());
                spnBayarMulai.setValue(record.getBayarMulai());
                spnBayarSampai.setValue(record.getBayarSampai());
            }
            else {
                txtIDPenghuni.setText("");
                txtNama.setText("");
                txtAlamat.setText("");
                txtNoHP.setText("");
                txtPekerjaan.setText("");
                txtKendaraan.setText("");
                cbxHuni.setSelectedIndex(1);
                //spnBayarMulai.setText("");
                //spnBayarSampai.setText("");
            }
        } catch (Exception ex) {
            Logger.getLogger(TablePenghuni.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }

    public void refresh() {
        DefaultTableModel dtm = (DefaultTableModel) masterTable.getModel();
        int sum = dtm.getRowCount();
        for (int i = 0; i < sum; i++) {
            dtm.removeRow(0);
        }
        initFirst();
    }

    public void hapus() {
        int index = masterTable.getSelectedRow();
        if (index == -1)
            return;
        Database.removeDataPenghuni(koneksi, ((Penghuni)dbPenghuni.get(index)).getIdPenghuni());
        dbPenghuni.remove(index);
        refresh();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblAlamat = new javax.swing.JLabel();
        lblHP = new javax.swing.JLabel();
        txtNoHP = new javax.swing.JTextField();
        lblPekerjaan = new javax.swing.JLabel();
        txtPekerjaan = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        lblKendaraan = new javax.swing.JLabel();
        txtKendaraan = new javax.swing.JTextField();
        lblHuni = new javax.swing.JLabel();
        lblBayarSampai = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbxHuni = new javax.swing.JComboBox();
        spnBayarMulai = new javax.swing.JSpinner();
        spnBayarSampai = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        txtIDPenghuni = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnRegistrasi = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(640, 480));
        setMinimumSize(new java.awt.Dimension(640, 480));

        masterTable.setAutoCreateRowSorter(true);
        masterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Penghuni", "Nama", "Bayar Mulai", "Bayar Sampai", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        masterTable.setToolTipText("Database Penghuni");
        masterTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        masterTable.getTableHeader().setReorderingAllowed(false);
        masterTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masterTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(masterTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblNama.setText("Nama");

        txtNama.setEditable(false);

        lblAlamat.setText("Alamat");

        lblHP.setText("No HP");

        txtNoHP.setEditable(false);

        lblPekerjaan.setText("Pekerjaan");

        txtPekerjaan.setEditable(false);

        txtAlamat.setColumns(20);
        txtAlamat.setEditable(false);
        txtAlamat.setFont(txtAlamat.getFont().deriveFont(txtAlamat.getFont().getSize()-2f));
        txtAlamat.setLineWrap(true);
        txtAlamat.setRows(8);
        txtAlamat.setTabSize(4);
        txtAlamat.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtAlamat);

        lblKendaraan.setText("Kendaraan");

        txtKendaraan.setEditable(false);

        lblHuni.setText("Huni");

        lblBayarSampai.setText("Bayar Sampai");

        jLabel1.setText("Bayar Mulai");

        cbxHuni.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TETAP", "INSIDENTAL" }));

        spnBayarMulai.setModel(new javax.swing.SpinnerDateModel());
        spnBayarMulai.setEditor(new javax.swing.JSpinner.DateEditor(spnBayarMulai, "yyyy-MM-dd"));

        spnBayarSampai.setModel(new javax.swing.SpinnerDateModel());
        spnBayarSampai.setEditor(new javax.swing.JSpinner.DateEditor(spnBayarSampai, "yyyy-MM-dd"));

        jLabel2.setText("ID Penghuni");

        txtIDPenghuni.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNama)
                    .addComponent(lblAlamat)
                    .addComponent(lblPekerjaan)
                    .addComponent(lblHP)
                    .addComponent(lblKendaraan)
                    .addComponent(lblHuni)
                    .addComponent(jLabel2))
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbxHuni, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnBayarMulai)
                        .addGap(18, 18, 18)
                        .addComponent(lblBayarSampai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnBayarSampai)
                        .addGap(40, 40, 40))
                    .addComponent(txtIDPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKendaraan, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addComponent(txtPekerjaan, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addComponent(txtNoHP, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIDPenghuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlamat)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPekerjaan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKendaraan)
                    .addComponent(txtKendaraan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHuni)
                    .addComponent(cbxHuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(spnBayarMulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBayarSampai)
                    .addComponent(spnBayarSampai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Check Out");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.setEnabled(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnRegistrasi.setText("Registrasi");
        btnRegistrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrasiActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.setToolTipText("Batalkan perubahan");
        btnBatal.setEnabled(false);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUbah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegistrasi)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnSimpan)
                    .addComponent(btnRegistrasi)
                    .addComponent(btnBatal)
                    .addComponent(btnRefresh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void masterTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterTableMouseClicked
        // TODO add your handling code here:
        //showSelectedElement();
        showSelectedElement();
    }//GEN-LAST:event_masterTableMouseClicked

    private void btnRegistrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrasiActionPerformed
        // TODO add your handling code here:
        newPenghuni pb = new newPenghuni();
        pb.setVisible(true);
        refresh();
    }//GEN-LAST:event_btnRegistrasiActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        if (dbPenghuni.isEmpty() == false) {
            dbPenghuni.clear();
        }
        refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        if (masterTable.getSelectedRow() == -1)
            return;
        txtNama.setEditable(true);
        txtAlamat.setEditable(true);
        txtNoHP.setEditable(true);
        txtPekerjaan.setEditable(true);
        txtKendaraan.setEditable(true);
        //cbxHuni.setEnabled(true);
        //spnBayarMulai.setEnabled(true);
        //spnBayarSampai.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnSimpan.setEnabled(true);
        btnBatal.setEnabled(true);
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        txtNama.setEditable(false);
        txtAlamat.setEditable(false);
        txtNoHP.setEditable(false);
        txtPekerjaan.setEditable(false);
        txtKendaraan.setEditable(false);
        //cbxHuni.setEnabled(false);
        //spnBayarMulai.setEnabled(false);
        //spnBayarSampai.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnBatal.setEnabled(false);
        simpan();
        refresh();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        txtNama.setEditable(false);
        txtAlamat.setEditable(false);
        txtNoHP.setEditable(false);
        txtPekerjaan.setEditable(false);
        txtKendaraan.setEditable(false);
        //cbxHuni.setEnabled(false);
        //spnBayarMulai.setEnabled(false);
        //spnBayarSampai.setEnabled(false);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnBatal.setEnabled(false);
        //refresh();
        showSelectedElement();
    }//GEN-LAST:event_btnBatalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRegistrasi;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox cbxHuni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblBayarSampai;
    private javax.swing.JLabel lblHP;
    private javax.swing.JLabel lblHuni;
    private javax.swing.JLabel lblKendaraan;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblPekerjaan;
    private javax.swing.JTable masterTable;
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
