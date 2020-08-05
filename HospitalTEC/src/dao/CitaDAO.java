package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Cita;

public class CitaDAO extends ConexionMySQL{

    
    /**
     *Metodo que registra las citas en la base de datos
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public boolean registrarCita (Cita cita) {
        PreparedStatement ps = null;
        Connection con = conectar();

        String sql = "INSERT INTO Cita (areaDeTrabajo,fechaCita, horaCita, observaciones, "
        		+ "estadoCita, cedulaPaciente) VALUES(?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, cita.getAreaDeAplicacion());
            ps.setString(2, cita.getFechaDeCita());
            ps.setString(3,cita.getHoraDeCita());
            ps.setString(4, cita.getObservaciones());
            ps.setString(5, cita.getEstadoDeCita());
            ps.setInt(6, cita.getPaciente().getCedula());
            
            
            System.out.println(sql);
            
            ps.execute();
            
            rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
            rs.next();
            int idCita = rs.getInt(1);
            
            //System.out.print(id);
            
            cita.setIdentificador(idCita);;// le aseigna la llave al objeto del arraylist
            cita.getPaciente().getCitas().add(cita);//agrega el centro a la lista del sistema
            
            JOptionPane.showMessageDialog(null, "Su cita ha sido regitrada"); 
            
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
     *Metodo que cancela las citas de un paciente
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public void cancelarCita(Cita cita){
        PreparedStatement ps = null;
        Connection con = conectar();
    	
    	try {
    		String query="DELETE FROM Cita WHERE idCita=?";
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
     *Metodo utilizado para cargar el combobox de las citas con estado CANCELADA POR CENTRO MEDICO
     *y pasarlas a un estado de asignado
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public void citasCentroMedico(Cita cita){
        PreparedStatement ps = null;
        Connection con = conectar();
    	ResultSet rs=null;
    	
    	try {
    		String query="SELECT idCita FROM Cita WHERE estadoCita = 'CANCELADA POR CENTRO MEDICO'";
    		ps=con.prepareStatement(query);
    		
    		System.out.println(ps);
    		rs=ps.executeQuery();
    		
    		while(rs.next()){
    		System.out.println(rs.getString("idCita"));
    		System.out.println("---------------");
    		}
    	} catch (Exception e) {
    		System.out.println(e);
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

        try {
            ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, pDescripcion);
            
            System.out.println(sql);
            
            ps.execute();
            
            rs = ps.getGeneratedKeys();// obtiene las llaves que genero la BD
            rs.next();
            int idTipo = rs.getInt(1);
            
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
     *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
     *el estado de la cita
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public boolean filtroPacienteEstadoCita(Cita cita) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conectar();

        String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita"
        		+ " FROM paciente INNER JOIN cita ON paciente.cedula = cita.cedulaPaciente"
        		+ " WHERE cedulaPaciente  = ? AND estadoCita= ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cita.getPaciente().getCedula());
            ps.setString(2, cita.getEstadoDeCita());
            rs = ps.executeQuery();
            
            while(rs.next())
            {
               cita.setIdentificador( Integer.parseInt(rs.getString("idCita")));
               //System.out.println(rs.getString("idCita"));
               
               cita.setAreaDeAplicacion(rs.getString("areaDeTrabajo"));
               //System.out.println(rs.getString("areaDeTrabajo"));
               
               cita.setHoraDeCita(rs.getString("fechaCita"));
               //System.out.println(rs.getString("fechaCita"));
               
               cita.setHoraDeCita(rs.getString("horaCita"));
               //System.out.println(rs.getString("horaCita"));
               
               cita.setObservaciones(rs.getString("observaciones"));
               //System.out.println(rs.getString("observaciones"));
               
               cita.setEstadoDeCita(rs.getString("estadoCita"));
               //System.out.println(rs.getString("estadoCita"));
               
               return true;
            }
            return false;
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
     *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
     *la especialidad de la cita
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public boolean filtroPacienteEspecialidadCita(Cita cita) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conectar();

        String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita \n"
        		+ "FROM  paciente INNER JOIN cita ON paciente.cedula = cita.cedulaPaciente \n"
        		+ " WHERE cedulaPaciente = ? and areaDeTrabajo = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cita.getPaciente().getCedula());
            ps.setString(2, cita.getAreaDeAplicacion());
            rs = ps.executeQuery();
            
            while(rs.next())
            {
               cita.setIdentificador( Integer.parseInt(rs.getString("idCita")));
              //System.out.println(rs.getString("idCita"));
               
               cita.setAreaDeAplicacion(rs.getString("areaDeTrabajo"));
               //System.out.println(rs.getString("areaDeTrabajo"));
               
               cita.setHoraDeCita(rs.getString("fechaCita"));
               //System.out.println(rs.getString("fechaCita"));
               
               cita.setHoraDeCita(rs.getString("horaCita"));
               //System.out.println(rs.getString("horaCita"));
               
               cita.setObservaciones(rs.getString("observaciones"));
               //System.out.println(rs.getString("observaciones"));
               
               cita.setEstadoDeCita(rs.getString("estadoCita"));
               //System.out.println(rs.getString("estadoCita"));
               
               return true;
            }
            return false;
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
     *Metodo utilizado para cargar las citas asociadas a un paciente filtradas por
     *la fecha de las citas
     * @param Cita cita
     * @return
     * @throws SQLException
     */
    public boolean filtroPacienteFechaCita(Cita cita) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conectar();

        String sql = "SELECT idCita, areaDeTrabajo, fechaCita, horaCita, observaciones, estadoCita"
        		+ "FROM paciente INNER JOIN cita ON paciente.cedula = cita.cedulaPaciente"
        		+ " WHERE fechaCita BETWEEN ? AND ? AND cedulaPaciente = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cita.getFechaDeCita());
            ps.setString(2, cita.getFechaDeCita());
            ps.setInt(2, cita.getPaciente().getCedula());
            rs = ps.executeQuery();
            
            while(rs.next())
            {
               cita.setIdentificador( Integer.parseInt(rs.getString("idCita")));
               System.out.println(rs.getString("idCita"));
               
               cita.setAreaDeAplicacion(rs.getString("areaDeTrabajo"));
               System.out.println(rs.getString("areaDeTrabajo"));
               
               cita.setHoraDeCita(rs.getString("fechaCita"));
               System.out.println(rs.getString("fechaCita"));
               
               cita.setHoraDeCita(rs.getString("horaCita"));
               System.out.println(rs.getString("horaCita"));
               
               cita.setObservaciones(rs.getString("observaciones"));
               System.out.println(rs.getString("observaciones"));
               
               cita.setEstadoDeCita(rs.getString("estadoCita"));
               System.out.println(rs.getString("estadoCita"));
               
               return true;
            }
            return false;
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


