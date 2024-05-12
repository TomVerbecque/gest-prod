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

public class AchatVenteController {
	@FXML
	private TableView<AchatVente> tableViewAchatVente;
	
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
		        System.out.println(achatvente);
			    ObservableList<AchatVente> observableList = FXCollections.observableList(achatvente);
			    tableViewAchatVente.setItems(observableList);
			    System.out.println(observableList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	    
		});
}}
