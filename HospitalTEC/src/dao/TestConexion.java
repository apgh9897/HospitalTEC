package dao;

import java.util.Date;

import modelo.Administrador;
import modelo.CentroDeAtencion;
import modelo.Cita;
import modelo.Paciente;
import modelo.Tratamiento;

public class TestConexion {

	public static void main(String[] args) {
		ConexionMySQL con = new ConexionMySQL();
		con.conectar();
		
		//Tratamiento tratamiento1 = new Tratamiento("Acetaminofen",2, "Medicamento");
		//TratamientoDAO td = new TratamientoDAO();
		//td.registrarTratamiento(tratamiento1);
		
		//Administrador admin = new Administrador(117340434, "marip");
		
		//CentroDeAtencion centro = new CentroDeAtencion("San Juan de Dios", "San Jose", 577, "Hospital");
		//CentroAtencionDAO cd = new CentroAtencionDAO();
		//cd.registrarCentroDeAtencion(centro, admin);
		//cd.registrarTipoDeCentroDeAtencion("EBAIS", admin);
		
		
	
		//Paciente paciente1 = new Paciente(117340437, "Mariana", "1995/05/05","A +" , "Costarriccense", "San Jose", "Curridabat", "Curridabat");
		
		
		//Cita cita1 = new Cita("Registrada", "Odontologia", "9:00:00", "Primera cita", paciente1);
		//CitaDAO citaD = new CitaDAO();
		//cita1.setEstadoDeCita("Asignada");
		//citaD.registrarCita(cita1);
		//cita1.setIdentificador(2);
		
		//citaD.actualizarEstado(cita1);
		//citaD.cancelarCita(cita1);
		//citaD.citasCentroMedico(cita1);
		//citaD.registrarEstadoCita("ASIGNADA");
		//citaD.filtroPacienteEspecialidadCita(cita1);
		//citaD.filtroPacienteEstadoCita(cita1);
		//citaD.filtroPacienteFechaCita(cita1);
		
	}
}
