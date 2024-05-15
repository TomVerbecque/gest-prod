package l3miage.gest_prod;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indicateur {
	
	public static boolean QuantiteDisponible(Map<String, Integer> quantiteTotale, Map<String, Integer> quantiteChaineEntree) {
        for (String code : quantiteChaineEntree.keySet()) {
            Integer neededQuantity = quantiteChaineEntree.get(code);
            Integer availableQuantity = quantiteTotale.getOrDefault(code, 0);

            if (availableQuantity < neededQuantity) {
              
                return false; // Quantité insuffisante trouvée, retourner false immédiatement
            }
            
        }
        return true; // Toutes les quantités nécessaires sont disponibles
    }
	
	public static void EnleverQuantite(Map<String, Integer> quantiteTotale, Map<String, Integer> quantiteChaineEntree) {
        quantiteChaineEntree.forEach((code, neededQuantity) -> {
            int availableQuantity = quantiteTotale.get(code);
            quantiteTotale.put(code, availableQuantity - neededQuantity);
        });
    }
	
	public static double totalAchat (String cheminFichier) throws FileNotFoundException, IOException{
   	 double totalAchat = 0;
   	 try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            reader.readLine();  // Ignorer l'en-tête
            String ligne;
            while ((ligne = reader.readLine()) != null) {
            	String[] data = ligne.split(",");
            	if(!data[1].equals("NA")) {
            		  totalAchat= totalAchat + Integer.parseInt(data[1])*Integer.parseInt(data[3]);
            	}
                
               
              
            }
        }
   	 return totalAchat;
   }

	public static double totalVenteProduction(Map<String, Integer> quantiteChaineSortie, Map<String, Integer> codePrixVente) {
	    double totalValue = 0;
	    for (Map.Entry<String, Integer> entry : quantiteChaineSortie.entrySet()) {
	        String code = entry.getKey();
	        Integer quantite = entry.getValue();
	        if (codePrixVente.containsKey(code)) {
	            Integer prixVente = codePrixVente.get(code);
	            totalValue += quantite * prixVente;
	           
	        }
	    }
	    return totalValue;
	}

	
	double calculerValeurProduction(List<ChaineProduction> chaines, Map<String, Double> prixVente) {
	    double valeurTotale = 0;
	    
	    return valeurTotale;
	}
}
