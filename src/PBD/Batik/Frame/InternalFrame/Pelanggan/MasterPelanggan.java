/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PBD.Batik.Frame.InternalFrame.Pelanggan;

import PBD.Batik.Frame.Koneksi;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akbar
 */
public class MasterPelanggan extends javax.swing.JInternalFrame {

    /**
     * Creates new form MasterPegawai
     */
    
    Connection conn;
    Statement cmd;
    ResultSet rs;
    DefaultTableModel tmPelanggan;
    String SQL;
    
    Statement cmd1;
    ResultSet rs1;
    
    public MasterPelanggan() {
        initComponents();
        
        loadIDPelanggan();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
    
    private void loadIDPelanggan(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM pelanggan";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDPelanggan.removeAllItems();
            cbIDPelanggan.addItem("Pilih ID Pelanggan");
            while(rs.next()){
                String cid = rs.getString("id_pelanggan");
                cbIDPelanggan.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void showTabel() {
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT id_pelanggan, nama_pelanggan, jk_pelanggan, TO_CHAR(tgl_lahir_pelanggan, 'DD MON YYYY') as TGL_LAHIR_PELANGGAN, telp_pelanggan, "
                    + "provinsi_pelanggan, kabupaten_pelanggan, kecamatan_pelanggan, kelurahan_pelanggan, almt_pelanggan FROM pelanggan ORDER BY id_pelanggan ASC");
            
            //Setting kolom dari DefaultTableModel
            tmPelanggan = new DefaultTableModel(new String[] {"ID_PELANGGAN", "NAMA_PELANGGAN", "JK_PELANGGAN", "TGL_LAHIR_PELANGGAN", "TELP_PELANGGAN",
                "PROVINSI_PELANGGAN", "KABUPATEN_PELANGGAN", "KECAMATAN_PELANGGAN", "KELURAHAN_PELANGGAN", "ALMT_PELANGGAN"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmPelanggan.addRow(new Object[] {rs.getString("id_pelanggan"), rs.getString("nama_pelanggan"), rs.getString("jk_pelanggan"), 
                        rs.getString("tgl_lahir_pelanggan"), rs.getString("telp_pelanggan"), rs.getString("provinsi_pelanggan"), rs.getString("kabupaten_pelanggan"), 
                        rs.getString("kecamatan_pelanggan"), rs.getString("kelurahan_pelanggan"), rs.getString("almt_pelanggan")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbPelanggan.setDefaultEditor(Object.class, null);
            
            tbPelanggan.setModel(tmPelanggan);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private String cekPelanggan(int angka) {
        String pelanggan = null;
        try {
            switch(angka) {
                case 1:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT nama_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pelanggan = rs1.getString("nama_pelanggan");
                        }
                    }
                break;
                
                case 2:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT jk_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pelanggan = rs1.getString("jk_pelanggan");
                        }
                    }
                break;
                
                case 3:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT TO_CHAR(tgl_lahir_pelanggan, 'DD MON YYYY') as tgl_lahir_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            pelanggan = rs1.getString("tgl_lahir_pelanggan");
                        }
                    }
                break;
                
                case 4:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT telp_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pelanggan = rs1.getString("telp_pelanggan");
                        }
                    }
                break;
                
                case 5:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT provinsi_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pelanggan = rs1.getString("provinsi_pelanggan");
                        }
                    }
                break;
                
