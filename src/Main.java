import java.util.*;

public class Main {
    ArrayList<Produit> produits = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void main() {
        int choix = 0;
        do {
            afficherMenu();
            System.out.print("Votre Choix : ");
            choix = lireUNnombreEntier();

            switch (choix) {
                case 1:
                    AjouterUnProduit();
                    break;
                case 2:
                    afficherLesProduits();
                    break;
                case 3:
                    rechercherProduit();
                    break;
                case 4:
                    supprimerUnProduit();
                    break;
                case 5:
                    vendreUnProduit();
                    break;
                case 6:
                    modifierUnProduit();
                    break;
                case 7:
                    afficherTriesParPrix();
                    break;
                case 8:
                    valeurTotalDuStock();
                    break;
                case 9:
                    produitEnStockFaible();
                    break;
                case 0:
                    System.out.println("bye bye !!!!");
                    break;
                default:
                    System.out.println("Choix invalide !!");
            }


        } while (choix != 0);

    }

    //  Affichage du menu
    public void afficherMenu() {
        System.out.println("************************* Menu du Boutique *************************");
        System.out.println("Choisissez l'operation que vous souhaitez affectuer ");
        System.out.println("1.Ajouter un Produit ");
        System.out.println("2.Afficher les Produits ");
        System.out.println("3.Rechercher Un Produit ");
        System.out.println("4.Supprimer Un Produit ");
        System.out.println("5.Vendre Un Produit ");
        System.out.println("6.Modifier un produit");
        System.out.println("7.Afficher Les Produits Tries par prix");
        System.out.println("8. Valeur Total Du Stock");
        System.out.println("9. Produit en Stock Faible");
        System.out.println("0.Quiter ");
        System.out.println("************************* Fin Menu *************************\n");
    }

    // 1. Ajout Produit
    public void AjouterUnProduit() {
        String nom;
        double prix;
        int quantite = 0;
        int contunier = 0;
        do {
            System.out.println("****************** Ajout D'un Produit ******************");
            System.out.print("Nom Produit : ");
            nom = sc.nextLine();
            if (rechercherParNom(nom) != null){
                System.out.println("ce produit existe deja");
                return;
            }
            System.out.print("Prix Produit : ");
            prix = lireunNombreReel();
            if (prix <= 0){
                return ;
            }
            System.out.print("Quantite Produit : ");
            quantite = lireUNnombreEntier();
            if (quantite < 0){
                return ;
            }
            Produit produit = new Produit(nom, prix, quantite);
            produits.add(produit);
            System.out.println("******************* Fin D'Ajout *******************");
            System.out.println("Cliquer 1 pour Contunier a Ajouter Ou 0 pour Quiter");
            contunier = lireUNnombreEntier();
        } while (contunier == 1);

    }

    // 2- Affichage des produits
    public void afficherLesProduits() {
        System.out.println("****************** Affichage des Produits ******************");
        if (produits.isEmpty()) {
            System.out.println("il ya Aucun produit dans la boutiaue !!!");
        } else {
            for (Produit produit : produits) {
                System.out.println(produit + "\n");
            }
        }
        System.out.println("******************* Fin D'Affichage *******************");

    }

    //  rechercher un produit par nom
    public Produit rechercherParNom(String nom) {
        for (Produit produit : produits) {
            if (produit.getNom().equalsIgnoreCase(nom)) {
                return produit;
            }
        }
        return null;
    }

    // 3- rechercher Produit
    public void rechercherProduit() {
        System.out.println("******************* Recherche Produit *******************");
        String nom;
        System.out.print("Rechercher un produit par son Nom : ");
        nom = sc.nextLine();
        Produit produit = rechercherParNom(nom);
        if (produit == null) {
            System.out.println(nom + " N'existe pas dans la Boutique ");
        } else {
            System.out.println(produit);
        }
        System.out.println("******************* Fin De Recherche *******************");
    }

