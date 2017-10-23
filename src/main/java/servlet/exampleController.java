/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rbastide
 */
@WebServlet(name = "exampleController", urlPatterns = {"/exampleController"})
public class exampleController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// On récupère les paramètres de la requête
		String p1 = request.getParameter("p1");
		String jspView; // La page à afficher
		// En fonction des paramètres, on initialise les variables utilisées dans les JSP
		// Et on choisit la vue (page JSP) à afficher
		if (null == p1) { // Pas de paramètre : on montre le formulaire de saisie
			jspView = "saisieParametre.html";
		} else if ("M".equals(p1)) { // Paramètre OK
			// On positionne des infos à afficher dans la page
			request.setAttribute("message", "Monsieur");
			// On va vers une page d'affichage
			jspView = "afficheMessage.jsp";
		} else if ("F".equals(p1)) { // Idem
			request.setAttribute("message", "Madame");
			jspView = "afficheMessage.jsp";
		} else { // Paramètre incorrect
			request.setAttribute("errorMessage", "Paramètre p1 incorrect: " + p1);
			jspView = "afficheErreur.jsp";
		}
		// On continue vers la page JSP sélectionnée
		request.getRequestDispatcher("views/" + jspView).forward(request, response);
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
