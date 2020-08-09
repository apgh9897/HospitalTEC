package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import modelo.Hash;
import modelo.Paciente;

/**
*
* @author Yasuara Espinoza
*/
public class PacienteDAO extends ConexionMySQL {
  
  /**
   * 
   * @return 
   * @param pFuncionario
   * @throws java.lang.ClassNotFoundException
   * 
   */
  public boolean registrarPaciente(Paciente pPaciente) throws ClassNotFoundException, SQLException{

    PreparedStatement ps = null;
    Connection con = conectar();
    String pass= Hash.sha1(pPaciente.getContrasenna());// encripta contrasena
      
    String sql  = "INSERT INTO paciente(cedula, nombreCompleto, fechaNacimiento, tipoSangre,"
    		    + " nacionalidad,residencia, usuario, contrasenia) VALUES( '"+ pPaciente.getCedula()+"',"
    		    + "'"+ pPaciente.getNombre()+"', '"+pPaciente.getFechaDeNacimiento()+"',"
    		    + "'"+pPaciente.getTipoDeSangre()+"','"+pPaciente.getNacionalidad()+"',"
    		    +"'"+pPaciente.getProvincia()+"','"+pPaciente.getUsuario()+"','"+pass+"')";
    		  
    try {
      ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
          
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
   * 
   * @return 
   * @param pPaciente
   * @throws java.lang.ClassNotFoundException
   * 
   */
  
  public boolean modificarPaciente(Paciente pPaciente) {
    PreparedStatement ps = null;
    Connection con =  conectar();

    String sql = "UPDATE Paciente SET nombreCompleto=?,"
    		+ "tipoSangre=? ,nacionalidad=? , residencia=?, usuario=?,"
    		+ "contrasenia=?  WHERE cedula =?";
    
    String pass= Hash.sha1(pPaciente.getContrasenna());// encripta contrasena
    
    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, pPaciente.getNombre());
      ps.setString(2, pPaciente.getTipoDeSangre());
      ps.setString(3, pPaciente.getNacionalidad());
      ps.setString(4, pPaciente.getProvincia());
      ps.setString(5, pPaciente.getUsuario());
      ps.setString(6, pass);
      ps.setString(7, pPaciente.getCedula());
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
   *Metodo que elimina un funcionario
   * @param pCedula
   * @return
   * @throws SQLException
   */
  public boolean eliminarPaciente(String pCedula){
    PreparedStatement ps = null;
    Connection con = conectar();
  	
  	try {
      String query="DELETE FROM Paciente WHERE cedula=?";
      ps=con.prepareStatement(query);
  	  ps.setString(1, pCedula);
  		
  	  System.out.println(ps);
  		
  	  ps.executeUpdate();
  	  return true;
  	} catch (Exception e) {
  	  System.out.println(e);
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
   *Metodo que consulta una funcionario en especifico
   * @param  pCedula
   * @return 
   * @throws SQLException
   */
  public Paciente consultarPaciente(String pCedula){
	Paciente pan=null;
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT *"
		        + " from Paciente "
		        + " WHERE cedula="+"'"+pCedula+"'";

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
        { 
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaComoCadena = sdf.format(rs.getDate("fechaNacimiento"));
        Paciente pan2 = new Paciente(rs.getString("usuario"), rs.getString("contrasenia"),pCedula,
        		rs.getString("nombreCompleto"), fechaComoCadena, rs.getString("tipoSangre"),
        		rs.getString("nacionalidad"), rs.getString("residencia"));
    
        pan=pan2;


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

   return pan;
  }

}