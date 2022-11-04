package PBD.Batik.Frame;

import java.sql.*;

public class Koneksi {
    
    //Deklarasi variabel yang dibutuhkan
    public static Connection conn; //Untuk koneksi ke database
    public static String driverName = "oracle.jdbc.driver.OracleDriver"; //Untuk set nama driver jdbc oracle
    public static String namaJdbc = "jdbc:oracle:thin:"; //Untuk set nama jdbc
    public static String host = "@localhost:";
    public static String port = "1521:";
    public static String sid = "xe";
    public static String namaDB = "orcl"; //Nama database awal install
    public static String namaUserDB = "MATKUL"; //Nama user yang menjadi tempat penyimpanan tabel
    public static String pwdUser = "MATKUL";
    
    public static String url_db = namaJdbc + host + port + sid;
    
    //Membuat fungsi koneksi (getKoneksi)
    public static Connection getKoneksi() {
        //Cek koneksi sudah aktif atau belum
        if (conn == null) {
            //Mengaktifkan koneksi
            try {
                Class.forName(driverName);
                conn = DriverManager.getConnection(url_db, namaUserDB, pwdUser);
                System.out.println("Koneksi Sukses");
                
            } catch (Exception e) {
                System.err.println("Koneksi Gagal");
            }
        }
        return conn;
    }
    
    public static void main(String[] args) {
        
        try {
            conn = Koneksi.getKoneksi();
            System.out.println();
        
            Statement cmd = null;
            ResultSet rs = null;

            //Select Statement
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT ID_PEGAWAI, NAMA_PEGAWAI, ALMT_PEGAWAI FROM PEGAWAI");
            
            if (rs.next()) {
                do {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                } while(rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
            
            //DML Statement
            cmd = conn.createStatement();
            int exec = cmd.executeUpdate("INSERT INTO PEGAWAI VALUES(gen_id('pegawai'), 'Ferguso', '17-JUN-2000', 'DSF', '081553257126', 'L')");
            
            if (exec > 0) {
                System.out.println("Data Pegawai sudah berhasil disimpan");
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