                case 6:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT kabupaten_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pelanggan = rs1.getString("kabupaten_pelanggan");
                        }
                    }
                break;
                
                case 7:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT kecamatan_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pelanggan = rs1.getString("kecamatan_pelanggan");
                        }
                    }
                break;
                
                case 8:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT kelurahan_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {
                        
                    } else {
                        while(rs1.next()) {
                            pelanggan = rs1.getString("kelurahan_pelanggan");
                        }
                    }
                break;
                
                case 9:
                    cmd1 = conn.createStatement();
                    rs1 = cmd1.executeQuery("SELECT almt_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
                    if (rs1 == null) {

                    } else {
                        while(rs1.next()) {
                            pelanggan = rs1.getString("almt_pelanggan");
                        }
                    }
                break;
            }
            
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pelanggan;
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
        labAlamatPelanggan = new javax.swing.JLabel();
        labTglLahirPelanggan = new javax.swing.JLabel();
        txKabupaten = new javax.swing.JTextField();
        Kabupaten = new javax.swing.JLabel();
        Kecamatan = new javax.swing.JLabel();
        txKecamatan = new javax.swing.JTextField();
        Kelurahan = new javax.swing.JLabel();
        txKelurahan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        labIDPelanggan = new javax.swing.JLabel();
        labNamaPelanggan = new javax.swing.JLabel();
        txNamaPelanggan = new javax.swing.JTextField();
        labTeleponPelanggan = new javax.swing.JLabel();
        txTeleponPelanggan = new javax.swing.JTextField();
        Provinsi = new javax.swing.JLabel();
        txProvinsi = new javax.swing.JTextField();
        AlamatPelanggan = new javax.swing.JLabel();
        txAlamatPelanggan = new javax.swing.JTextField();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        cbIDPelanggan = new javax.swing.JComboBox<>();
        btnClear = new javax.swing.JButton();
        txTglLahirPelanggan = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPelanggan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnClear1 = new javax.swing.JButton();
        btnMenuTambah = new javax.swing.JButton();
        labAlamatPelanggan1 = new javax.swing.JLabel();
        labTglLahirPelanggan1 = new javax.swing.JLabel();
        txKabupaten1 = new javax.swing.JTextField();
        Kabupaten1 = new javax.swing.JLabel();
        Kecamatan1 = new javax.swing.JLabel();
        txKecamatan1 = new javax.swing.JTextField();
        Kelurahan1 = new javax.swing.JLabel();
        txKelurahan1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        labNamaPelanggan1 = new javax.swing.JLabel();
        txNamaPelanggan1 = new javax.swing.JTextField();
        labTeleponPelanggan1 = new javax.swing.JLabel();
        txTeleponPelanggan1 = new javax.swing.JTextField();
        Provinsi1 = new javax.swing.JLabel();
        txProvinsi1 = new javax.swing.JTextField();
        AlamatPelanggan1 = new javax.swing.JLabel();
        txAlamatPelanggan1 = new javax.swing.JTextField();
        rbLaki1 = new javax.swing.JRadioButton();
        rbPerempuan1 = new javax.swing.JRadioButton();
        txTglLahirPelanggan1 = new com.toedter.calendar.JDateChooser();

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

        labAlamatPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labAlamatPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        labAlamatPelanggan.setText("Gender Pelanggan");

        labTglLahirPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglLahirPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        labTglLahirPelanggan.setText("Tanggal Lahir Pelanggan");

        txKabupaten.setBackground(new java.awt.Color(255, 255, 255));
        txKabupaten.setForeground(new java.awt.Color(0, 0, 0));

        Kabupaten.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Kabupaten.setForeground(new java.awt.Color(255, 255, 255));
        Kabupaten.setText("Kabupaten");

        Kecamatan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Kecamatan.setForeground(new java.awt.Color(255, 255, 255));
        Kecamatan.setText("Kecamatan");

        txKecamatan.setBackground(new java.awt.Color(255, 255, 255));
        txKecamatan.setForeground(new java.awt.Color(0, 0, 0));

        Kelurahan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Kelurahan.setForeground(new java.awt.Color(255, 255, 255));
        Kelurahan.setText("Kelurahan");

        txKelurahan.setBackground(new java.awt.Color(255, 255, 255));
        txKelurahan.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(246, 182, 112));
        jLabel7.setText("Data Pelanggan");

        labIDPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        labIDPelanggan.setText("ID Pelanggan");

        labNamaPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPelanggan.setText("Nama Pelanggan");

        txNamaPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        txNamaPelanggan.setForeground(new java.awt.Color(0, 0, 0));

        labTeleponPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTeleponPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        labTeleponPelanggan.setText("Telepon Pelanggan");

        txTeleponPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        txTeleponPelanggan.setForeground(new java.awt.Color(0, 0, 0));

        Provinsi.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Provinsi.setForeground(new java.awt.Color(255, 255, 255));
        Provinsi.setText("Provinsi");

        txProvinsi.setBackground(new java.awt.Color(255, 255, 255));
        txProvinsi.setForeground(new java.awt.Color(0, 0, 0));

        AlamatPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        AlamatPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        AlamatPelanggan.setText("Alamat Pelanggan");

        txAlamatPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        txAlamatPelanggan.setForeground(new java.awt.Color(0, 0, 0));

        rbLaki.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(rbLaki);
        rbLaki.setForeground(new java.awt.Color(255, 255, 255));
        rbLaki.setText("L");

        rbPerempuan.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(rbPerempuan);
        rbPerempuan.setForeground(new java.awt.Color(255, 255, 255));
        rbPerempuan.setText("P");

        cbIDPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPelangganActionPerformed(evt);
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

        txTglLahirPelanggan.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(labIDPelanggan))
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labTeleponPelanggan)
                                .addGap(108, 108, 108)
                                .addComponent(txTeleponPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Provinsi)
                                .addGap(208, 208, 208)
                                .addComponent(txProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(labTglLahirPelanggan)
                                        .addGap(58, 58, 58))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(labAlamatPelanggan)
                                        .addGap(116, 116, 116)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbLaki)
                                        .addGap(27, 27, 27)
                                        .addComponent(rbPerempuan))
                                    .addComponent(txTglLahirPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(AlamatPelanggan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txAlamatPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Kelurahan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Kabupaten)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txKabupaten, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Kecamatan)
                                .addGap(183, 183, 183)
                                .addComponent(txKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labNamaPelanggan)
                            .addGap(127, 127, 127)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbIDPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(172, 370, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(btnUpdate)
                .addGap(48, 48, 48)
                .addComponent(btnDelete)
                .addGap(47, 47, 47)
                .addComponent(btnClear)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDPelanggan)
                    .addComponent(cbIDPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labNamaPelanggan))
                    .addComponent(txNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbLaki)
                        .addComponent(rbPerempuan))
                    .addComponent(labAlamatPelanggan))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labTglLahirPelanggan)
                    .addComponent(txTglLahirPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labTeleponPelanggan))
                    .addComponent(txTeleponPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(Provinsi))
                    .addComponent(txProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Kabupaten)
                    .addComponent(txKabupaten, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Kecamatan)
                    .addComponent(txKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Kelurahan)
                    .addComponent(txKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlamatPelanggan)
                    .addComponent(txAlamatPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Master", jPanel1);

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tbPelanggan.setBackground(new java.awt.Color(38, 42, 65));
        tbPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        tbPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pelanggan", "Nama Pelanggan", "Gender Pelanggan", "Tgl Lahir Pelanggan", "Telepon Pelanggan", "Provinsi", "Kabupaten", "Kecamatan", "Kelurahan", "Alamat Pelanggan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPelanggan.setGridColor(new java.awt.Color(255, 255, 255));
        tbPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPelangganMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPelanggan);

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

        btnMenuTambah.setBackground(new java.awt.Color(0, 255, 0));
        btnMenuTambah.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnMenuTambah.setForeground(new java.awt.Color(0, 0, 0));
        btnMenuTambah.setText("Tambah Data");
        btnMenuTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuTambahActionPerformed(evt);
            }
        });

        labAlamatPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labAlamatPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        labAlamatPelanggan1.setText("Gender Pelanggan");

        labTglLahirPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglLahirPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        labTglLahirPelanggan1.setText("Tanggal Lahir Pelanggan");

        txKabupaten1.setBackground(new java.awt.Color(255, 255, 255));
        txKabupaten1.setForeground(new java.awt.Color(0, 0, 0));

        Kabupaten1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Kabupaten1.setForeground(new java.awt.Color(255, 255, 255));
        Kabupaten1.setText("Kabupaten");

        Kecamatan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Kecamatan1.setForeground(new java.awt.Color(255, 255, 255));
        Kecamatan1.setText("Kecamatan");

        txKecamatan1.setBackground(new java.awt.Color(255, 255, 255));
        txKecamatan1.setForeground(new java.awt.Color(0, 0, 0));

        Kelurahan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Kelurahan1.setForeground(new java.awt.Color(255, 255, 255));
        Kelurahan1.setText("Kelurahan");

        txKelurahan1.setBackground(new java.awt.Color(255, 255, 255));
        txKelurahan1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(246, 182, 112));
        jLabel8.setText("Data Pelanggan");

        labNamaPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPelanggan1.setText("Nama Pelanggan");

        txNamaPelanggan1.setBackground(new java.awt.Color(255, 255, 255));
        txNamaPelanggan1.setForeground(new java.awt.Color(0, 0, 0));

        labTeleponPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTeleponPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        labTeleponPelanggan1.setText("Telepon Pelanggan");

        txTeleponPelanggan1.setBackground(new java.awt.Color(255, 255, 255));
        txTeleponPelanggan1.setForeground(new java.awt.Color(0, 0, 0));

        Provinsi1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Provinsi1.setForeground(new java.awt.Color(255, 255, 255));
        Provinsi1.setText("Provinsi");

        txProvinsi1.setBackground(new java.awt.Color(255, 255, 255));
        txProvinsi1.setForeground(new java.awt.Color(0, 0, 0));

        AlamatPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        AlamatPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        AlamatPelanggan1.setText("Alamat Pelanggan");

        txAlamatPelanggan1.setBackground(new java.awt.Color(255, 255, 255));
        txAlamatPelanggan1.setForeground(new java.awt.Color(0, 0, 0));

        rbLaki1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup2.add(rbLaki1);
        rbLaki1.setForeground(new java.awt.Color(255, 255, 255));
        rbLaki1.setText("L");

        rbPerempuan1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup2.add(rbPerempuan1);
        rbPerempuan1.setForeground(new java.awt.Color(255, 255, 255));
        rbPerempuan1.setText("P");

        txTglLahirPelanggan1.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labTglLahirPelanggan1)
                        .addGap(58, 58, 58)
                        .addComponent(txTglLahirPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(labTeleponPelanggan1)
                            .addGap(108, 108, 108)
                            .addComponent(txTeleponPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(Provinsi1)
                            .addGap(208, 208, 208)
                            .addComponent(txProvinsi1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(Kelurahan1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txKelurahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(Kabupaten1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txKabupaten1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(Kecamatan1)
                            .addGap(183, 183, 183)
                            .addComponent(txKecamatan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(AlamatPelanggan1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txAlamatPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(labNamaPelanggan1)
                                    .addGap(127, 127, 127))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(labAlamatPelanggan1)
                                    .addGap(116, 116, 116)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(rbLaki1)
                                    .addGap(27, 27, 27)
                                    .addComponent(rbPerempuan1))
                                .addComponent(txNamaPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(370, 370, 370))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(btnMenuTambah)
                .addGap(18, 18, 18)
                .addComponent(btnClear1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labNamaPelanggan1))
                    .addComponent(txNamaPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labAlamatPelanggan1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbLaki1)
                        .addComponent(rbPerempuan1)))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labTglLahirPelanggan1)
                    .addComponent(txTglLahirPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labTeleponPelanggan1))
                    .addComponent(txTeleponPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(Provinsi1))
                    .addComponent(txProvinsi1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Kabupaten1)
                    .addComponent(txKabupaten1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Kecamatan1)
                    .addComponent(txKecamatan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Kelurahan1)
                    .addComponent(txKelurahan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlamatPelanggan1)
                    .addComponent(txAlamatPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear1)
                    .addComponent(btnMenuTambah))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tambah Data", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnMenuTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuTambahActionPerformed
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
            String tanggal = String.valueOf(df.format(txTglLahirPelanggan1.getDate()));
            
            CallableStatement call = conn.prepareCall("{call INS_PELANGGAN(?,?,?,?,?,?,?,?,?)}");
            call.setString(1, txNamaPelanggan1.getText());
            call.setString(2, gender);
            call.setString(3, tanggal);
            call.setString(4, txTeleponPelanggan1.getText());
            call.setString(5, txProvinsi1.getText());
            call.setString(6, txKabupaten1.getText());
            call.setString(7, txKecamatan1.getText());
            call.setString(8, txKelurahan1.getText());
            call.setString(9, txAlamatPelanggan1.getText());
            call.execute();
            
