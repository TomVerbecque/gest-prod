package l3miage.gest_prod;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indicateur {
	
	
	/*
	 *Fonction Retournant un Booleen pour savoir si la quantite en entrée d'une chaine est disponible en stock
	 *ENTREE: Deux variables comprenant le code de l'élément et sa quantite
	 *SORTIE: Booleen
	*/
	public static boolean QuantiteDisponible(Map<String, Integer> quantiteTotale, Map<String, Integer> quantiteChaineEntree) {
		boolean ok=true;
        for (String code : quantiteChaineEntree.keySet()) {
            Integer neededQuantity = quantiteChaineEntree.get(code);
            Integer availableQuantity = quantiteTotale.getOrDefault(code, 0);

            if (availableQuantity < neededQuantity) {
              
                ok=false; 
            }
            
        }
        return ok; 
    }
	
	
	/*
	 *Fonction Supprimant le stock de l'entree de la chaine au stock total
	 *Elle est appelée si QuantiteDisponible retourne Vrai
	 *ENTREE: Deux variables comprenant le code de l'élément et sa quantite
	*/
	public static void EnleverQuantite(Map<String, Integer> quantiteTotale, Map<String, Integer> quantiteChaineEntree) {
        quantiteChaineEntree.forEach((code, neededQuantity) -> {
            int availableQuantity = quantiteTotale.get(code);
            quantiteTotale.put(code, availableQuantity - neededQuantity);
        });
    }
	
	
	/*
	 *Fonction Retournant le total des achats effectués
	 *ENTREE: Chemin du fichier prix.csv
	 *SORTIE: Total acheté
	*/
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

	/*
	 *Fonction Retournant le total des ventes effectués
	 *ENTREE: Deux Map, un avec Code Element et Quantite et l'autre avec Code Element et son prix
	 *SORTIE: Total vendu
	*/
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
	
	public boolean verifierDisponibilitePersonnel(String chaineId, Map<String, Integer> personnelDisponible, Map<String, Integer> besoinPersonnel) {
	    Integer personnelNecessaire = besoinPersonnel.get(chaineId);
	    Integer personnelDispo = personnelDisponible.getOrDefault(chaineId, 0);

	    return personnelDispo >= personnelNecessaire;
	}

	public void reserverPersonnel(String chaineId, Map<String, Integer> personnelDisponible, Map<String, Integer> besoinPersonnel) {
	   
	        int personnelRestant = personnelDisponible.get(chaineId) - besoinPersonnel.get(chaineId);
	        personnelDisponible.put(chaineId, personnelRestant);
	    
	}


	

}
