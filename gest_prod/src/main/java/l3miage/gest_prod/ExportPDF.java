package l3miage.gest_prod;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportPDF {
	public static void exportDataToPDF() throws DocumentException, IOException {
	    Document document = new Document();
	    PdfWriter.getInstance(document, new FileOutputStream("SimulationProduction.pdf"));
	    document.open();
	    
	    
	    document.add(new Paragraph("Stock"));
	    document.add(new Paragraph("Code - Nom - Quantité"));
	    List<Element> listElements;
	    List<ChaineProduction> chaines= new ArrayList<>();
	    listElements = GestionCSV.readElementCSV("src/main/java/l3miage/gest_prod/files/elements.csv");
	    Map<String, Element> mapElements = new HashMap<>();
	    
        for (Element element : listElements) {
            mapElements.put(element.getCode(), element);
            
        }
        List<AchatVente> achatVentes = GestionCSV.readPrixCSV("src/main/java/l3miage/gest_prod/files/prix.csv", mapElements);
        AchatVente achatVente;
        Element element;
        int totalStock;
        for(int i=0;i<listElements.size();i++) {
        	achatVente=achatVentes.get(i);
        	element=listElements.get(i);
        	totalStock=Integer.parseInt(element.getQuantity())+Integer.parseInt(achatVente.getQuantite());
        	document.add(new Paragraph(element.getCode()+" - "+element.getName()+" - "+ totalStock)); // Remplacez par les données réelles à exporter
        }
        
        document.add(new Paragraph("------------------------------------------"));
        
        document.add(new Paragraph("Achat"));
        document.add(new Paragraph("Nom - Quantité - Prix"));
        int totalAchatElement;
        int totalAchat=0;
	    for(AchatVente achatvente : achatVentes) {
	    	if(!achatvente.getAchat().equals("NA")) {
		    	totalAchatElement = Integer.parseInt(achatvente.getQuantite())*Integer.parseInt(achatvente.getAchat());
		    	document.add(new Paragraph(achatvente.getElement()+" "+achatvente.getQuantite()+" "+totalAchatElement ));
		    	totalAchat=totalAchat+totalAchatElement;
	    	} }
	    document.add(new Paragraph("Coût Total : " + totalAchat + " euros"));
	    
	    document.add(new Paragraph("------------------------------------------"));
	    
	    document.add(new Paragraph("Personnel"));
	    document.add(new Paragraph("Code - Nom - Prénom - Chaine(s)"));
	    List<ChaineProduction> chaineTotale= GestionCSV.readChaineActiveCSV("src/main/java/l3miage/gest_prod/files/chaines.csv", mapElements);
	    chaines = GestionCSV.readChaineActiveCSV("src/main/java/l3miage/gest_prod/files/chaines.csv", mapElements);
	    Map<String, ChaineProduction> chaineProductionMap= new HashMap<>();	
        for (ChaineProduction chaine : chaines) {
      	  chaineProductionMap.put(chaine.getCode(), chaine);
	            
	          
	        }
        
        
        List<Personnel> personnel = GestionCSV.readPersonnelCSV("src/main/java/l3miage/gest_prod/files/personnel.csv",chaineProductionMap,chaineTotale);
        Map<String,Integer> quantiteTotale = new HashMap<>();
        Map<String,Integer> quantiteChaineEntree = new HashMap<>();
        List<AchatVente> listAchatVentes = GestionCSV.readPrixCSV("src/main/java/l3miage/gest_prod/files/prix.csv", mapElements);
        List<ChaineProduction> chaineReussies = new ArrayList<ChaineProduction>();
        
        for(Personnel personne:personnel) {
        	document.add(new Paragraph(personne.getCode()+" - "+personne.getNom()+" - "+personne.getPrenom()+" - "+personne.getChaine()));
        }
        int totalChaine = 0;
        int chaineReussie =0;
        for(int i=0; i<listAchatVentes.size();i++) {
        	achatVente=listAchatVentes.get(i);
        	element=listElements.get(i);
        	
        	quantiteTotale.put(achatVente.getCode(), Integer.parseInt(achatVente.getQuantite())+Integer.parseInt(element.getQuantity()));
      
        }
        for(ChaineProduction chaine: chaines) {
        	for(int i=0; i< Integer.parseInt(chaine.getActivationLevel());i++) {
        		totalChaine= totalChaine+1;
	        	quantiteChaineEntree=GestionCSV.parseElementQuantiteChaine(chaine.getEntreeString(),mapElements);
	        	
	        	if(Indicateur.quantiteDisponible(quantiteTotale, quantiteChaineEntree)){
	        		if(Indicateur.verifPersonnel(personnel, chaine)) {
		        		chaineReussie=chaineReussie+1;
		        		chaineReussies.add(chaine);
		        		Indicateur.EnleverQuantite(quantiteTotale,quantiteChaineEntree);
		        		Indicateur.enleverPersonnel(personnel, chaine);
	        		}
	        	}
        	}
        }
    
        
        double valeurAchat =Indicateur.totalAchat("src/main/java/l3miage/gest_prod/files/prix.csv");
	    document.add(new Paragraph("------------------------------------------"));
	    if(totalChaine>0) {
	    	double pourcentageChaineReussie= chaineReussie*100/totalChaine;
	        document.add(new Paragraph("Chaine(s) Réussie(s) " + pourcentageChaineReussie + "%"));
	    }
	    else {
	    	 document.add(new Paragraph("Aucune Chaine"));
	    }
       
        double totalValeur = 0;
        Map<String,Integer> quantiteChaineSortie = new HashMap<>();
        Map<String,Integer> codePrixVente=  new HashMap<>();
        codePrixVente=GestionCSV.codePrixVente("src/main/java/l3miage/gest_prod/files/prix.csv");
        double valeurChaine;
        document.add(new Paragraph("Code - Nom - Sortie - Valeur - Niveau Activation"));
        ChaineProduction verifChaine;
        
       
        int cptNivAct=1;
        for(int i=0; i<chaineReussies.size();i++) {
        	
        	verifChaine=chaineReussies.get(i);
        	quantiteChaineSortie=GestionCSV.parseElementQuantiteChaine(verifChaine.getSortieString(),mapElements);
        	valeurChaine=Indicateur.totalVenteProduction(quantiteChaineSortie,codePrixVente);
        	totalValeur=totalValeur+valeurChaine;
        	
        	if(i+1<chaineReussies.size()) {
	        	if(verifChaine.getCode().equals(chaineReussies.get(i+1).getCode())){
	        		cptNivAct=cptNivAct+1;
	        	}
	        	else {
	        		document.add(new Paragraph(verifChaine.getCode()+" - "+verifChaine.getName()+" - "+verifChaine.getOutputElements()+" - "+valeurChaine+ " - "+cptNivAct));
	        		cptNivAct=1;
	        	}
        	
        	}
        	else {
        		document.add(new Paragraph(verifChaine.getCode()+" - "+verifChaine.getName()+" - "+verifChaine.getOutputElements()+" - "+valeurChaine+ " - "+cptNivAct));
        	}
        	
        }
        document.add(new Paragraph("Valeur Totale des Chaînes"));
        document.add(new Paragraph(""+totalValeur));
        totalValeur=totalValeur-valeurAchat;
        
        document.add(new Paragraph("Valeur Totale de la Production"));
        document.add(new Paragraph(""+totalValeur));
	    
	    document.close();
	}
}
