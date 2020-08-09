package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import modelo.Funcionario;
import modelo.Hash;

/**
*
* @author Yasuara Espinoza
*/
public class FuncionarioDAO extends ConexionMySQL {
  
  /**
   * 
   * @return 
   * @param pFuncionario
   * @throws java.lang.ClassNotFoundException
   * 
   */
  public boolean registrarAdministrador(Funcionario pFuncionario) throws ClassNotFoundException, 
                 SQLException{

    PreparedStatement ps = null;
    Connection con = conectar();
    String pass= Hash.sha1(pFuncionario.getContrasenna());// encripta contrasena
      
    String sql  = "INSERT INTO funcionario(cedula, nombreCompleto, tipoFuncionario, fechaIngreso,"
    		    + " areaDeTrabajo,usuario, contrasenia) VALUES( '"+ pFuncionario.getCedula()+"',"
    		    + "'"+ pFuncionario.getNombre()+"', '"+pFuncionario.getTipoDeFuncionario()+"',"
    		    + "'"+pFuncionario.getFechaDeIngreso()+"','"+pFuncionario.getAreaDeTrabajo()+"',"
    		    +"'"+pFuncionario.getUsuario()+"','"+pass+"')";
    		  
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
   * @param pFuncionario
   * @throws java.lang.ClassNotFoundException
   * 
   */
  
  public boolean modificarFuncionario(Funcionario pFuncionario) {
    PreparedStatement ps = null;
    Connection con =  conectar();

    String sql = "UPDATE Funcionario SET nombreCompleto=?,tipoFuncionario=?,"
    		+ "areaDeTrabajo=? ,usuario=? , contrasenia=?  WHERE cedula =?";
    
    String pass= Hash.sha1(pFuncionario.getContrasenna());// encripta contrasena
    
    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, pFuncionario.getNombre());
      ps.setString(2, pFuncionario.getTipoDeFuncionario());
      ps.setString(3, pFuncionario.getAreaDeTrabajo());
      ps.setString(4, pFuncionario.getUsuario());
      ps.setString(5, pass);
      ps.setString(6, pFuncionario.getCedula());
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
  public void eliminarAdministrador(String pCedula){
    PreparedStatement ps = null;
    Connection con = conectar();
  	
  	try {
      String query="DELETE FROM Funcionario WHERE cedula=?";
      ps=con.prepareStatement(query);
  	  ps.setString(1, pCedula);
  		
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
   *Metodo que consulta una funcionario en especifico
   * @param  pCedula
   * @return 
   * @throws SQLException
   */
  public Funcionario consultarFuncionario(String pCedula){
    Funcionario fun=null;
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT *"
		        + " from Funcionario "
		        + " WHERE cedula="+"'"+pCedula+"'";

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
        {  
        Funcionario fun2 = new Funcionario();
        fun2.setCedula(pCedula);
        fun2.setNombre(rs.getString("nombreCompleto"));
        fun2.setTipoDeFuncionario(rs.getString("tipoFuncionario"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaComoCadena = sdf.format(rs.getDate("fechaIngreso"));
        fun2.setFechaDeIngreso(fechaComoCadena);
        fun2.setAreaDeTrabajo(rs.getString("areaDeTrabajo"));
        fun2.setUsuario(rs.getString("usuario"));
        fun2.setContrasenna(rs.getString("contrasenia"));

        fun=fun2;


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

   return fun;
  }

}