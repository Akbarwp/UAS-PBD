/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PBD.Batik.Frame.InternalFrame.Pegawai;

import PBD.Batik.Frame.Koneksi;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

/**
 *
 * @author akbar
 */
public class MasterPegawai extends javax.swing.JInternalFrame {

    /**
     * Creates new form MasterPegawai
     */
    
    Connection conn;
    Statement cmd;
    ResultSet rs;
    DefaultTableModel tmPegawai;
    String SQL;
    
    Statement cmd1;
    ResultSet rs1;
    
    public MasterPegawai() {
        initComponents();
        
        loadIDPegawai();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
    
    private void loadIDPegawai(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM pegawai";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDPegawai.removeAllItems();
            cbIDPegawai.addItem("Pilih ID Pegawai");
            while(rs.next()){
                String cid = rs.getString("id_pegawai");
                cbIDPegawai.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void showTabel() {
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT id_pegawai, nama_pegawai, TO_CHAR(tgl_lahir_pegawai, 'DD MON YYYY') as TGL_LAHIR_PEGAWAI, "
                    + "almt_pegawai, telp_pegawai, jk_pegawai FROM pegawai ORDER BY id_pegawai ASC");
            
            //Setting kolom dari DefaultTableModel
            tmPegawai = new DefaultTableModel(new String[] {"ID_PEGAWAI", "NAMA_PEGAWAI", "TGL_LAHIR_PEGAWAI", "ALMT_PEGAWAI", "TELP_PEGAWAI", "JK_PEGAWAI"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmPegawai.addRow(new Object[] {rs.getString("id_pegawai"), rs.getString("nama_pegawai"), rs.getString("tgl_lahir_pegawai"), 
                        rs.getString("almt_pegawai"), rs.getString("telp_pegawai"), rs.getString("jk_pegawai")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbPegawai.setDefaultEditor(Object.class, null);
            
            tbPegawai.setModel(tmPegawai);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private String cekPegawai(int angka) {
        String pegawai = null;
        try {
            switch(angka) {
                case 1:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT nama_pegawai FROM pegawai WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("nama_pegawai");
                        }
                    }
                break;
                
                case 2:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT TO_CHAR(tgl_lahir_pegawai, 'DD MON YYYY') as tgl_lahir_pegawai FROM pegawai WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("tgl_lahir_pegawai");
                        }
                    }
                break;
                
                case 3:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT almt_pegawai FROM pegawai WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("almt_pegawai");
                        }
                    }
                break;
                
                case 4:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT TELP_PEGAWAI FROM pegawai WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("TELP_PEGAWAI");
                        }
                    }
                break;
                
                case 5:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT JK_PEGAWAI FROM pegawai WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            pegawai = rs1.getString("JK_PEGAWAI");
                        }
                    }
                break;
            }
            
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pegawai;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        labNamaPegawai = new javax.swing.JLabel();
        labTglLahirPegawai = new javax.swing.JLabel();
        labIDPegawai = new javax.swing.JLabel();
        labAlamatPegawai = new javax.swing.JLabel();
        labTeleponPegawai = new javax.swing.JLabel();
        labJKPegawai = new javax.swing.JLabel();
        txNamaPegawai = new javax.swing.JTextField();
        txAlamatPegawai = new javax.swing.JTextField();
        txTeleponPegawai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbIDPegawai = new javax.swing.JComboBox<>();
        btnClear = new javax.swing.JButton();
        txTglLahirPegawai = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPegawai = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnClear1 = new javax.swing.JButton();
        btnTambahData = new javax.swing.JButton();
        labNamaPegawai1 = new javax.swing.JLabel();
        txNamaPegawai1 = new javax.swing.JTextField();
        labTglLahirPegawai1 = new javax.swing.JLabel();
        labAlamatPegawai1 = new javax.swing.JLabel();
        txAlamatPegawai1 = new javax.swing.JTextField();
        labTeleponPegawai1 = new javax.swing.JLabel();
        txTeleponPegawai1 = new javax.swing.JTextField();
        labJKPegawai1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rbLaki1 = new javax.swing.JRadioButton();
        rbPerempuan1 = new javax.swing.JRadioButton();
        txTglLahirPegawai1 = new com.toedter.calendar.JDateChooser();

        setBorder(null);
        setResizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(38, 42, 65));

        btnUpdate.setBackground(new java.awt.Color(255, 255, 0));
        btnUpdate.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        rbLaki.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(rbLaki);
        rbLaki.setForeground(new java.awt.Color(255, 255, 255));
        rbLaki.setText("L");

        rbPerempuan.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(rbPerempuan);
        rbPerempuan.setForeground(new java.awt.Color(255, 255, 255));
        rbPerempuan.setText("P");

        labNamaPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPegawai.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPegawai.setText("Nama Pegawai");

        labTglLahirPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglLahirPegawai.setForeground(new java.awt.Color(255, 255, 255));
        labTglLahirPegawai.setText("Tanggal Lahir Pegawai");

        labIDPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDPegawai.setForeground(new java.awt.Color(255, 255, 255));
        labIDPegawai.setText("ID Pegawai");

        labAlamatPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labAlamatPegawai.setForeground(new java.awt.Color(255, 255, 255));
        labAlamatPegawai.setText("Alamat Pegawai");

        labTeleponPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTeleponPegawai.setForeground(new java.awt.Color(255, 255, 255));
        labTeleponPegawai.setText("Telepon Pegawai");

        labJKPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJKPegawai.setForeground(new java.awt.Color(255, 255, 255));
        labJKPegawai.setText("Gender Pegawai");

        txNamaPegawai.setBackground(new java.awt.Color(255, 255, 255));
        txNamaPegawai.setForeground(new java.awt.Color(0, 0, 0));

        txAlamatPegawai.setBackground(new java.awt.Color(255, 255, 255));
        txAlamatPegawai.setForeground(new java.awt.Color(0, 0, 0));

        txTeleponPegawai.setBackground(new java.awt.Color(255, 255, 255));
        txTeleponPegawai.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(246, 182, 112));
        jLabel7.setText("Data Pegawai");

        cbIDPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPegawaiActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 0, 0));
        btnClear.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear.setForeground(new java.awt.Color(0, 0, 0));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txTglLahirPegawai.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labTeleponPegawai)
                            .addComponent(labJKPegawai))
                        .addGap(126, 126, 126)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbLaki)
                                .addGap(27, 27, 27)
                                .addComponent(rbPerempuan))
                            .addComponent(txTeleponPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(btnUpdate)
                        .addGap(48, 48, 48)
                        .addComponent(btnDelete)
                        .addGap(30, 30, 30)
                        .addComponent(btnClear))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNamaPegawai)
                            .addComponent(labIDPegawai))
                        .addGap(145, 145, 145)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbIDPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labAlamatPegawai)
                            .addComponent(labTglLahirPegawai))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txTglLahirPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txAlamatPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(380, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDPegawai)
                    .addComponent(cbIDPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labNamaPegawai))
                    .addComponent(txNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labTglLahirPegawai)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(labAlamatPegawai))
                            .addComponent(txAlamatPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(labTeleponPegawai))
                            .addComponent(txTeleponPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbLaki)
                            .addComponent(rbPerempuan)
                            .addComponent(labJKPegawai))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete)
                            .addComponent(btnClear)))
                    .addComponent(txTglLahirPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(145, 145, 145))
        );

        rbLaki.getAccessibleContext().setAccessibleName("L\nP");

        jTabbedPane1.addTab("Master", jPanel1);

        tbPegawai.setBackground(new java.awt.Color(38, 42, 65));
        tbPegawai.setForeground(new java.awt.Color(255, 255, 255));
        tbPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Pegawai", "Nama Pegawai", "Tanggal Lahir Pegawai", "Alamat Pegawai", "Telepon Pegawai", "Gender Pegawai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPegawai.setGridColor(new java.awt.Color(255, 255, 255));
        tbPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPegawaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPegawai);

        jTabbedPane1.addTab("Tabel", jScrollPane2);

        jPanel2.setBackground(new java.awt.Color(38, 42, 65));

        btnClear1.setBackground(new java.awt.Color(255, 0, 0));
        btnClear1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(0, 0, 0));
        btnClear1.setText("Clear");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        btnTambahData.setBackground(new java.awt.Color(0, 255, 0));
        btnTambahData.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnTambahData.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahData.setText("Tambah Data");
        btnTambahData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahDataActionPerformed(evt);
            }
        });

        labNamaPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPegawai1.setText("Nama Pegawai");

        txNamaPegawai1.setBackground(new java.awt.Color(255, 255, 255));
        txNamaPegawai1.setForeground(new java.awt.Color(0, 0, 0));

        labTglLahirPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglLahirPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        labTglLahirPegawai1.setText("Tanggal Lahir Pegawai");

        labAlamatPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labAlamatPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        labAlamatPegawai1.setText("Alamat Pegawai");

        txAlamatPegawai1.setBackground(new java.awt.Color(255, 255, 255));
        txAlamatPegawai1.setForeground(new java.awt.Color(0, 0, 0));

        labTeleponPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTeleponPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        labTeleponPegawai1.setText("Telepon Pegawai");

        txTeleponPegawai1.setBackground(new java.awt.Color(255, 255, 255));
        txTeleponPegawai1.setForeground(new java.awt.Color(0, 0, 0));

        labJKPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labJKPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        labJKPegawai1.setText("Gender Pegawai");

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(246, 182, 112));
        jLabel8.setText("Data Pegawai");

        rbLaki1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup2.add(rbLaki1);
        rbLaki1.setForeground(new java.awt.Color(255, 255, 255));
        rbLaki1.setText("L");

        rbPerempuan1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup2.add(rbPerempuan1);
        rbPerempuan1.setForeground(new java.awt.Color(255, 255, 255));
        rbPerempuan1.setText("P");

        txTglLahirPegawai1.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(btnTambahData)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labNamaPegawai1)
                                .addGap(146, 146, 146)
                                .addComponent(txNamaPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(labTglLahirPegawai1)
                                .addGap(77, 77, 77)
                                .addComponent(txTglLahirPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labAlamatPegawai1)
                                .addGap(135, 135, 135)
                                .addComponent(txAlamatPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(labTeleponPegawai1)
                                        .addGap(127, 127, 127))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(labJKPegawai1)
                                        .addGap(135, 135, 135)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txTeleponPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rbLaki1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbPerempuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(378, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(labNamaPegawai1))
                    .addComponent(txNamaPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labTglLahirPegawai1)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labAlamatPegawai1)
                            .addComponent(txAlamatPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labTeleponPegawai1)
                            .addComponent(txTeleponPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labJKPegawai1)
                            .addComponent(rbLaki1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbPerempuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClear1)
                            .addComponent(btnTambahData)))
                    .addComponent(txTglLahirPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114))
        );

        jTabbedPane1.addTab("Tambah Data", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    
    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        showTabel();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnTambahDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDataActionPerformed
        // TODO add your handling code here:
        try {
            String gender;
            if(rbLaki1.isSelected()) {
                gender = "L";
            } else {
                gender = "P";
            }
            
            conn = Koneksi.getKoneksi();
            
            String formatDate = "dd MMM yy";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = df.format(txTglLahirPegawai1.getDate());
            
            CallableStatement call = conn.prepareCall("{call INS_PEGAWAI(?,?,?,?,?)}");
            call.setString(1, txNamaPegawai1.getText());
            call.setString(2, tanggal);
            call.setString(3, txAlamatPegawai1.getText());
            call.setString(4, txTeleponPegawai1.getText());
            call.setString(5, gender);
            call.execute();
            
//            SQL = "INSERT INTO pegawai (id_pegawai, nama_pegawai, tgl_lahir_pegawai, almt_pegawai, telp_pegawai, jk_pegawai) VALUES ("
//            + " gen_id('pegawai'), "
//            + "'" + txNamaPegawai1.getText() + "',"
//            + "'" + txTglLahirPegawai1.getText() + "',"
//            + "'" + txAlamatPegawai1.getText() + "',"
//            + "'" + txTeleponPegawai1.getText() + "',"
//            + "'" + gender + "')";
//            cmd = conn.createStatement();
//            cmd.executeUpdate(SQL);
            JOptionPane.showMessageDialog(this, "Success");

            clear();
            showTabel();
            loadIDPegawai();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
            if(e.getLocalizedMessage() == null) {
                System.out.println("");
            }
        }
    }//GEN-LAST:event_btnTambahDataActionPerformed

    private void clear() {
        cbIDPegawai.setSelectedIndex(0);
        txNamaPegawai.setText("");
        txTglLahirPegawai.setDate(null);
        txAlamatPegawai.setText("");
        txTeleponPegawai.setText("");
        buttonGroup1.clearSelection();
        
        txNamaPegawai1.setText("");
        txTglLahirPegawai1.setDate(null);
        txAlamatPegawai1.setText("");
        txTeleponPegawai1.setText("");
        buttonGroup2.clearSelection();
    }
    
    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            String gender;
            if(rbLaki.isSelected()) {
                gender = "L";
            } else {
                gender = "P";
            }
            
            String formatDate = "dd MMM yyyy";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = String.valueOf(df.format(txTglLahirPegawai.getDate()));
            
            CallableStatement call = conn.prepareCall("{call UPD_PEGAWAI(?,?,?)}");
            call.setString(1, "nama");
            call.setString(2, cbIDPegawai.getSelectedItem().toString());
            call.setString(3, txNamaPegawai.getText());
            call.execute();
            
            CallableStatement cal2 = conn.prepareCall("{call UPD_PEGAWAI(?,?,?)}");
            cal2.setString(1, "lahir");
            cal2.setString(2, cbIDPegawai.getSelectedItem().toString());
            cal2.setString(3, tanggal);
            cal2.execute();
            
            CallableStatement cal3 = conn.prepareCall("{call UPD_PEGAWAI(?,?,?)}");
            cal3.setString(1, "almt");
            cal3.setString(2, cbIDPegawai.getSelectedItem().toString());
            cal3.setString(3, txAlamatPegawai.getText());
            cal3.execute();
            
            CallableStatement cal4 = conn.prepareCall("{call UPD_PEGAWAI(?,?,?)}");
            cal4.setString(1, "telepon");
            cal4.setString(2, cbIDPegawai.getSelectedItem().toString());
            cal4.setString(3, txTeleponPegawai.getText());
            cal4.execute();
            
            CallableStatement cal5 = conn.prepareCall("{call UPD_PEGAWAI(?,?,?)}");
            cal5.setString(1, "jk");
            cal5.setString(2, cbIDPegawai.getSelectedItem().toString());
            cal5.setString(3, gender);
            cal5.execute();
            
