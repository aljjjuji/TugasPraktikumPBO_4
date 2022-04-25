/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// 123200106
// Al Jauzi Abdurrohman, Plug-B
/**
 *
 * @author ASUS
 */

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception;  
import java.sql.ResultSet;

public class LoginDaftar
{  
    public static void main(String arg[])  
    {  
        // konek database
        Connector connector = new Connector(); 
        
        // instansiasi class loginForm
        loginForm frameLogin = new loginForm();  
        frameLogin.pack(); 
        frameLogin.setVisible(true);
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // instansiasi class daftarForm
        daftarForm frameDaftar = new daftarForm();
        frameDaftar.pack();
        frameDaftar.setVisible(true);
        frameDaftar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
}

class loginForm extends JFrame
{  
    // deklarasi komponen
    JButton login, cancel;  
    JPanel panel;  
    JLabel uname, pass;  
    final JTextField textFieldUsername, textFieldPassword;  
      
    loginForm()  
    {     
        // konek database
        Connector connector = new Connector(); 
        // buat label untuk uname
        uname = new JLabel();  
        uname.setText("Username: "); 
        // buat text field untuk uname agar bisa diinput user
        textFieldUsername = new JTextField(15); 
  
        // buat label untuk pass
        pass = new JLabel();  
        pass.setText("Password: ");          
        // buat text field untuk pass agar bisa diinput user
        textFieldPassword = new JTextField(15);
          
        // buat tombol login dan cancel
        login = new JButton("Login");
        cancel = new JButton("Cancel");
        
        // menambahkan komponen ke dalam window/panel
        panel = new JPanel(new GridLayout(3, 1));  
        panel.add(uname);
        panel.add(textFieldUsername);
        panel.add(pass);
        panel.add(textFieldPassword);
        panel.add(login);
        panel.add(cancel);
        
        // mengatur layout window/panel dan menambahkan judul
        add(panel, BorderLayout.CENTER);  
        setTitle("Login");
        
        // aksi jika tombol login ditekan
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                   try {
            // query untuk mengambil nama username yang di-input
            String qUname = "SELECT * FROM users WHERE username = '"+getUsername()+"'";
            connector.statement = connector.koneksi.createStatement();
            ResultSet unameBenar = connector.statement.executeQuery(qUname);
            
            // cek apakah username ada atau tidak
            if(unameBenar.next()){
                // mengambil isi kolom id dari username yang telah di-inputkan
                int id = unameBenar.getInt("id");
                // query untuk mengambil data akun
                String qPass = "SELECT * FROM users WHERE password = '"+getPassword()+"' AND id = '"+id+"'";
                ResultSet passBenar = connector.statement.executeQuery(qPass);
                // cek apakah akun ada atau tidak
                if(passBenar.next()){
                    JOptionPane.showMessageDialog(null,"Login berhasil.");
                }
                // jika username benar dan password salah
                else{
                    JOptionPane.showMessageDialog(null,"Password salah.");
                }
            }
            // jika username tidak ada
            else{
                JOptionPane.showMessageDialog(null,"Username tidak ditemukan.");
            }
            System.out.println("Insert Berhasil");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
            }
        });
        
        // aksi jika tombol cancel ditekan
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);            
            } 
        });
    }
    
    // method untuk mengambil inputan username sebagai string
    public String getUsername(){
        return textFieldUsername.getText();
    }
    
    // method untuk mengambil inputan password sebagai string
    public String getPassword() {
        return textFieldPassword.getText();
    }
}

class daftarForm extends JFrame
{  
    // deklarasi komponen
    JButton daftar, cancel;  
    JPanel panel;  
    JLabel uname, pass;  
    final JTextField textFieldUsername, textFieldPassword;  
      
    daftarForm()  
    {     
        // konek database
        Connector connector = new Connector(); 
        // buat label untuk uname
        uname = new JLabel();  
        uname.setText("Username: "); 
        // buat text field untuk uname agar bisa diinput user
        textFieldUsername = new JTextField(15); 
  
        // buat label untuk pass
        pass = new JLabel();  
        pass.setText("Password: ");          
        // buat text field untuk pass agar bisa diinput user
        textFieldPassword = new JTextField(15);
          
        // buat tombol login dan cancel
        daftar = new JButton("Daftar");
        cancel = new JButton("Cancel");
        
        // menambahkan komponen ke dalam window/panel
        panel = new JPanel(new GridLayout(3, 1));  
        panel.add(uname);
        panel.add(textFieldUsername);
        panel.add(pass);
        panel.add(textFieldPassword);
        panel.add(daftar);
        panel.add(cancel);
        
        // mengatur layout window/panel dan menambahkan judul
        add(panel, BorderLayout.CENTER);  
        setTitle("Daftar");
        
        // aksi jika tombol login ditekan
        daftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                   try {
            String qUname = "SELECT * FROM users WHERE username = '"+getUsername()+"'";
            connector.statement = connector.koneksi.createStatement();
            ResultSet isUname = connector.statement.executeQuery(qUname);
            if(isUname.next()){
                JOptionPane.showMessageDialog(null,"Username sudah terdaftar.");
            }
            else{
                String qPass = "INSERT INTO users (`username`, `password`) VALUES ('"+getUsername()+"','"+getPassword()+"')";
                connector.statement = connector.koneksi.createStatement();
                connector.statement.executeUpdate(qPass);
                JOptionPane.showMessageDialog(null,"Akun berhasil didaftarkan.");
            }
            System.out.println("Insert Berhasil");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
            }
        });
        
        // aksi jika tombol cancel ditekan
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);            
            } 
        });
    }
    
    // method untuk mengambil inputan username sebagai string
    public String getUsername(){
        return textFieldUsername.getText();
    }
    
    // method untuk mengambil inputan password sebagai string
    public String getPassword() {
        return textFieldPassword.getText();
    }
}