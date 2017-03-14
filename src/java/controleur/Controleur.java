/*
 * 
 * 
 * 
 */
package controleur;

import java.io.IOException;
import javafx.util.Pair;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Camion;
import modele.Distance;
import oracle.net.aso.d;
import persistance.IPersistance;

/**
 * Controleur.java
 *
 */
public class Controleur {

	private final IPersistance persistance;
	private final ServletContext context;
	private final HttpServletRequest req;
	private final HttpServletResponse res;

	public Controleur(IPersistance persistance, ServletContext context, HttpServletRequest req, HttpServletResponse res) {
		this.persistance = persistance;
		this.context = context;
		this.req = req;
		this.res = res;
	}

	public void localiserCamion() throws ServletException, IOException {
		String immat = req.getParameter("ip_immat");
		
		try {
			Camion c = persistance.getCamion(immat);
			req.setAttribute("c", c);
		} catch (Exception e) {
			req.setAttribute("err", e.toString());
			e.printStackTrace();
		}
		context.getRequestDispatcher("/vue/repLocCamion.jsp").forward(req, res);
	}

	public void deplacerCamion() throws ServletException, IOException {
		String immat = req.getParameter("ip_immat");
		String ville = req.getParameter("ip_ville");

		try {
			persistance.deplacerCamion(immat, ville);
			Camion c = persistance.getCamion(immat);
			req.setAttribute("c", c);
		} catch (Exception e) {
			req.setAttribute("err", e.toString());
			e.printStackTrace();
		}
		context.getRequestDispatcher("/vue/repLocCamion.jsp").forward(req, res);
	}

	public void consulterDistance() throws ServletException, IOException {
		String immat = req.getParameter("ip_immat");
		String ville = req.getParameter("ip_ville");
		
		try {
			Pair<Camion, Distance> pair = persistance.consulterDistance(immat, ville);
			req.setAttribute("c", pair.getKey());
			req.setAttribute("d", pair.getValue());
		} catch (Exception e) {
			req.setAttribute("err", e.toString());
			e.printStackTrace();
		}
		context.getRequestDispatcher("/vue/repConDistance.jsp").forward(req, res);
	}

}
