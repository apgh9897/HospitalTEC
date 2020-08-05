package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import modelo.Tratamiento;

public class TratamientoDAO extends ConexionMySQL{

    /**
    *Metodo utilizado para regitrar un tratamiento nuevo en la base de datos
    * @param Tratamiento tratamiento
    * @return
    * @throws SQLException
    */
   public boolean registrarTratamiento(Tratamiento tratamiento) {
       PreparedStatement ps = null;
       Connection con = conectar();
       String sql = "INSERT INTO Tratamiento (nombre, dosis, tipoTratamiento) VALUES(?,?,?)";

       try {
           
           ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
           ps.addBatch(sql);
           ps.setString(1, tratamiento.getNombre());
           ps.setInt(2, tratamiento.getDosis());
           ps.setString(3, tratamiento.getTipoTratamiento());
           System.out.println(sql);
        // se muestra mensaje al insertarlo con exito
           JOptionPane.showMessageDialog(null,"El tratamiento ha sido agregado"); 
           ps.execute();
           
           rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
           rs.next();
           int codigo = rs.getInt(1);

           tratamiento.setCodigo(codigo);;// le aseigna la llave al objeto del arraylist
           
           return true;
       } catch (SQLException e) {
    	   // mensaje de sql al querer insertar una llave primaria duplicada 
           if (e.getSQLState().startsWith("23")) {
           JOptionPane.showMessageDialog(null, "Este tratamiento ya ha sido registrado"); 
           }
           return false;
       } finally {
           try {
               con.close();
           } catch (SQLException e) {
               System.err.println(e);
           }
       }
   }
   
 
}
