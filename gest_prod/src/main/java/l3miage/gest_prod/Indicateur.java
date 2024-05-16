package l3miage.gest_prod;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indicateur {
	

	/**
	 * Fonction Retournant un Booleen pour savoir si la quantite en entrée d'une chaine est disponible en stock
	 * @param quantiteTotale
	 * @param quantiteChaineEntree
	 * @return boolean ok
	 */
	public static boolean quantiteDisponible(Map<String, Integer> quantiteTotale, Map<String, Integer> quantiteChaineEntree) {
		boolean ok=false;
        for (String code : quantiteChaineEntree.keySet()) {
            Integer neededQuantity = quantiteChaineEntree.get(code);
            Integer availableQuantity = quantiteTotale.getOrDefault(code, 0);
           
            if (availableQuantity > neededQuantity) {                     
            	  ok=true;           	              	  
               }                
            }                            
        return ok; 
    }
	

	/**
	 * Fonction Retournant True si il y a assez de personnel pour réaliser la chaine
	 * @param personnel
	 * @param chaine
	 * @return boolean ok
	 */
	public static boolean verifPersonnel(List<Personnel> personnel,ChaineProduction chaine) {
		boolean ok=false;
		int totalPersonne=Integer.parseInt(chaine.getPersonnel());
		int cptPersonneDispo=0;
		for(Personnel personne: personnel) {
			
			for(int i=0;i< personne.getChaines().size();i++) {
			
				if(personne.getChaines().get(i).getCode().equals(chaine.getCode())&& personne.isEstDispo()) {
					cptPersonneDispo=cptPersonneDispo+1;
				}
			}
		}
		if (cptPersonneDispo >= totalPersonne) {
			ok=true;
		}
		return ok;
		
	}
	
	
	
	/**
	 * Fonction pour set estDispo False des personnels utilisés par la chaine afin d'empecher leur réutilisation
	 * @param personnel
	 * @param chaine
	 */
	public static void enleverPersonnel(List<Personnel> personnel, ChaineProduction chaine) {
	    int totalPersonne = Integer.parseInt(chaine.getPersonnel());
	    int cpt = 0;

	   
	    for (Personnel p : personnel) {
	        if (p.isEstDispo()) { 
	            p.setEstDispo(false);  
	            cpt++;  
	            if (cpt >= totalPersonne) {
	                break;  
	            }
	        }
	    }
	}
	
	
	/**
	 * Fonction Supprimant le stock de l'entree de la chaine au stock total
	 * @param quantiteTotale
	 * @param quantiteChaineEntree
	 */
	public static void EnleverQuantite(Map<String, Integer> quantiteTotale, Map<String, Integer> quantiteChaineEntree) {
        quantiteChaineEntree.forEach((code, neededQuantity) -> {
            int availableQuantity = quantiteTotale.get(code);
            quantiteTotale.put(code, availableQuantity - neededQuantity);
        });
    }
	
	
	/**
	 * Fonction Retournant le total des achats effectués
	 * @param cheminFichier
	 * @return double totalAchat 
	 * @throws FileNotFoundException
	 * @throws IOException
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

	
	/**
	 * Fonction Retournant le total des ventes effectués
	 * @param quantiteChaineSortie
	 * @param codePrixVente
	 * @return  double totalValue
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
	
	


	

}
