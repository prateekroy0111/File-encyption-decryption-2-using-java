
import java.sql.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class Db_Connect {
    Connection conn=null;
    
    public static Connection ConnectDb() {
        try{
            //Load DB Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Create Connection with DB
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/enc_n_dec","root","password");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    } 
}
