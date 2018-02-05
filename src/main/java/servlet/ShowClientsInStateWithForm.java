package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "ShowClientsInStateWithForm", urlPatterns = {"/ShowClientsInStateWithForm"})
public class ShowClientsInStateWithForm extends HttpServlet {

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

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ShowClientsInState</title>");
			out.println("</head>");
			out.println("<body>");
			try {
				// Créér le ExtendedDAO avec sa source de données
				ExtendedDAO dao = new ExtendedDAO(DataSourceFactory.getDataSource());
				List<String> states = dao.existingStates();
				// Trouver la valeur du paramètre HTTP state
				String state = request.getParameter("state");
				// On n'a pas forcément le paramètre
				if (null == state) {
					state = states.get(0);
				}

				List<CustomerEntity> customers = dao.customersInState(state);

				// Formulaire 
				out.println("<form>");
				out.println("<select name='state' onchange='this.form.submit()'>");

				for (String s : states) {
					// Il faut veiller à ce que l'état passé en paramètre
					// soit sélectionné dans la liste de choix
					// Par défaut le 1° est sélectionné
					out.printf("<option value='%s' %s>%s</option>%n",
						s,
						s.equals(state) ? "selected" : "",
						s);
				}
				out.println("</select>");
				out.println("<input type='submit'>");
				out.println("</form>");

				// En-tete de table
				out.println("<table border=2>");
				out.println("<tr> <th>Id</th> <th>Name</th> <th>Address</th> </tr>");

				for (CustomerEntity c : customers) {
					// Afficher les propriétés du client			
					out.printf("<tr> <td>%d</td> <td>%s</td> <td>%s</td> </tr>%n",
						c.getCustomerId(),
						c.getName(),
						c.getAddressLine1());
				}
				out.println("</table>");
			} catch (DAOException e) {
				out.printf("<h3>Erreur : %s</h3>", e.getMessage());
			}
			out.printf("<hr><a href='%s'>Retour au menu</a>",request.getContextPath());			
			out.println("</body>");
			out.println("</html>");
		} catch (Exception ex) {
			Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
	}// </editor-fold>

}
