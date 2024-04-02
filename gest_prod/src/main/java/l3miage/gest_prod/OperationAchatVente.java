package l3miage.gest_prod;

public class OperationAchatVente {
    private String codeElement;
    private int prixAchat;
    private int prixVente;
    private int quantite;

    public OperationAchatVente(String p_codeElement, int p_prixAchat, int p_prixVente, int p_quantite) {
        this.codeElement = p_codeElement;
        this.prixAchat = p_prixAchat;
        this.prixVente = p_prixVente;
        this.quantite = p_quantite;
    }

    public String getCodeElement() {
        return this.codeElement;
    }
    
    public int getPrixAchat() {
        return this.prixAchat;
    }

    public int getPrixVente() {
        return this.prixVente;
    }

    public int getQuantite() {
        return this.quantite;
    }
}