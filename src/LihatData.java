/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class LihatData {
    public String Email, Username, Password, Nama, TTL, Jenis_Kelamin, Domisili, Deskripsi;
    int jmlData;
    String data[][] = new String[500][8];
    
    connector connector = new connector(); 
    
    //DEKLARASI KOMPONEN
    JFrame window = new JFrame("JDBC");
    JTable tabel;
    DefaultTableModel tableModel; //otomatis dibuat kalo buat JTable
    JScrollPane scrollPane;
    //membuat kolom dgn array tipe object;
    Object namaKolom[] = {"Email","Username","Password","Nama","TTL","Jenis_Kelamin","Domosili","Deskripsi"}; 
    
    public LihatData(){
        window.setLayout(null);
        window.setSize(550,600);
        window.setDefaultCloseOperation(3);
        
        window.setVisible(true);
        
        window.setLocationRelativeTo(null);
        window.setResizable(false);
    
         try{
            int jmlData = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
            String query = "Select * from `data`"; //proses pengambilan data
            connector.statement = connector.koneksi.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query); //result isinya tabel belum berupa string
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("Email"); 
                data[jmlData][1] = resultSet.getString("Username");
                data[jmlData][2] = resultSet.getString("Password");
                data[jmlData][3] = resultSet.getString("Nama");
                data[jmlData][4] = resultSet.getString("TTL");
                data[jmlData][5] = resultSet.getString("Jenis Kelamin");
                data[jmlData][6] = resultSet.getString("Domisili");
                data[jmlData][7] = resultSet.getString("Deskripsi");
                jmlData++; 
            }
            connector.statement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
         
         tabel = new JTable(data,namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
         scrollPane = new JScrollPane(tabel);
         window.add(scrollPane);
         
         scrollPane.setBounds(20, 35, 500, 300);
         scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        }
       public int getBanyakData(){ //menghitung jumlah baris yg ada pada db
        int jmlData = 0; //nilai awal 0
        try{
            connector.statement = connector.koneksi.createStatement();
            String query = "Select * from `data`";
            ResultSet resultSet = connector.statement.executeQuery(query); //pengambilan data dalam java dari database
            while(resultSet.next()){ //ambil nilai dari baris ke 0 sampai baris akhir
                jmlData++;
            }
            return jmlData; //mengembalikan jumlah data ke readtiket
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
        
        //dua dimensi (baris-kolom)
        public String [][] readdata(){ 
        try{
            int jmlData = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
            String data[][] = new String[getBanyakData()][4];//menampung array. barisnya isinya di getBanyakData,kolomnya itu atribut
            String query = "Select * from `data`"; //proses pengambilan data
            ResultSet resultSet = connector.statement.executeQuery(query); //result isinya tabel belum berupa string
            while(resultSet.next()){ //konversi tabel ke string
                data[jmlData][0] = resultSet.getString("no_mk"); 
                data[jmlData][1] = resultSet.getString("nama_mk"); 
                data[jmlData][2] = resultSet.getString("nip_dosen");
                data[jmlData][3] = resultSet.getString("kelas");
                jmlData++; 
                
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }
}


