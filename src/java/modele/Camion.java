/*
 * 
 * 
 * 
 */
package modele;

/**
 * Camion.java
 * 
 */
public class Camion {

	private String Immat;
	private String Marque;
	private String VilleLoc;

	public Camion(String Immat, String Marque, String VilleLoc) {
		this.Immat = Immat;
		this.Marque = Marque;
		this.VilleLoc = VilleLoc;
	}

	public String getImmat() {
		return Immat;
	}

	public void setImmat(String Immat) {
		this.Immat = Immat;
	}

	public String getMarque() {
		return Marque;
	}

	public void setMarque(String Marque) {
		this.Marque = Marque;
	}

	public String getVilleLoc() {
		return VilleLoc;
	}

	public void setVilleLoc(String VilleLoc) {
		this.VilleLoc = VilleLoc;
	}
	
	
}
