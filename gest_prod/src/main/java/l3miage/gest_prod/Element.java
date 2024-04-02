package l3miage.gest_prod;

public class Element {
	
    private String code;
    private String nom;
    private int qteStock;
    private String unite;

    public Element(String p_code, String p_nom, int p_qteStock, String p_unite) {
        this.code = p_code;
        this.nom = p_nom;
        this.qteStock = p_qteStock;
        this.unite = p_unite;
    }

    public String getCode() {
        return this.code;
    }

    public String getNom() {
        return this.nom;
    }

    public int getQteStock() {
        return this.qteStock;
    }
    public void setQteStock(int quantite) {
        this.qteStock = quantite;
    }

    public String getUniteMesure() {
        return this.unite;
    }

    public void ajouterQteStock(double qte) {
        this.qteStock += qte;
    }
    
    public void soustraireQteStock(double qte) {
        this.qteStock -= qte;
    }

    @Override
    public String toString() {
        return "Element " + this.code + ", nom : " + this.nom + ", quantité en stock : " + this.qteStock + ", unité de mesure : " + this.unite;
    }
}
