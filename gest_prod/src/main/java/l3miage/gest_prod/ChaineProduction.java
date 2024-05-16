package l3miage.gest_prod;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Map;

public class ChaineProduction {
    private SimpleStringProperty code;
    private SimpleStringProperty name;
    private SimpleStringProperty activationLevel;
    private SimpleStringProperty inputElements;
    private SimpleStringProperty outputElements;
    private SimpleStringProperty personnel;
	private String sortieString;
	private String entreeString;

    // Constructeur
    public ChaineProduction(String code, String name,String activationLevel, Map<Element, String> entrees, Map<Element, String> sorties, String entreeString, String sortieString, String personnel) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.activationLevel = new SimpleStringProperty(activationLevel);
        this.inputElements = new SimpleStringProperty(GestionCSV.formatElements(entrees));
        this.outputElements = new SimpleStringProperty(GestionCSV.formatElements(sorties));
        this.personnel =new SimpleStringProperty(personnel);
        this.sortieString= sortieString;
        this.entreeString = entreeString;
    }

    //Getters
    public String getCode() {return code.get();}
    public String getName() {return name.get();}
	public String getActivationLevel() {return activationLevel.get();}
	public String getInputElements() {return inputElements.get();}
	public String getOutputElements() {return outputElements.get();}
    public String getSortieString() {return sortieString;}
	public String getEntreeString() {return entreeString;}
	public String getPersonnel() {return personnel.get();}

    //Setters   
    public void setCode(String code) {this.code.set(code);}
    public void setName(String name) {this.name.set(name);}
	public void setActivationLevel(String activationLevel) {this.activationLevel.set(activationLevel);}
	public void setInputElements(String inputElements) {this.inputElements.set(inputElements);}
	public void setOutputElements(String outputElements) {this.outputElements.set(outputElements);}
	public void setSortieString(String sortieString) {this.sortieString = sortieString;}
	public void setEntreeString(String entreeString) {this.entreeString = entreeString;}
	public void setPersonnel(String personnel) {this.personnel.set(personnel);;}




}
