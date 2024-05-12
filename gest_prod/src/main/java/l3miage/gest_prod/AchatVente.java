package l3miage.gest_prod;

import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AchatVente {
    private SimpleStringProperty element;
    private SimpleStringProperty achat;
    private SimpleStringProperty vente;
    private SimpleIntegerProperty quantite;

    public AchatVente(String entrees, String p_prixAchat, String p_prixVente, int p_quantite) {
        this.element = new SimpleStringProperty(entrees);
        this.achat = new SimpleStringProperty(p_prixAchat);
        this.vente = new SimpleStringProperty(p_prixVente);
        this.quantite = new SimpleIntegerProperty(p_quantite);
    }

    
	public String getElement() {
		return this.element.get();
	}
	

	public void setElement(String element) {
		this.element.set(element);
	}
	

	public String getAchat() {
		return this.achat.get();
	}
	

	public void setAchat(String achat) {
		this.achat.set(achat);
	}
	

	

	public String getVente() {
		return this.vente.get();
	}
	

	public void setVente(String vente) {
		this.vente.set(vente);
	}


	public int getQuantite() {
		return this.quantite.get();
	}
	


	public void setQuantite(int quantite) {
		this.quantite.set(quantite);
	}
	
	
}