package Modeles;

public class Entreprise {
	
	private int id_entreprise;
	private String nom_entreprise;
	private float capital;
	
	public Entreprise(int id_entreprise, String nom_entreprise, float capital) {
		super();
		this.id_entreprise = id_entreprise;
		this.nom_entreprise = nom_entreprise;
		this.capital = capital;
	}

	public int getId_entreprise() {
		return id_entreprise;
	}

	public void setId_entreprise(int id_entreprise) {
		this.id_entreprise = id_entreprise;
	}

	public String getNom_entreprise() {
		return nom_entreprise;
	}

	public void setNom_entreprise(String nom_entreprise) {
		this.nom_entreprise = nom_entreprise;
	}

	public float getCapital() {
		return capital;
	}

	public void setCapital(float capital) {
		this.capital = capital;
	}
}
