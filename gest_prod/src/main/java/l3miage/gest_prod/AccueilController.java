package l3miage.gest_prod;

import java.awt.Label;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AccueilController {
	
	private StringProperty texte = new SimpleStringProperty("Texte initial");
	@FXML
    private static Label labelValeur= new Label();
	
    @FXML
    private static Label labelProduction= new Label();      
	
	@FXML
	public void initialize() {
		
		Platform.runLater(() -> {
			
			List<ChaineProduction> chaines= new ArrayList<>();
			List<Element> listElements;
			try {
				
				listElements = GestionCSV.readElementCSV("src/main/java/l3miage/gest_prod/files/elements.csv");
				 Map<String, Element> mapElements = new HashMap<>();
			        for (Element element : listElements) {
			            mapElements.put(element.getCode(), element);
			            System.out.println(mapElements);
			            
			        }
			        
			        chaines = GestionCSV.readChaineActiveCSV("src/main/java/l3miage/gest_prod/files/chaines.csv", mapElements);
			        Map<String,Integer> quantiteTotale = new HashMap<>();
			        Map<String,Integer> quantiteChaineEntree = new HashMap<>();
			        List<AchatVente> listAchatVentes = GestionCSV.readPrixCSV("src/main/java/l3miage/gest_prod/files/prix.csv", mapElements);
			        List<ChaineProduction> chaineReussies = new ArrayList<ChaineProduction>();
			        AchatVente achatVente;
			        Element element;
			        int totalChaine = 0;
			        int chaineReussie =0;
			        for(int i=0; i<listAchatVentes.size();i++) {
			        	achatVente=listAchatVentes.get(i);
			        	element=listElements.get(i);
			        	
			        	quantiteTotale.put(achatVente.getCode(), Integer.parseInt(achatVente.getQuantite())+Integer.parseInt(element.getQuantity()));
			        	System.out.println(quantiteTotale);
			        }
			        for(ChaineProduction chaine: chaines) {
			        	for(int i=0; i< Integer.parseInt(chaine.getActivationLevel());i++) {
			        		totalChaine= totalChaine+1;
				        	quantiteChaineEntree=GestionCSV.parseElementQuantiteChaine(chaine.getEntreeString(),mapElements);
				        	System.out.println(quantiteChaineEntree);
				        	if(Indicateur.QuantiteDisponible(quantiteTotale, quantiteChaineEntree)){
				        		chaineReussie=chaineReussie+1;
				        		chaineReussies.add(chaine);
				        		Indicateur.EnleverQuantite(quantiteTotale,quantiteChaineEntree);
				        		System.out.println(quantiteTotale);
				        	}
			        	}
			        }
			        System.out.println(totalChaine);
			        System.out.println(chaineReussie);
			        System.out.println(chaines);
			        
			        double pourcentageChaineReussie= chaineReussie*100/totalChaine;
			        System.out.println(pourcentageChaineReussie);
			        double valeurAchat =Indicateur.totalAchat("src/main/java/l3miage/gest_prod/files/prix.csv");
			        
			        
			        double totalValeur = 0;
			        Map<String,Integer> quantiteChaineSortie = new HashMap<>();
			        Map<String,Integer> codePrixVente=  new HashMap<>();
			        codePrixVente=GestionCSV.codePrixVente("src/main/java/l3miage/gest_prod/files/prix.csv");
			        for(ChaineProduction chaine: chaineReussies) {
			        	quantiteChaineSortie=GestionCSV.parseElementQuantiteChaine(chaine.getSortieString(),mapElements);
			        	totalValeur=totalValeur+Indicateur.totalVenteProduction(quantiteChaineSortie,codePrixVente);
			        }
			        totalValeur=totalValeur-valeurAchat;
			        System.out.println(totalValeur);
			      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

       
	    
		});
	

}
}
