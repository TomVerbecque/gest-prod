package l3miage.gest_prod;



import java.io.IOException;

import com.itextpdf.text.DocumentException;

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
    
    @FXML
    public void Export() throws DocumentException, IOException {
        ExportPDF.exportDataToPDF();
    }
}
