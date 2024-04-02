package l3miage.gest_prod;

import java.util.Map;

public class ChaineProduction {

	private String code;
	private String nom;
	private Map<Element, Integer> elementsEntres;
	private Map<Element, Integer> elementsSortis;
	private int niveauProd;
	
	public ChaineProduction(String p_code, String p_nom, Map<Element, Integer> p_elementsEntres, Map<Element, Integer> p_elementsSortis) {
		this.code = p_code;
		this.nom = p_nom;
		this.elementsEntres = p_elementsEntres;
		this.elementsSortis = p_elementsSortis;
		this.niveauProd = 0;
	}
	
	public String getCode() {
        return this.code;
    }

    public String getNom() {
        return this.nom;
    }

    public Map<Element, Integer> getEntrees() {
        return this.elementsEntres;
    }

    public Map<Element, Integer> getSorties() {
        return this.elementsSortis;
    }

    public int getNiveauActivation() {
        return this.niveauProd;
    }
    
    public String toString() {
        return "ChaineDeProduction " + this.code + ", nom : " + this.nom + ", entrees : " + this.elementsEntres + ", sorties : " + this.elementsSortis + ", niveauActivation : " + this.niveauProd;
    }
}