//            SQL = "INSERT INTO pelanggan (id_pelanggan, nama_pelanggan, jk_pelanggan, tgl_lahir_pelanggan, telp_pelanggan, "
//                    + "provinsi_pelanggan, kabupaten_pelanggan, kecamatan_pelanggan, kelurahan_pelanggan, almt_pelanggan) VALUES ("
//            + " gen_id('pelanggan'), "
//            + "'" + txNamaPelanggan1.getText() + "',"
//            + "'" + gender + "',"
//            + "'" + txTglLahirPelanggan1.getText() + "',"
//            + "'" + txTeleponPelanggan1.getText() + "',"
//            + "'" + txProvinsi1.getText() + "',"
//            + "'" + txKabupaten1.getText() + "',"
//            + "'" + txKecamatan1.getText() + "',"
//            + "'" + txKelurahan1.getText() + "',"
//            + "'" + txAlamatPelanggan1.getText() + "')";
//            cmd = conn.createStatement();
//            cmd.executeUpdate(SQL);

            JOptionPane.showMessageDialog(this, "Success");

            clear();
            showTabel();
            loadIDPelanggan();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnMenuTambahActionPerformed

    private void clear() {
        cbIDPelanggan.setSelectedIndex(0);
        txNamaPelanggan.setText("");
        buttonGroup1.clearSelection();
        txTglLahirPelanggan.setDate(null);
        txTeleponPelanggan.setText("");
        txProvinsi.setText("");
        txKabupaten.setText("");
        txKecamatan.setText("");
        txKelurahan.setText("");
        txAlamatPelanggan.setText("");
        
        txNamaPelanggan1.setText("");
        buttonGroup2.clearSelection();
        txTglLahirPelanggan1.setDate(null);
        txTeleponPelanggan1.setText("");
        txProvinsi1.setText("");
        txKabupaten1.setText("");
        txKecamatan1.setText("");
        txKelurahan1.setText("");
        txAlamatPelanggan1.setText("");
    }
    
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        showTabel();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            String gender;
            if(rbLaki.isSelected()) {
                gender = "L";
            } else {
                gender = "P";
            }
            
            String formatDate = "dd MMM yy";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = String.valueOf(df.format(txTglLahirPelanggan.getDate()));
            
            CallableStatement call = conn.prepareCall("{call UPD_PELANGGAN(?,?,?)}");
            call.setString(1, "nama");
            call.setString(2, cbIDPelanggan.getSelectedItem().toString());
            call.setString(3, txNamaPelanggan.getText());
            call.execute();
            
            CallableStatement cal2 = conn.prepareCall("{call UPD_PELANGGAN(?,?,?)}");
            cal2.setString(1, "jk");
            cal2.setString(2, cbIDPelanggan.getSelectedItem().toString());
            cal2.setString(3, gender);
            cal2.execute();
            
            CallableStatement cal3 = conn.prepareCall("{call UPD_PELANGGAN(?,?,?)}");
            cal3.setString(1, "lahir");
            cal3.setString(2, cbIDPelanggan.getSelectedItem().toString());
            cal3.setString(3, tanggal);
            cal3.execute();
            
            CallableStatement cal4 = conn.prepareCall("{call UPD_PELANGGAN(?,?,?)}");
            cal4.setString(1, "telepon");
            cal4.setString(2, cbIDPelanggan.getSelectedItem().toString());
            cal4.setString(3, txTeleponPelanggan.getText());
            cal4.execute();
            
            CallableStatement cal5 = conn.prepareCall("{call UPD_PELANGGAN(?,?,?)}");
            cal5.setString(1, "prov");
            cal5.setString(2, cbIDPelanggan.getSelectedItem().toString());
            cal5.setString(3, txProvinsi.getText());
            cal5.execute();
            
            CallableStatement cal6 = conn.prepareCall("{call UPD_PELANGGAN(?,?,?)}");
            cal6.setString(1, "kab");
            cal6.setString(2, cbIDPelanggan.getSelectedItem().toString());
            cal6.setString(3, txKabupaten.getText());
            cal6.execute();
            
            CallableStatement cal7 = conn.prepareCall("{call UPD_PELANGGAN(?,?,?)}");
            cal7.setString(1, "kec");
            cal7.setString(2, cbIDPelanggan.getSelectedItem().toString());
            cal7.setString(3, txKecamatan.getText());
            cal7.execute();
            
            CallableStatement cal8 = conn.prepareCall("{call UPD_PELANGGAN(?,?,?)}");
            cal8.setString(1, "kel");
            cal8.setString(2, cbIDPelanggan.getSelectedItem().toString());
            cal8.setString(3, txKelurahan.getText());
            cal8.execute();
            
            CallableStatement cal9 = conn.prepareCall("{call UPD_PELANGGAN(?,?,?)}");
            cal9.setString(1, "almt");
            cal9.setString(2, cbIDPelanggan.getSelectedItem().toString());
            cal9.setString(3, txAlamatPelanggan.getText());
            cal9.execute();
            
