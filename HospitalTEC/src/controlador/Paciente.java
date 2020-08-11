package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PacienteDAO;

/**
 * Servlet implementation class Paciente
 */
@WebServlet("/Paciente")
public class Paciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paciente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String contrasenia = request.getParameter("contrasenia");
		String nombre = request.getParameter("firstName");
		String cedula = request.getParameter("Cedula");
		String tipoSangre = request.getParameter("TipoSangre");
		String fechaNacimiento = request.getParameter("FechaNacimiento");
		String nacionalidad = request.getParameter("Nacionalidad");
		String provincia = request.getParameter("Provincia");
		String tipoCentro = request.getParameter("TipoCentro");
		
		PacienteDAO pacienteDAO = new PacienteDAO();
		Paciente pacienteNuevo = new Paciente(usuario, contrasenia, cedula, nombre, fechaNacimiento, tipoSangre, nacionalidad, provincia);
		
		try {
			pacienteDAO.registrarPaciente(pacienteNuevo);
		} catch (Exception e) {
			e.printStackTrace();
	
	}

}
