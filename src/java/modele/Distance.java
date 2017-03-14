/*
 * 
 * 
 * 
 */
package modele;

/**
 * Distance.java
 * 
 */
public class Distance {
	
	private String Ville1;
	private String Ville2;
	private float Distance;

	public Distance(String Ville1, String Ville2, float Distance) {
		this.Ville1 = Ville1;
		this.Ville2 = Ville2;
		this.Distance = Distance;
	}

	public String getVille1() {
		return Ville1;
	}

	public void setVille1(String Ville1) {
		this.Ville1 = Ville1;
	}

	public String getVille2() {
		return Ville2;
	}

	public void setVille2(String Ville2) {
		this.Ville2 = Ville2;
	}

	public float getDistance() {
		return Distance;
	}

	public void setDistance(float Distance) {
		this.Distance = Distance;
	}

}
