package l3miage.gest_prod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.TableView;

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
                
                Map<Element, Integer> entrees = parseElementQuantite(data[3], elementsMap);
                Map<Element, Integer> sorties = parseElementQuantite(data[4], elementsMap);
                ChaineProduction chaine = new ChaineProduction(data[0], data[1],data[2], entrees, sorties);
                chaines.add(chaine);
            }
        }
        return chaines;
    }
    
    public static List<AchatVente> readPrixCSV(String cheminFichier, Map<String, Element> elementsMap) throws IOException {
        List<AchatVente> achatventes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
               
                String nomElement = elementsMap.get(data[0]).getName();
                AchatVente achatvente = new AchatVente(nomElement,data[1], data[2],Integer.parseInt((data[3])));
                achatventes.add(achatvente);
            }
        }
        return achatventes;
    }
    public static String formatElements(Map<Element, Integer> elements) {
        StringBuilder builder = new StringBuilder();
        if (elements != null) {
            elements.forEach((element, quantity) -> {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append(element.getName() + " x " + quantity);  // Assumant que Element a une méthode getName()
            });
        }
        return builder.toString();
    }
    private static Map<Element, Integer> parseElementQuantite(String data, Map<String, Element> elementsMap) {
        Map<Element, Integer> elements = new HashMap<>();
        for (String part : data.split("\\|")) {
            String[] item = part.split(":");
            Element element = elementsMap.get(item[0]);
            int quantite = Integer.parseInt(item[1]);
            elements.put(element, quantite);
        }
        return elements;
    }
    
    public static void saveChaineCSV(List<ChaineProduction> chaines, String cheminFichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            writer.write("Code,Nom,Niveau d'Activation,Entrees,Sorties\n");
            for (ChaineProduction chaine : chaines) {
                writer.write(chaine.getCode() + "," +
                             chaine.getName() + "," +
                             chaine.getActivationLevel() + "," +
                             chaine.getInputElements() + "," +
                             chaine.getOutputElements() + "\n");
            }
        }
    }
    
    
}
