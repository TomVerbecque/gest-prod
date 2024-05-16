package l3miage.gest_prod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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
	
	/**
	 * Récupérer les éléments dans le fichier csv et les instancier en objet element
	 * @param cheminFichier
	 * @return List<Element> elements
	 * @throws IOException
	 */
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
    
    /**
     * Récupérer les chaines de production du fichier csv et les instancier en comparant les élements en sorties et entrees avec une map<string,element>
     * @param cheminFichier
     * @param elementsMap
     * @return List<ChaineProduction> chaines
     * @throws IOException
     */
    public static List<ChaineProduction> readChaineCSV(String cheminFichier, Map<String, Element> elementsMap) throws IOException {
        List<ChaineProduction> chaines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
                
                Map<Element, String> entrees = parseElementQuantite(data[3], elementsMap);
                Map<Element, String> sorties = parseElementQuantite(data[4], elementsMap);
                ChaineProduction chaine = new ChaineProduction(data[0], data[1],data[2], entrees, sorties,data[3],data[4],data[5]);
                chaines.add(chaine);
            }
        }
        return chaines;
    }
    
   /**
    * Récupère la liste personnel du fichier csv et compare avec listChaine pour pouvoir ajouter la ou les listes à la personne
    * @param cheminFichier
    * @param chaineProductionMap
    * @param listChaine
    * @return List<Personnel> personnels
    * @throws IOException
    */
   public static List<Personnel> readPersonnelCSV(String cheminFichier, Map<String, ChaineProduction> chaineProductionMap, List<ChaineProduction> listChaine) throws IOException {
        List<Personnel> personnels = new ArrayList<>();
        List<ChaineProduction>chaines= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
                
                String chaine = parseChaine(data[3], listChaine);
                chaines = parseChaineString(data[3],listChaine);
                Personnel personnel = new Personnel(data[0], data[1],data[2],chaine,chaines);
                personnels.add(personnel);
            }
        }
        return personnels;
    }
    
   
   /**
    *  Récupérer les chaines de production avec un niveau d'activation supérieur à 0 du fichier csv et les instancier en comparant les élements en sorties et entrees avec une map<string,element>
    * @param cheminFichier
    * @param elementsMap
    * @return List<ChaineProduction> chaines
    * @throws IOException
    */
    public static List<ChaineProduction> readChaineActiveCSV(String cheminFichier, Map<String, Element> elementsMap) throws IOException {
        List<ChaineProduction> chaines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
                if(Integer.parseInt(data[2])>0) {
	                Map<Element, String> entrees = parseElementQuantite(data[3], elementsMap);
	                Map<Element, String> sorties = parseElementQuantite(data[4], elementsMap);
	                ChaineProduction chaine = new ChaineProduction(data[0], data[1],data[2], entrees, sorties,data[3],data[4],data[5]);
	                chaines.add(chaine);
            }
                }
        }
        return chaines;
    }
    
    /**
     * Récupère une liste avec en indice 0 l'entree et indice 1 la sortie de la chaine de production
     * @param cheminFichier
     * @return List<String[]> entreeSortie
     * @throws IOException
     */
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
    
    /**
     * récupère le prix des éléments en comparant le code grâce à la map
     * @param cheminFichier
     * @param elementsMap
     * @return List<AchatVente> achatventes
     * @throws IOException
     */
    public static List<AchatVente> readPrixCSV(String cheminFichier, Map<String, Element> elementsMap) throws IOException {
        List<AchatVente> achatventes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
               
                String nomElement = elementsMap.get(data[0]).getName();
                AchatVente achatvente = new AchatVente(data[0],nomElement,data[1], data[2],(data[3]));
                achatventes.add(achatvente);
            }
        }
        return achatventes;
    }
    
    /**
     * Récupère une liste comprenant le stock de l'objet + la quantite commandée
     * @param cheminFichier
     * @param elementsMap
     * @return List<AchatVente> achatventes
     * @throws IOException
     */
    public static List<AchatVente> ajoutCommandeStockCSV(String cheminFichier, Map<String, Element> elementsMap) throws IOException {
        List<AchatVente> achatventes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(",");
               
                String nomElement = elementsMap.get(data[0]).getName();
                AchatVente achatvente = new AchatVente(data[0],nomElement,data[1], data[2],(data[3]));
                achatventes.add(achatvente);
            }
        }
        return achatventes;
    }
    
    /**
     * Retourne une map avec le code de l'élément et son prix
     * @param cheminFichier
     * @return  Map<String, Integer> codePrix
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Map<String,Integer> codePrixVente (String cheminFichier) throws FileNotFoundException, IOException{
    	 Map<String, Integer> codePrix = new HashMap<>();
    	 try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
             reader.readLine();  // Ignorer l'en-tête
             String ligne;
             while ((ligne = reader.readLine()) != null) {
                 String[] data = ligne.split(",");
                
                 codePrix.put(data[0], Integer.parseInt(data[2]));
             }
         }
    	 return codePrix;
    }
    
    /**
     * Permet de récupérer en string le nom de l'élément et sa quantité à partir de ce qui est récupéré du fichier csv
     * @param elements
     * @return StringBuilder builder
     */
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
    
    /**
     *  Permet de récupérer en string le nom de ou des élément et leur quantité en entree et/ou sortie de chaine à partir de ce qui est récupéré du fichier csv
     * @param data
     * @param elementsMap
     * @return
     */
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
    
   
    /**
     * Permet de récupérer l'element et sa quantite pour la chaine en entree ou sortie
     * @param data
     * @param elementsMap
     * @return
     */
    public static Map<String, Integer> parseElementQuantiteChaine(String data, Map<String, Element> elementsMap) {
        Map<String, Integer> elements = new HashMap<>();
        for (String part : data.split("\\|")) {
            String[] item = part.split(":");
            Element element = elementsMap.get(item[0]);
            int quantite = Integer.parseInt((item[1]));
            elements.put(element.getCode(), quantite);
        }
        return elements;
    }
    
    /**
     * récuoère pour les employés en string les chaines qu'ils peuvent réaliser
     * @param data
     * @param listChaine
     * @return String nomChaine
     */
    public static String parseChaine(String data,List<ChaineProduction> listChaine) {
        String nomChaine = "";
        
        for (String part : data.split("\\|")) {
        	
        	 for(ChaineProduction chaines : listChaine) {
          	   if(part.equals(chaines.getCode())) {
          		   nomChaine=nomChaine+" "+chaines.getName();       		  
          	   }       	                          
             }    
            
        }
        return nomChaine;
    }
    
 
    /**
     * récupère pour les employés une liste de chaine dont ils peuvent s'occuper
     * @param data
     * @param chaine
     * @return List<ChaineProduction> nomChaine
     */
	public static List<ChaineProduction> parseChaineString(String data, List<ChaineProduction> chaine) {
		List<ChaineProduction> nomChaine=new ArrayList<>();
        
        for (String part : data.split("\\|")) {
        	
           for(ChaineProduction chaines : chaine) {
        	   
        	   if(part.equals(chaines.getCode())) {
        		   
        		   nomChaine.add(chaines);
        		          		   
        	   }       	                          
           }       
        }
        return nomChaine;
    }
    
    /**
     * Sauvegarde les modifs faites sur les niveau de modification
     * @param chaines
     * @param cheminFichier
     * @throws IOException
     */
    public static void saveChaineCSV(List<ChaineProduction> chaines, String cheminFichier) throws IOException {
    	List<String[]> entreeSortie = readChaineEntreSortieCSV(cheminFichier);
    	String entree;
    	String sortie;
    	String[] infos;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            writer.write("Code,Nom,Niveau d'Activation,Entrees,Sorties,Personnel\n");
            for (int i = 0; i < chaines.size(); i++) {
            	infos=entreeSortie.get(i);
            	entree= infos[0];
            	sortie = infos[1];
                writer.write(chaines.get(i).getCode() + "," +
                             chaines.get(i).getName() + "," +
                             chaines.get(i).getActivationLevel() + "," +
                             entree + "," +
                             sortie + "," +
                             chaines.get(i).getPersonnel() + "\n");
            }
        }
    }
    
    /**
     * sauvegarde les modifs faites sur la quantite achetés
     * @param achatVentes
     * @param cheminFichier
     * @param mapElements
     * @throws IOException
     */
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
    
    /**
     * permet de récupérer le code dans une map à partir de son nom
     * @param map
     * @param nom
     * @return
     */
    public static String getCodeByElementName(Map<String, Element> map, String nom) {
        for (Map.Entry<String, Element> entry : map.entrySet()) {
            if (entry.getValue().getName().equals(nom)) {
                return entry.getKey();  // Retourne le code de l'élément correspondant
            }
        }
        return null; // Retourne null si aucun élément correspondant n'est trouvé
    }
    
    
    
}
