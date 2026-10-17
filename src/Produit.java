public class Produit {
    // declaration des attribus
    private String nom;
    private double prix;
    private int quantite;
    // le constructeur
    public Produit(String nom, double prix,int quantite){
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }
    // les getteurs et les setteurs

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // la methode tostring

    @Override
    public String toString() {
        return "Nom Produit : "+getNom()+"\nPrix Produit : "+getPrix()+"\nQuantite produit : "+getQuantite();
    }
    public void retirerStock(int quantiteDemandee) {
        if (quantite < quantiteDemandee) {
            throw new StockInsuffisantException("la quantite est insuffisante");
        } else {
            setQuantite(quantite - quantiteDemandee);
        }
    }
}
