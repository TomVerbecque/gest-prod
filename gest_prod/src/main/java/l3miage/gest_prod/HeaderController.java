package l3miage.gest_prod;



import javafx.fxml.FXML;

public class HeaderController {
    private ControllerFXML mainController;
    

    public void init(ControllerFXML mainController) {
        this.mainController = mainController;
    }
    
    @FXML
    public void loadAccueilView() {
        mainController.loadView("/Accueil.fxml");
    }
    
    @FXML
    public void loadStockView() {
        mainController.loadView("/Stock.fxml");
    }

    @FXML
    public void loadChaineProductionView() {
        mainController.loadView("/ChaineProduction.fxml");
    }

    @FXML
    public void loadAchatVenteView() {
        mainController.loadView("/AchatVente.fxml");
    }
}
