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
import java.util.Map.Entry;

import javafx.scene.control.TableView;

public class GestionCSV {

    public static List<Element> readElementCSV(String cheminFichier) throws IOException {
        List<Element> elements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            reader.readLine(); // Ignorer l'en-tête
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
                Element element = new Element(data[0], data[1], (data[2]), data[3]);
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
                
                Map<Element, String> entrees = parseElementQuantite(data[3], elementsMap);
                Map<Element, String> sorties = parseElementQuantite(data[4], elementsMap);
                ChaineProduction chaine = new ChaineProduction(data[0], data[1],data[2], entrees, sorties);
                chaines.add(chaine);
            }
        }
        return chaines;
    }
    
    public static List<String[]> readChaineEntreSortieCSV(String cheminFichier) throws IOException {
        List<String[]> entreeSortie = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
                
                
                entreeSortie.add(new String[]{data[3], data[4]});
            }
        }
        return entreeSortie;
    }
    
    public static List<AchatVente> readPrixCSV(String cheminFichier, Map<String, Element> elementsMap) throws IOException {
        List<AchatVente> achatventes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
               
                String nomElement = elementsMap.get(data[0]).getName();
                AchatVente achatvente = new AchatVente(nomElement,data[1], data[2],(data[3]));
                achatventes.add(achatvente);
            }
        }
        return achatventes;
    }
    public static String formatElements(Map<Element, String> elements) {
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
    private static Map<Element, String> parseElementQuantite(String data, Map<String, Element> elementsMap) {
        Map<Element, String> elements = new HashMap<>();
        for (String part : data.split("\\|")) {
            String[] item = part.split(":");
            Element element = elementsMap.get(item[0]);
            String quantite = (item[1]);
            elements.put(element, quantite);
        }
        return elements;
    }
    
    public static void saveChaineCSV(List<ChaineProduction> chaines, String cheminFichier) throws IOException {
    	List<String[]> entreeSortie = readChaineEntreSortieCSV(cheminFichier);
    	String entree;
    	String sortie;
    	String[] infos;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            writer.write("Code,Nom,Niveau d'Activation,Entrees,Sorties\n");
            for (int i = 0; i < chaines.size(); i++) {
            	infos=entreeSortie.get(i);
            	entree= infos[0];
            	sortie = infos[1];
                writer.write(chaines.get(i).getCode() + "," +
                             chaines.get(i).getName() + "," +
                             chaines.get(i).getActivationLevel() + "," +
                             entree + "," +
                             sortie + "\n");
            }
        }
    }
    
    public static void savePrixCSV(List<AchatVente> achatVentes, String cheminFichier,Map<String, Element> mapElements) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
        	
            writer.write("Code,Prix Achat,Prix Vente,Quantité Commandée\n");
            for (AchatVente achatVente : achatVentes) {
            	String code =  getCodeByElementName(mapElements, achatVente.getElement());
                writer.write(code + "," +
                			achatVente.getAchat() + "," +
                			achatVente.getVente() + "," +
                			achatVente.getQuantite() + "\n");
            }
        }
    }
    
    public static String getCodeByElementName(Map<String, Element> map, String nom) {
        for (Map.Entry<String, Element> entry : map.entrySet()) {
            if (entry.getValue().getName().equals(nom)) {
                return entry.getKey();  // Retourne le code de l'élément correspondant
            }
        }
        return null; // Retourne null si aucun élément correspondant n'est trouvé
    }
    
    
    
}
