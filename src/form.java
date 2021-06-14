/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;
import java.sql.Connection;
import javax.swing.JOptionPane;


public class form {
   public static void main(String[] args) {
	form form = new form();
   }
}

class Form extends JFrame implements ActionListener {
    
    String tEmail, tUsername, tPassword, tNama, tTTLahir, tjk, tDomisili, tDesc;
    int valid, count;
    
     connector connector = new connector(); 
      
      final JTextField fjudul = new JTextField(0);
      JLabel ljudul = new JLabel(" Formulir Pendaftaran ");

      final JTextField femail = new JTextField(50);
      JLabel lemail = new JLabel(" Email ");
      
      final JTextField fusername = new JTextField(50);
      JLabel lusername = new JLabel(" Username ");
      
      final JTextField fpassword = new JTextField(50);
      JLabel lpassword = new JLabel(" Password ");
      
      final JTextField fnama = new JTextField(50);
      JLabel lnama = new JLabel(" Nama Lengkap ");
      
      final JTextField fttl = new JTextField(50);
      JLabel lttl = new JLabel(" TTLahir ");
      
      final JTextField fjk = new JTextField(50);
      JLabel ljk = new JLabel(" Jenis Kelamin ");
      
      final JTextField fdomisili = new JTextField(50);
      JLabel ldomisili = new JLabel(" Domisili ");
      
      final JTextField fdesc = new JTextField(200);
      JLabel ldesc = new JLabel(" Deskripsi Singkat ");
            
    JButton bcancel = new JButton("Cancel");  
    JButton bdaftar = new JButton("Lihat Data");
    JButton bcheck = new JButton("Check");
    JButton bok = new JButton("OK");

      ButtonGroup group = new ButtonGroup();
      
   public Form() {
	setTitle(" Formulir ");
	setDefaultCloseOperation(3);
	setSize(750,750);

	

	setLayout(null);
	add(ljudul); add(fjudul); add(lemail); add(femail); add(lusername); 
        add(fusername); add(lpassword); add(fpassword); add(lnama); add(fnama); 
        add(lttl); add(fttl); add(ljk); add(fjk); add(ldomisili); add(fdomisili); add(ldesc); add(fdesc);
        add(bdaftar); add(bcancel); add(bcheck); add(bok);

	// setBounds(m,n,o,p)
	// m = posisi x; n = posisi n
	// o = panjang komponen; p = tinggi komponen
        
        ljudul.setBounds(150,0,220,50);
        fjudul.setBounds(0,0,0,0);
        
	lemail.setBounds(30,50,220,30);
	femail.setBounds(150,50,250,30);
        
	lusername.setBounds(30,100,220,30);
	fusername.setBounds(150,100,250,30);
        
        lpassword.setBounds(30,150,220,30);
	fpassword.setBounds(150,150,250,30);
        
        lnama.setBounds(30,200,220,30);
        fnama.setBounds(150,200,250,30);
        
        lttl.setBounds(30,250,220,30);
        fttl.setBounds(150,250,250,30);
        
        ljk.setBounds(30,300,220,30);
        fjk.setBounds(150,300,250,30);
        
        ldomisili.setBounds(30,350,220,30);
        fdomisili.setBounds(150,350,250,30);
        
        ldesc.setBounds(30,400,220,100);
        fdesc.setBounds(150,450,250,100);
        
        bcancel.setBounds(150,600,75,40);
        bcheck.setBounds(250,600,75,40);
        bok.setBounds(350,600,75,40);
        bdaftar.setBounds(450,600,75,40);
       
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        femail.addActionListener((ActionListener) this);
        fusername.addActionListener((ActionListener) this);
        fpassword.addActionListener((ActionListener) this);
        fnama.addActionListener((ActionListener) this);
        fttl.addActionListener((ActionListener) this);
        fjk.addActionListener((ActionListener) this);
        fdomisili.addActionListener((ActionListener) this);
        fdesc.addActionListener((ActionListener) this);
        bcancel.addActionListener((ActionListener) this);
        bcheck.addActionListener((ActionListener) this);
        bok.addActionListener((ActionListener) this);
        bdaftar.addActionListener((ActionListener) this);
   }
   
    @Override
   public void actionPerformed(ActionEvent e){     
        
        if(e.getSource() == bcancel){
             femail.setText("");
             fusername.setText("");
             fpassword.setText("");
             fnama.setText("");
             fttl.setText("");
             fjk.setText("");
             fdomisili.setText("");
             fdesc.setText("");
        }
        if (e.getSource() == bcheck){
            valid = 0;
            tEmail = femail.getText();
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if (tEmail.matches(regex));
            else { 
                femail.setText("Harus sesuai format");
                 valid = 1;
            }
            tUsername = fusername.getText();
            Pattern form = Pattern.compile("[^a-zA-Z0-9]");
            Matcher equal = form.matcher(tUsername);
            boolean no = equal.find();
            if(no){ 
                fusername.setText("hanya boleh menggunakan huruf dan angka");
                 valid = 1;
            }
            tPassword = fpassword.getText();
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(tPassword);
            boolean password = matcher.find();
            if(password);
            else{
                fpassword.setText("harus mengandung huruf, angka, dan simbol");
                 valid = 1;
            }
            tNama = fnama.getText();
            tTTLahir = fttl.getText();
            tjk = fjk.getText();
            tDomisili = fdomisili.getText();
            tDesc = fdesc.getText();
            count = tDesc.length();
            if(count > 200){ 
                fdesc.setText("max 200 character");
                 valid = 1;
            }
        }
        
         if (e.getSource()==bok){
                try{
                 connector connector = new connector(); 
                String query = "INSERT INTO `data`(`Email`,`Username`,`Password`,`Nama`,'TTL','Jenis_Kelamin',"
                        + "'Domisili','Deskripsi') "
                        + "VALUES ('"+femail.getText()+"','"+fusername.getText()+"','"+fpassword.getText()+"',"
                        + "'"+fnama.getText()+"','"+fttl.getText()+"','"+fjk.getText()+"',"
                        + "'"+fdomisili.getText()+"','"+fdesc.getText()+"')";
                   
                connector.statement = connector.koneksi.createStatement();
                connector.statement.executeUpdate(query);

                System.out.println("Insert Data Berhasil");
                JOptionPane.showMessageDialog(null,"Insert Data Berhasil !!");
                } catch (Exception ex){
                System.out.println(ex.getMessage());
                }
            }
        
           if (e.getSource()==bdaftar){
            new LihatData();
            dispose();
        }
    }
}