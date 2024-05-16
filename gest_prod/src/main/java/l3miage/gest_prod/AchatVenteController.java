package l3miage.gest_prod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class AchatVenteController {
	
	@FXML
	private TableView<AchatVente> tableViewAchatVente;
	@FXML
	private TableColumn<AchatVente, String> quantiteColumn;
	
	private void saveQuantiteChanges(Map<String, Element> mapElements) {
	    try {
	        GestionCSV.savePrixCSV(tableViewAchatVente.getItems(),"src/main/java/l3miage/gest_prod/files/prix.csv",  mapElements);
	        System.out.println("Modifications sauvegardées.");
	    } catch (IOException e) {
	        e.printStackTrace(); 
	        System.out.println("Erreur lors de la sauvegarde des modifications.");
	    }
	}
	
	@FXML
	public void initialize() {
		Platform.runLater(() -> {
		List<AchatVente> achatvente= new ArrayList<>();
		List<Element> listElements;
		try {
			listElements = GestionCSV.readElementCSV("src/main/java/l3miage/gest_prod/files/elements.csv");
			 Map<String, Element> mapElements = new HashMap<>();
		        for (Element element : listElements) {
		            mapElements.put(element.getCode(), element);
		            
		        }
		        achatvente = GestionCSV.readPrixCSV("src/main/java/l3miage/gest_prod/files/prix.csv", mapElements);
			    ObservableList<AchatVente> observableList = FXCollections.observableList(achatvente);
			    tableViewAchatVente.setItems(observableList);
			    quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
			  
			    quantiteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			    quantiteColumn.setOnEditCommit(event -> {
			        AchatVente achatVente = event.getRowValue();
			        if (!"NA".equals(achatVente.getAchat())) {
			            achatVente.setQuantite(event.getNewValue());
			            saveQuantiteChanges(mapElements);
			        }
			    });
	       
			    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	
	    
		});
}}
