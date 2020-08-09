package dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import modelo.Hospitalizacion;
/**
*
* @author Maria Jose Placido
*/
public class HospitalizacionDAO  extends ConexionMySQL {
	
    /**
     *Metodo que registra las hospitalizaciones en la base de datos
     * @param Hospitalizacion hospi el nuevo objeto de hospitalizacion 
     * que se registrara
     * @return hospita la hospitalizacion creada
     * @throws SQLException
     */
    public Hospitalizacion registrarHospitalizacion (Hospitalizacion hospi) {
        PreparedStatement ps = null;
        Hospitalizacion hospita = new Hospitalizacion();
        Connection con = (Connection) conectar();

        String sql = "INSERT INTO Hospitalizacion (centroDeAtencion,idPaciente, diagnostico, "
        		+ "fechaInicio,fechaFinal, areaDeTrabajo, funcionario) VALUES(?,?,?,?,?,?,?)";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, hospi.getNombreCentroDeAtencion());
            ps.setString(2, hospi.getCedulaPaciente());
            ps.setString(3,hospi.getDiagnostico());
            ps.setString(4, hospi.getFechaInicio());
            ps.setString(5, hospi.getFechaFinal());
            ps.setString(6, hospi.getEspecialidad());
            ps.setString(7, hospi.getNombreFuncionario());      
            ps.execute();
            
            rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
            rs.next();
            
            int numHospi = rs.getInt(1);
            hospi.setNumeroHospitalizacion(numHospi);
            hospita = hospi;
            
            JOptionPane.showMessageDialog(null, "La hospitalizacion ha sido regitrada");
            
