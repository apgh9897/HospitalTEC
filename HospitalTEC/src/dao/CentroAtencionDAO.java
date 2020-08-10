package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import modelo.CentroDeAtencion;
/**
*
* @author Maria Jose Placido
*/
public class CentroAtencionDAO extends ConexionMySQL {
	

    
  /**
   *Metodo que registra el centro de atencion en la base de datos
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public boolean registrarCentroDeAtencion (CentroDeAtencion centro) {
    PreparedStatement ps = null;
    Connection con = (Connection) conectar();

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
   *Metodo que modifica un centro de atencion
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public boolean modificarCentreo(CentroDeAtencion centro) {
    PreparedStatement ps = null;
    Connection con =  (Connection) conectar();

    String sql = "UPDATE CentroDeAtencion SET nombre =?, lugar =?, capPacientes=?,"
        		+ " tipoCentro=? WHERE idCentroDeAtencion =? ";

    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, centro.getNombre());
      ps.setString(2, centro.getUbicacion());
      ps.setInt(3, centro.getCapacidadMaxima());
      ps.setString(4, centro.getTipoDeCentro());
      ps.execute();

      JOptionPane.showMessageDialog(null,"El centro de atencion ha sido modificado");
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
   *Metodo que elimina un centro de atencion
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public void eliminarCentro(CentroDeAtencion centro){
    PreparedStatement ps = null;
    Connection con = (Connection) conectar();
    	
    try {
      String query="DELETE FROM CentroDeAtencion WHERE idCentroDeAtencion=?";
      ps=con.prepareStatement(query);
      ps.setInt(1, centro.getCodigoCentro());
    		
      System.out.println(ps);
      JOptionPane.showMessageDialog(null,"El centro de atencion ha sido eliminado");
    		
      ps.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }

  }
    
  /**
   *Metodo que consulta una centro de atencion en especifico
   * @param int pId es el identificador del centro que se desea buscar en el sistema
   * @return
   * @throws SQLException
   */
  public String consultarCentro(int pId){
    String consulta="";
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idCentroDeAtencion, nombre, lugar, capPacientes, tipoCentro"
		        + " from CentroDeAtencion "
		        + " WHERE idCentroDeAtencion =  " +pId;

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
      {  
        CentroDeAtencion centro = new CentroDeAtencion(rs.getString("nombre"), rs.getString("lugar"),
        		                  rs.getInt("capPacientes"), rs.getString("tipoCentro"));
        centro.setCodigoCentro(rs.getInt("idCentroDeAtencion"));
            	  
             	
        consulta=consulta +"\n"+"id Centro:"+ centro.getCodigoCentro()+"\n"+"Nombre: "+ 
 	                      centro.getNombre()+"\n"+ "Ubicacion:" + centro.getUbicacion()+"\n"+
 	                      "Capacidad Maxima:" + centro.getCapacidadMaxima()+"\n"+
 	                      "Tipo de centro:" + centro.getTipoDeCentro()+"\n";
             	

       }
    } catch (SQLException e) {
      System.err.println(e);

    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.err.println(e);
             }
    }
    System.out.print(consulta);
    return consulta;
  }
    
  /**
   *Metodo que registra el tipo de centro de atencion en la base de datos
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public boolean registrarTipoDeCentroDeAtencion (String pDescripcion) {
    PreparedStatement ps = null;
    Connection con = (Connection) conectar();
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

    
  /**
   *Metodo que elimina un tipo de centro de atencion
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public void eliminarTipoCentro(int pId){
    PreparedStatement ps = null;
    Connection con = (Connection) conectar();
    	
    try {
      String query="DELETE FROM TipoCentro WHERE =" + pId;
      ps=con.prepareStatement(query);
    		
    		
      System.out.println(ps);
      JOptionPane.showMessageDialog(null,"El centro de atencion ha sido eliminado");
    		
      ps.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }

  }
    
  /**
   *Metodo que modifica un centro de atencion
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public boolean modificarTipoCentro(int  pId, String pDescripcion) {
    PreparedStatement ps = null;
    Connection con =  (Connection) conectar();
        

    String sql = "UPDATE TipoCentro SET descripcion ="  + "'"+pDescripcion+ "'" 
        + " where  idTipo= " +pId ;

    try {
      ps = con.prepareStatement(sql);
      ps.execute();

      JOptionPane.showMessageDialog(null,"El tipo de centro de atencion ha sido modificado");
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
