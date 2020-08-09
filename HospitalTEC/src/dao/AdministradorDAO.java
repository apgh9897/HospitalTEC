package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Administrador;
import modelo.Hash;

/**
*
* @author Yasuara Espinoza
*/
public class AdministradorDAO extends ConexionMySQL {
  /**
  *Metodo para el inicio de sesion
  * @param pUsuario
  * @param pContrasenia
  * @return
  * @throws SQLException
  */
	
  public int iniciarSesion(String pUsuario, String pContrasenia) {
    int tipoUsuario=0;
    PreparedStatement ps = null;
    Connection con = conectar();
    ResultSet rs=null;
    String pass= Hash.sha1(pContrasenia);// encripta contrasena
      
    String sql = "SELECT usuario WHERE usuario=? and contrasenia =? from Administrador";

    try {
      ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1,pUsuario);
      ps.setString(2,pass);
          
      System.out.println(sql);
          
      ps.execute();
      rs = ps.executeQuery();
          
      if(rs.next()) {
        tipoUsuario=1;  
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
    if(tipoUsuario==0) {
      PreparedStatement ps2 = null;
      Connection con2 = conectar();
      ResultSet rs2=null;

      String sql2 = "SELECT * WHERE usuario=? and contrasenia =? from Funcionario";

      try {
        ps2 = con2.prepareStatement(sql2,PreparedStatement.RETURN_GENERATED_KEYS);
        ps2.setString(1,pUsuario);
        ps2.setString(2,pass);
              
        System.out.println(sql2);
              
        ps2.execute();
        rs2 = ps2.executeQuery();
              
        if(rs2.next()) {
          if(rs2.getNString("tipoFuncionaio").equals("DOCTOR")) {
            tipoUsuario=2;
          }if(rs2.getNString("tipoFuncionaio").equals("ENFERMERO")) {
            tipoUsuario=3;
          }if(rs2.getNString("tipoFuncionaio").equals("SECRETARIO")) {
            tipoUsuario=4;
          }
        }
              
      } catch (SQLException e) {
        System.err.println(e);

      } finally {
        try {
          con2.close();
        } catch (SQLException e) {
          System.err.println(e);
        }
      }
    }if(tipoUsuario==0) {
      PreparedStatement ps3 = null;
      Connection con3 = conectar();
      ResultSet rs3=null;

      String sql3 = "SELECT * WHERE usuario=? and contrasenia =? from Paciente";

      try {
        ps3 = con3.prepareStatement(sql3,PreparedStatement.RETURN_GENERATED_KEYS);
        ps3.setString(1,pUsuario);
        ps3.setString(2,pass);
              
        System.out.println(sql3);
              
        ps3.execute();
        rs3 = ps3.executeQuery();
              
        if(rs3.next()) {
            tipoUsuario=5;
        }
              
      } catch (SQLException e) {
        System.err.println(e);

      } finally {
        try {
         con3.close();
        } catch (SQLException e) {
          System.err.println(e);
        }
      }
    }
	return tipoUsuario;
  }
  
  /**
   * 
   * @return 
   * @param pAdministrador
   * @throws java.lang.ClassNotFoundException
   * 
   */
  public boolean registrarAdministrador(Administrador pAdministrador) throws ClassNotFoundException, SQLException{

    PreparedStatement ps = null;
    Connection con = conectar();
    String pass= Hash.sha1(pAdministrador.getContrasenna());// encripta contrasena
      
    String sql  = "INSERT INTO administrador(usuario, contrasenia) VALUES(?,?)"; 
    try {
      ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1,pAdministrador.getUsuario());
      ps.setString(2,pass);
          
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
   * @param pAdministrador
   * @throws java.lang.ClassNotFoundException
   * 
   */
  
  public boolean modificarAdministrador(Administrador pAdministrador) {
    PreparedStatement ps = null;
    Connection con =  conectar();

    String sql = "UPDATE Administrador SET contrasenia=? WHERE usuario =?";
    String pass= Hash.sha1(pAdministrador.getContrasenna());// encripta contrasena
    
    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, pass);
      ps.setString(2, pAdministrador.getUsuario());
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
   *Metodo que elimina un administrador
   * @param pUsuario
   * @return
   * @throws SQLException
   */
  public void eliminarAdministrador(String pUsuario){
    PreparedStatement ps = null;
    Connection con = conectar();
  	
  	try {
      String query="DELETE FROM Administrador WHERE usuario=?";
      ps=con.prepareStatement(query);
  	  ps.setString(1, pUsuario);
  		
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
   *Metodo que consulta un administrador en especifico
   * @param  pUsuario
   * @return 
   * @throws SQLException
   */
  public Administrador consultarAdministrador(String pUsuario){
    Administrador admi=null;
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT *"
		        + " from Administrador "
		        + " WHERE usuario= '"+pUsuario+"'";

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
        {  
        Administrador admi2 = new Administrador(rs.getString("usuario"), rs.getString("contrasenia"));

        admi=admi2;


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

   return admi;
  }

}
