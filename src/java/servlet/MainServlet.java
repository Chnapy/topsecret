/*
 * 
 * 
 * 
 */
package servlet;

import controleur.Controleur;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistance.PersistanceOracle;

/**
 *
 * @author haddad1
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/index.html"})
public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String page = req.getParameter("p");
		if (page == null) {
			page = "";
		}

		String path;

		switch (page) {
			case "deplacer":
				path = "/vue/vueDepCamion.html";
				break;
			case "consulter":
				path= "/vue/vueConDistance.html";
				System.out.println("CONSULTER");
				break;
			default:
				path = "/vue/vueLocCamion.html";
				break;
		}

		this.getServletContext().getRequestDispatcher(path).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String operation = req.getParameter("operation");
			if (operation == null) {
				operation = "";
			}

			Controleur c = new Controleur(new PersistanceOracle(), getServletContext(), req, resp);

			switch (operation) {
				case "localiser":
					c.localiserCamion();
					break;
				case "deplacer":
					c.deplacerCamion();
					break;
				case "consulter":
					c.consulterDistance();
					break;
				default:
					break;
			}
		} catch (IOException | ClassNotFoundException | SQLException | ServletException ex) {
			Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
