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
    
    
    //Getters
    public String getCode() {return code;}
    public String getElement() {return this.element.get();}
	public String getAchat() {return this.achat.get();}
	public String getVente() {return this.vente.get();}
	public String getQuantite() {return this.quantite.get();}

    //Setters
    public void setCode(String code){this.code = code;}	
    public void setElement(String element) {this.element.set(element);}
	public void setAchat(String achat) {this.achat.set(achat);}
	public void setVente(String vente) {this.vente.set(vente);}
	public void setQuantite(String quantite) {this.quantite.set(quantite);}
	
	
}