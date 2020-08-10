package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Paciente;
import dao.PacienteDAO;

/**
 * Servlet implementation class AgregarPaciente
 */
@WebServlet("/AgregarPaciente")
public class AgregarPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Paciente nuevopaciente;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarPaciente() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String cedula = request.getParameter("Cedula");
		String cedula = request.getParameter("Cedula");
		String nombre = request.getParameter("firstName");
		String fechaNacimiento = request.getParameter("FechaNacimiento");
		String tipoSangre = request.getParameter("TipoSangre");
		String nacionalidad = request.getParameter("Nacionalidad");
		String provincia = request.getParameter("Provincia");

		Paciente nuevopaciente= new Paciente(cedula,nombre,fechaNacimiento,tipoSangre,nacionalidad,provincia);
		PacienteDAO paciente = new PacienteDAO();
		try {
			paciente.regitrarPaciente(nuevopaciente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("loginView.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	

}
