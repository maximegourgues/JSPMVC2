package controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.ExtendedDAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

@WebServlet(name = "ClientsInStateMVC", urlPatterns = {"/ClientsInStateMVC"})
public class ShowClientsInStateWithFormController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			// Créér le ExtendedDAO avec sa source de données
			ExtendedDAO dao = new ExtendedDAO(DataSourceFactory.getDataSource());
			List<String> states = dao.existingStates();
			// Trouver la valeur du paramètre HTTP selectedState
			String selectedState = request.getParameter("state");
			// On n'a pas forcément le paramètre
			if (null == selectedState) {
				selectedState = states.get(0);
			}
			List<CustomerEntity> customers = dao.customersInState(selectedState);

			request.setAttribute("selectedState", selectedState);
			request.setAttribute("states", states);
			request.setAttribute("customers", customers);
			// On continue vers la page JSP sélectionnée
			request.getRequestDispatcher("views/clientsInState.jsp").forward(request, response);
		} catch (DAOException ex) {
			Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
		}
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
