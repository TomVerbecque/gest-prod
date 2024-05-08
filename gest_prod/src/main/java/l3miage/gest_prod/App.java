package l3miage.gest_prod;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
/*
public class App {
  public static void main(String[] args) {
    System.out.println("L'application démarre !");
    
    //System.out.println("Répertoire de travail actuel : " + System.getProperty("user.dir"));
    
    String fichierElement = "src/main/java/l3miage/gest_prod/files/elements.csv";
    String fichierChaine = "src/main/java/l3miage/gest_prod/files/chaines.csv";

    try {
        List<Element> elements = GestionCSV.readElementCSV(fichierElement);
        for (Element element : elements) {
            System.out.println(element);
        }
    } catch (IOException e) {
        System.out.println("Erreur lors de la lecture du fichier elements.csv: " + e.getMessage());
    }
    
    try {
        List<Element> listElements = GestionCSV.readElementCSV(fichierElement);
        Map<String, Element> mapElements = new HashMap<>();
        for (Element element : listElements) {
            mapElements.put(element.getCode(), element);
        }
        List<ChaineProduction> chaines = GestionCSV.readChaineCSV(fichierChaine, mapElements);
        
        for (ChaineProduction chaine : chaines) {
            System.out.println(chaine);
        }
    } catch (IOException e) {
        System.out.println("Erreur lors de la lecture du fichier CSV: " + e.getMessage());
        e.printStackTrace();
    }
  }
}
*/

public class App extends Application {
	
	@Override
	public void start(Stage primaryStage) {
	    try {
	    	
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            primaryStage.setTitle("Gestion Production");
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		System.out.println("Répertoire de travail actuel : " + System.getProperty("user.dir"));
		launch(args);
	}
}
