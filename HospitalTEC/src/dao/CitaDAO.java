package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cita;
import modelo.Paciente;
/**
*
* @author Maria Jose Placido
*/
public class CitaDAO extends ConexionMySQL{

    
  /**
   *Metodo que registra las citas en la base de datos
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public Cita registrarCita (Cita cita) {
    PreparedStatement ps = null;
    Cita citaD = new Cita();
    Connection con = conectar();

    String sql = "INSERT INTO Cita (areaDeTrabajo,fechaCita, horaCita, observaciones,"
        		+ "estadoCita, cedulaPaciente) VALUES(?,?,?,?,?,?)";

    try {
      ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, cita.getAreaDeAplicacion());
      ps.setString(2, cita.getFechaDeCita());
      ps.setString(3,cita.getHoraDeCita());
      ps.setString(4, cita.getObservaciones());
      ps.setString(5, cita.getEstadoDeCita());
      ps.setString(6, cita.getPaciente().getCedula());
            
            
      System.out.println(sql);
            
      ps.execute();
            
      rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
      rs.next();
      int idCita = rs.getInt(1);
            
      //System.out.print(id);
            
      cita.setIdentificador(idCita);;// le aseigna la llave al objeto del arraylist
      citaD = cita;
            
      JOptionPane.showMessageDialog(null, "Su cita ha sido regitrada"); 
            
      return citaD;
   } catch (SQLException e) {
      System.err.println(e);
      return citaD;
   } finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
   }
  }
    
  /**
   *Metodo que elimina las citas de un paciente
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public void eliminarCita(Cita cita){
    PreparedStatement ps = null;
    Connection con = conectar();
    	
    try {
      String query="DELETE FROM Cita WHERE idCita=?";
      ps=con.prepareStatement(query);
      ps.setInt(1, cita.getIdentificador());
    		
      System.out.println(ps);
      JOptionPane.showMessageDialog(null,"La cita ha sido eliminada");
    		
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
   *Metodo que modifica las citas de un paciente
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public boolean modificarCita(Cita cita) {
    PreparedStatement ps = null;
    Connection con =  conectar();

    String sql = "UPDATE Cita SET areaDeTrabajo =?, fechaCita =?, horaCita=?, observaciones=?, estadoCita =? WHERE cedulaPaciente =? ";

    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, cita.getAreaDeAplicacion());
      ps.setString(2, cita.getFechaDeCita());
      ps.setString(3, cita.getHoraDeCita());
      ps.setString(4, cita.getObservaciones());
      ps.setString(5, cita.getEstadoDeCita());
      ps.setString(6, cita.getPaciente().getCedula());
            
            
      ps.execute();

        JOptionPane.showMessageDialog(null,"La cita ha sido modificada");
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
   *Metodo que consulta una cita en especifico
   * @param int pId es el identificador de la cita que se desea buscar en el sistema
   * @return
   * @throws SQLException
   */
  public String consultarCita(int pId){
    String consulta="";
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita,"
 				+ "cedula,nombreCompleto,fechaNacimiento,tipoSangre,nacionalidad,residencia"
		        + " from paciente "
		        + " inner join cita " 
		        + " on paciente.cedula = cita.cedulaPaciente"
		        + " WHERE idCita =  " +pId;

    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
        {

            			  
        Paciente paciente = new Paciente(rs.getString("usuario"), rs.getString("contrasenia"),
             			rs.getString("cedula"),rs.getString("nombreCompleto"), rs.getString("fechaNacimiento"),
             			rs.getString("tipoSangre"),rs.getString("nacionalidad"),rs.getString("residencia") );
             	
             	
        Cita cita = new Cita(rs.getString("estadoCita"),rs.getString("areaDeTrabajo"),
                          rs.getString("horaCita"), rs.getString("observaciones"), paciente);
             	
        cita.setIdentificador(rs.getInt("idCita"));
             	
        consulta=consulta +"\n"+"id Cita:"+ cita.getIdentificador()+"\n"+"Especialidad: "+ 
 	                      cita.getAreaDeAplicacion()+"\n"+ "Fecha de la Cita:" + cita.getFechaDeCita()+"\n"+
 	                      "Hora de la cita:"+ cita.getHoraDeCita()+"\n" + 
 	                      "Observaciones:" + cita.getObservaciones()+"\n"+
 	                      "Estado de la cita:" + cita.getEstadoDeCita() +"\n"+
 	                      "Nombre del Paciente:" + cita.getPaciente().getNombre()+"\n";
             	

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
   *Metodo que cancela las citas de un paciente
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public void cancelarCita(Cita cita){
    PreparedStatement ps = null;
    Connection con = conectar();
    	
    try {
      String query="UPDATE Cita SET  estadoCita = 'CANCELADA' Cita WHERE idCita=?";
      ps=con.prepareStatement(query);
      ps.setInt(1, cita.getIdentificador());
    		
      System.out.println(ps);
      JOptionPane.showMessageDialog(null,"La cita ha sido cancelada");
    		
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
   *Metodo que actualiza el estado las citas de un paciente en la base de datos
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public void actualizarCita(Cita cita ){
    PreparedStatement ps = null;
    Connection con = conectar();
        
    try {
      String query="UPDATE Cita SET estadoCita=? WHERE idCita=?";
      ps=con.prepareStatement(query);
      ps.setString(1, cita.getEstadoDeCita());

      System.out.println(ps);
      JOptionPane.showMessageDialog(null,"El estado de su cita ha sido actualizado");

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
   *Metodo que registra los posibles estados de las citas en la base de datos
   * @param Cita cita
   * @return
   * @throws SQLException
   */
  public boolean registrarEstadoCita (String pDescripcion) {
    PreparedStatement ps = null;
    Connection con = conectar();

    String sql = "INSERT INTO EstadoCita (descripcion) VALUES(?)";

    ResultSet rs=null;
        
    try {
      ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, pDescripcion);
            
      System.out.println(sql);
            
      ps.execute();
            
      rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
      rs.next();
      //int idTipo = rs.getInt(1);
            
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

    
  /**MODULO DE PACIENTES, DOCTOR, ENFERMERO Y SECRETARIO
   *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
   *la fecha de las citas
   * @param pCedula
   * @param pFechaPiso
   * @param pFechaTecho
   * @return
   * @throws SQLException
   */
  public String consultarCitaRangoFechasPaciente(String pCedula,String pFechaPiso, String pFechaTecho){
    String consulta="";
 	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita," 
        				+ "cedula,nombreCompleto,fechaNacimiento,tipoSangre,nacionalidad,residencia,"
        				+ "usuario, contrasenia"
        		        + " from paciente "
        		        + " inner join cita " 
        		        + " on paciente.cedula = cita.cedulaPaciente" 
        		        + " where  cita.fechaCita >= " + "'"+pFechaPiso+ "'" + " AND cita.fechaCita <=  "+ "'"+pFechaTecho+"'"
        		        + " AND cita.cedulaPaciente =  " +"'"+pCedula+ "'" ;



    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
        {
            			  
        Paciente paciente = new Paciente(rs.getString("usuario"), rs.getString("contrasenia"),
             			rs.getString("cedula"),rs.getString("nombreCompleto"), rs.getString("fechaNacimiento"),
             			rs.getString("tipoSangre"),rs.getString("nacionalidad"),rs.getString("residencia") );
            	
            	
        Cita cita = new Cita(rs.getString("estadoCita"),rs.getString("areaDeTrabajo"),
                         rs.getString("horaCita"), rs.getString("observaciones"), paciente);
            	
        cita.setIdentificador(rs.getInt("idCita"));
            	
        consulta=consulta +"\n"+"id Cita:"+ cita.getIdentificador()+"\n"+"Especialidad: "+ 
	                      cita.getAreaDeAplicacion()+"\n"+ "Fecha de la Cita:" + cita.getFechaDeCita()+"\n"+
	                      "Hora de la cita:"+ cita.getHoraDeCita()+"\n" + 
	                      "Observaciones:" + cita.getObservaciones()+"\n"+
	                      "Estado de la cita:" + cita.getEstadoDeCita() +"\n"+
	                      "Nombre del Paciente:" + cita.getPaciente().getNombre()+"\n";
            	

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

  /**MODULO DE PACIENTES, DOCTOR, ENFERMERO Y SECRETARIO
   *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
   *la fecha de las citas
   * @param pCedula
   * @param pFechaPiso
   * @param pFechaTecho
   * @return
   * @throws SQLException
  */
  public String consultarCitaRangoGeneral(String pFechaPiso, String pFechaTecho){
    String consulta="";
 	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita," 
        				+ "cedula,nombreCompleto,fechaNacimiento,tipoSangre,nacionalidad,residencia,"
        				+ "usuario, contrasenia"
        		        + " from paciente "
        		        + " inner join cita " 
        		        + " on paciente.cedula = cita.cedulaPaciente" 
        		        + " where  cita.fechaCita >= " + "'"+pFechaPiso+ "'" + " AND cita.fechaCita <=  "+ "'"+pFechaTecho+"'";



    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
      {
            			  
        Paciente paciente = new Paciente(rs.getString("usuario"), rs.getString("contrasenia"),
             			rs.getString("cedula"),rs.getString("nombreCompleto"), rs.getString("fechaNacimiento"),
             			rs.getString("tipoSangre"),rs.getString("nacionalidad"),rs.getString("residencia") );
            	
            	
        Cita cita = new Cita(rs.getString("estadoCita"),rs.getString("areaDeTrabajo"),
                         rs.getString("horaCita"), rs.getString("observaciones"), paciente);
            	
        cita.setIdentificador(rs.getInt("idCita"));
            	
        consulta=consulta +"\n"+"id Cita:"+ cita.getIdentificador()+"\n"+"Especialidad: "+ 
	                      cita.getAreaDeAplicacion()+"\n"+ "Fecha de la Cita:" + cita.getFechaDeCita()+"\n"+
	                      "Hora de la cita:"+ cita.getHoraDeCita()+"\n" + 
	                      "Observaciones:" + cita.getObservaciones()+"\n"+
	                      "Estado de la cita:" + cita.getEstadoDeCita() +"\n"+
	                      "Nombre del Paciente:" + cita.getPaciente().getNombre()+"\n";
            	

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
    
  /**MODULO DE PACIENTES, DOCTOR, ENFERMERO, SECRETARIO
   *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
   *el estado de la cita
   * @param pCedula
   * @param pFechaPiso
   * @param pFechaTecho
   * @return
   * @throws SQLException
  */
  public String consultarCitaPacienteEstado(int pCedula,String pEstado){
    String consulta="";
    PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita," 
         				+ "cedula,nombreCompleto,fechaNacimiento,tipoSangre,nacionalidad,residencia,"
         				+ "usuario,contrasenia"
         		        + " from paciente "
         		        + " inner join cita " 
         		        + " on paciente.cedula = cita.cedulaPaciente" 
         		        + " where  estadoCita= " + "'"+pEstado+ "'" + "AND cita.cedulaPaciente =  "+ "'" +pCedula+ "'" ;



    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
      {
             			  
        Paciente paciente = new Paciente(rs.getString("usuario"), rs.getString("contrasenia"),
              			rs.getString("cedula"),rs.getString("nombreCompleto"), rs.getString("fechaNacimiento"),
              			rs.getString("tipoSangre"),rs.getString("nacionalidad"),rs.getString("residencia") );
             	
        Cita cita = new Cita(rs.getString("estadoCita"),rs.getString("areaDeTrabajo"),
                          rs.getString("horaCita"), rs.getString("observaciones"), paciente);
             	
        cita.setIdentificador(rs.getInt("idCita"));
             	
        consulta=consulta +"\n"+"id Cita:"+ cita.getIdentificador()+"\n"+"Especialidad: "+ 
 	                      cita.getAreaDeAplicacion()+"\n"+ "Fecha de la Cita:" + cita.getFechaDeCita()+"\n"+
 	                      "Hora de la cita:"+ cita.getHoraDeCita()+"\n" + 
 	                      "Observaciones:" + cita.getObservaciones()+"\n"+
 	                      "Estado de la cita:" + cita.getEstadoDeCita() +"\n"+
 	                      "Nombre del Paciente:" + cita.getPaciente().getNombre()+"\n";
             	

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
    
  /**MODULO DE PACIENTES, DOCTOR, ENFERMERO, SECRETARIO
   *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
   *el estado de la cita
   * @param pFechaPiso
   * @param pFechaTecho
   * @return
   * @throws SQLException
   */
  public String consultarCitaPacienteEstadoGeneral(String pEstado){
    String consulta="";
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

     String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita," 
         				+ "cedula,nombreCompleto,fechaNacimiento,tipoSangre,nacionalidad,residencia,"
         				+ "usuario,contrasenia"
         		        + " from paciente "
         		        + " inner join cita " 
         		        + " on paciente.cedula = cita.cedulaPaciente" 
         		        + " where  estadoCita= " + "'"+pEstado+ "'";



     try {
       ps = (PreparedStatement) con.prepareStatement(sql);
       rs = ps.executeQuery();
       while(rs.next())
       {
             			  
         Paciente paciente = new Paciente(rs.getString("usuario"), rs.getString("contrasenia"),
              			rs.getString("cedula"),rs.getString("nombreCompleto"), rs.getString("fechaNacimiento"),
              			rs.getString("tipoSangre"),rs.getString("nacionalidad"),rs.getString("residencia") );
             	
         Cita cita = new Cita(rs.getString("estadoCita"),rs.getString("areaDeTrabajo"),
                          rs.getString("horaCita"), rs.getString("observaciones"), paciente);
             	
         cita.setIdentificador(rs.getInt("idCita"));
             	
         consulta=consulta +"\n"+"id Cita:"+ cita.getIdentificador()+"\n"+"Especialidad: "+ 
 	                      cita.getAreaDeAplicacion()+"\n"+ "Fecha de la Cita:" + cita.getFechaDeCita()+"\n"+
 	                      "Hora de la cita:"+ cita.getHoraDeCita()+"\n" + 
 	                      "Observaciones:" + cita.getObservaciones()+"\n"+
 	                      "Estado de la cita:" + cita.getEstadoDeCita() +"\n"+
 	                      "Nombre del Paciente:" + cita.getPaciente().getNombre()+"\n";
             	

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
    
  /**MODULO DE PACIENTES, DOCTOR, ENFERMERO, SECRETARIO
   *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
   *el estado de la cita
   * @param pCedula
   * @param pEspecialidad
   * @return consulta de las citas asociadas 
   * @throws SQLException
   */
  public String consultarCitaPacienteEspecialidad(int pCedula,String pEspecialidad){
    String consulta="";
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita," 
         				+ "cedula,nombreCompleto,fechaNacimiento,tipoSangre,nacionalidad,residencia,"
         				+ "usuario,contrasenia"
         		        + " from paciente "
         		        + " inner join cita " 
         		        + " on paciente.cedula = cita.cedulaPaciente" 
         		        + " where  estadoCita= " + "'"+pEspecialidad+ "'" + "AND cita.cedulaPaciente =  " + "'" +pCedula + "'" ;



    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
      {
             			  
        Paciente paciente = new Paciente(rs.getString("usuario"), rs.getString("contrasenia"),
              			rs.getString("cedula"),rs.getString("nombreCompleto"), rs.getString("fechaNacimiento"),
              			rs.getString("tipoSangre"),rs.getString("nacionalidad"),rs.getString("residencia") );
             	
        Cita cita = new Cita(rs.getString("estadoCita"),rs.getString("areaDeTrabajo"),
                          rs.getString("horaCita"), rs.getString("observaciones"), paciente);
             	
        cita.setIdentificador(rs.getInt("idCita"));
             	
        consulta=consulta +"\n"+"id Cita:"+ cita.getIdentificador()+"\n"+"Especialidad: "+ 
 	                      cita.getAreaDeAplicacion()+"\n"+ "Fecha de la Cita:" + cita.getFechaDeCita()+"\n"+
 	                      "Hora de la cita:"+ cita.getHoraDeCita()+"\n" + 
 	                      "Observaciones:" + cita.getObservaciones()+"\n"+
 	                      "Estado de la cita:" + cita.getEstadoDeCita() +"\n"+
 	                      "Nombre del Paciente:" + cita.getPaciente().getNombre()+"\n";
             	

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
    
  /**MODULO DE PACIENTES, DOCTOR, ENFERMERO, SECRETARIO
    *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
    *el estado de la cita
    * @param pCedula
    * @param pEspecialidad
    * @return consulta de las citas asociadas 
    * @throws SQLException
  */
  public String consultarCitaPacienteEspecialidadGeneral(String pEspecialidad){
    String consulta="";
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita," 
         				+ "cedula,nombreCompleto,fechaNacimiento,tipoSangre,nacionalidad,residencia,"
         				+ "usuario,contrasenia"
         		        + " from paciente "
         		        + " inner join cita " 
         		        + " on paciente.cedula = cita.cedulaPaciente" 
         		        + " where  estadoCita= " + "'"+pEspecialidad+ "'";



    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
      {
             			  
        Paciente paciente = new Paciente(rs.getString("usuario"), rs.getString("contrasenia"),
        rs.getString("cedula"),rs.getString("nombreCompleto"), rs.getString("fechaNacimiento"),
              			rs.getString("tipoSangre"),rs.getString("nacionalidad"),rs.getString("residencia") );             	
        Cita cita = new Cita(rs.getString("estadoCita"),rs.getString("areaDeTrabajo"),
                          rs.getString("horaCita"), rs.getString("observaciones"), paciente);
             	
        cita.setIdentificador(rs.getInt("idCita"));
             	
        consulta=consulta +"\n"+"id Cita:"+ cita.getIdentificador()+"\n"+"Especialidad: "+ 
 	                      cita.getAreaDeAplicacion()+"\n"+ "Fecha de la Cita:" + cita.getFechaDeCita()+"\n"+
 	                      "Hora de la cita:"+ cita.getHoraDeCita()+"\n" + 
 	                      "Observaciones:" + cita.getObservaciones()+"\n"+
 	                      "Estado de la cita:" + cita.getEstadoDeCita() +"\n"+
 	                      "Nombre del Paciente:" + cita.getPaciente().getNombre()+"\n";
             	

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
    
    /**MODULO DE DOCTOR, ENFERMERO, SECRETARIO
     *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
     *el nombre del paciente
     * @param pCedula
     * @param pEspecialidad
     * @return consulta de las citas asociadas 
     * @throws SQLException
     */
  public String consultarCitaPacienteNombre(String pNombre){
    String consulta="";
  	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita," 
         				+ "cedula,nombreCompleto,fechaNacimiento,tipoSangre,nacionalidad,residencia,"
         				+ "usuario,contrasenia"
         		        + " from paciente "
         		        + " inner join cita " 
         		        + " on paciente.cedula = cita.cedulaPaciente" 
         		        + " where  estadoCita= " + "'"+pNombre+ "'";



    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
      while(rs.next())
      {
             			  
        Paciente paciente = new Paciente(rs.getString("usuario"), rs.getString("contrasenia"),
              			rs.getString("cedula"),rs.getString("nombreCompleto"), rs.getString("fechaNacimiento"),
              			rs.getString("tipoSangre"),rs.getString("nacionalidad"),rs.getString("residencia") );
             	
             	
        Cita cita = new Cita(rs.getString("estadoCita"),rs.getString("areaDeTrabajo"),
                          rs.getString("horaCita"), rs.getString("observaciones"), paciente);
             	
        cita.setIdentificador(rs.getInt("idCita"));
             	
        consulta=consulta +"\n"+"id Cita:"+ cita.getIdentificador()+"\n"+"Especialidad: "+ 
 	                      cita.getAreaDeAplicacion()+"\n"+ "Fecha de la Cita:" + cita.getFechaDeCita()+"\n"+
 	                      "Hora de la cita:"+ cita.getHoraDeCita()+"\n" + 
 	                      "Observaciones:" + cita.getObservaciones()+"\n"+
 	                      "Estado de la cita:" + cita.getEstadoDeCita() +"\n"+
 	                      "Nombre del Paciente:" + cita.getPaciente().getNombre()+"\n";
             	

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
   *Metodo utilizado para cargar las citas del sistema
   * @return
   * @throws SQLException
   */
  public String consultarCantidadCitasSistema(){
    String consulta="";
 	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select COUNT(idCita) as cant_citas from cita ";


    //System.out.print(sql);
    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      //rs= ps.executeQuery(sql);
      rs = ps.executeQuery();
            
      while(rs.next())
      {
        //System.out.print(rs.getString("nombre"));
        consulta=consulta +"Cantidad general de citas registradas en el sistema: "+ rs.getInt("cant_citas");

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
   *Metodo utilizado para contar las citas del sistema segun un rango de fechas
   * @return
   * @throws SQLException
   */
  public String consultarCantidadCitasFechas(String pFechaPiso, String pFechaTecho){
    String consulta="";
 	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select COUNT(idCita) as cant_citas from cita "
        		      + " where  cita.fechaCita >= " + "'"+pFechaPiso+ "'" + " AND cita.fechaCita <=  "+ "'"+pFechaTecho+"'";
    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
            
      while(rs.next())
      {
        consulta=consulta +"Cantidadde citas segun el rango de fechas: "+ rs.getInt("cant_citas");

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
   *Metodo utilizado para contar las citas del sistema segun su estado
   * @return
   * @throws SQLException
   */
  public String consultarCantidadCitasEstado(String pEstado){
    String consulta="";
 	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select COUNT(idCita) as cant_citas from cita "
        		+ " where  estadoCita= " + "'"+pEstado+ "'";
    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
            
      while(rs.next())
      {
        consulta=consulta +"Cantidadde citas segun su estado: "+ rs.getInt("cant_citas");

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
   *Metodo utilizado para contar las citas del sistema segun su estado
   * @return
   * @throws SQLException
   */
  public String consultarCantidadCitasEspecialidad(String pEspecialidad){
    String consulta="";
 	PreparedStatement ps = null;
    ResultSet rs=null;
    Connection con = (Connection) conectar();

    String sql = "select COUNT(idCita) as cant_citas from cita "
        		+ " where  estadoCita= " + "'"+pEspecialidad+ "'";
    try {
      ps = (PreparedStatement) con.prepareStatement(sql);
      rs = ps.executeQuery();
            
      while(rs.next())
      {
        consulta=consulta +"Cantidadde citas segun la especialidad: "+ rs.getInt("cant_citas");

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


