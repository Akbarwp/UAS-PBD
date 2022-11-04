/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PBD.Batik.Frame.InternalFrame.Penjualan;

import PBD.Batik.Frame.Koneksi;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author akbar
 */
public class MasterPenjualan extends javax.swing.JInternalFrame {

    /**
     * Creates new form MasterPenjualan
     */
    
    Connection conn;
    Statement cmd;
    ResultSet rs;
    DefaultTableModel tmJual;
    String SQL;
    
    Statement cmd1;
    ResultSet rs1;
    
    public MasterPenjualan() {
        initComponents();
        
        loadIDPelanggan();
        loadIDPegawai();
        loadIDBarang();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
    
    private void loadIDPelanggan(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM pelanggan";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDPelanggan.removeAllItems();
            cbIDPelanggan1.removeAllItems();
            cbIDPelanggan.addItem("Pilih ID Pelanggan");
            cbIDPelanggan1.addItem("Pilih ID Pelanggan");
            while(rs.next()){
                String cid = rs.getString("id_pelanggan");
                cbIDPelanggan.addItem(cid);
                cbIDPelanggan1.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void loadIDPegawai(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM pegawai";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDPegawai.removeAllItems();
            cbIDPegawai1.removeAllItems();
            cbIDPegawai.addItem("Pilih ID Pegawai");
            cbIDPegawai1.addItem("Pilih ID Pegawai");
            while(rs.next()){
                String cid = rs.getString("id_pegawai");
                cbIDPegawai.addItem(cid);
                cbIDPegawai1.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void loadIDBarang(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM barang";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDBarang.removeAllItems();
            cbIDBarang1.removeAllItems();
            cbIDBarang.addItem("Pilih ID Barang");
            cbIDBarang1.addItem("Pilih ID Barang");
            while(rs.next()){
                String cid = rs.getString("id_barang");
                cbIDBarang.addItem(cid);
                cbIDBarang1.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void showTabel() {
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT ID_TRANS_JUAL, ID_PELANGGAN, ID_PEGAWAI, to_char(TGL_TRANS_JUAL, 'DD MON YYYY') as TGL_TRANS_JUAL FROM trans_jual ORDER BY id_trans_jual ASC");
            
            //Setting kolom dari DefaultTableModel
            tmJual = new DefaultTableModel(new String[] {"ID_TRANS_JUAL", "ID_PELANGGAN", "ID_PEGAWAI",  "TGL_TRANS_JUAL"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmJual.addRow(new Object[] {rs.getString("ID_TRANS_JUAL"), rs.getString("ID_PELANGGAN"), rs.getString("ID_PEGAWAI"), 
                        rs.getString("TGL_TRANS_JUAL")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            tbTrans.setDefaultEditor(Object.class, null);
            
            tbTrans.setModel(tmJual);
            
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT * FROM detil_trans_jual ORDER BY id_trans_jual ASC");
            
            //Setting kolom dari DefaultTableModel
            tmJual = new DefaultTableModel(new String[] {"ID_TRANS_JUAL", "ID_BARANG", "KUANTITAS_BARANG", "TOTAL_HARGA_BARANG"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmJual.addRow(new Object[] {rs.getString("ID_TRANS_JUAL"), rs.getString("ID_BARANG"), rs.getInt("KUANTITAS_BARANG"), 
                        rs.getInt("TOTAL_HARGA_BARANG")});
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            tbDetail.setDefaultEditor(Object.class, null);
            tbDetail.setModel(tmJual);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private String namaPegawai() {
        String pegawai = null;
        try {
            cmd1 = conn.createStatement();
            rs1 = cmd1.executeQuery("SELECT nama_pegawai FROM pegawai WHERE id_pegawai = '" + cbIDPegawai.getSelectedItem().toString() + "' ");
            if (rs1 == null) {
                
            } else {
                while(rs1.next()) {
                    pegawai = rs1.getString("nama_pegawai");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pegawai;
    }
    
    private String namaPelanggan() {
        String pelanggan = null;
        try {
            cmd1 = conn.createStatement();
            rs1 = cmd1.executeQuery("SELECT nama_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan.getSelectedItem().toString() + "' ");
            if (rs1 != null) {
                while(rs1.next()) {
                    pelanggan = rs1.getString("nama_pelanggan");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pelanggan;
    }
    
    private String namaBarang() {
        String barang = null;
        try {
            cmd1 = conn.createStatement();
            rs1 = cmd1.executeQuery("SELECT nama_barang FROM barang WHERE id_barang = '" + cbIDBarang.getSelectedItem().toString() + "' ");
            if (rs1 != null) {
                while(rs1.next()) {
                    barang = rs1.getString("nama_barang");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return barang;
    }
    
    // ---------------------------------------------------------------------------------------------------------------------------------------------
    
    private String namaPegawai1() {
        String pegawai = null;
        try {
            cmd1 = conn.createStatement();
            rs1 = cmd1.executeQuery("SELECT nama_pegawai FROM pegawai WHERE id_pegawai = '" + cbIDPegawai1.getSelectedItem().toString() + "' ");
            if (rs1 == null) {
                
            } else {
                while(rs1.next()) {
                    pegawai = rs1.getString("nama_pegawai");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pegawai;
    }
    
    private String namaPelanggan1() {
        String pelanggan = null;
        try {
            cmd1 = conn.createStatement();
            rs1 = cmd1.executeQuery("SELECT nama_pelanggan FROM pelanggan WHERE id_pelanggan = '" + cbIDPelanggan1.getSelectedItem().toString() + "' ");
            if (rs1 != null) {
                while(rs1.next()) {
                    pelanggan = rs1.getString("nama_pelanggan");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pelanggan;
    }
    
    private String namaBarang1() {
        String barang = null;
        try {
            cmd1 = conn.createStatement();
            rs1 = cmd1.executeQuery("SELECT nama_barang FROM barang WHERE id_barang = '" + cbIDBarang1.getSelectedItem().toString() + "' ");
            if (rs1 != null) {
                while(rs1.next()) {
                    barang = rs1.getString("nama_barang");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return barang;
    }
    
    public void clear() {
        txIDPenjualan.setText("ID Transaksi Penjualan");
        cbIDPelanggan.setSelectedIndex(0);
        cbIDPegawai.setSelectedIndex(0);
        cbIDBarang.setSelectedIndex(0);
        txKuantitas.setText(null);
        txTotalHarga.setText("Total Harga Barang");
        
        cbIDPelanggan1.setSelectedIndex(0);
        cbIDPegawai1.setSelectedIndex(0);
        cbIDBarang1.setSelectedIndex(0);
        txKuantitas1.setText(null);
        
        txTglPenjualan11.setDate(null);
        txTglPenjualan12.setDate(null);
        
        txNamaPelanggan.setText("Nama Pelanggan");
        txNamaPegawai.setText("Nama Pegawai");
        txNamaBarang.setText("Nama barang");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        labKuantitas = new javax.swing.JLabel();
        txKuantitas = new javax.swing.JTextField();
        labTotalHarga = new javax.swing.JLabel();
        labNamaBarang = new javax.swing.JLabel();
        cbIDPegawai = new javax.swing.JComboBox<>();
        cbIDPelanggan = new javax.swing.JComboBox<>();
        cbIDBarang = new javax.swing.JComboBox<>();
        labNamaPegawai = new javax.swing.JLabel();
        labNamaPelanggan = new javax.swing.JLabel();
        labIDTransJual = new javax.swing.JLabel();
        labTglPenjualan = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnClear2 = new javax.swing.JButton();
        txTotalHarga = new javax.swing.JLabel();
        txTglPenjualan12 = new com.toedter.calendar.JDateChooser();
        txNamaPegawai = new javax.swing.JLabel();
        txNamaPelanggan = new javax.swing.JLabel();
        txNamaBarang = new javax.swing.JLabel();
        txIDPenjualan = new javax.swing.JLabel();
        btnFaktur = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTrans = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetail = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        labKuantitas1 = new javax.swing.JLabel();
        txKuantitas1 = new javax.swing.JTextField();
        labNamaBarang1 = new javax.swing.JLabel();
        cbIDPegawai1 = new javax.swing.JComboBox<>();
        cbIDPelanggan1 = new javax.swing.JComboBox<>();
        cbIDBarang1 = new javax.swing.JComboBox<>();
        btnTambahData = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        labNamaPelanggan1 = new javax.swing.JLabel();
        labTglPenjualan1 = new javax.swing.JLabel();
        labNamaPegawai1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txTglPenjualan11 = new com.toedter.calendar.JDateChooser();
        txNamaPegawai1 = new javax.swing.JLabel();
        txNamaPelanggan1 = new javax.swing.JLabel();
        txNamaBarang1 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1020, 720));
        setPreferredSize(new java.awt.Dimension(1020, 720));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
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

        labKuantitas.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labKuantitas.setForeground(new java.awt.Color(255, 255, 255));
        labKuantitas.setText("Kuantitas Barang");

        txKuantitas.setBackground(new java.awt.Color(255, 255, 255));
        txKuantitas.setForeground(new java.awt.Color(0, 0, 0));

        labTotalHarga.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTotalHarga.setForeground(new java.awt.Color(255, 255, 255));
        labTotalHarga.setText("Total Harga");

        labNamaBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang.setForeground(new java.awt.Color(255, 255, 255));
        labNamaBarang.setText("Nama Barang");

        cbIDPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPegawaiActionPerformed(evt);
            }
        });

        cbIDPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPelangganActionPerformed(evt);
            }
        });

        cbIDBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDBarangActionPerformed(evt);
            }
        });

        labNamaPegawai.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPegawai.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPegawai.setText("Nama Pegawai");

        labNamaPelanggan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPelanggan.setText("Nama Pelanggan");

        labIDTransJual.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDTransJual.setForeground(new java.awt.Color(255, 255, 255));
        labIDTransJual.setText("ID Transaksi Penjualan");

        labTglPenjualan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglPenjualan.setForeground(new java.awt.Color(255, 255, 255));
        labTglPenjualan.setText("Tanggal Penjualan");

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(246, 182, 112));
        jLabel7.setText("Data Penjualan");

        btnClear2.setBackground(new java.awt.Color(255, 0, 0));
        btnClear2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear2.setForeground(new java.awt.Color(0, 0, 0));
        btnClear2.setText("Clear");
        btnClear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear2ActionPerformed(evt);
            }
        });

        txTotalHarga.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txTotalHarga.setForeground(new java.awt.Color(255, 255, 255));
        txTotalHarga.setText("Total Harga Barang");

        txTglPenjualan12.setDateFormatString("dd-MM-yyyy");

        txNamaPegawai.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaPegawai.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPegawai.setText("Nama Pegawai");
        txNamaPegawai.setToolTipText("");

        txNamaPelanggan.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPelanggan.setText("Nama Pelanggan");
        txNamaPelanggan.setToolTipText("");

        txNamaBarang.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaBarang.setForeground(new java.awt.Color(255, 255, 255));
        txNamaBarang.setText("Nama Barang");
        txNamaBarang.setToolTipText("");

        txIDPenjualan.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txIDPenjualan.setForeground(new java.awt.Color(255, 255, 255));
        txIDPenjualan.setText("ID Transaksi Penjualan");

        btnFaktur.setBackground(new java.awt.Color(204, 204, 204));
        btnFaktur.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnFaktur.setForeground(new java.awt.Color(0, 0, 0));
        btnFaktur.setText("Faktur");
        btnFaktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFakturActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(cbIDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labKuantitas)
                                .addGap(130, 130, 130))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labNamaBarang)
                                    .addComponent(labTotalHarga))
                                .addGap(162, 162, 162)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labTglPenjualan)
                                    .addComponent(labIDTransJual)
                                    .addComponent(labNamaPegawai)
                                    .addComponent(labNamaPelanggan))
                                .addGap(75, 75, 75)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbIDPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbIDPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txTglPenjualan12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txIDPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txNamaPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txNamaPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(btnUpdate)
                .addGap(38, 38, 38)
                .addComponent(btnClear2)
                .addGap(30, 30, 30)
                .addComponent(btnFaktur)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDTransJual)
                    .addComponent(txIDPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNamaPegawai)
                    .addComponent(cbIDPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNamaPelanggan)
                    .addComponent(cbIDPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labTglPenjualan)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labNamaBarang)
                            .addComponent(cbIDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labKuantitas)
                            .addComponent(txKuantitas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labTotalHarga)
                            .addComponent(txTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txTglPenjualan12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnClear2)
                    .addComponent(btnFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
        );

        jTabbedPane1.addTab("Master", jPanel1);

        tbTrans.setBackground(new java.awt.Color(38, 42, 65));
        tbTrans.setForeground(new java.awt.Color(255, 255, 255));
        tbTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbTrans.setGridColor(new java.awt.Color(255, 255, 255));
        tbTrans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTrans);

        jTabbedPane1.addTab("Tabel Trans", jScrollPane1);

        tbDetail.setBackground(new java.awt.Color(38, 42, 65));
        tbDetail.setForeground(new java.awt.Color(255, 255, 255));
        tbDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDetail.setGridColor(new java.awt.Color(255, 255, 255));
        tbDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetailMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbDetail);

        jTabbedPane1.addTab("Tabel Detail", jScrollPane2);

        jPanel2.setBackground(new java.awt.Color(38, 42, 65));

        labKuantitas1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labKuantitas1.setForeground(new java.awt.Color(255, 255, 255));
        labKuantitas1.setText("Kuantitas Barang");

        txKuantitas1.setBackground(new java.awt.Color(255, 255, 255));
        txKuantitas1.setForeground(new java.awt.Color(0, 0, 0));

        labNamaBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang1.setForeground(new java.awt.Color(255, 255, 255));
        labNamaBarang1.setText("Nama Barang");

        cbIDPegawai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPegawai1ActionPerformed(evt);
            }
        });

        cbIDPelanggan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPelanggan1ActionPerformed(evt);
            }
        });

        cbIDBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDBarang1ActionPerformed(evt);
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

        btnClear1.setBackground(new java.awt.Color(255, 0, 0));
        btnClear1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(0, 0, 0));
        btnClear1.setText("Clear");
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        labNamaPelanggan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPelanggan1.setText("Nama Pelanggan");

        labTglPenjualan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglPenjualan1.setForeground(new java.awt.Color(255, 255, 255));
        labTglPenjualan1.setText("Tanggal Penjualan");

        labNamaPegawai1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPegawai1.setText("Nama Pegawai");

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(246, 182, 112));
        jLabel8.setText("Data Penjualan");

        txTglPenjualan11.setDateFormatString("dd-MM-yyyy");

        txNamaPegawai1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaPegawai1.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPegawai1.setText("Nama Pegawai");
        txNamaPegawai1.setToolTipText("");

        txNamaPelanggan1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaPelanggan1.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPelanggan1.setText("Nama Pelanggan");
        txNamaPelanggan1.setToolTipText("");

        txNamaBarang1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaBarang1.setForeground(new java.awt.Color(255, 255, 255));
        txNamaBarang1.setText("Nama Barang");
        txNamaBarang1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labNamaBarang1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labNamaPelanggan1)
                                            .addComponent(labTglPenjualan1)
                                            .addComponent(labNamaPegawai1))
                                        .addGap(115, 115, 115)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbIDBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cbIDPelanggan1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbIDPegawai1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txTglPenjualan11, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txNamaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txNamaPegawai1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txNamaPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labKuantitas1)
                                .addGap(130, 130, 130)
                                .addComponent(txKuantitas1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(btnTambahData)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear1)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(labNamaPegawai1)
                        .addGap(41, 41, 41)
                        .addComponent(labNamaPelanggan1)
                        .addGap(39, 39, 39)
                        .addComponent(labTglPenjualan1)
                        .addGap(38, 38, 38)
                        .addComponent(labNamaBarang1)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labKuantitas1)
                            .addComponent(txKuantitas1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbIDPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txNamaPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbIDPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txNamaPelanggan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(txTglPenjualan11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbIDBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txNamaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear1)
                    .addComponent(btnTambahData))
                .addGap(92, 92, 92))
        );

        jTabbedPane1.addTab("Tambah Data", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        showTabel();
    }//GEN-LAST:event_formInternalFrameOpened

    private void tbTransMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransMouseClicked
        // TODO add your handling code here:
        int index = tbTrans.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            
            txIDPenjualan.setText(tbTrans.getValueAt(index, 0).toString());
            cbIDPelanggan.setSelectedItem(tbTrans.getValueAt(index, 1).toString());
            txNamaPelanggan.setText(tbTrans.getValueAt(index, 1).toString());
            cbIDPegawai.setSelectedItem(tbTrans.getValueAt(index, 2).toString());
            txNamaPegawai.setText(tbTrans.getValueAt(index, 2).toString());
            
            
            try {
            String date = tbTrans.getValueAt(index, 3).toString();
            java.util.Date date2 = new SimpleDateFormat("dd MMM yy").parse(date);
            txTglPenjualan12.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            
            cbIDBarang.setSelectedItem(tbDetail.getValueAt(index, 1).toString());
            txNamaBarang.setText(tbDetail.getValueAt(index, 1).toString());
            txKuantitas.setText(tbDetail.getValueAt(index, 2).toString());
            txTotalHarga.setText("Rp" + tbDetail.getValueAt(index, 3).toString());
            
            txNamaPegawai.setText(namaPegawai());
            txNamaPelanggan.setText(namaPelanggan());
            txNamaBarang.setText(namaBarang());
        }
    }//GEN-LAST:event_tbTransMouseClicked

    private void btnTambahDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDataActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();
            
            String formatDate = "dd MMM yy";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = String.valueOf(df.format(txTglPenjualan11.getDate()));
            
            CallableStatement call = conn.prepareCall("{call INS_PENJUALAN(?,?,?,?,?)}");
            call.setString(1, cbIDPelanggan1.getSelectedItem().toString());
            call.setString(2, cbIDPegawai1.getSelectedItem().toString());
            call.setString(3, tanggal);
            call.setString(4, cbIDBarang1.getSelectedItem().toString());
            call.setInt(5, Integer.parseInt(txKuantitas1.getText()));
            call.execute();

            JOptionPane.showMessageDialog(this, "Success");

            clear();
            showTabel();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahDataActionPerformed

    private void tbDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetailMouseClicked
        // TODO add your handling code here:
        int index = tbDetail.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {

            txIDPenjualan.setText(tbDetail.getValueAt(index, 0).toString());
            cbIDBarang.setSelectedItem(tbDetail.getValueAt(index, 1).toString());
            txNamaBarang.setText(tbDetail.getValueAt(index, 1).toString());
            txKuantitas.setText(tbDetail.getValueAt(index, 2).toString());
            txTotalHarga.setText("Rp" + tbDetail.getValueAt(index, 3).toString());
            
            cbIDPelanggan.setSelectedItem(tbTrans.getValueAt(index, 1).toString());
            txNamaPelanggan.setText(tbTrans.getValueAt(index, 1).toString());
            cbIDPegawai.setSelectedItem(tbTrans.getValueAt(index, 2).toString());
            txNamaPegawai.setText(tbTrans.getValueAt(index, 2).toString());
            
            try {
                String date = tbTrans.getValueAt(index, 3).toString();
                java.util.Date date2 = new SimpleDateFormat("dd MMM yy").parse(date);
                txTglPenjualan12.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            
            txNamaPegawai.setText(namaPegawai());
            txNamaPelanggan.setText(namaPelanggan());
            txNamaBarang.setText(namaBarang());
        }
    }//GEN-LAST:event_tbDetailMouseClicked

    private void btnClear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear2ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClear2ActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            CallableStatement call1 = conn.prepareCall("{call UPD_PENJUALAN(?,?,?)}");
            call1.setString(1, "pelanggan");
            call1.setString(2, txIDPenjualan.getText());
            call1.setString(3, cbIDPelanggan.getSelectedItem().toString());
            call1.execute();
            
            CallableStatement call2 = conn.prepareCall("{call UPD_PENJUALAN(?,?,?)}");
            call2.setString(1, "pegawai");
            call2.setString(2, txIDPenjualan.getText());
            call2.setString(3, cbIDPegawai.getSelectedItem().toString());
            call2.execute();
            
            String formatDate = "dd-MM-yyyy";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = String.valueOf(df.format(txTglPenjualan12.getDate()));
            
            CallableStatement call3 = conn.prepareCall("{call UPD_PENJUALAN(?,?,?)}");
            call3.setString(1, "tanggal");
            call3.setString(2, txIDPenjualan.getText());
            call3.setString(3, tanggal);
            call3.execute();
            
            CallableStatement call4 = conn.prepareCall("{call UPD_BARANGJUAL(?,?,?)}");
            call4.setString(1, txIDPenjualan.getText());
            call4.setString(2, cbIDBarang.getSelectedItem().toString());
            call4.setString(3, txKuantitas.getText());
            call4.execute();
            
            JOptionPane.showMessageDialog(this, "Success");
            showTabel();
            
            clear();
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cbIDPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPegawaiActionPerformed
        // TODO add your handling code here:
        txNamaPegawai.setText(namaPegawai());
        if (txNamaPegawai.getText() == null) {
            txNamaPegawai.setText("Nama Pegawai");
        }
    }//GEN-LAST:event_cbIDPegawaiActionPerformed

    private void cbIDPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPelangganActionPerformed
        // TODO add your handling code here:
        txNamaPelanggan.setText(namaPelanggan());
        if (txNamaPelanggan.getText() == null) {
            txNamaPelanggan.setText("Nama Pelanggan");
        }
    }//GEN-LAST:event_cbIDPelangganActionPerformed

    private void cbIDBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDBarangActionPerformed
        // TODO add your handling code here:
        txNamaBarang.setText(namaBarang());
        if (txNamaBarang.getText() == null) {
            txNamaBarang.setText("Nama Barang");
        }
    }//GEN-LAST:event_cbIDBarangActionPerformed

    private void cbIDPegawai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPegawai1ActionPerformed
        // TODO add your handling code here:
        txNamaPegawai1.setText(namaPegawai1());
        if (txNamaPegawai1.getText() == null) {
            txNamaPegawai1.setText("Nama Pegawai");
        }
    }//GEN-LAST:event_cbIDPegawai1ActionPerformed

    private void cbIDPelanggan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPelanggan1ActionPerformed
        // TODO add your handling code here:
        txNamaPelanggan1.setText(namaPelanggan1());
        if (txNamaPelanggan1.getText() == null) {
            txNamaPelanggan1.setText("Nama Pelanggan");
        }
    }//GEN-LAST:event_cbIDPelanggan1ActionPerformed

    private void cbIDBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDBarang1ActionPerformed
        // TODO add your handling code here:
        txNamaBarang1.setText(namaBarang1());
        if (txNamaBarang1.getText() == null) {
            txNamaBarang1.setText("Nama Barang");
        }
    }//GEN-LAST:event_cbIDBarang1ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        loadIDPelanggan();
        loadIDPegawai();
        loadIDBarang();
    }//GEN-LAST:event_formInternalFrameActivated

    private void btnFakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFakturActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();
            
            HashMap hash = new HashMap(1);
            hash.put("Transaksi", txIDPenjualan.getText());
            
            if(txIDPenjualan.getText().equalsIgnoreCase("ID Transaksi Penjualan")) {
                JOptionPane.showMessageDialog(this, "Pilih ID Transaksi Penjualan Terlebih Dahulu");
            } else {
                File ReportPenjualanSedikit = new File("src/PBD/Batik/Report/Invoice.jasper");
                JasperPrint JP = JasperFillManager.fillReport(ReportPenjualanSedikit.getPath(), hash, conn);
                JasperViewer.viewReport(JP, false);
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnFakturActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnClear2;
    private javax.swing.JButton btnFaktur;
    private javax.swing.JButton btnTambahData;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbIDBarang;
    private javax.swing.JComboBox<String> cbIDBarang1;
    private javax.swing.JComboBox<String> cbIDPegawai;
    private javax.swing.JComboBox<String> cbIDPegawai1;
    private javax.swing.JComboBox<String> cbIDPelanggan;
    private javax.swing.JComboBox<String> cbIDPelanggan1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labIDTransJual;
    private javax.swing.JLabel labKuantitas;
    private javax.swing.JLabel labKuantitas1;
    private javax.swing.JLabel labNamaBarang;
    private javax.swing.JLabel labNamaBarang1;
    private javax.swing.JLabel labNamaPegawai;
    private javax.swing.JLabel labNamaPegawai1;
    private javax.swing.JLabel labNamaPelanggan;
    private javax.swing.JLabel labNamaPelanggan1;
    private javax.swing.JLabel labTglPenjualan;
    private javax.swing.JLabel labTglPenjualan1;
    private javax.swing.JLabel labTotalHarga;
    private javax.swing.JTable tbDetail;
    private javax.swing.JTable tbTrans;
    private javax.swing.JLabel txIDPenjualan;
    private javax.swing.JTextField txKuantitas;
    private javax.swing.JTextField txKuantitas1;
    private javax.swing.JLabel txNamaBarang;
    private javax.swing.JLabel txNamaBarang1;
    private javax.swing.JLabel txNamaPegawai;
    private javax.swing.JLabel txNamaPegawai1;
    private javax.swing.JLabel txNamaPelanggan;
    private javax.swing.JLabel txNamaPelanggan1;
    private com.toedter.calendar.JDateChooser txTglPenjualan11;
    private com.toedter.calendar.JDateChooser txTglPenjualan12;
    private javax.swing.JLabel txTotalHarga;
    // End of variables declaration//GEN-END:variables
}
