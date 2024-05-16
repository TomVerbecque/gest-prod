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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class PersonnelController {
	@FXML public TableView<Personnel> tableViewPersonnel;
	
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
			            
			          
			        }
			      chaines = GestionCSV.readChaineCSV("src/main/java/l3miage/gest_prod/files/chaines.csv", mapElements);
		          Map<String, ChaineProduction> chaineProductionMap= new HashMap<>();	
		          for (ChaineProduction chaine : chaines) {
		        	  chaineProductionMap.put(chaine.getCode(), chaine);
			            
			          
			        }
		          List<Personnel> personnel = GestionCSV.readPersonnelCSV("src/main/java/l3miage/gest_prod/files/personnel.csv",chaineProductionMap);
				  ObservableList<Personnel> observableList = FXCollections.observableList(personnel);
				  tableViewPersonnel.setItems(observableList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	       
		    
			});
	}
}

