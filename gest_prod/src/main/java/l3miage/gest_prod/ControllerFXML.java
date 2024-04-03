package l3miage.gest_prod;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerFXML {
	 
	public void loadStockView(ActionEvent event) {
		try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stock.fxml"));
	            Parent secondView = loader.load();

	            Scene secondScene = new Scene(secondView);
	            Stage newWindow = new Stage();
	            newWindow.setTitle("Stock");
	            newWindow.setScene(secondScene);

	            newWindow.show();
		} catch (Exception e) {
	            e.printStackTrace();
		}
	}

	    
	public void loadChaineProductionView(ActionEvent event) {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChaineProduction.fxml"));
	        Parent secondView = loader.load();

	        Scene secondScene = new Scene(secondView);
	        Stage newWindow = new Stage();
	        newWindow.setTitle("Chaine de production");
	        newWindow.setScene(secondScene);

	        newWindow.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadAchatVenteView(ActionEvent event) {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AchatVente.fxml"));
	        Parent secondView = loader.load();

	        Scene secondScene = new Scene(secondView);
	        Stage newWindow = new Stage();
	        newWindow.setTitle("Achats et ventes");
	        newWindow.setScene(secondScene);

	        newWindow.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void loadView(String fxmlFile, ActionEvent event) {
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
