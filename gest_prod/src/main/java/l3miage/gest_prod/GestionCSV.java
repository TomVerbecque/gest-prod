package l3miage.gest_prod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public static ObservableList<ChaineProduction> readChaineCSV(String filePath) {
        ObservableList<ChaineProduction> chaines = FXCollections.observableArrayList();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.skip(1) // Sauter l'en-tête
                .map(line -> {
                    String[] data = line.split(",");
                    return new ChaineProduction(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4]);
                })
                .forEach(chaines::add);
        } catch (IOException e) {
            e.printStackTrace();
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
