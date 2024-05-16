package l3miage.gest_prod;


import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControllerFXML {

    @FXML
    private HeaderController headerController; 

    @FXML
    private Pane mainContent;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            if (headerController != null) {
                headerController.init(this);
                loadView("/Accueil.fxml");
            } else {
                System.out.println("HeaderController is not yet initialized.");
            }
        });
    }

    public void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Node view = loader.load();
            mainContent.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

}


