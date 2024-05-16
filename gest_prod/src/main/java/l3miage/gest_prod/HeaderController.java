package l3miage.gest_prod;



import java.io.IOException;

import com.itextpdf.text.DocumentException;

import javafx.fxml.FXML;

public class HeaderController {
    private ControllerFXML mainController;
    
    //à l'initialisation on appelle le controller principal
    public void init(ControllerFXML mainController) {
        this.mainController = mainController;
    }
    
    //Fonctions permettant de naviguer entre les vues avec les boutons de l'header
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
    
    @FXML
    public void loadPersonnelView() {
        mainController.loadView("/Personnel.fxml");
    }
    
    //Fonction d'export de la simulation en pdf
    @FXML
    public void Export() throws DocumentException, IOException {
        ExportPDF.exportDataToPDF();
    }
}
