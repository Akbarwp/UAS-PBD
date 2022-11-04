/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PBD.Batik.Frame.InternalFrame.Pembelian;

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
public class MasterPembelian extends javax.swing.JInternalFrame {

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
    
    public MasterPembelian() {
        initComponents();
        
        loadIDBarang();
        loadIDPemasok();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
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
    
    private void loadIDPemasok(){
        try {
            conn = Koneksi.getKoneksi();
            SQL = "SELECT * FROM pemasok";
            cmd = conn.createStatement();
            rs = cmd.executeQuery(SQL);
            cbIDPemasok.removeAllItems();
            cbIDPemasok1.removeAllItems();
            cbIDPemasok.addItem("Pilih ID Pemasok");
            cbIDPemasok1.addItem("Pilih ID Pemasok");
            while(rs.next()){
                String cid = rs.getString("id_pemasok");
                cbIDPemasok.addItem(cid);
                cbIDPemasok1.addItem(cid);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    private void showTabel() {
        try {
            conn = Koneksi.getKoneksi();
            
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT ID_TRANS_BELI, ID_BARANG, to_char(TGL_TRANS_BELI, 'DD MON YYYY') as TGL_TRANS_BELI FROM trans_beli ORDER BY id_trans_beli ASC");
            
            //Setting kolom dari DefaultTableModel
            tmJual = new DefaultTableModel(new String[] {"ID_TRANS_BELI", "ID_BARANG", "TGL_TRANS_BELI"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmJual.addRow(new Object[] {rs.getString("ID_TRANS_BELI"), rs.getString("ID_BARANG"), rs.getString("TGL_TRANS_BELI")});
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
            rs = cmd.executeQuery("SELECT * FROM detil_trans_beli ORDER BY id_trans_beli ASC");
            
            //Setting kolom dari DefaultTableModel
            tmJual = new DefaultTableModel(new String[] {"ID_TRANS_BELI", "ID_PEMASOK", "PASOKAN", "TOTAL_HARGA_PASOKAN"},0);
            
            //Pindah isi dari result set ke dalam DefaultTableModel
            if (rs.next()) {
                do {                    
                    tmJual.addRow(new Object[] {rs.getString("ID_TRANS_BELI"), rs.getString("ID_PEMASOK"), rs.getInt("PASOKAN"), 
                        rs.getInt("TOTAL_HARGA_PASOKAN")});
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
    
    private String namaBarang() {
        String barang = null;
        try {
            conn = Koneksi.getKoneksi();
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
    
    private String namaBarang1() {
        String barang = null;
        try {
            conn = Koneksi.getKoneksi();
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
    
    private String namaPemasok() {
        String pelanggan = null;
        try {
            conn = Koneksi.getKoneksi();
            cmd1 = conn.createStatement();
            rs1 = cmd1.executeQuery("SELECT nama_pemasok FROM pemasok WHERE id_pemasok = '" + cbIDPemasok.getSelectedItem().toString() + "' ");
            if (rs1 != null) {
                while(rs1.next()) {
                    pelanggan = rs1.getString("nama_pemasok");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pelanggan;
    }
    
    private String namaPemasok1() {
        String pelanggan = null;
        try {
            conn = Koneksi.getKoneksi();
            cmd1 = conn.createStatement();
            rs1 = cmd1.executeQuery("SELECT nama_pemasok FROM pemasok WHERE id_pemasok = '" + cbIDPemasok1.getSelectedItem().toString() + "' ");
            if (rs1 != null) {
                while(rs1.next()) {
                    pelanggan = rs1.getString("nama_pemasok");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return pelanggan;
    }
    
    public void clear() {
        txIDTransPembelian.setText("ID Transaksi Pembelian");
        cbIDBarang.setSelectedIndex(0);
        cbIDPemasok.setSelectedIndex(0);
        txPasokan.setText(null);
        txTotalHarga.setText("Total Harga Pasokan");
        
        cbIDBarang1.setSelectedIndex(0);
        cbIDPemasok1.setSelectedIndex(0);
        txPasokan1.setText(null);
        
        txTglPembelian.setDate(null);
        txTglPembelian1.setDate(null);
        
        txNamaBarang.setText("Nama Barang");
        txNamaPemasok.setText("Nama Pemasok");
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
        labNamaBarang = new javax.swing.JLabel();
        labNamaPemasok = new javax.swing.JLabel();
        labIDTransBeli = new javax.swing.JLabel();
        labTglPembelian = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        labPasokan = new javax.swing.JLabel();
        txPasokan = new javax.swing.JTextField();
        labTotalHarga = new javax.swing.JLabel();
        cbIDBarang = new javax.swing.JComboBox<>();
        cbIDPemasok = new javax.swing.JComboBox<>();
        txNamaPemasok = new javax.swing.JLabel();
        txNamaBarang = new javax.swing.JLabel();
        txTglPembelian = new com.toedter.calendar.JDateChooser();
        btnClear = new javax.swing.JButton();
        txTotalHarga = new javax.swing.JLabel();
        txIDTransPembelian = new javax.swing.JLabel();
        btnFaktur = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTrans = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetail = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnTambahData = new javax.swing.JButton();
        btnClear1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        labNamaBarang1 = new javax.swing.JLabel();
        labNamaPemasok1 = new javax.swing.JLabel();
        labTglPembelian1 = new javax.swing.JLabel();
        labPasokan1 = new javax.swing.JLabel();
        cbIDBarang1 = new javax.swing.JComboBox<>();
        cbIDPemasok1 = new javax.swing.JComboBox<>();
        txTglPembelian1 = new com.toedter.calendar.JDateChooser();
        txPasokan1 = new javax.swing.JTextField();
        txNamaBarang1 = new javax.swing.JLabel();
        txNamaPemasok1 = new javax.swing.JLabel();

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

        labNamaBarang.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang.setForeground(new java.awt.Color(255, 255, 255));
        labNamaBarang.setText("Nama Barang");

        labNamaPemasok.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPemasok.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPemasok.setText("Nama Pemasok");

        labIDTransBeli.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labIDTransBeli.setForeground(new java.awt.Color(255, 255, 255));
        labIDTransBeli.setText("ID Transaksi Pembelian");

        labTglPembelian.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglPembelian.setForeground(new java.awt.Color(255, 255, 255));
        labTglPembelian.setText("Tanggal Pembelian");

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(246, 182, 112));
        jLabel7.setText("Data Pembelian");

        btnUpdate.setBackground(new java.awt.Color(255, 255, 0));
        btnUpdate.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        labPasokan.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labPasokan.setForeground(new java.awt.Color(255, 255, 255));
        labPasokan.setText("Pasokan");

        txPasokan.setBackground(new java.awt.Color(255, 255, 255));
        txPasokan.setForeground(new java.awt.Color(0, 0, 0));

        labTotalHarga.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTotalHarga.setForeground(new java.awt.Color(255, 255, 255));
        labTotalHarga.setText("Total Harga");

        cbIDBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDBarangActionPerformed(evt);
            }
        });

        cbIDPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPemasokActionPerformed(evt);
            }
        });

        txNamaPemasok.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaPemasok.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPemasok.setText("Nama Pemasok");

        txNamaBarang.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaBarang.setForeground(new java.awt.Color(255, 255, 255));
        txNamaBarang.setText("Nama Barang");

        txTglPembelian.setDateFormatString("dd-MM-yyyy");

        btnClear.setBackground(new java.awt.Color(255, 0, 0));
        btnClear.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnClear.setForeground(new java.awt.Color(0, 0, 0));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txTotalHarga.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txTotalHarga.setForeground(new java.awt.Color(255, 255, 255));
        txTotalHarga.setText("Total Harga Pasokan");

        txIDTransPembelian.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txIDTransPembelian.setForeground(new java.awt.Color(255, 255, 255));
        txIDTransPembelian.setText("ID Transaksi Pembelian");
        txIDTransPembelian.setToolTipText("");

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
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labIDTransBeli)
                                    .addComponent(labNamaBarang))
                                .addGap(75, 75, 75)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbIDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txIDTransPembelian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labTglPembelian)
                                    .addComponent(labNamaPemasok))
                                .addGap(114, 114, 114)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbIDPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txTglPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labTotalHarga, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labPasokan))
                                .addGap(180, 180, 180)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txPasokan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(btnUpdate)
                                .addGap(32, 32, 32)
                                .addComponent(btnClear)
                                .addGap(18, 18, 18)
                                .addComponent(btnFaktur)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNamaPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDTransBeli)
                    .addComponent(txIDTransPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNamaBarang)
                    .addComponent(cbIDBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNamaPemasok)
                    .addComponent(cbIDPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNamaPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labTglPembelian)
                    .addComponent(txTglPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPasokan)
                    .addComponent(txPasokan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTotalHarga)
                    .addComponent(txTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnClear)
                    .addComponent(btnFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114))
        );

        jTabbedPane1.addTab("Master", jPanel1);

        tbTrans.setBackground(new java.awt.Color(38, 42, 65));
        tbTrans.setForeground(new java.awt.Color(255, 255, 255));
        tbTrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
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

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(246, 182, 112));
        jLabel8.setText("Data Pembelian");

        labNamaBarang1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaBarang1.setForeground(new java.awt.Color(255, 255, 255));
        labNamaBarang1.setText("Nama Barang");

        labNamaPemasok1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labNamaPemasok1.setForeground(new java.awt.Color(255, 255, 255));
        labNamaPemasok1.setText("Nama Pemasok");

        labTglPembelian1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labTglPembelian1.setForeground(new java.awt.Color(255, 255, 255));
        labTglPembelian1.setText("Tanggal Pembelian");

        labPasokan1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labPasokan1.setForeground(new java.awt.Color(255, 255, 255));
        labPasokan1.setText("Pasokan");

        cbIDBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDBarang1ActionPerformed(evt);
            }
        });

        cbIDPemasok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDPemasok1ActionPerformed(evt);
            }
        });

        txTglPembelian1.setDateFormatString("dd-MM-yyyy");

        txPasokan1.setBackground(new java.awt.Color(255, 255, 255));
        txPasokan1.setForeground(new java.awt.Color(0, 0, 0));

        txNamaBarang1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaBarang1.setForeground(new java.awt.Color(255, 255, 255));
        txNamaBarang1.setText("Nama Barang");

        txNamaPemasok1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txNamaPemasok1.setForeground(new java.awt.Color(255, 255, 255));
        txNamaPemasok1.setText("Nama Pemasok");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(btnTambahData)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labTglPembelian1)
                                    .addComponent(labNamaPemasok1))
                                .addGap(85, 85, 85)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbIDPemasok1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txTglPembelian1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(labNamaBarang1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(labPasokan1)
                                .addGap(180, 180, 180)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbIDBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txPasokan1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txNamaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txNamaPemasok1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNamaBarang1)
                    .addComponent(cbIDBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNamaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNamaPemasok1)
                    .addComponent(cbIDPemasok1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNamaPemasok1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labTglPembelian1)
                    .addComponent(txTglPembelian1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labPasokan1)
                    .addComponent(txPasokan1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear1)
                    .addComponent(btnTambahData))
                .addGap(143, 143, 143))
        );

        jTabbedPane1.addTab("Tambah Data", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

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
            
            txIDTransPembelian.setText(tbTrans.getValueAt(index, 0).toString());
            cbIDBarang.setSelectedItem(tbTrans.getValueAt(index, 1).toString());
            
            try {
            String date = tbTrans.getValueAt(index, 2).toString();
            java.util.Date date2 = new SimpleDateFormat("dd MMM yy").parse(date);
            txTglPembelian.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            
            cbIDPemasok.setSelectedItem(tbDetail.getValueAt(index, 1).toString());
            txPasokan.setText(tbDetail.getValueAt(index, 2).toString());
            txTotalHarga.setText("Rp" + tbDetail.getValueAt(index, 3).toString());
            
            txNamaBarang.setText(namaBarang());
            txNamaPemasok.setText(namaPemasok());
        }
    }//GEN-LAST:event_tbTransMouseClicked

    private void tbDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetailMouseClicked
        // TODO add your handling code here:
        int index = tbTrans.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2) {
            
            txIDTransPembelian.setText(tbTrans.getValueAt(index, 0).toString());
            cbIDBarang.setSelectedItem(tbTrans.getValueAt(index, 1).toString());
            
            try {
            String date = tbTrans.getValueAt(index, 2).toString();
            java.util.Date date2 = new SimpleDateFormat("dd MMM yy").parse(date);
            txTglPembelian.setDate(date2);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            
            cbIDPemasok.setSelectedItem(tbDetail.getValueAt(index, 1).toString());
            txPasokan.setText(tbDetail.getValueAt(index, 2).toString());
            txTotalHarga.setText("Rp" + tbDetail.getValueAt(index, 3).toString());
            
            txNamaBarang.setText(namaBarang());
            txNamaPemasok.setText(namaPemasok());
        }
    }//GEN-LAST:event_tbDetailMouseClicked

    private void btnTambahDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahDataActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();
            
            String formatDate = "dd MMM yy";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = String.valueOf(df.format(txTglPembelian1.getDate()));
            
            CallableStatement call = conn.prepareCall("{call INS_PEMBELIAN(?,?,?,?)}");
            call.setString(1, cbIDBarang1.getSelectedItem().toString());
            call.setString(2, tanggal);
            call.setString(3, cbIDPemasok1.getSelectedItem().toString());
            call.setInt(4, Integer.parseInt(txPasokan1.getText()));
            call.execute();

            JOptionPane.showMessageDialog(this, "Success");

            clear();
            showTabel();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahDataActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            String formatDate = "dd-MM-yyyy";
            SimpleDateFormat df = new SimpleDateFormat(formatDate);
            String tanggal = String.valueOf(df.format(txTglPembelian.getDate()));
            
            CallableStatement call1 = conn.prepareCall("{call UPD_PEMBELIAN(?,?,?)}");
            call1.setString(1, "tanggal");
            call1.setString(2, txIDTransPembelian.getText());
            call1.setString(3, tanggal);
            call1.execute();
            
            CallableStatement call2 = conn.prepareCall("{call UPD_PEMBELIAN(?,?,?)}");
            call2.setString(1, "pemasok");
            call2.setString(2, txIDTransPembelian.getText());
            call2.setString(3, cbIDPemasok.getSelectedItem().toString());
            call2.execute();
            
            CallableStatement call3 = conn.prepareCall("{call UPD_BARANGBELI(?,?,?)}");
            call3.setString(1, txIDTransPembelian.getText());
            call3.setString(2, cbIDBarang.getSelectedItem().toString());
            call3.setString(3, txPasokan.getText());
            call3.execute();
            
            JOptionPane.showMessageDialog(this, "Success");
            showTabel();
            
            clear();
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cbIDBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDBarangActionPerformed
        // TODO add your handling code here:
        txNamaBarang.setText(namaBarang());
        if (txNamaBarang.getText() == null) {
            txNamaBarang.setText("Nama Barang");
        }
    }//GEN-LAST:event_cbIDBarangActionPerformed

    private void cbIDPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPemasokActionPerformed
        // TODO add your handling code here:
        txNamaPemasok.setText(namaPemasok());
        if (txNamaPemasok.getText() == null) {
            txNamaPemasok.setText("Nama Pemasok");
        }
    }//GEN-LAST:event_cbIDPemasokActionPerformed

    private void cbIDBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDBarang1ActionPerformed
        // TODO add your handling code here:
        txNamaBarang1.setText(namaBarang1());
        if (txNamaBarang1.getText() == null) {
            txNamaBarang1.setText("Nama Barang");
        }
    }//GEN-LAST:event_cbIDBarang1ActionPerformed

    private void cbIDPemasok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDPemasok1ActionPerformed
        // TODO add your handling code here:
        txNamaPemasok1.setText(namaPemasok1());
        if (txNamaPemasok1.getText() == null) {
            txNamaPemasok1.setText("Nama Pemasok");
        }
    }//GEN-LAST:event_cbIDPemasok1ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        loadIDBarang();
        loadIDPemasok();
    }//GEN-LAST:event_formInternalFrameActivated

    private void btnFakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFakturActionPerformed
        // TODO add your handling code here:
        try {
            conn = Koneksi.getKoneksi();

            HashMap hash = new HashMap(1);
            hash.put("Transaksi", txIDTransPembelian.getText());

            if(txIDTransPembelian.getText().equalsIgnoreCase("ID Transaksi Pembelian")) {
                JOptionPane.showMessageDialog(this, "Pilih ID Transaksi Pembelian Terlebih Dahulu");
            } else {
                File ReportPenjualanSedikit = new File("src/PBD/Batik/Report/InvoicePembelian.jasper");
                JasperPrint JP = JasperFillManager.fillReport(ReportPenjualanSedikit.getPath(), hash, conn);
                JasperViewer.viewReport(JP, false);
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btnFakturActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnFaktur;
    private javax.swing.JButton btnTambahData;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbIDBarang;
    private javax.swing.JComboBox<String> cbIDBarang1;
    private javax.swing.JComboBox<String> cbIDPemasok;
    private javax.swing.JComboBox<String> cbIDPemasok1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labIDTransBeli;
    private javax.swing.JLabel labNamaBarang;
    private javax.swing.JLabel labNamaBarang1;
    private javax.swing.JLabel labNamaPemasok;
    private javax.swing.JLabel labNamaPemasok1;
    private javax.swing.JLabel labPasokan;
    private javax.swing.JLabel labPasokan1;
    private javax.swing.JLabel labTglPembelian;
    private javax.swing.JLabel labTglPembelian1;
    private javax.swing.JLabel labTotalHarga;
    private javax.swing.JTable tbDetail;
    private javax.swing.JTable tbTrans;
    private javax.swing.JLabel txIDTransPembelian;
    private javax.swing.JLabel txNamaBarang;
    private javax.swing.JLabel txNamaBarang1;
    private javax.swing.JLabel txNamaPemasok;
    private javax.swing.JLabel txNamaPemasok1;
    private javax.swing.JTextField txPasokan;
    private javax.swing.JTextField txPasokan1;
    private com.toedter.calendar.JDateChooser txTglPembelian;
    private com.toedter.calendar.JDateChooser txTglPembelian1;
    private javax.swing.JLabel txTotalHarga;
    // End of variables declaration//GEN-END:variables
}
