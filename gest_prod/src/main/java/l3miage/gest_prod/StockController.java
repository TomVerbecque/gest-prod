package l3miage.gest_prod;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class StockController {
    @FXML
    private TableView<Element> tableViewStock;
    
    
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            List<Element> elements = null;
			try {
				elements = GestionCSV.readElementCSV("src/main/java/l3miage/gest_prod/files/elements.csv");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ObservableList<Element> data = FXCollections.observableArrayList(elements);
            System.out.println(data);
            tableViewStock.setItems(data);
        });
    }


   
}