    // 4- suppression d'un produit
    public void supprimerUnProduit() {
        System.out.println("****************** Suppression D'un Produit ******************");
        String nom;
        System.out.print("Donnez le nom du produit a supprimer : ");
        nom = sc.nextLine();
        Produit produit = rechercherParNom(nom);
        if (produit == null) {
            System.out.println(nom + " N'existe pas dans la Boutique donc on ne peut pas le supprimer");
        } else {
            produits.remove(produit);
            System.out.println(nom + " a ete supprimer avec succes !!");
            System.out.println("****************** Fin de la Suppression ******************");
        }

    }

    // 5- vente d'un produit
    public void vendreUnProduit() {
        System.out.print("Donnez le nom du produit : ");
        String nom = sc.nextLine();

        Produit produit = rechercherParNom(nom);
        if (produit == null) {
            System.out.println("Produit introuvable !!!!");
            return;
        }

        System.out.print("Donnez la quantite que vous voulez : ");
        int quantiteDemander = lireUNnombreEntier();
        if (quantiteDemander <= 0) {
            System.out.println("la quantite doit etre positive ");
            return;
        }

        try {
            produit.retirerStock(quantiteDemander);
          double  sommeTotal = produit.getPrix() * quantiteDemander;
            System.out.println("Somme Total a payer : " + sommeTotal + " FCFA");
        } catch (StockInsuffisantException e) {
            System.out.println(e.getMessage());
        }
    }
    // 6- modifiaction d'un prosuit
    public void modifierUnProduit(){
        String nom;
        String nouveauNom;
        double nouveauPrix;
        int nouveauQuantite ;
        int contunier = 0;
        System.out.print("Nom du produit a modifier : ");
        nom = sc.nextLine();
        Produit produit = rechercherParNom(nom);

        if (produit != null){
            System.out.println("******************* Modification *******************");
           do {
               System.out.println("Donnez le nouveau Nom");
               nouveauNom = sc.nextLine();
               Produit existant = rechercherParNom(nouveauNom);
               if (existant != null && existant != produit) {
                   System.out.println("ce produit existe deja");
                   return;
               }
               System.out.println("Donnez le nouveau Prix");
               nouveauPrix = lireunNombreReel();
               if (nouveauPrix <= 0){
                   System.out.println("le prix doit etre positive");
                   return;
               }
               System.out.println("Donnez le nouveau Qunatite");
               nouveauQuantite = lireUNnombreEntier();
               if (nouveauQuantite < 0){
                   System.out.println("la quantite doit pas etre negative ");
                   return;
               }
               produit.setNom(nouveauNom);
               produit.setPrix(nouveauPrix);
               produit.setQuantite(nouveauQuantite);
               System.out.println("******************* Fin De Modification *******************");
               System.out.println("Cliquer 1 pour Contunier a Ajouter Ou 0 pour Quiter");
               contunier = lireUNnombreEntier();
           }while (contunier ==1);

        }
        else {
            System.out.println("Produit intruvable !!!");
        }

    }

    // methode pour lire  un nombre entier
    public int lireUNnombreEntier() {
        while (true) {
            try {
                int nb = Integer.parseInt(sc.nextLine());
                return nb;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Entrez un nombre entier");
            }
        }
    }

    // methode pour lire un nombre double
    public double lireunNombreReel() {
        while (true) {
            try {
                double nb = Double.parseDouble(sc.nextLine());
                return nb;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Entrez un nombre");
            }
        }
    }
    public void afficherTriesParPrix(){
        produits.sort((p1,p2) -> Double.compare(p1.getPrix(), p2.getPrix()));
        afficherLesProduits();
    }
    public void valeurTotalDuStock(){
        double total = produits.stream()
                .mapToDouble(p ->p.getPrix()*p.getQuantite())
                .sum();
        System.out.println("la somme total de l'stock  : "+ total +" FCFA");
    }
    public void produitEnStockFaible(){
        int seuil;
        System.out.print("Donnez le seuil a verfier : ");
        seuil = lireUNnombreEntier();
        boolean trouve= false;
       for (Produit p : produits){
           if (p.getQuantite() < seuil){
               trouve = true;
               System.out.println(p+"\n");
           }
       }
       if (!trouve){
           System.out.println("Aucune produit ne sous se seuil demander ");
       }
    }


}

