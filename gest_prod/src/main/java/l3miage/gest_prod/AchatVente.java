package l3miage.gest_prod;

import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AchatVente {
	private String code;
	private SimpleStringProperty element;
    private SimpleStringProperty achat;
    private SimpleStringProperty vente;
    private SimpleStringProperty quantite;

    public AchatVente(String code,String entrees, String p_prixAchat, String p_prixVente, String p_quantite) {
    	this.code=code;
        this.element = new SimpleStringProperty(entrees);
        this.achat = new SimpleStringProperty(p_prixAchat);
        this.vente = new SimpleStringProperty(p_prixVente);
        this.quantite = new SimpleStringProperty(p_quantite);
    }
    public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
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


	public String getQuantite() {
		return this.quantite.get();
	}
	


	public void setQuantite(String quantite) {
		this.quantite.set(quantite);
	}
	
	
}