            return hospita;
        } catch (SQLException e) {
            System.err.println(e);
            return hospita;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    /**
     *Metodo que elimina las hospitalizaciones de un paciente
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public void eliminarHospitalizacion(Hospitalizacion hospi){
        PreparedStatement ps = null;
        Connection con = (Connection) conectar();
    	
    	try {
    		String query="DELETE FROM Hospitalizacion WHERE idHospitalizacion=?";
    		ps=(PreparedStatement) con.prepareStatement(query);
    		ps.setInt(1, hospi.getNumeroHospitalizacion());
    		
    		System.out.println(ps);
    		JOptionPane.showMessageDialog(null,"La hospitalizacion ha sido eliminada");
    		
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
     *Metodo que modifica las hospitalizaciones de un paciente
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public boolean modificarHospitalizacion(Hospitalizacion hospi ) {
        PreparedStatement ps = null;
        Connection con =  (Connection) conectar();

        String sql = "UPDATE Hospitalizacion SET centroDeAtencion = ?, idPaciente = ?,"
        		+ " diagnostico = ?, fechaInicio = ?,fechaFinal = ?, areaDeTrabajo = ?,"
        		+ " funcionario = ? WHERE idHospitalizacion =? ";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, hospi.getNombreCentroDeAtencion());
            ps.setString(2, hospi.getCedulaPaciente());
            ps.setString(3, hospi.getDiagnostico());
            ps.setString(4, hospi.getFechaInicio());
            ps.setString(5, hospi.getFechaFinal());
            ps.setString(6, hospi.getEspecialidad());
            ps.setString(7, hospi.getNombreFuncionario());
            
            
            ps.execute();

            JOptionPane.showMessageDialog(null,"La hospitalizacion ha sido modificada");
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
     *Metodo que consulta una hospitalizacion en especifico
     * @param int pId es el identificador de la cita que se desea buscar en el sistema
     * @return
     * @throws SQLException
     */
    public String consultarHospitalizacion(int pId){
        String consulta="";
  	   PreparedStatement ps = null;
        ResultSet rs=null;
         Connection con = (Connection) conectar();

         String sql = "SELECT idHospitalizacion, centroDeAtencion,idPaciente, diagnostico,"
         		+ " fechaInicio,fechaFinal, areaDeTrabajo, funcionario, nombreCompleto"
		        + "FROM paciente INNER JOIN hospitalizacion "
		        + "ON paciente.cedula = hospitalizacion.idPaciente "
		        + " WHERE idHospitalizacion =  " +pId;

         try {
             ps = (PreparedStatement) con.prepareStatement(sql);
             rs = ps.executeQuery();
             while(rs.next())
             {            			  
            	Hospitalizacion hospi = new Hospitalizacion(rs.getString("centroDeAtencion"), 
            			rs.getString("idPaciente"), rs.getString("nombreCompleto"),
            			rs.getString("diagnostico"),rs.getString("fechaInicio"),
            			rs.getString("fechaFinal"), rs.getString("areaDeTrabajo"), 
            			rs.getString("funcionario"));
            	 hospi.setNumeroHospitalizacion(rs.getInt("idHospitalizacion"));
             	
                 consulta=consulta +"\n"+"Id hospitalizacion:"+ hospi.getNumeroHospitalizacion()
                 					+"\n"+"Centro de atencion: "+ hospi.getNombreCentroDeAtencion()
                 					+"\n"+ "Cedula del paciente:" + hospi.getCedulaPaciente()
                 					+"\n"+"Nombre del paciente:"+ hospi.getNombrePaciente()
                 					+"\n" + "Diagnostico:" + hospi.getDiagnostico()
                 					+"\n"+"Fecha de Inicio:" + hospi.getFechaInicio() 
                 					+"\n"+"Fecha de Final:" + hospi.getFechaFinal()
                 					+"\n"+"Especialidad:" + hospi.getEspecialidad()
                 					+"\n"+"Nombre del Funcionario:" +hospi.getNombreFuncionario()+"\n";
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
     *Metodo que consulta una hospitalizacion de un pacienteen especifico
     * @param int pId es el identificador de la cita que se desea buscar en el sistema
     * @return
     * @throws SQLException
     */
    public String consultarHospitalizacionPaciente(int pCedula){
        String consulta="";
  	   PreparedStatement ps = null;
        ResultSet rs=null;
         Connection con = (Connection) conectar();

         String sql = "SELECT idHospitalizacion, centroDeAtencion,idPaciente, diagnostico, fechaInicio,"
         		+ "fechaFinal, areaDeTrabajo, funcionario, nombreCompleto"
		        + "FROM paciente INNER hospitalizacion "
		        + "ON paciente.cedula = hospitalizacion.idPaciente "
		        + " WHERE idPaciente =  " +pCedula;

         try {
             ps = (PreparedStatement) con.prepareStatement(sql);
             rs = ps.executeQuery();
             while(rs.next())
             {            			  
            	Hospitalizacion hospi = new Hospitalizacion(rs.getString("centroDeAtencion"), 
            			rs.getString("idPaciente"), rs.getString("nombreCompleto"),
            			rs.getString("diagnostico"),rs.getString("fechaInicio"),
            			rs.getString("fechaFinal"), rs.getString("areaDeTrabajo"), 
            			rs.getString("funcionario"));
            	 hospi.setNumeroHospitalizacion(rs.getInt("idHospitalizacion"));
             	
                 consulta=consulta +"\n"+"Id hospitalizacion:"+ hospi.getNumeroHospitalizacion()
                 					+"\n"+"Centro de atencion: "+ hospi.getNombreCentroDeAtencion()
                 					+"\n"+ "Cedula del paciente:" + hospi.getCedulaPaciente()
                 					+"\n"+"Nombre del paciente:"+ hospi.getNombrePaciente()
                 					+"\n" + "Diagnostico:" + hospi.getDiagnostico()
                 					+"\n"+"Fecha de Inicio:" + hospi.getFechaInicio() 
                 					+"\n"+"Fecha de Final:" + hospi.getFechaFinal()
                 					+"\n"+"Especialidad:" + hospi.getEspecialidad()
                 					+"\n"+"Nombre del Funcionario:" +hospi.getNombreFuncionario()+"\n";
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
     *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
     *la fecha de las citas
     * @param pCedula
     * @param pFechaPiso
     * @param pFechaTecho
     * @return
     * @throws SQLException
     */
    public String consultarHospitalizacionRangoFechas(String pFechaPiso, String pFechaTecho){
       String consulta="";
 	   PreparedStatement ps = null;
       ResultSet rs=null;
        Connection con = (Connection) conectar();

        String sql =  "SELECT idHospitalizacion, centroDeAtencion,idPaciente, diagnostico, fechaInicio,"
         			  + "fechaFinal, areaDeTrabajo, funcionario, nombreCompleto"
         			  + "FROM paciente INNER hospitalizacion "
         			  + "ON paciente.cedula = hospitalizacion.idPaciente "
         			  + " where  hospitalizacion.fechaInicio >= " + "'"+pFechaPiso+ "'" + " AND hospitalizacion.fechaFinal <=  "+ "'"+pFechaTecho+"'";



        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
            	Hospitalizacion hospi = new Hospitalizacion(rs.getString("centroDeAtencion"), 
            			rs.getString("idPaciente"), rs.getString("nombreCompleto"),
            			rs.getString("diagnostico"),rs.getString("fechaInicio"),
            			rs.getString("fechaFinal"), rs.getString("areaDeTrabajo"), 
            			rs.getString("funcionario"));
            	 hospi.setNumeroHospitalizacion(rs.getInt("idHospitalizacion"));
             	
                 consulta=consulta +"\n"+"Id hospitalizacion:"+ hospi.getNumeroHospitalizacion()
                 					+"\n"+"Centro de atencion: "+ hospi.getNombreCentroDeAtencion()
                 					+"\n"+ "Cedula del paciente:" + hospi.getCedulaPaciente()
                 					+"\n"+"Nombre del paciente:"+ hospi.getNombrePaciente()
                 					+"\n" + "Diagnostico:" + hospi.getDiagnostico()
                 					+"\n"+"Fecha de Inicio:" + hospi.getFechaInicio() 
                 					+"\n"+"Fecha de Final:" + hospi.getFechaFinal()
                 					+"\n"+"Especialidad:" + hospi.getEspecialidad()
                 					+"\n"+"Nombre del Funcionario:" +hospi.getNombreFuncionario()+"\n";

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
     *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
     *el estado de la cita
     * @param pCedula
     * @param pFechaPiso
     * @param pFechaTecho
     * @return
     * @throws SQLException
     */
    public String consultarHospitalizacionPacienteEstado(String pEstado){
       String consulta="";
  	   PreparedStatement ps = null;
       ResultSet rs=null;
       Connection con = (Connection) conectar();

         String sql =  "SELECT idHospitalizacion, centroDeAtencion,idPaciente, diagnostico, fechaInicio,"
        		 		+ "fechaFinal, areaDeTrabajo, funcionario, nombreCompleto"
        		 		+ "FROM paciente INNER hospitalizacion "
        		 		+ "ON paciente.cedula = hospitalizacion.idPaciente "
        		 		+ " WHERE  estadoHospitalizacion= " + "'"+pEstado+ "'";



         try {
             ps = (PreparedStatement) con.prepareStatement(sql);
             rs = ps.executeQuery();
             while(rs.next())
             {
             	Hospitalizacion hospi = new Hospitalizacion(rs.getString("centroDeAtencion"), 
            			rs.getString("idPaciente"), rs.getString("nombreCompleto"),
            			rs.getString("diagnostico"),rs.getString("fechaInicio"),
            			rs.getString("fechaFinal"), rs.getString("areaDeTrabajo"), 
            			rs.getString("funcionario"));
            	 hospi.setNumeroHospitalizacion(rs.getInt("idHospitalizacion"));
             	
                 consulta=consulta +"\n"+"Id hospitalizacion:"+ hospi.getNumeroHospitalizacion()
                 					+"\n"+"Centro de atencion: "+ hospi.getNombreCentroDeAtencion()
                 					+"\n"+ "Cedula del paciente:" + hospi.getCedulaPaciente()
                 					+"\n"+"Nombre del paciente:"+ hospi.getNombrePaciente()
                 					+"\n" + "Diagnostico:" + hospi.getDiagnostico()
                 					+"\n"+"Fecha de Inicio:" + hospi.getFechaInicio() 
                 					+"\n"+"Fecha de Final:" + hospi.getFechaFinal()
                 					+"\n"+"Especialidad:" + hospi.getEspecialidad()
                 					+"\n"+"Nombre del Funcionario:" +hospi.getNombreFuncionario()+"\n";
             	

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
     *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
     *el estado de la cita
     * @param pCedula
     * @param pEspecialidad
     * @return consulta de las citas asociadas 
     * @throws SQLException
     */
    public String consultarCitaPacienteEspecialidad(String pEspecialidad){
       String consulta="";
  	   PreparedStatement ps = null;
       ResultSet rs=null;
       Connection con = (Connection) conectar();

         String sql = "SELECT idHospitalizacion, centroDeAtencion,idPaciente, diagnostico, fechaInicio,"
   			  			+ "fechaFinal, areaDeTrabajo, funcionario, nombreCompleto"
   			  			+ "FROM paciente INNER hospitalizacion "
   			  			+ "ON paciente.cedula = hospitalizacion.idPaciente "
   	      		 		+ " WHERE  areaDeTrabajo= " + "'"+pEspecialidad+ "'";


         try {
             ps = (PreparedStatement) con.prepareStatement(sql);
             rs = ps.executeQuery();
             while(rs.next())
             {
              	Hospitalizacion hospi = new Hospitalizacion(rs.getString("centroDeAtencion"), 
            			rs.getString("idPaciente"), rs.getString("nombreCompleto"),
            			rs.getString("diagnostico"),rs.getString("fechaInicio"),
            			rs.getString("fechaFinal"), rs.getString("areaDeTrabajo"), 
            			rs.getString("funcionario"));
            	 hospi.setNumeroHospitalizacion(rs.getInt("idHospitalizacion"));
             	
                 consulta=consulta +"\n"+"Id hospitalizacion:"+ hospi.getNumeroHospitalizacion()
                 					+"\n"+"Centro de atencion: "+ hospi.getNombreCentroDeAtencion()
                 					+"\n"+ "Cedula del paciente:" + hospi.getCedulaPaciente()
                 					+"\n"+"Nombre del paciente:"+ hospi.getNombrePaciente()
                 					+"\n" + "Diagnostico:" + hospi.getDiagnostico()
                 					+"\n"+"Fecha de Inicio:" + hospi.getFechaInicio() 
                 					+"\n"+"Fecha de Final:" + hospi.getFechaFinal()
                 					+"\n"+"Especialidad:" + hospi.getEspecialidad()
                 					+"\n"+"Nombre del Funcionario:" +hospi.getNombreFuncionario()+"\n";
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
     *Metodo que consulta una hospitalizacion en especifico
     * @param int pId es el identificador de la cita que se desea buscar en el sistema
     * @return
     * @throws SQLException
     */
    public String consultarHospitalizacionNombrePaciente(String pNombre){
        String consulta="";
  	   PreparedStatement ps = null;
        ResultSet rs=null;
         Connection con = (Connection) conectar();

         String sql = "SELECT idHospitalizacion, centroDeAtencion,idPaciente, diagnostico,"
         		+ " fechaInicio,fechaFinal, areaDeTrabajo, funcionario, nombreCompleto"
		        + "FROM paciente INNER JOIN hospitalizacion "
		        + "ON paciente.cedula = hospitalizacion.idPaciente "
		        + " WHERE nombreCompleto =  " +pNombre;

         try {
             ps = (PreparedStatement) con.prepareStatement(sql);
             rs = ps.executeQuery();
             while(rs.next())
             {            			  
            	Hospitalizacion hospi = new Hospitalizacion(rs.getString("centroDeAtencion"), 
            			rs.getString("idPaciente"), rs.getString("nombreCompleto"),
            			rs.getString("diagnostico"),rs.getString("fechaInicio"),
            			rs.getString("fechaFinal"), rs.getString("areaDeTrabajo"), 
            			rs.getString("funcionario"));
            	 hospi.setNumeroHospitalizacion(rs.getInt("idHospitalizacion"));
             	
                 consulta=consulta +"\n"+"Id hospitalizacion:"+ hospi.getNumeroHospitalizacion()
                 					+"\n"+"Centro de atencion: "+ hospi.getNombreCentroDeAtencion()
                 					+"\n"+ "Cedula del paciente:" + hospi.getCedulaPaciente()
                 					+"\n"+"Nombre del paciente:"+ hospi.getNombrePaciente()
                 					+"\n" + "Diagnostico:" + hospi.getDiagnostico()
                 					+"\n"+"Fecha de Inicio:" + hospi.getFechaInicio() 
                 					+"\n"+"Fecha de Final:" + hospi.getFechaFinal()
                 					+"\n"+"Especialidad:" + hospi.getEspecialidad()
                 					+"\n"+"Nombre del Funcionario:" +hospi.getNombreFuncionario()+"\n";
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
