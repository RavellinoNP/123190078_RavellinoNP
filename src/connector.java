/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */


import java.sql.*;

public class connector {
    String DBurl = "jdbc:mysql://localhost/pendaftaran";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    public connector() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pendaftaran",
                    "root","");
            System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            System.out.println("Koneksi gagal");
        }
    }
}