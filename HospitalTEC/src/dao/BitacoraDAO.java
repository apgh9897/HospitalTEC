package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Bitacora;
/**
*
* @author Yasuara Espinoza
*/

public class BitacoraDAO extends ConexionMySQL {
	
	 /**
     *Metodo que registra la bitacora en la base de datos
     * @param pBitacora
     * @return
     * @throws SQLException
     */
  public boolean registrarBitacora (Bitacora pBitacora) {
    PreparedStatement ps = null;
    Connection con = conectar();

    String sql = "INSERT INTO Bitacora (idCita, idEstado, fecha, hora, usuario)"
        		+ " VALUES(?,?,?,?,?)";

    try {
      ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setInt(1, pBitacora.getCodigoCita());
      ps.setInt(2,pBitacora.getIdEstado());
      ps.setDate(5, (Date) pBitacora.getFecha());
      ps.setDate(4, (Date) pBitacora.getHora());
      ps.setString(5, pBitacora.getFuncionario());
            
      System.out.println(sql);
            
      ps.execute();
            
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
   *Metodo que modifica una bitacora
   * @param pBitacora
   * @return
   * @throws SQLException
   */
  public boolean modificarBitacora(Bitacora pBitacora) {
    PreparedStatement ps = null;
    Connection con =  conectar();

    String sql = "UPDATE Bitacora SET usuario=? WHERE idCita =?  and idEstado=?";

    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, pBitacora.getFuncionario());
      ps.setInt(2,pBitacora.getCodigoCita());
      ps.setInt(3, pBitacora.getIdEstado());
      ps.execute();

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
   *Metodo que elimina una bitacora
   * @param pBitacora
   * @return
   * @throws SQLException
   */
  public void eliminarBitacora(Bitacora pBitacora){
    PreparedStatement ps = null;
    Connection con = conectar();
    	
    try {
      String query="DELETE FROM Bitacora WHERE idCita=? and idEstado=?";
      ps=con.prepareStatement(query);
      ps.setInt(1, pBitacora.getCodigoCita());
      ps.setInt(2, pBitacora.getIdEstado());
    		
      System.out.println(ps);
    		
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
   *Metodo que consulta toda una bitacora de la cita
   * @param  pIdCita es el identificador del centro que se desea buscar en el sistema
   * @throws SQLException
   */
  public String consultarBitacoraToda(int pIdCita){
    String consulta="";
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT fecha, hora, usuario, idEstado"
 		        + " from Bitacora "
 		        + " WHERE idCita =  " +pIdCita ;

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
        {  
        String estado="";
        if(rs.getInt("idEstado")==1) {
          estado="REGISTRADA";
        }
        if(rs.getInt("idEstado")==2) {
          estado="CANCELADA POR PACIENTE";
        }
        if(rs.getInt("idEstado")==3) {
           estado="CANDELADA POR CENTRO MEDICO";
        }
        if(rs.getInt("idEstado")==4) {
           estado="ASIGNADA";
        }
        if(rs.getInt("idEstado")==5) {
           estado="REALIZADA";
        }
        consulta=consulta +"\n"+"Estado de cita:"+ estado +"\n"+"Fecha de actualizacion: "+ 
	            rs.getDate("fecha")+"\n"+ "Hora de actualizacion:" + rs.getDate("fecha")+"\n"+
	            "Usuario:"+ rs.getString("usuario")+"\n";

             	
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
   *Metodo que consulta una bitacora en especifico
   * @param  pIdCita es el identificador del centro que se desea buscar en el sistema
   * @return pIdEstado
   * @throws SQLException
   */
  public Bitacora consultarBitacora(int pIdCita, int pIdEstado){
    Bitacora bit=null;
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT fecha, hora, usuario"
		        + " from Bitacora "
		        + " WHERE idCita =  " +pIdCita + "idEstado="+ pIdEstado;

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
        {  
        Bitacora bit2 = new Bitacora(rs.getDate("fecha"), rs.getDate("hora"),
            			         rs.getString("usuario"), pIdCita, pIdEstado);
        bit=bit2;


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

    return bit;
  }

}
