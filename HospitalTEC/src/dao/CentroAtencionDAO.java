package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.CentroDeAtencion;


public class CentroAtencionDAO extends ConexionMySQL {
	

    
    /**
     *Metodo que registra el centro de atencion en la base de datos
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public boolean registrarCentroDeAtencion (CentroDeAtencion centro) {
        PreparedStatement ps = null;
        Connection con = conectar();

        String sql = "INSERT INTO CentroDeAtencion (nombre, lugar, capPacientes, tipoCentro)"
        		+ " VALUES(?,?,?,?)";

        try {
            ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, centro.getNombre());
            ps.setString(2, centro.getUbicacion());
            ps.setInt(3, centro.getCapacidadMaxima());
            ps.setString(4, centro.getTipoDeCentro());
            
            System.out.println(sql);
            
            ps.execute();
            
            rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
            rs.next();
            int codigoCentro = rs.getInt(1);
            
            //System.out.print(id);
            
            centro.setCodigoCentro(codigoCentro);// le aseigna la llave al objeto del arraylist
           // admin.getCentrosDeAtencion().add(centro);//agrega el centro a la lista del sistema
            
            JOptionPane.showMessageDialog(null, "El Centro ha sido insertado"); 
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    
    /**
     *Metodo que registra el tipo de centro de atencion en la base de datos
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public boolean registrarTipoDeCentroDeAtencion (String pDescripcion) {
        PreparedStatement ps = null;
        Connection con = conectar();
       // CentroDeAtencion centro = new CentroDeAtencion();

        String sql = "INSERT INTO TipoCentro (descripcion) VALUES(?)";

        try {
            ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, pDescripcion);
            
            System.out.println(sql);
            
            ps.execute();
            
            rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
            rs.next();
           // int idTipo = rs.getInt(1);
           
            //System.out.print(id);
            
            // le aseigna la llave al objeto del arraylist
           // admin.getCentrosDeAtencion().add(centro);//agrega el centro a la lista del sistema
            
            JOptionPane.showMessageDialog(null, "Se ha creado un nuevo tipo de centro"); 
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
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
