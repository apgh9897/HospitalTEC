package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CentroAtencionDAO;
import dao.FuncionarioDAO;
import modelo.CentroDeAtencion;
//import modelo.CentroDeAtencion;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   // private static CentroDeAtencion centro;   
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
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
		String button = request.getParameter("BtnAdmin");
		CentroAtencionDAO tipoCentro= new CentroAtencionDAO();
		if (button.equals("CrearTipo")) {
			
			
			String descripcion = request.getParameter("TipoCentro").toString();
			//System.out.println(descripcion);
			try {
				tipoCentro.registrarTipoDeCentroDeAtencion(descripcion);
			} catch (Exception e) {
				e.printStackTrace();
			}

			
			} else if(button.equals("CrearEspecialidad")) {			
			FuncionarioDAO fun = new FuncionarioDAO();
			String descripcionArea = request.getParameter("AreaEspecialidad").toString();
			
			
			try {
				fun.registrarTipoAreaDeTrabajo(descripcionArea);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
					
				}else if(button.equals("CrearCentro")) {
										CentroAtencionDAO nuevoCentro = new CentroAtencionDAO();
					String nombreCentro = request.getParameter("firstName").toString();
					String ubicacion = request.getParameter("Ubicacion").toString();
					String tipoCentroo = request.getParameter("Cap").toString();
					String capacidad = request.getParameter("tipoCentroSelect").toString();
					
					CentroDeAtencion centro = new CentroDeAtencion(nombreCentro,ubicacion,capacidad,tipoCentroo);
					
					try {
						nuevoCentro.registrarCentroDeAtencion(centro);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
		ArrayList<CentroDeAtencion> lista = tipoCentro.consultarCentros();
		String result = "";
		for(CentroDeAtencion centro: lista) {	
			result += "<p>"
					+ centro.getNombre() + "</p>";	
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html><head></head><title>Centro</title><body><div>" + result + " </div> </body></html>");

		out.close();

		response.getStatus();
		
	}
	

}
