package PBD.Praktikum;

import java.sql.*;

public class Pertemuan9 {

    public static void main(String[] args) {
        
        try {
            //Koneksi
            String url = "jdbc:oracle:thin:MATKUL/MATKUL@localhost:1521:xe";
            Connection conn = DriverManager.getConnection(url);
            if (conn.isClosed()) {
                System.out.println("koneksi Gagal");
            }
            
            //Ambil data
            Statement cmd = null; //Menerima perintah SQL (Select, DML)
            ResultSet rs = null; //Menampung hasil SELECT
            
            System.out.println("---Contoh 1:");
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT COUNT(ID_PEGAWAI) as jml FROM PEGAWAI");
            rs.next();
            System.out.println("Jumlah pegawai (cara1) = " + rs.getInt(1) + " orang");
            System.out.println("Jumlah pegawai (cara2) = " + rs.getInt("jml") + " orang");
            
            
            //Ambil banyak data
            System.out.println("\n---Contoh 2:");
            cmd = conn.createStatement();
            rs = cmd.executeQuery("SELECT ID_PEGAWAI, NAMA_PEGAWAI FROM PEGAWAI");
            
            if (rs.next()) {
                System.out.println("ID Pegawai\tNama Pegawai");
                do {                    
                    System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
                } while (rs.next());
                
            } else {
                System.out.println("Data tidak ada");
            }
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
