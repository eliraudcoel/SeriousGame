package Modeles;

public class Evenement {
	
	private int id_evenement;
	private String type_evenement;
	private int quantite;
	private float prix;
	private float perte;
	
	public Evenement(int id_evenement, String type_evenement, int quantite,
			float prix, float perte) {
		super();
		this.id_evenement = id_evenement;
		this.type_evenement = type_evenement;
		this.quantite = quantite;
		this.prix = prix;
		this.perte = perte;
	}

	public int getId_evenement() {
		return id_evenement;
	}

	public void setId_evenement(int id_evenement) {
		this.id_evenement = id_evenement;
	}

	public String getType_evenement() {
		return type_evenement;
	}

	public void setType_evenement(String type_evenement) {
		this.type_evenement = type_evenement;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public float getPerte() {
		return perte;
	}

	public void setPerte(float perte) {
		this.perte = perte;
	}
}
