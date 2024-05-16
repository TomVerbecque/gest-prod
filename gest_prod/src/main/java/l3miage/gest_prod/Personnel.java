package l3miage.gest_prod;

import javafx.beans.property.SimpleStringProperty;

public class Personnel {
	private SimpleStringProperty code;
    private SimpleStringProperty nom;
	private SimpleStringProperty prenom;
    private SimpleStringProperty Chaine;

    public Personnel(String code, String nom, String prenom, String Chaine) {
        this.code = new SimpleStringProperty(code);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.Chaine = new SimpleStringProperty(Chaine);
    }

    public String getCode() { return code.get(); }
    public String getNom() { return nom.get(); }
    public String getPrenom() { return prenom.get(); }
    public String getChaine() { return Chaine.get(); }

    // Plus les setters si nécessaire
    public void setCode(String code) { this.code.set(code); }
    public void setNom(String nom) { this.nom.set(nom); }
    public void setPrenom(String prenom) { this.prenom.set(prenom); }
    public void setChaine(String Chaine) { this.Chaine.set(Chaine); }
}
