package l3miage.gest_prod;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
