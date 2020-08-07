package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.user.Student;

/**
 * Servlet implementation class AgregarPaciente
 */
@WebServlet("/AgregarPaciente")
public class AgregarPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Paciente paciente = new Paciente();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarPaciente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("firstName");
		String cedula = request.getParameter("Cedula");
		String email = request.getParameter("email");
		String tipoSangre = request.getParameter("TipoSangre");
		String fechaNacimiento = request.getParameter("FechaNacimiento");
		String nacionalidad = request.getParameter("Nacionalidad");
		String provincia = request.getParameter("Provincia");

		
		Paciente(int pCedula, String pNombre, String pFechaNacimiento, String pTipoDeSangre,
				  String pNacionalidad, String pProvincia, String email;
				  
		Paciente pacienteNuevo = new Paciente(cedula,nombre,fechaNacimiento,tipoSangre,nacionalidad,provincia,email);
		
		Student newStudent = new Student(id, name, lastName, email, password);

	}

}
