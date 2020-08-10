package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
*
* @author MariaJosePlacido
*/

public class ConexionMySQL {
 static Connection con;
 static java.sql.Statement st;
 static ResultSet rs;
 private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
 private static final String USER = "root";
 private static final String PASSWORD = "";
 private static final String URL ="jdbc:mysql://localhost:3306/hospital";
 
 static{
	 
     try {
         Class.forName(CONTROLADOR);
     } catch (ClassNotFoundException e){
         JOptionPane.showMessageDialog(null,"ERROR EN EL DRIVER" + e);
         e.printStackTrace();
         
     }
     
 }
 
 public Connection conectar() {
	 
	 Connection con = null;
	 try {
		 con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		 System.out.println("Conexion ok");
		 
	 }catch(SQLException e) {
		 System.out.println("Error en la conexion");
		 e.printStackTrace();
	 }
	 return con;
 }
 
	    
}