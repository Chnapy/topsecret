package persistance;

import java.sql.SQLException;
import javafx.util.Pair;
import modele.Camion;
import modele.Distance;

public interface IPersistance {
	
	public Camion getCamion(String immat) throws Exception;
	
	public boolean issetVille(String ville) throws SQLException;
	
	public void deplacerCamion(String immat, String ville) throws Exception;
	
	public Pair<Camion, Distance> consulterDistance(String immat, String ville) throws Exception;

}
