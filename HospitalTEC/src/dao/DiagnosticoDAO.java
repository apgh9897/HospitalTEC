package dao;


import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import modelo.Diagnostico;
/**
*
* @author Yasuara Espinoza
*/
public class DiagnosticoDAO extends ConexionMySQL {
  /**
   * @param pDiagnostico
   * @param pIdCita
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public Diagnostico registrarDiagnostico(Diagnostico pDiagnostico, int pIdCita) {
    Diagnostico diag=null;
    PreparedStatement ps = null;
    Connection con = (Connection) conectar();
    String sql = "INSERT INTO Diagnostico (nombre, nivel, idCita, observaciones, idTratamiento) VALUES(?,?,?,?,?)";

    try {
		           
      ps = (PreparedStatement) con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	  ps.addBatch(sql);
	  ps.setString(1, pDiagnostico.getNombre());
	  ps.setString(2, pDiagnostico.getNivel());
	  ps.setInt(3, pIdCita);
	  ps.setString(4, pDiagnostico.getObservaciones());
	  ps.setInt(5, pDiagnostico.getTratamiento().getCodigo());
	  System.out.println(sql);
		           
      ps.execute();
		           
	  ResultSet rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
	  rs.next();
	  int codigo = rs.getInt(1);

	  pDiagnostico.setCodigo(codigo);// le aseigna la llave al objeto del arraylist
	  diag=pDiagnostico;
		           
      return diag;
    } catch (SQLException e) {
      if (e.getSQLState().startsWith("23")) { // mensaje de sql al querer insertar       	                                       //una llave primaria duplicada 
	  }
      return diag; 
	} finally {
	  try {
	    con.close();
	  } catch (SQLException e) {
		System.err.println(e);
	  }
    }
  }
		
  /**
   * @param pDiagnostico
   * @return
   */
  public boolean eliminarDiagnostico(Diagnostico pDiagnostico) {
    PreparedStatement ps = null;
	Connection con = (Connection) conectar();

	String sql = "DELETE FROM Diagnostico WHERE idDiagnostico=? ";

	try {
	  ps = (PreparedStatement) con.prepareStatement(sql);
	  ps.setInt(1, pDiagnostico.getCodigo());
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
   * Metodo que modifica el diagnostico
   * @param pDiagnostico
   * @return
   * @throws SQLException
   */
  public boolean modificarDiagnostico(Diagnostico pDiagnostico  ) {
    PreparedStatement ps = null;
	Connection con =  (Connection) conectar();

	String sql = "UPDATE Diagnostico SET nombre = ?, nivel = ?,"
	        		+ " observaciones = ?,idTratamiento = ?"
	        		+ " WHERE idDiagnostico =? ";

	try {
	  ps = (PreparedStatement) con.prepareStatement(sql);
	  ps.setString(1, pDiagnostico.getNombre());
	  ps.setString(2, pDiagnostico.getNivel());
	  ps.setString(3, pDiagnostico.getObservaciones());
	  ps.setInt(4,pDiagnostico.getTratamiento().getCodigo());
	  ps.setInt(5, pDiagnostico.getCodigo());

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
   *Metodo que consulta un diagnostico especifico
   * @param int pId es el identificador del diagnostico que se desea buscar en el sistema
   * @return
   * @throws SQLException
   */
  public Diagnostico consultarDiagnostico(int pId){
    Diagnostico diag=null;
	PreparedStatement ps2 = null;
	ResultSet rs2=null;
	Connection con2 = (Connection) conectar();

	String sql2 = "SELECT *"
			        + "FROM diagnostico"
			        + " WHERE idDiagnostico =  " +pId;

	try {
	  ps2 = (PreparedStatement) con2.prepareStatement(sql2);
	  rs2 = ps2.executeQuery();
	  while(rs2.next())
	  {     
	    TratamientoDAO tratadao=new TratamientoDAO();
	    Diagnostico diag2=new Diagnostico(rs2.getInt("idDiagnostico"),rs2.getNString("nombre"),
	            			 rs2.getNString("nivel"), rs2.getNString("observaciones"),
	            			 tratadao.consultarTratamiento(rs2.getInt("idTratamiento")) );
        diag=diag2;

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
	   return diag;
  }
	   
  /**
   * @param pNombreDiagnostico
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public int registrarDiagnosticoCatalogo(String pNombreDiagnostico) {
    int codigo=0;
    PreparedStatement ps = null;
    Connection con = (Connection) conectar();
    String sql = "INSERT INTO CatalogoDiagnostico ( descripcion) VALUES(?)";

    try {
		           
      ps = (PreparedStatement) con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	  ps.addBatch(sql);
      ps.setString(1,pNombreDiagnostico);
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
   * @param pNombreDiagnostico
   * @return
   */
  public boolean eliminarDiagnosticoCatalogo( String pNombreDiagnostico) {
    PreparedStatement ps = null;
	Connection con = (Connection) conectar();

	String sql = "DELETE FROM CatalogoDiagnostico WHERE  descripcion=?";

	try {
	  ps = (PreparedStatement) con.prepareStatement(sql);
	  ps.setString(1, pNombreDiagnostico);
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
  public String consultarDiagnosticoRangoFechas(String pCedula,String pFechaPiso, String pFechaTecho){
    String consulta="";
    PreparedStatement ps = null;
	ResultSet rs=null;
	Connection con = (Connection) conectar();

	String sql = "select diagnostico.idDiagnostico ,diagnostico.nombre, diagnostico.nivel,"
	       		        + " diagnostico.idCita, diagnostico.observaciones, diagnostico.idTratamiento" 
	       		        + " from diagnostico "
	       		        + " inner join cita " 
	       		        + " on cita.idCita=diagnostico.idCita "
	       		        + " where  cita.fechaCita >= " + "'"+pFechaPiso+ "'" + " AND cita.fechaCita <=  "+ "'"+pFechaTecho+"'"
	       		        + " AND cita.cedulaPaciente =  "+ "'" +pCedula + "'" ;



    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
	  //rs= ps.executeQuery(sql);
	  rs = ps.executeQuery();
	  //System.out.print(sql);
	  while(rs.next())
	  {
	    //System.out.print(rs.getString("nombre"));
	    consulta=consulta +"\n"+"id Diagnostico:"+ rs.getInt("idDiagnostico")+"\n"+"Nombre de Diagnostico: "+ 
	             rs.getString("nombre")+"\n"+ "Nivel:" + rs.getString("nivel")+"\n"+
	             "ID Cita:"+ rs.getInt("idCita")+"\n"+ "Observaciones:" + rs.getString("observaciones")+"\n"+
	             "ID Tratamiento:"+ rs.getInt("idTratamiento");

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
   * @param pNivel
   * @return
   */
  public String consultarDiagnosticoPorNivel(String pCedula,String pNivel){
    String consulta="";
    PreparedStatement ps = null;
	ResultSet rs=null;
	Connection con = (Connection) conectar();

	String sql = "select diagnostico.idDiagnostico ,diagnostico.nombre, diagnostico.nivel,"
	       		 + " diagnostico.idCita, diagnostico.observaciones, diagnostico.idTratamiento" 
	       		 + " from diagnostico "
	       		 + " inner join cita " 
	       		 + " on cita.idCita=diagnostico.idCita "
	       		 + " where  diagnostico.nivel = " + "'"+pNivel+ "'" 
	       		 + " AND cita.cedulaPaciente =  " + "'"+pCedula + "'" ;



	try {
	  ps = (PreparedStatement) con.prepareStatement(sql);
	  //rs= ps.executeQuery(sql);
	  rs = ps.executeQuery();
	  //System.out.print(sql);
	  while(rs.next())
	  {
	    //System.out.print(rs.getString("nombre"));
	    consulta=consulta +"\n"+"id Diagnostico:"+ rs.getInt("idDiagnostico")+"\n"+"Nombre de Diagnostico: "+ 
	             rs.getString("nombre")+"\n"+ "Nivel:" + rs.getString("nivel")+"\n"+
	             "ID Cita:"+ rs.getInt("idCita")+"\n"+ "Observaciones:" + rs.getString("observaciones")+"\n"+
	              "ID Tratamiento:"+ rs.getInt("idTratamiento");

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
   * @param pNombreDiagnostico
   * @return
   */
  public String consultarDiagnosticoPorNombre(String pCedula,String pNombreDiagnostico){
    String consulta="";
    PreparedStatement ps = null;
	ResultSet rs=null;
	Connection con = (Connection) conectar();

	String sql = "select diagnostico.idDiagnostico ,diagnostico.nombre, diagnostico.nivel,"
	       	   + " diagnostico.idCita, diagnostico.observaciones, diagnostico.idTratamiento" 
	       	   + " from diagnostico "
	       	   + " inner join cita " 
	       	   + " on cita.idCita=diagnostico.idCita "
	       	   + " where  diagnostico.nombre = " + "'"+pNombreDiagnostico+ "'" 
	       	   + " AND cita.cedulaPaciente =  " + "'" +pCedula + "'"  ;



	try {
	  ps = (PreparedStatement) con.prepareStatement(sql);
	  //rs= ps.executeQuery(sql);
	  rs = ps.executeQuery();
	  //System.out.print(sql);
	  while(rs.next()){
	    //System.out.print(rs.getString("nombre"));
	    consulta=consulta +"\n"+"id Diagnostico:"+ rs.getInt("idDiagnostico")+"\n"+"Nombre de Diagnostico: "+ 
	            		   rs.getString("nombre")+"\n"+ "Nivel:" + rs.getString("nivel")+"\n"+
	            		   "ID Cita:"+ rs.getInt("idCita")+"\n"+ "Observaciones:" + rs.getString("observaciones")+"\n"+
	            		   "ID Tratamiento:"+ rs.getInt("idTratamiento");

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
   * @param pNivel
   * @return
   */
  public String consultarCantidadNivelDiagnostico(String pNivel){
    String consulta="";
    PreparedStatement ps = null;
	ResultSet rs=null;
	Connection con = (Connection) conectar();

	String sql = "select diagnostico.nivel," + 
	       		 " COUNT(idDiagnostico) as cant_diagnosticos" + 
	       		 " from diagnostico " + 
	       		 " where diagnostico.nivel= " + "'"+pNivel+"'"+
	       		 " group by nivel" ;


	 //System.out.print(sql);
	 try {
	   ps = (PreparedStatement) con.prepareStatement(sql);
	   //rs= ps.executeQuery(sql);
	   rs = ps.executeQuery();
	           
	   while(rs.next()){
	     //System.out.print(rs.getString("nombre"));
	     consulta=consulta +"\n"+"Nivel de Diagnostico:"+ rs.getString("nivel")+"\n"+"Cantidad: "+ 
	     rs.getInt("cant_diagnosticos");

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
  public String consultarCantidadDiagnosticosGeneral(){
    String consulta="";
    PreparedStatement ps = null;
	ResultSet rs=null;
	Connection con = (Connection) conectar();

	String sql = "select " + 
	       		 " COUNT(idDiagnostico) as cant_diagnosticos" + 
	       		 " from diagnostico ";


	 //System.out.print(sql);
	 try {
	    ps = (PreparedStatement) con.prepareStatement(sql);
	    //rs= ps.executeQuery(sql);
	    rs = ps.executeQuery();
	           
	    while(rs.next()){
	     //System.out.print(rs.getString("nombre"));
	     consulta=consulta +"Cantidad general de diagnosticos: "+ rs.getInt("cant_diagnosticos");

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
  public String consultarCantidadDiagnosticosPorEspecialidada(String pEspecialidad){
    String consulta="";
    PreparedStatement ps = null;
	ResultSet rs=null;
	Connection con = (Connection) conectar();

	String sql = "select " + 
	       		 " COUNT(diagnostico.idDiagnostico) as cant_diagnosticos " + 
	       		 " from diagnostico " + 
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
	           
	  while(rs.next()){
	    //System.out.print(rs.getString("nombre"));
	    consulta=consulta +"\n"+"Especialidad:"+ pEspecialidad+"\n"+"Cantidad de Diagnosticos: "+ 
	             rs.getInt("cant_diagnosticos");

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
  public String consultarCantidadDiagnosticosPorPaciente(String pCedulaPaciente){
    String consulta="";
    PreparedStatement ps = null;
	ResultSet rs=null;
	Connection con = (Connection) conectar();

	String sql = "select " + 
	       		 " COUNT(diagnostico.idDiagnostico) as cant_diagnosticos " + 
	       		 " from diagnostico " + 
	       		 " inner join cita  " + 
	       		 " on cita.idCita=diagnostico.idCita " + 
	       		 " where  cita.cedulaPaciente= " + "'"+ pCedulaPaciente + "'"  ;


	//System.out.print(sql);
	try {
	  ps = (PreparedStatement) con.prepareStatement(sql);
	  //rs= ps.executeQuery(sql);
	  rs = ps.executeQuery();
	           
	  while(rs.next()){
	    consulta=consulta +"\n"+"Cedula Paciente:"+ pCedulaPaciente+"\n"+"Cantidad de Diagnosticos: "+ 
	             rs.getInt("cant_diagnosticos");

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