//            SQL = "UPDATE pegawai SET "
//                    + "nama_pegawai = " + "'" + txNamaPegawai.getText() + "',"
//                    + "tgl_lahir_pegawai = " + "'" + txTglLahirPegawai.getText() + "',"
//                    + "almt_pegawai = " + "'" + txAlamatPegawai.getText() + "',"
//                    + "telp_pegawai = " + "'" + txTeleponPegawai.getText() + "',"
//                    + "jk_pegawai = " + "'" + gender + "'"
//                    + "WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "'";
//            cmd = conn.createStatement();
//            cmd.executeUpdate(SQL);

            JOptionPane.showMessageDialog(this, "Success");
            showTabel();
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            int jawab = JOptionPane.showConfirmDialog(this, "Silakan Konfirmasi?");
            
            switch(jawab){
                case JOptionPane.YES_OPTION: 
                    CallableStatement call = conn.prepareCall("{call DEL_PEGAWAI(?)}");
                    call.setString(1, cbIDPegawai.getSelectedItem().toString());
                    call.execute();
                    
//                    SQL = "DELETE pegawai WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "'";
//                    cmd = conn.createStatement();
//                    cmd.executeUpdate(SQL);
                    
                    JOptionPane.showMessageDialog(this, "Success");
                    showTabel();
                    loadIDPegawai();
                    clear();
                    break;
                    
                case JOptionPane.NO_OPTION:
                    break;
                    
                case JOptionPane.CANCEL_OPTION:
                    break;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tbPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPegawaiMouseClicked
        // TODO add your handling code here:
        int index = tbPegawai.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            
            cbIDPegawai.setSelectedItem(tbPegawai.getValueAt(index, 0).toString());
            txNamaPegawai.setText(tbPegawai.getValueAt(index, 1).toString());
            txAlamatPegawai.setText(tbPegawai.getValueAt(index, 3).toString());
            txTeleponPegawai.setText(tbPegawai.getValueAt(index, 4).toString());
            
            if(tbPegawai.getValueAt(index, 5).toString().equals("L")) {
                rbLaki.setSelected(true);
            } else {
                rbPerempuan.setSelected(true);
            }
            
            try {
                String date = tbPegawai.getValueAt(index, 2).toString();
                java.util.Date date2 = new SimpleDateFormat("dd MMM yy").parse(date);
                txTglLahirPegawai.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_tbPegawaiMouseClicked

    private void cbIDPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPegawaiActionPerformed
        // TODO add your handling code here:
        txNamaPegawai.setText(cekPegawai(1));
        try {
                String date = cekPegawai(2);
                Date date2 = new SimpleDateFormat("dd MMM yy").parse(date);
                txTglLahirPegawai.setDate(date2);
        } catch (Exception e) {
                if("java.lang.NullPointerException".equals(e.getLocalizedMessage())) {
                    System.out.println("");
                }
        }
        txAlamatPegawai.setText(cekPegawai(3));
        txTeleponPegawai.setText(cekPegawai(4));
        if("L".equals(cekPegawai(5))) {
            rbLaki.setSelected(true);
        } else if("P".equals(cekPegawai(5))) {
            rbPerempuan.setSelected(true);
        }
    }//GEN-LAST:event_cbIDPegawaiActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnTambahData;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbIDPegawai;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labAlamatPegawai;
    private javax.swing.JLabel labAlamatPegawai1;
    private javax.swing.JLabel labIDPegawai;
    private javax.swing.JLabel labJKPegawai;
    private javax.swing.JLabel labJKPegawai1;
    private javax.swing.JLabel labNamaPegawai;
    private javax.swing.JLabel labNamaPegawai1;
    private javax.swing.JLabel labTeleponPegawai;
    private javax.swing.JLabel labTeleponPegawai1;
    private javax.swing.JLabel labTglLahirPegawai;
    private javax.swing.JLabel labTglLahirPegawai1;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbLaki1;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JRadioButton rbPerempuan1;
    private javax.swing.JTable tbPegawai;
    private javax.swing.JTextField txAlamatPegawai;
    private javax.swing.JTextField txAlamatPegawai1;
    private javax.swing.JTextField txNamaPegawai;
    private javax.swing.JTextField txNamaPegawai1;
    private javax.swing.JTextField txTeleponPegawai;
    private javax.swing.JTextField txTeleponPegawai1;
    private com.toedter.calendar.JDateChooser txTglLahirPegawai;
    private com.toedter.calendar.JDateChooser txTglLahirPegawai1;
    // End of variables declaration//GEN-END:variables
}
