package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.util.Pair;
import modele.Camion;
import modele.Distance;

public class PersistanceOracle implements IPersistance {

	private static final String USER = "HADDADR";
	private static final String PASSWORD = "HADDADR";
	private static final String URL = "jdbc:oracle:thin:@vs-oracle:1521:ORCL";
	
	
	private static final String QUERY_GET = "SELECT * FROM Camions WHERE Immat=?";
	private static final String QUERY_ISSET = "SELECT * FROM Distances WHERE Ville1=? OR Ville2=?";
	private static final String QUERY_DEPLACER = "UPDATE Camions SET VilleLoc=? WHERE Immat=?";
	private static final String QUERY_DISTANCE = "SELECT * FROM Distances WHERE (Ville1=? AND Ville2=?) OR (Ville1=? AND Ville2=?)";

	private final Connection connexion;
	private final PreparedStatement stmt_get, stmt_isset, stmt_deplacer, stmt_distance;

	public PersistanceOracle() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.OracleDriver");
		this.connexion = DriverManager.getConnection(URL, USER, PASSWORD);
		
		this.stmt_get = this.connexion.prepareStatement(QUERY_GET);
		this.stmt_isset = this.connexion.prepareStatement(QUERY_ISSET);
		this.stmt_deplacer = this.connexion.prepareStatement(QUERY_DEPLACER);
		this.stmt_distance = this.connexion.prepareStatement(QUERY_DISTANCE);
	}

	@Override
	public Camion getCamion(String immat) throws Exception {
		this.stmt_get.setString(1, immat);
		try (ResultSet rs = stmt_get.executeQuery()) {
			if(!rs.next()) {
				throw new Exception("Camion non existant: " + immat);
			}
			
			return new Camion(rs.getString("Immat"), rs.getString("Marque"), rs.getString("VilleLoc"));
		}
	}

	@Override
	public boolean issetVille(String ville) throws SQLException {
		this.stmt_isset.setString(1, ville);
		this.stmt_isset.setString(2, ville);
		try (ResultSet rs = stmt_isset.executeQuery()) {
			return rs.next();
		}
	}

	@Override
	public void deplacerCamion(String immat, String ville) throws Exception {
		if(!issetVille(ville)) {
			throw new Exception("La ville demandée n'existe pas dans la table des distances: " + ville);
		}
		
		this.stmt_deplacer.setString(1, ville);
		this.stmt_deplacer.setString(2, immat);
		this.stmt_deplacer.executeUpdate();
	}

	@Override
	public Pair<Camion, Distance> consulterDistance(String immat, String ville) throws Exception {
		
		Camion c = getCamion(immat);
		
		this.stmt_distance.setString(1, c.getVilleLoc());
		this.stmt_distance.setString(2, ville);
		this.stmt_distance.setString(3, ville);
		this.stmt_distance.setString(4, c.getVilleLoc());
		try (ResultSet rs = stmt_distance.executeQuery()) {
			if(!rs.next()) {
				throw new Exception("Distance non existante: " + c.getVilleLoc() + " <-> " + ville);
			}
			
			Distance d = new Distance(rs.getString("Ville1"), rs.getString("Ville2"), rs.getFloat("Distance"));
			
			return new Pair(c, d);
					
		}
	}

//	@Override
//	public boolean addClient(Client client) throws SQLException {
//		if (issetClient(client)) {
//			return false;
//		}
//
//		stmt_add.setString(1, client.getNom());
//		stmt_add.setString(2, client.getAdresse());
//		stmt_add.executeUpdate();
//
//		return true;
//	}
//
//	@Override
//	public boolean issetClient(Client client) throws SQLException {
//		stmt_isset.setString(1, client.getNom());
//		stmt_isset.setString(2, client.getAdresse());
//		try (ResultSet rs = stmt_isset.executeQuery()) {
//			return rs.next();
//		}
//	}
//
//	@Override
//	public Set<Client> rechercheClient(String nom) throws SQLException {
//		stmt_search.setString(1, nom);
//		Set<Client> set = new HashSet();
//		try (ResultSet rs = stmt_search.executeQuery()) {
//			while (rs.next()) {
//				set.add(new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse")));
//			}
//		}
//		return set;
//	}
//
//	@Override
//	public Compte creerCompte(String nom, int solde) throws SQLException {
//		Client c;
//		try {
//			c = this.rechercheClient(nom).iterator().next();
//		} catch (NoSuchElementException ex) {
//			return null;
//		}
//		
//		stmt_creacompte.setInt(1, c.getId());
//		stmt_creacompte.setInt(2, solde);
//		
//		stmt_creacompte.executeUpdate();
//		
//		long id;
//		
//		try (ResultSet rs = stmt_idcompte.executeQuery()) {
//			if(rs.next()) {
//				id = rs.getLong("id");
//			} else {
//				return null;
//			}
//		}
//		
//		return new Compte(id, solde);
//	}
//
	@Override
	protected void finalize() throws Throwable {
		try {
			this.stmt_get.close();
			stmt_isset.close();
			stmt_deplacer.close();
			stmt_distance.close();
			this.connexion.close();
		} catch (Exception ex) {
		}
		super.finalize();
	}
}
