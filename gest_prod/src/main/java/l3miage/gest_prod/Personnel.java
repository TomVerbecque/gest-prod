package l3miage.gest_prod;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class Personnel {
	private SimpleStringProperty code;
    private SimpleStringProperty nom;
	private SimpleStringProperty prenom;
    private SimpleStringProperty Chaine;
    private boolean estDispo;
    private List<ChaineProduction> chaines;

    

	public Personnel(String code, String nom, String prenom, String Chaine, List<ChaineProduction>chaines) {
        this.code = new SimpleStringProperty(code);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.Chaine = new SimpleStringProperty(Chaine);
        this.estDispo=true;
        this.chaines=chaines;
    }

	//Getters
    public String getCode() { return code.get(); }
    public String getNom() { return nom.get(); }
    public String getPrenom() { return prenom.get(); }
    public String getChaine() { return Chaine.get(); }
    public boolean isEstDispo() {return estDispo;}
	public List<ChaineProduction> getChaines() {return chaines;}

    //Setters
    public void setCode(String code) { this.code.set(code); }
    public void setNom(String nom) { this.nom.set(nom); }
    public void setPrenom(String prenom) { this.prenom.set(prenom); }
    public void setChaine(String Chaine) { this.Chaine.set(Chaine); }
	public void setEstDispo(boolean estDispo) {this.estDispo = estDispo;}
	public void setChaines(List<ChaineProduction> chaines) {this.chaines = chaines;}

    



	
}
