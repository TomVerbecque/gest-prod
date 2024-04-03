package l3miage.gest_prod;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerFXML {
	 
	    private void loadStockView(ActionEvent event) {
	        loadView("Stock.fxml", event);
	    }

	    
	    private void loadProductionView(ActionEvent event) {
	        loadView("Production.fxml", event);
	    }

	    // Méthodes similaires pour les autres boutons

	    private void loadView(String fxmlFile, ActionEvent event) {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	            Parent view = loader.load();
	            Scene scene = new Scene(view);
	            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	            stage.setScene(scene);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
