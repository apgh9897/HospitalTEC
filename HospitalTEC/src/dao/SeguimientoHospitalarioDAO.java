package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.PreparedStatement;

import modelo.SeguimientoHospitalario;

public class SeguimientoHospitalarioDAO extends ConexionMySQL {
	
  /**
   *Metodo que registra el seguimiento hospitalario en la base de datos
   * @param pSeguimientoHospitalario
   * @return
   * @throws SQLException
   */
  public boolean registrarSeguimientoHospitalario (SeguimientoHospitalario pSeguimientoHospitalario) {
    PreparedStatement ps = null;
    Connection con = conectar();

    String sql = "INSERT INTO SeguimientoHospitalario ( fecha, funcionario, observacion, tratamiento, idHospitalizacion)"
        		+ " VALUES(" + pSeguimientoHospitalario.getFechaSeguimiento() + ","
        		+ "'"+pSeguimientoHospitalario.getNombreFuncionarioSeguimiento()+"',"
        		+ "'"+pSeguimientoHospitalario.getObservacion()+"',"
        		+ "'"+pSeguimientoHospitalario.getTratamiento()+"',"
        		+ "'"+pSeguimientoHospitalario.getIdHospitalizacion()+"')";

    try {
      ps = (PreparedStatement) con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
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
   *Metodo que modifica un seguimiento hospitalario
   * @param pSeguimientoHospitalario
   * @return
   * @throws SQLException
   */
  public boolean modificarSeguimientoHospitalario(SeguimientoHospitalario pSeguimientoHospitalario) {
    PreparedStatement ps = null;
    Connection con =  conectar();

    String sql = "UPDATE SeguimientoHospitalario SET fecha="+ pSeguimientoHospitalario.getFechaSeguimiento() +","
        		+ " funcionario=?, observacion=?,"
        		+ " tratamiento =?, idHospitalizacion=? WHERE idSeguimientoHospitalario=?";

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      ps.setString(1,pSeguimientoHospitalario.getNombreFuncionarioSeguimiento());
      ps.setString(2,pSeguimientoHospitalario.getObservacion());
      ps.setString(3,pSeguimientoHospitalario.getTratamiento());
      ps.setInt(4,pSeguimientoHospitalario.getIdHospitalizacion());
      ps.setInt(5,pSeguimientoHospitalario.getNumeroSeguimiento());
            
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
   *Metodo que elimina un SeguimientoHospitalario
   * @param pSeguimientoHospitalario
   * @return
   * @throws SQLException
   */
  public void eliminarBitacora(SeguimientoHospitalario pSeguimientoHospitalario){
    PreparedStatement ps = null;
    Connection con = conectar();
    	
    try {
      String query="DELETE FROM SeguimientoHospitalario WHERE idSeguimientoHospitalario=?";
      ps=(PreparedStatement) con.prepareStatement(query);
      ps.setInt(1, pSeguimientoHospitalario.getNumeroSeguimiento());
    		
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
   *Metodo que consulta toda un SeguimientoHospitalario de una hospitalizacion
   * @param  pIdHospitalizacion es el identificador del centro que se desea buscar en el sistema
   * @throws SQLException
   */
  public String consultarSeguimientoHospitalarioToda(int pIdHospitalizacion){
    String consulta="";
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idSeguimientoHospitalario, fecha, funcionario, observacion, tratamiento"
 		        + " from SeguimientoHospitalario "
 		        + " WHERE idHospitalizacion =  " +pIdHospitalizacion ;

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
      {  

        consulta=consulta +"\n"+"id Seguimiento hospitalario:"+ rs.getInt("idSeguimientoHospitalario")
            	          +"\n"+"Fecha de actualizacion: "+ rs.getDate("fecha")+"\n"
            			  + "Funcionario:" + rs.getString("funcionario")+"\n"
	            		  + "Observaciones:"+ rs.getString("observacion")+"\n"
	            		  + "Tratamiento:"+ rs.getString("tratamiento")+"\n";

             	
             	

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
   *Metodo que consulta un Seguimiento Hospitalario en especifico
   * @param  pIdSeguimiento es el identificador del centro que se desea buscar en el sistema
   * @throws SQLException
   */
  public SeguimientoHospitalario consultarBitacora(int pIdSeguimiento){
    SeguimientoHospitalario  seg=null;
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT fecha, funcionario, observacion,"
         		+ " tratamiento, idHospitalizacion"
		        + " from SeguimientoHospitalario "
		        + " WHERE idSeguimientoHospitalario=  " +pIdSeguimiento ;

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
      {  
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");  
        String strDate = formatter.format(rs.getDate("fecha"));   
            	 
        SeguimientoHospitalario seg2 = new SeguimientoHospitalario(strDate,
            			                        rs.getString("funcionario"), rs.getString("observacion"), 
            			                        rs.getString("tratamiento"), rs.getInt("idHospitalizacion"));
            	 
        seg2.setNumeroSeguimiento(rs.getInt("idSeguimientoHospitalario"));
            	 
        seg=seg2;


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

    return seg;
  }

}
