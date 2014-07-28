package Modeles;

public class Tour {

	private int id_tour;
	private int num_tour;
	private String regle;
	private boolean tour_actuel;
	
	public Tour(int id_tour, int num_tour, String regle, boolean tour_actuel) {
		super();
		this.id_tour = id_tour;
		this.num_tour = num_tour;
		this.regle = regle;
		this.tour_actuel = tour_actuel;
	}
	
	public int getId_tour() {
		return id_tour;
	}
	public void setId_tour(int id_tour) {
		this.id_tour = id_tour;
	}
	public int getNum_tour() {
		return num_tour;
	}
	public void setNum_tour(int num_tour) {
		this.num_tour = num_tour;
	}
	public String getRegle() {
		return regle;
	}
	public void setRegle(String regle) {
		this.regle = regle;
	}
	public boolean isTour_actuel() {
		return tour_actuel;
	}
	public void setTour_actuel(boolean tour_actuel) {
		this.tour_actuel = tour_actuel;
	}
}
