package l3miage.gest_prod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionCSV {

    public static List<Element> readElementCSV(String cheminFichier) throws IOException {
        List<Element> elements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // Ignorer l'en-tête
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
                Element element = new Element(data[0], data[1], Integer.parseInt(data[2]), data[3]);
                elements.add(element);
            }
        }
        return elements;
    }

    public static List<ChaineProduction> readChaineCSV(String cheminFichier, Map<String, Element> elementsMap) throws IOException {
        List<ChaineProduction> chaines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
                Map<Element, Integer> entrees = parseElement(data[2], elementsMap);
                Map<Element, Integer> sorties = parseElement(data[3], elementsMap);
                ChaineProduction chaine = new ChaineProduction(data[0], data[1],1, entrees, sorties);
                chaines.add(chaine);
            }
        }
        return chaines;
    }
    
    private static Map<Element, Integer> parseElement(String data, Map<String, Element> elementsMap) {
        Map<Element, Integer> elements = new HashMap<>();
        for (String part : data.split("\\|")) {
            String[] item = part.split(":");
            Element element = elementsMap.get(item[0]);
            int quantite = Integer.parseInt(item[1]);
            elements.put(element, quantite);
        }
        return elements;
    }
}
