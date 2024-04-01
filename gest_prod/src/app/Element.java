package app;

public class Element {
	
	private String code;
	private String nom;
	private double qteStock;
	private String unite;
	
	public Element(String p_code, String p_nom, double p_qteStock, String p_unite) {
		this.code = p_code;
		this.nom = p_nom;
		this.qteStock = p_qteStock;
		this.unite = p_unite;
	}
	
	public String getCode() {
		return code;
	}

	public String getNom() {
		return nom;
	}
	
	public double getQteStock() {
		return qteStock;
	}
	
	public void setQteStock(double nouvQte) {
		this.qteStock = nouvQte;
	}
	
	public void ajouterQte (double qte) {
		this.qteStock += qte;
	}
	
	public void soustraireQte(double qte) {
		this.qteStock -= qte;
	}

	public String getUnite() {
		return unite;
	}
	
	public String toString() {
		return "Element " + this.code +" : nom : " + this.nom + ", qteStock : " + this.qteStock + ", unité : " + this.unite;
	}
}