//            SQL = "UPDATE pelanggan SET "
//                    + "nama_pelanggan = " + "'" + txNamaPelanggan.getText() + "',"
//                    + "jk_pelanggan = " + "'" + gender + "',"
//                    + "tgl_lahir_pelanggan = " + "'" + txTglLahirPelanggan.getText() + "',"
//                    + "telp_pelanggan = " + "'" + txTeleponPelanggan.getText() + "',"
//                    + "provinsi_pelanggan = " + "'" + txProvinsi.getText() + "',"
//                    + "kabupaten_pelanggan = " + "'" + txKabupaten.getText() + "',"
//                    + "kecamatan_pelanggan = " + "'" + txKecamatan.getText() + "',"
//                    + "kelurahan_pelanggan = " + "'" + txKelurahan.getText() + "',"
//                    + "almt_pelanggan = " + "'" + txAlamatPelanggan.getText() + "'"
//                    + "WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "'";
//            cmd = conn.createStatement();
//            cmd.executeUpdate(SQL);
            JOptionPane.showMessageDialog(this, "Success");
            showTabel();
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            int jawab = JOptionPane.showConfirmDialog(this, "Silakan Konfirmasi?");
            
            switch(jawab){
                case JOptionPane.YES_OPTION:
                    CallableStatement call = conn.prepareCall("{call DEL_PELANGGAN(?)}");
                    call.setString(1, cbIDPelanggan.getSelectedItem().toString());
                    call.execute();
                    
//                    SQL = "DELETE pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "'";
//                    cmd = conn.createStatement();
//                    cmd.executeUpdate(SQL);
                    JOptionPane.showMessageDialog(this, "Success");
                    showTabel();
                    loadIDPelanggan();
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

    private void tbPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPelangganMouseClicked
        // TODO add your handling code here:
        int index = tbPelanggan.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            
            cbIDPelanggan.setSelectedItem(tbPelanggan.getValueAt(index, 0).toString());
            txNamaPelanggan.setText(tbPelanggan.getValueAt(index, 1).toString());
            if(tbPelanggan.getValueAt(index, 2).toString().equals("L")) {
                rbLaki.setSelected(true);
            } else {
                rbPerempuan.setSelected(true);
            }
            
            try {
                String date = tbPelanggan.getValueAt(index, 3).toString();
                java.util.Date date2 = new SimpleDateFormat("dd MMM yy").parse(date);
                txTglLahirPelanggan.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            
            txTeleponPelanggan.setText(tbPelanggan.getValueAt(index, 4).toString());
            txProvinsi.setText(tbPelanggan.getValueAt(index, 5).toString());
            txKabupaten.setText(tbPelanggan.getValueAt(index, 6).toString());
            txKecamatan.setText(tbPelanggan.getValueAt(index, 7).toString());
            txKelurahan.setText(tbPelanggan.getValueAt(index, 8).toString());
            txAlamatPelanggan.setText(tbPelanggan.getValueAt(index, 9).toString());
        }
    }//GEN-LAST:event_tbPelangganMouseClicked

    private void cbIDPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPelangganActionPerformed
        // TODO add your handling code here:
        txNamaPelanggan.setText(cekPelanggan(1));
        if("L".equals(cekPelanggan(2))) {
            rbLaki.setSelected(true);
        } else if("P".equals(cekPelanggan(2))) {
            rbPerempuan.setSelected(true);
        }
        try {
                String date = cekPelanggan(3);
                java.util.Date date2 = new SimpleDateFormat("dd MMM yy").parse(date);
                txTglLahirPelanggan.setDate(date2);
        } catch (Exception e) {
                if("java.lang.NullPointerException".equals(e.getLocalizedMessage())) {
                    System.out.println("");
                }
        }
        txTeleponPelanggan.setText(cekPelanggan(4));
        txProvinsi.setText(cekPelanggan(5));
        txKabupaten.setText(cekPelanggan(6));
        txKecamatan.setText(cekPelanggan(7));
        txKelurahan.setText(cekPelanggan(8));
        txAlamatPelanggan.setText(cekPelanggan(9));
    }//GEN-LAST:event_cbIDPelangganActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AlamatPelanggan;
    private javax.swing.JLabel AlamatPelanggan1;
    private javax.swing.JLabel Kabupaten;
    private javax.swing.JLabel Kabupaten1;
    private javax.swing.JLabel Kecamatan;
    private javax.swing.JLabel Kecamatan1;
    private javax.swing.JLabel Kelurahan;
    private javax.swing.JLabel Kelurahan1;
    private javax.swing.JLabel Provinsi;
    private javax.swing.JLabel Provinsi1;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnMenuTambah;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbIDPelanggan;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labAlamatPelanggan;
    private javax.swing.JLabel labAlamatPelanggan1;
    private javax.swing.JLabel labIDPelanggan;
    private javax.swing.JLabel labNamaPelanggan;
    private javax.swing.JLabel labNamaPelanggan1;
    private javax.swing.JLabel labTeleponPelanggan;
    private javax.swing.JLabel labTeleponPelanggan1;
    private javax.swing.JLabel labTglLahirPelanggan;
    private javax.swing.JLabel labTglLahirPelanggan1;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbLaki1;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JRadioButton rbPerempuan1;
    private javax.swing.JTable tbPelanggan;
    private javax.swing.JTextField txAlamatPelanggan;
    private javax.swing.JTextField txAlamatPelanggan1;
    private javax.swing.JTextField txKabupaten;
    private javax.swing.JTextField txKabupaten1;
    private javax.swing.JTextField txKecamatan;
    private javax.swing.JTextField txKecamatan1;
    private javax.swing.JTextField txKelurahan;
    private javax.swing.JTextField txKelurahan1;
    private javax.swing.JTextField txNamaPelanggan;
    private javax.swing.JTextField txNamaPelanggan1;
    private javax.swing.JTextField txProvinsi;
    private javax.swing.JTextField txProvinsi1;
    private javax.swing.JTextField txTeleponPelanggan;
    private javax.swing.JTextField txTeleponPelanggan1;
    private com.toedter.calendar.JDateChooser txTglLahirPelanggan;
    private com.toedter.calendar.JDateChooser txTglLahirPelanggan1;
    // End of variables declaration//GEN-END:variables
}
