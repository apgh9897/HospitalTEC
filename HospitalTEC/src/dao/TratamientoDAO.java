package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import modelo.Tratamiento;
/**
*
* @author YasuaraEspinoza
*/

public class TratamientoDAO extends ConexionMySQL {

  /**
   * @param pTratamiento
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public Tratamiento registrarTratamiento(Tratamiento pTratamiento) {
    Tratamiento trata=null;
	PreparedStatement ps = null;
	Connection con = (Connection) conectar();
	String sql = "INSERT INTO Tratamiento (nombre, dosis, tipoTratamiento) VALUES(?,?,?)";

	try {
	           
	  ps = (PreparedStatement) con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	  ps.addBatch(sql);
	  ps.setString(1, pTratamiento.getNombre());
	  ps.setInt(2, pTratamiento.getDosis());
	  ps.setString(3, pTratamiento.getTipoTratamiento());
	  System.out.println(sql);
	           
	  ps.execute();
	           
	  ResultSet rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
	  rs.next();
	  int codigo = rs.getInt(1);

	  pTratamiento.setCodigo(codigo);;// le aseigna la llave al objeto del arraylist
	           
	  trata=pTratamiento;
	  return trata;
	           //return true;
	  } catch (SQLException e) {
	    if (e.getSQLState().startsWith("23")) { // mensaje de sql al querer insertar
	        	                                       //una llave primaria duplicada 

	    }
	  return trata;
	  } finally {
	    try {
	      con.close();
	    } catch (SQLException e) {
	      System.err.println(e);
	    }
	 }
  }
	
  /**
   * @param pTratamiento
   * @return
   */
  public boolean eliminarTratamiento(Tratamiento pTratamiento) {
    PreparedStatement ps = null;
    Connection con = (Connection) conectar();

    String sql = "DELETE FROM Tratamiento WHERE idTratamiento=? ";

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      ps.setInt(1, pTratamiento.getCodigo());
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
   * Metodo que modifica un tratamiento
   * @param pTratamiento
   * @return
   * @throws SQLException
   */
  public boolean modificarTratamiento(Tratamiento pTratamiento  ) {
    PreparedStatement ps = null;
    Connection con =  (Connection) conectar();

    String sql = "UPDATE Tratamiento SET nombre = ?, dosis = ?,"
       		+ " tipoTratamiento = ?"
       		+ " WHERE idTratamiento =? ";

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      ps.setString(1, pTratamiento.getNombre());
      ps.setInt(2, pTratamiento.getDosis());
      ps.setString(3, pTratamiento.getTipoTratamiento());
      ps.setInt(4,pTratamiento.getCodigo());

           
           
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
   *Metodo que consulta un tratamiento especifico
   * @param int pId es el identificador del tratamiento que se desea buscar en el sistema
   * @return
   * @throws SQLException
   */
  public Tratamiento consultarTratamiento(int pId){
    Tratamiento trat=null;
 	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idTratamiento, nombre,dosis, tipoTratamiento,"
		        + "FROM tratamiento"
		        + " WHERE idTratamiento =  " +pId;

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
      {            			  
        Tratamiento trat2 =new Tratamiento(rs.getNString("nombre"),rs.getInt("dosis"),
        rs.getNString("tipoTratamiento"));
        trat2.setCodigo(rs.getInt("idDiagnostico"));
           	 
        trat=trat2;

           	 
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
    return trat;
  }
   
  /**
   * @param pNombreTratamiento
   * @param pNombreDiagnostico
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
  */
  public int registrarTratamientoCatalogo(String pNombreTratamiento, String pNombreDiagnostico) {
    int codigo =0;
    PreparedStatement ps = null;
	Connection con = (Connection) conectar();
	String sql = "INSERT INTO CatalogoTratamiento (catalogoDiagnostico, descripcion) VALUES(?,?)";

	try {
	           
	  ps = (PreparedStatement) con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	  ps.addBatch(sql);
	  ps.setString(1,pNombreDiagnostico);
	  ps.setString(2, pNombreTratamiento);
	  System.out.println(sql);
	           
	  ps.execute();
	           
	  ResultSet rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
	  rs.next();
	  codigo = rs.getInt(1);

	  //pTratamiento.setCodigo(codigo);// le aseigna la llave al objeto del arraylist
	  System.out.println(codigo);
	  return codigo;
	} catch (SQLException e) {
	  if (e.getSQLState().startsWith("23")) { // mensaje de sql al querer insertar
	        	                                       //una llave primaria duplicada 

	  }
	  return codigo;
	} finally {
	  try {
	    con.close();
	  } catch (SQLException e) {
	    System.err.println(e);
	  }
	}
  }
	
  /**
   * @param pNombreTratamiento
   * @param pNombreDiagnostico
   * @return
   */
  public boolean eliminarTratamientoCatalogo(String pNombreTratamiento, String pNombreDiagnostico) {
    PreparedStatement ps = null;
    Connection con = (Connection) conectar();

    String sql = "DELETE FROM CatalogoTratamiento WHERE catalogoDiagnostico=? and descripcion=?";

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      ps.setString(1, pNombreDiagnostico);
      ps.setString(2, pNombreTratamiento);
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
   * @param pCedula
   * @param pFechaPiso
   * @param pFechaTecho
   * @return
   */
  public String consultarTratamientoRangoFechas(String pCedula,String pFechaPiso, String pFechaTecho){
    String consulta="";
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select tratamiento.idTratamiento ,tratamiento.nombre, tratamiento.dosis, tratamiento.tipoTratamiento" 
       		        + " from tratamiento "
       		        + " inner join diagnostico " 
       		        + " on diagnostico.idTratamiento=tratamiento.idTratamiento " 
       		        + " inner join cita " 
       		        + " on cita.idCita=diagnostico.idCita "
       		        + " where  cita.fechaCita >= " + "'"+pFechaPiso+ "'" + " AND cita.fechaCita <=  "+ "'"+pFechaTecho+"'"
       		        + " AND cita.cedulaPaciente =  " + "'"+pCedula + "'"  ;



   try {
     ps = (PreparedStatement) con.prepareStatement(sql);
     //rs= ps.executeQuery(sql);
     rs = ps.executeQuery();
     //System.out.print(sql);
     while(rs.next())
     {
     //System.out.print(rs.getString("nombre"));
       Tratamiento tra=new Tratamiento(rs.getString("nombre"),rs.getInt("dosis"),
       rs.getString("tipoTratamiento"));
       tra.setCodigo(rs.getInt("idTratamiento"));
       consulta=consulta +"\n"+"id Tratamiento:"+ tra.getCodigo()+"\n"+"Nombre de Tratamiento: "+ 
                tra.getNombre()+"\n"+ "Dosis:" + tra.getDosis()+"\n"+
                "Tipo de tratamiento:"+ tra.getTipoTratamiento()+"\n";

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
   * @param pCedula
   * @param pTipoTratamiento
   * @return
   */
  public String consultarTratamientoPorTipo(String pCedula,String pTipoTratamiento){
    String consulta="";
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select tratamiento.idTratamiento ,tratamiento.nombre, tratamiento.dosis, tratamiento.tipoTratamiento" 
       		        + " from tratamiento "
       		        + " inner join diagnostico " 
       		        + " on diagnostico.idTratamiento=tratamiento.idTratamiento " 
       		        + " inner join cita " 
       		        + " on cita.idCita=diagnostico.idCita "
       		        + " where  tratamiento.tipoTratamiento = "+ "'"+pTipoTratamiento+"'"
       		        + " AND cita.cedulaPaciente =  " + "'" +pCedula + "'"  ;


    //System.out.print(sql);
    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      //rs= ps.executeQuery(sql);
      rs = ps.executeQuery();
           
      while(rs.next())
      {
        //System.out.print(rs.getString("nombre"));
        Tratamiento tra=new Tratamiento(rs.getString("nombre"),rs.getInt("dosis"),
            		                          rs.getString("tipoTratamiento"));
        tra.setCodigo(rs.getInt("idTratamiento"));
        consulta=consulta +"\n"+"id Tratamiento:"+ tra.getCodigo()+"\n"+"Nombre de Tratamiento: "+ 
        	                      tra.getNombre()+"\n"+ "Dosis:" + tra.getDosis()+"\n"+
            		              "Tipo de tratamiento:"+ tra.getTipoTratamiento()+"\n";

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
   * @param pCedula
   * @param pNombreTratamiento
   * @return
   */
  public String consultarTratamientoPorNombre(String pCedula,String pNombreTratamiento){
    String consulta="";
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select tratamiento.idTratamiento ,tratamiento.nombre, tratamiento.dosis, tratamiento.tipoTratamiento" 
       		        + " from tratamiento "
       		        + " inner join diagnostico " 
       		        + " on diagnostico.idTratamiento=tratamiento.idTratamiento " 
       		        + " inner join cita " 
       		        + " on cita.idCita=diagnostico.idCita "
       		        + " where  tratamiento.nombre = "+ "'"+pNombreTratamiento+"'"
       		        + " AND cita.cedulaPaciente =  "+ "'"  +pCedula + "'"  ;


     //System.out.print(sql);
     try {
       ps = (PreparedStatement) con.prepareStatement(sql);
       //rs= ps.executeQuery(sql);
       rs = ps.executeQuery();
           
       while(rs.next())
       {
         //System.out.print(rs.getString("nombre"));
         Tratamiento tra=new Tratamiento(rs.getString("nombre"),rs.getInt("dosis"),
            		                          rs.getString("tipoTratamiento"));
         tra.setCodigo(rs.getInt("idTratamiento"));
         consulta=consulta +"\n"+"id Tratamiento:"+ tra.getCodigo()+"\n"+"Nombre de Tratamiento: "+ 
        	                      tra.getNombre()+"\n"+ "Dosis:" + tra.getDosis()+"\n"+
            		              "Tipo de tratamiento:"+ tra.getTipoTratamiento()+"\n";

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
   * @param pNombreTratamiento
   * @return
   */
  public String consultarCantidadTipoTratamiento(String pNombreTratamiento){
    String consulta="";
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select tratamiento.tipoTratamiento," + 
       		        " COUNT(idTratamiento) as cant_tratamientos" + 
       		        " from tratamiento " + 
       		        " where tratamiento.tipoTratamiento= " + "'"+pNombreTratamiento+"'"+
       		        " group by tipoTratamiento" ;


    //System.out.print(sql);
    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      //rs= ps.executeQuery(sql);
      rs = ps.executeQuery();
           
      while(rs.next())
      {
        //System.out.print(rs.getString("nombre"));
        consulta=consulta +"\n"+"Tipo Tratamiento:"+ rs.getString("tipoTratamiento")+"\n"+"Cantidad: "+ 
            		             rs.getInt("cant_tratamientos");

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
   * @return
   */
  public String consultarCantidadTratamientoGeneral(){
    String consulta="";
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select " + 
       		        " COUNT(idTratamiento) as cant_tratamientos" + 
       		        " from tratamiento ";


     //System.out.print(sql);
    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      //rs= ps.executeQuery(sql);
      rs = ps.executeQuery();
           
      while(rs.next())
      {
        //System.out.print(rs.getString("nombre"));
        consulta=consulta +"Cantidad general de tratamientos: "+ rs.getInt("cant_tratamientos");

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
   * @param pEspecialidad
   * @return
   */
  public String consultarCantidadTratamientoPorEspecialidada(String pEspecialidad){
    String consulta="";
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select " + 
       		        " COUNT(tratamiento.idTratamiento) as cant_tratamientos " + 
       		        " from tratamiento " + 
       		        " inner join diagnostico  " + 
       		        " on diagnostico.idTratamiento=tratamiento.idTratamiento  " + 
       		        " inner join cita  " + 
       		        " on cita.idCita=diagnostico.idCita " + 
       		        " inner join TipoAreaDeTrabajo " + 
       		        " on TipoAreaDeTrabajo.descripcion=cita.areaDeTrabajo" + 
       		        " where  TipoAreaDeTrabajo.descripcion= " +"'"+pEspecialidad+"'" ;


    //System.out.print(sql);
    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      //rs= ps.executeQuery(sql);
      rs = ps.executeQuery();
           
      while(rs.next())
      {
        //System.out.print(rs.getString("nombre"));
        consulta=consulta +"\n"+"Especialidad:"+ pEspecialidad+"\n"+"Cantidad de Tratamientos: "+ 
            		             rs.getInt("cant_tratamientos");

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
   * @param pCedulaPaciente
   * @return
   */
  public String consultarCantidadTratamientoPorPaciente(String pCedulaPaciente){
    String consulta="";
	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select " + 
       		     " COUNT(tratamiento.idTratamiento) as cant_tratamientos " + 
       		     " from tratamiento " + 
       		     " inner join diagnostico  " + 
       		     " on diagnostico.idTratamiento=tratamiento.idTratamiento  " + 
       		     " inner join cita  " + 
       		     " on cita.idCita=diagnostico.idCita " + 
       		     " where  cita.cedulaPaciente= " + "'" +pCedulaPaciente + "'" ;


   //System.out.print(sql);
   try {
     ps = (PreparedStatement) con.prepareStatement(sql);
     //rs= ps.executeQuery(sql);
     rs = ps.executeQuery();
           
     while(rs.next())
     {
       //System.out.print(rs.getString("nombre"));
       consulta=consulta +"\n"+"Cedula Paciente:"+ pCedulaPaciente+"\n"+"Cantidad de Tratamientos: "+ 
                rs.getInt("cant_tratamientos");

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
  
}
