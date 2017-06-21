package ileinterdite;

import Cartes.Montee_Des_Eaux;
import Cartes.Carte_Tresor;
import Cartes.SacDeSable;
import Cartes.Helicoptere;
import Cartes.Carte_Inondation;
import Cartes.Carte_Tresor;
import Cartes.Carte_Tresor_Abs;
import Cartes.Helicoptere;
import Cartes.SacDeSable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class JeuIleInterdite {

    /**
     * @param joueurs the joueurs to set
     */
   
    
    
    private Grille grille;
    private Scanner scanner;
    private ArrayList<Carte_Tresor_Abs> cartes_trésor_defausse;
    private ArrayList<Aventurier> roles;
    private ArrayList<Tresor> trésors;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Carte_Tresor_Abs> cartes_trésor_pioche;
    private ArrayList<Carte_Inondation> cartes_innondation_defausse;
    private ArrayList<Carte_Inondation> cartes_innondation_pioche;
    private ArrayList<Carte_Inondation> cartes_innodation_cimetiere;
    private ArrayList<Carte_Tresor_Abs> cartestrésors;
    private ArrayList<Carte_Inondation> cartesInondation;
    private Joueur joueur_courant; 
    private Controleur observateur ;
    private int niveau_eau;
    private boolean passerTour ;
    
    public JeuIleInterdite(){
        // init des tresors 
        ArrayList<Tresor> trésors = new ArrayList<>();
        
        Tresor tresor1 = new Tresor("La Pierre Sacrée");
        Tresor tresor2 = new Tresor("La Statue Du Zéphyr");
        Tresor tresor3 = new Tresor("Le Cristal Ardent");
        Tresor tresor4 = new Tresor("Le Calice De L’onde");
        trésors.add(tresor1);
        trésors.add(tresor2);
        trésors.add(tresor3);
        trésors.add(tresor4);
        
        
        
        cartesInondation = new ArrayList<>();
        cartestrésors = new ArrayList<>();
        scanner = new Scanner(System.in);
        grille = new Grille(trésors);
        roles = new ArrayList<>();
        
        cartes_innondation_defausse = new ArrayList<>();
        cartes_innondation_pioche = new ArrayList<>();
        cartes_innodation_cimetiere = new ArrayList<>();
        cartes_trésor_defausse = new ArrayList<>();
        cartes_trésor_pioche = new ArrayList<>();
        joueurs = new ArrayList<>();
        
        
        
        // init des cartes trésors 
        
        for (int i = 0 ; i < 27 ; i++){
            if (i <=4){
                cartestrésors.add(new Carte_Tresor(tresor1));
            }
            if (4 < i && i<=9 ){
                cartestrésors.add(new Carte_Tresor(tresor2));
            }
             if (9 < i && i<=14 ){
                cartestrésors.add(new Carte_Tresor(tresor3));
            }
              if (14 < i && i<=19 ){
                cartestrésors.add(new Carte_Tresor(tresor4));
            }
             if (19 < i && i<=22){
                 cartestrésors.add(new Montee_Des_Eaux());
             }
             if (22< i && i<=25){
                 cartestrésors.add(new Helicoptere());
             }
             if (25 < i && i<=27){
                 cartestrésors.add(new SacDeSable());
             }
        }
        
             for(int i = 0; i < grille.getTuiles().length; i++){
              if(grille.getTuile(i).getNom() != null){
                      cartesInondation.add(new Carte_Inondation(grille.getTuile(i)));
              }}
             
             melangeCartes(cartestrésors);
             melangeCartes(cartesInondation);
             
    } 
    
     public void setJoueurs(ArrayList<Joueur> joueurs_ihm) {
        this.joueurs = joueurs_ihm;
        for (Joueur joueur : joueurs){
            System.out.println(joueur.getPion()+" pion du joueur dans JeuIleInterdite");
            System.out.println(grille.getTuile(grille.getNumTuilePion(joueur.getPion())));
            joueur.setPostition(grille.getTuile(grille.getNumTuilePion(joueur.getPion())));
        }
    }
     
     
    public void initCartesJoueurs(){
        
        for(int i = 0; i<joueurs.size(); i++){
            for(int j = 0; j<2; i++){              
                while(cartestrésors.get(j).getType()=="Montee_Des_Eaux"){
                    melangeCartes(cartestrésors); 
                    }
                joueurs.get(i).addCarteMain(cartestrésors.get(i));
                cartestrésors.remove(i);
            
            }
        }
    }
    
    
   public boolean verifDonnerCarte(Joueur joueurDonneur, Joueur joueurReceveur,Carte_Tresor_Abs carte){
       return ((joueurReceveur.getMain().size()>=5) && (joueurDonneur.getPosition() == joueurReceveur.getPosition()) && (carte.getType() != "Helicoptere") && (carte.getType() != "SacDeSable"));
   }
    
    public void donnerCarte(Joueur joueurDonneur, Joueur joueurReceveur,Carte_Tresor_Abs carte) {
             
            joueurReceveur.addCarteMain(carte);
            joueurDonneur.supprimerCarte(carte);
   
}
     
    public void DemarrerJeu (){
        boolean b = jeuGagné(grille) ;
        boolean c = jeuPerdu(grille);
        
        //InitJoueur();
        while (!c && !b){
        for (Joueur joueur : getJoueurs()) {
            setJoueurCourant(joueur);
            TourJoueur(joueur_courant);
        }}
        
        if (c){
            Message2 m = new Message2();
            m.setType(TypesMessage.PERDU);
            observateur.traiterMessage(m);
        } 
        
        if (b){
             Message2 m = new Message2();
            m.setType(TypesMessage.GAGNER);
            observateur.traiterMessage(m);
        }
    }

           
    public void piocheCarteTresor(Joueur joueur){
        if (joueur.getMain().size()<5){
            joueur.addCarteMain(cartestrésors.get(0));
            cartestrésors.remove(0);
        }
    }
    
    public Tuile piocheCarteInondation(){
        
        Carte_Inondation carte = cartesInondation.get(0);  // Regarde Luca
        Tuile tuile = carte.getTuileassociée();
        if (tuile.getEtat() == Etat.ASSECHEE){
            tuile.SetEtat(Etat.INONDEE);
            cartes_innondation_defausse.add(carte);
        }
        else if (tuile.getEtat() == Etat.INONDEE){
            tuile.SetEtat(Etat.DISPARUE);
            cartes_innodation_cimetiere.add(carte);
        }
        
        cartesInondation.remove(0);
        return tuile;
        
    }
    
    public void UtiliserAction() {
            joueur_courant.setNbPointAction(joueur_courant.getNbPointAction()-1);
        }
    
    public void PasserTour(){
        passerTour = true ;
    }
    public void TourJoueur(Joueur joueur) {
        joueur_courant = joueur ;
        joueur_courant.setNbPointAction(3);
        passerTour= false ;
        Message2 msg = new Message2();
        msg.setType(TypesMessage.DEBUT_TOUR);
        msg.setJoueur(joueur);
        observateur.traiterMessage(msg);
        
        
        //String couleur = joueur.getPion().name();
       // System.out.println("Tour de "+joueur.getNom()+"("+joueur.getAventurier().getRole()+")"+"ayant le pion "+couleur);
        while (joueur_courant.getNbPointAction() > 0 || passerTour != true){
            
                   
                    
        } }
           // System.out.println("Actions possibles par ");
            //System.out.println("0- Passer tour");
            //System.out.println("1- Deplacement ");
            //    System.out.println("2- Afficher la grille");
           // if (joueur.getAventurier().getTuilesAssechables(joueur).size()>0 ){
              //  System.out.println("3- Assecher "); 
            //}
            //int action = scanner.nextInt();
            //scanner.nextLine();
               // if ( action == 0 ) {
                  //  sortie =true ;
                //}  
                //else if (action == 1){
                    //Deplacement(joueur);
                   // pointsActions = pointsActions - 1 ;
//                }
//                else if (action == 2){
//                     for (Tuile tuile : getGrille().getTuiles()){
//                    System.out.println(tuile.getNumero()+" "+tuile.getNom()+" "+tuile.getEtat());
//                    }
//                }
//                else if(action == 3 && grille.getTuilesAssechables(joueur).size() > 0 ){
//                    AssecherTuile(joueur);
//                    pointsActions = pointsActions - 1 ;
//                }
//                else {
//                    System.out.println("Action Impossible .");
//                }
//        }
//        System.out.println("Fin du tour de "+joueur.getNom());
//    } 
    /*
    public void Deplacement(Joueur joueur) {
        ArrayList<Tuile> tuiles_deplacement = joueur.getAventurier().getTuilesDeplacement(joueur);
        boolean controle_boucle =true;
        
        System.out.println("Voici les cases sur lesquelles vous pouvez vous déplacer:");
        for(Tuile tuile_deplacement : tuiles_deplacement){
            System.out.println(tuile_deplacement.getNumero()+" "+tuile_deplacement.getNom()+" "+tuile_deplacement.getEtat());
        }
        
        while (controle_boucle){
            
            System.out.println("Rentrez le numéro de la tuile sur laquelle vous voulez vous déplacer :");
            int tuile_choisie = scanner.nextInt(); //l'utilisateur saise le numéro de la tuile

            for (int i =0;i<tuiles_deplacement.size();i++){
                if (tuile_choisie==tuiles_deplacement.get(i).getNumero()){
                    joueur.setPostition(getGrille().getTuile(tuile_choisie)); // on modifie la position du joueur
                    
                    controle_boucle = false;
                    System.out.println("Vous vous êtes bien déplacé sur la tuile :"+tuile_choisie+" "+grille.getTuile(tuile_choisie).getNom()+" "+grille.getTuile(tuile_choisie).getEtat());    
                }else if(i==tuiles_deplacement.size()-1 && tuile_choisie != tuiles_deplacement.get(i).getNumero() && controle_boucle){
                    System.out.println("Vous ne pouvez pas vous déplacer sur cette tuile, veuillez rentrer un nouveau numero :");    
                }
            } //fin for
        } //fin while
    }
    *//*
    public void DeplacementPlongeur(Joueur joueur){
       
        boolean controle_boucle = true;
        
        System.out.println("Voici les cases sur lesquelles vous pouvez vous déplacer:");
        System.out.println(getGrille().getTuilesDeplacementPlongeur(joueur).size()+" hswlkdfhlskdjfhlkjsdwh");
        for(Tuile tuiles : getGrille().getTuilesDeplacementPlongeur(joueur)){
            System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());
        }
        
        while(controle_boucle){
            System.out.println("Rentrez le numéro de la tuile sur laquelle vous voulez vous déplacer :");
            int tuile_choisie = scanner.nextInt(); //l'utilisateur saise le numéro de la tuile
            
            while (getGrille().getTuile(tuile_choisie).getEtat() == Etat.INONDEE || getGrille().getTuile(tuile_choisie).getEtat() == Etat.DISPARUE){
                for (int i =0;i<getGrille().getTuilesDeplacementPlongeur(joueur).size();i++){
                    if (tuile_choisie==getGrille().getTuilesDeplacementPlongeur(joueur).get(i).getNumero()){
                         joueur.setPostition(getGrille().getTuile(tuile_choisie)); //on modifie la position du joueur
                    }
                 }
                System.out.println("Voici les cases sur lesquelles vous pouvez vous déplacer :");
                for(Tuile tuile_deplacement : getGrille().getTuilesDeplacementPlongeur(joueur)){
                    System.out.println(tuile_deplacement.getNumero()+" "+tuile_deplacement.getNom()+" "+tuile_deplacement.getEtat());
                }
                System.out.println("Rentrez le numéro de la tuile sur laquelle vous voulez vous déplacer :");
                tuile_choisie = scanner.nextInt();
            }
            
            if (getGrille().getTuile(tuile_choisie).getEtat()==Etat.ASSECHEE){
                for (int i =0;i<getGrille().getTuilesDeplacementPlongeur(joueur).size();i++){
                    if (tuile_choisie==getGrille().getTuilesDeplacementPlongeur(joueur).get(i).getNumero()){
                         joueur.setPostition(getGrille().getTuile(tuile_choisie)); //on modifie la position du joueur
                    }
                }//fin for
                controle_boucle=false;
                System.out.println("Vous vous êtes bien déplacé sur la tuile :"+getGrille().getTuile(tuile_choisie).getNumero()+" "+getGrille().getTuile(tuile_choisie).getNom()+" "+getGrille().getTuile(tuile_choisie).getEtat());
            }//fin if
        }//fin while     
    }
    */
    
    public void DeplacementNavigateur(Joueur joueur){
        
    }

    public void AssecherTuile(Joueur joueur) {
        ArrayList<Tuile> tuiles_assechables = joueur.getAventurier().getTuilesAssechables(joueur);
        boolean controle_boucle = true;
        
        System.out.println("Voici les tuiles que vous pouvez assécher :");
        for (Tuile tuiles : tuiles_assechables){       
            System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
        }
        while (controle_boucle){
            
            System.out.println("rentrez le numero de la tuile que vous voulez assécher");
            int tuile_choisie = scanner.nextInt();
            

            for (int i =0;i<tuiles_assechables.size();i++){
                if (tuile_choisie==tuiles_assechables.get(i).getNumero()){
                    grille.setEtat(tuile_choisie, Etat.ASSECHEE);
                    controle_boucle = false;
                    System.out.println("la case numero :"+tuile_choisie+ " a bien été asséechée"+" "+grille.getTuile(tuile_choisie).getEtat());    
                }else if(i==tuiles_assechables.size()-1 && tuile_choisie != tuiles_assechables.get(i).getNumero() && controle_boucle){
                    System.out.println("Vous ne pouvez pas assécher cette tuile!");  
                    for (Tuile tuiles : tuiles_assechables){       
                        System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
                    }
                }
            } //fin for
        } //fin while 
    }
    
    public void AssecherTuileIngenieur(Joueur joueur){
        //controle boucle ==0 -> 1ere tuile à assécher
        //controle boucle ==1 -> 2eme tuile à assécher
        //controle boucle ==2 -> sortie de boucle
        ArrayList<Tuile> tuiles_assechables1 = getGrille().getTuilesAssechables(joueur);
        ArrayList<Tuile> tuiles_assechables2;
        boolean possibilite_deuxieme_assechage = false;
        boolean deuxieme_assechage=false;
        boolean premier_assechage =true;
        
        System.out.println("Voici les tuiles que vous pouvez assécher :");
        for (Tuile tuiles : tuiles_assechables1){       
            System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
        }
        while (premier_assechage){
                System.out.println("rentrez le numero de la tuile que vous voulez assécher");
                int case_choisie = scanner.nextInt();
                

                for (int i =0;i<tuiles_assechables1.size();i++){
                    if (case_choisie==tuiles_assechables1.get(i).getNumero()){
                        grille.setEtat(case_choisie, Etat.ASSECHEE);
                        premier_assechage=false;  
                        System.out.println("la case numero :"+case_choisie+ " a bien été asséechée"+" "+grille.getTuile(case_choisie).getEtat());    

                    }else if(i==tuiles_assechables1.size()-1 && case_choisie != tuiles_assechables1.get(i).getNumero() && premier_assechage==true){
                        System.out.println("Vous ne pouvez pas assécher cette tuile!");  
                        for (Tuile tuiles : tuiles_assechables1){       
                            System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
                        }    
                    }//fin if
                }//fin for    
        }
        for (Tuile tuile : getGrille().getTuilesAssechables(joueur)){ // on vérifie si il y a d'autres cases à assécher
            if (tuile.getEtat()==Etat.INONDEE){
                possibilite_deuxieme_assechage=true;
            }
        }
        if (possibilite_deuxieme_assechage){
            System.out.println("Voulez-vous assécher une nouvelle tuile ? true/false");
            boolean bool = scanner.nextBoolean();
            deuxieme_assechage = bool;
            if (deuxieme_assechage){
                tuiles_assechables2=getGrille().getTuilesAssechables(joueur);
                for (Tuile tuile : tuiles_assechables2){
                    System.out.println(tuile.getNumero()+" "+tuile.getNom()+" "+tuile.getEtat());
                }
            
                while(deuxieme_assechage){
                    System.out.println("rentrez le numero de la tuile que vous voulez assécher");
                    int case_choisie = scanner.nextInt();


                    for (int i =0;i<tuiles_assechables2.size();i++){
                        if (case_choisie==tuiles_assechables2.get(i).getNumero()){
                            grille.setEtat(case_choisie, Etat.ASSECHEE);
                            deuxieme_assechage=false;  
                            System.out.println("la case numero :"+case_choisie+ " a bien été asséechée"+" "+grille.getTuile(case_choisie).getEtat());    

                        }else if(i==tuiles_assechables2.size()-1 && case_choisie != tuiles_assechables2.get(i).getNumero() && deuxieme_assechage==true){
                            System.out.println("Vous ne pouvez pas assécher cette tuile!");  
                            for (Tuile tuiles : tuiles_assechables2){       
                                System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
                            } //fin for   
                        }//fin
                    }//fin for    
                }//fin while
            }//fin if
        }//fin if 
    }//fin if

    public Grille getGrille() {
        return grille;
    }
    
    public ArrayList melangeCartes(ArrayList cartes){
        Collections.shuffle(cartes);
        return cartes;
        }
     
    /**
     * @return the joueurs
     */
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    
    
    
    public ArrayList<Tresor> getTrésors() {
        return trésors;
    }

    
    public Joueur getJoueurCourant(){
        return joueur_courant; 
    }
    
    public void setJoueurCourant(Joueur joueur) {
        this.joueur_courant=joueur;
    }
    public boolean jeuGagné(Grille grille) {
        
        Tuile heliport = null;
        for (int i = 0; i<grille.getTuiles().length;i++){
            
            if ((grille.getTuile(i).getNom())==("Heliport")) {
                heliport = grille.getTuile(i);
            }
        }
        int nbTresorsRecuperés = 0 ;
        for (Tresor tresor : trésors){
            
            if (tresor.estRecupere()){
                    nbTresorsRecuperés=nbTresorsRecuperés+1;
                    }
        }
                if (heliport.getJoueurspresents().size()==joueurs.size() && nbTresorsRecuperés == 4 ) {
                    return true;}
                else {
                    return false;}
        
    }
    public boolean jeuPerdu(Grille grille) {
        
        Tuile heliport = null;
        for (int i = 0; i<grille.getTuiles().length;i++){
            
            if ((grille.getTuile(i).getNom())==("Heliport")) {
                heliport = grille.getTuile(i);
            }
        }
       if(heliport.getEtat().equals(Etat.DISPARUE)) {
           return true;
       } else {
           return false;
       }
    }

    /**
     * @param observateur the observateur to set
     */
    public void setObservateur(Controleur observateur) {
        this.observateur = observateur;
    }
    public JeuIleInterdite(){
        // init des tresors 
        ArrayList<Tresor> trésors = new ArrayList<>();
        
        Tresor tresor1 = new Tresor("La Pierre Sacrée");
        Tresor tresor2 = new Tresor("La Statue Du Zéphyr");
        Tresor tresor3 = new Tresor("Le Cristal Ardent");
        Tresor tresor4 = new Tresor("Le Calice De L’onde");
        trésors.add(tresor1);
        trésors.add(tresor2);
        trésors.add(tresor3);
        trésors.add(tresor4);
        
        
        
        cartesInondation = new ArrayList<>();
        cartestrésors = new ArrayList<>();
        scanner = new Scanner(System.in);
        grille = new Grille(trésors);
        roles = new ArrayList<>();
        
        cartes_innondation_defausse = new ArrayList<>();
        cartes_innondation_pioche = new ArrayList<>();
        cartes_innodation_cimetiere = new ArrayList<>();
        cartes_trésor_defausse = new ArrayList<>();
        cartes_trésor_pioche = new ArrayList<>();
        joueurs = new ArrayList<>();
        
        
        
        // init des cartes trésors 
        
        for (int i = 0 ; i < 27 ; i++){
            if (i <=4){
                cartestrésors.add(new Carte_Tresor(tresor1));
            }
            if (4 < i && i<=9 ){
                cartestrésors.add(new Carte_Tresor(tresor2));
            }
             if (9 < i && i<=14 ){
                cartestrésors.add(new Carte_Tresor(tresor3));
            }
              if (14 < i && i<=19 ){
                cartestrésors.add(new Carte_Tresor(tresor4));
            }
             if (19 < i && i<=22){
                 cartestrésors.add(new Montee_Des_Eaux());
             }
             if (22< i && i<=25){
                 cartestrésors.add(new Helicoptere());
             }
             if (25 < i && i<=27){
                 cartestrésors.add(new SacDeSable());
             }
        }
        
             for(int i = 0; i < grille.getTuiles().length; i++){
              if(grille.getTuile(i).getNom() != null){
                      cartesInondation.add(new Carte_Inondation(grille.getTuile(i)));
              }}
             
             melangeCartes(cartestrésors);
             melangeCartes(cartesInondation);
             
    } 
    
     public void setJoueurs(ArrayList<Joueur> joueurs_ihm) {
        this.joueurs = joueurs_ihm;
        for (Joueur joueur : joueurs){
            System.out.println(joueur.getPion()+" pion du joueur dans JeuIleInterdite");
            System.out.println(grille.getTuile(grille.getNumTuilePion(joueur.getPion())));
            joueur.setPostition(grille.getTuile(grille.getNumTuilePion(joueur.getPion())));
        }
    }
     
     
    public void initCartesJoueurs(){
        
        for(int i = 0; i<joueurs.size(); i++){
            for(int j = 0; j<2; i++){              
                while(cartestrésors.get(j).getType()=="Montee_Des_Eaux"){
                    melangeCartes(cartestrésors); 
                    }
                joueurs.get(i).addCarteMain(cartestrésors.get(i));
                cartestrésors.remove(i);
            
            }
        }
    }
    
    
   public boolean verifDonnerCarte(Joueur joueurDonneur, Joueur joueurReceveur,Carte_Tresor_Abs carte){
       return ((joueurReceveur.getMain().size()>=5) && (joueurDonneur.getPosition() == joueurReceveur.getPosition()) && (carte.getType() != "Helicoptere") && (carte.getType() != "SacDeSable"));
   }
    
    public void donnerCarte(Joueur joueurDonneur, Joueur joueurReceveur,Carte_Tresor_Abs carte) {
             
            joueurReceveur.addCarteMain(carte);
            joueurDonneur.supprimerCarte(carte);
   
}
     
    public void Jeu (){
        boolean partiecontinue = true ;
        
        //InitJoueur();
        while (partiecontinue){
        for (Joueur joueur : getJoueurs()) {
            setJoueurCourant(joueur);
            TourJoueur(joueur);
        }}
    }

           
    public void piocheCarteTresor(Joueur joueur){
        if (joueur.getMain().size()<5){
            joueur.addCarteMain(cartestrésors.get(0));
            cartestrésors.remove(0);
        }
    }
    
    public Tuile piocheCarteInondation(){
        
        Carte_Inondation carte = cartesInondation.get(0);  // Regarde Luca
        Tuile tuile = carte.getTuileassociée();
        if (tuile.getEtat() == Etat.ASSECHEE){
            tuile.SetEtat(Etat.INONDEE);
            cartes_innondation_defausse.add(carte);
        }
        else if (tuile.getEtat() == Etat.INONDEE){
            tuile.SetEtat(Etat.DISPARUE);
            cartes_innodation_cimetiere.add(carte);
        }
        
        cartesInondation.remove(0);
        return tuile;
        
    }
    
    
    public void TourJoueur(Joueur joueur) {
        int pointsActions = 3 ;
        boolean sortie = false ;
        String couleur = joueur.getPion().name();
        System.out.println("Tour de "+joueur.getNom()+"("+joueur.getAventurier().getRole()+")"+"ayant le pion "+couleur);
        while (pointsActions > 0 && sortie == false ){
            System.out.println("Actions possibles par ");
            System.out.println("0- Passer tour");
            System.out.println("1- Deplacement ");
            System.out.println("2- Afficher la grille");
            if (joueur.getAventurier().getTuilesAssechables(joueur).size()>0 ){
                System.out.println("3- Assecher "); 
            }
            int action = scanner.nextInt();
            scanner.nextLine();
                if ( action == 0 ) {
                    sortie =true ;
                }  
                else if (action == 1){
                    //Deplacement(joueur);
                    pointsActions = pointsActions - 1 ;
                }
                else if (action == 2){
                     for (Tuile tuile : getGrille().getTuiles()){
                    System.out.println(tuile.getNumero()+" "+tuile.getNom()+" "+tuile.getEtat());
                    }
                }
                else if(action == 3 && grille.getTuilesAssechables(joueur).size() > 0 ){
                    AssecherTuile(joueur);
                    pointsActions = pointsActions - 1 ;
                }
                else {
                    System.out.println("Action Impossible .");
                }
        }
        System.out.println("Fin du tour de "+joueur.getNom());
    }
    /*
    public void Deplacement(Joueur joueur) {
        ArrayList<Tuile> tuiles_deplacement = joueur.getAventurier().getTuilesDeplacement(joueur);
        boolean controle_boucle =true;
        
        System.out.println("Voici les cases sur lesquelles vous pouvez vous déplacer:");
        for(Tuile tuile_deplacement : tuiles_deplacement){
            System.out.println(tuile_deplacement.getNumero()+" "+tuile_deplacement.getNom()+" "+tuile_deplacement.getEtat());
        }
        
        while (controle_boucle){
            
            System.out.println("Rentrez le numéro de la tuile sur laquelle vous voulez vous déplacer :");
            int tuile_choisie = scanner.nextInt(); //l'utilisateur saise le numéro de la tuile

            for (int i =0;i<tuiles_deplacement.size();i++){
                if (tuile_choisie==tuiles_deplacement.get(i).getNumero()){
                    joueur.setPostition(getGrille().getTuile(tuile_choisie)); // on modifie la position du joueur
                    
                    controle_boucle = false;
                    System.out.println("Vous vous êtes bien déplacé sur la tuile :"+tuile_choisie+" "+grille.getTuile(tuile_choisie).getNom()+" "+grille.getTuile(tuile_choisie).getEtat());    
                }else if(i==tuiles_deplacement.size()-1 && tuile_choisie != tuiles_deplacement.get(i).getNumero() && controle_boucle){
                    System.out.println("Vous ne pouvez pas vous déplacer sur cette tuile, veuillez rentrer un nouveau numero :");    
                }
            } //fin for
        } //fin while
    }
    *//*
    public void DeplacementPlongeur(Joueur joueur){
       
        boolean controle_boucle = true;
        
        System.out.println("Voici les cases sur lesquelles vous pouvez vous déplacer:");
        System.out.println(getGrille().getTuilesDeplacementPlongeur(joueur).size()+" hswlkdfhlskdjfhlkjsdwh");
        for(Tuile tuiles : getGrille().getTuilesDeplacementPlongeur(joueur)){
            System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());
        }
        
        while(controle_boucle){
            System.out.println("Rentrez le numéro de la tuile sur laquelle vous voulez vous déplacer :");
            int tuile_choisie = scanner.nextInt(); //l'utilisateur saise le numéro de la tuile
            
            while (getGrille().getTuile(tuile_choisie).getEtat() == Etat.INONDEE || getGrille().getTuile(tuile_choisie).getEtat() == Etat.DISPARUE){
                for (int i =0;i<getGrille().getTuilesDeplacementPlongeur(joueur).size();i++){
                    if (tuile_choisie==getGrille().getTuilesDeplacementPlongeur(joueur).get(i).getNumero()){
                         joueur.setPostition(getGrille().getTuile(tuile_choisie)); //on modifie la position du joueur
                    }
                 }
                System.out.println("Voici les cases sur lesquelles vous pouvez vous déplacer :");
                for(Tuile tuile_deplacement : getGrille().getTuilesDeplacementPlongeur(joueur)){
                    System.out.println(tuile_deplacement.getNumero()+" "+tuile_deplacement.getNom()+" "+tuile_deplacement.getEtat());
                }
                System.out.println("Rentrez le numéro de la tuile sur laquelle vous voulez vous déplacer :");
                tuile_choisie = scanner.nextInt();
            }
            
            if (getGrille().getTuile(tuile_choisie).getEtat()==Etat.ASSECHEE){
                for (int i =0;i<getGrille().getTuilesDeplacementPlongeur(joueur).size();i++){
                    if (tuile_choisie==getGrille().getTuilesDeplacementPlongeur(joueur).get(i).getNumero()){
                         joueur.setPostition(getGrille().getTuile(tuile_choisie)); //on modifie la position du joueur
                    }
                }//fin for
                controle_boucle=false;
                System.out.println("Vous vous êtes bien déplacé sur la tuile :"+getGrille().getTuile(tuile_choisie).getNumero()+" "+getGrille().getTuile(tuile_choisie).getNom()+" "+getGrille().getTuile(tuile_choisie).getEtat());
            }//fin if
        }//fin while     
    }
    */
    
    public void DeplacementNavigateur(Joueur joueur){
        
    }

    public void AssecherTuile(Joueur joueur) {
        ArrayList<Tuile> tuiles_assechables = joueur.getAventurier().getTuilesAssechables(joueur);
        boolean controle_boucle = true;
        
        System.out.println("Voici les tuiles que vous pouvez assécher :");
        for (Tuile tuiles : tuiles_assechables){       
            System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
        }
        while (controle_boucle){
            
            System.out.println("rentrez le numero de la tuile que vous voulez assécher");
            int tuile_choisie = scanner.nextInt();
            

            for (int i =0;i<tuiles_assechables.size();i++){
                if (tuile_choisie==tuiles_assechables.get(i).getNumero()){
                    grille.setEtat(tuile_choisie, Etat.ASSECHEE);
                    controle_boucle = false;
                    System.out.println("la case numero :"+tuile_choisie+ " a bien été asséechée"+" "+grille.getTuile(tuile_choisie).getEtat());    
                }else if(i==tuiles_assechables.size()-1 && tuile_choisie != tuiles_assechables.get(i).getNumero() && controle_boucle){
                    System.out.println("Vous ne pouvez pas assécher cette tuile!");  
                    for (Tuile tuiles : tuiles_assechables){       
                        System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
                    }
                }
            } //fin for
        } //fin while 
    }
    
    public void AssecherTuileIngenieur(Joueur joueur){
        //controle boucle ==0 -> 1ere tuile à assécher
        //controle boucle ==1 -> 2eme tuile à assécher
        //controle boucle ==2 -> sortie de boucle
        ArrayList<Tuile> tuiles_assechables1 = getGrille().getTuilesAssechables(joueur);
        ArrayList<Tuile> tuiles_assechables2;
        boolean possibilite_deuxieme_assechage = false;
        boolean deuxieme_assechage=false;
        boolean premier_assechage =true;
        
        System.out.println("Voici les tuiles que vous pouvez assécher :");
        for (Tuile tuiles : tuiles_assechables1){       
            System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
        }
        while (premier_assechage){
                System.out.println("rentrez le numero de la tuile que vous voulez assécher");
                int case_choisie = scanner.nextInt();
                

                for (int i =0;i<tuiles_assechables1.size();i++){
                    if (case_choisie==tuiles_assechables1.get(i).getNumero()){
                        grille.setEtat(case_choisie, Etat.ASSECHEE);
                        premier_assechage=false;  
                        System.out.println("la case numero :"+case_choisie+ " a bien été asséechée"+" "+grille.getTuile(case_choisie).getEtat());    

                    }else if(i==tuiles_assechables1.size()-1 && case_choisie != tuiles_assechables1.get(i).getNumero() && premier_assechage==true){
                        System.out.println("Vous ne pouvez pas assécher cette tuile!");  
                        for (Tuile tuiles : tuiles_assechables1){       
                            System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
                        }    
                    }//fin if
                }//fin for    
        }
        for (Tuile tuile : getGrille().getTuilesAssechables(joueur)){ // on vérifie si il y a d'autres cases à assécher
            if (tuile.getEtat()==Etat.INONDEE){
                possibilite_deuxieme_assechage=true;
            }
        }
        if (possibilite_deuxieme_assechage){
            System.out.println("Voulez-vous assécher une nouvelle tuile ? true/false");
            boolean bool = scanner.nextBoolean();
            deuxieme_assechage = bool;
            if (deuxieme_assechage){
                tuiles_assechables2=getGrille().getTuilesAssechables(joueur);
                for (Tuile tuile : tuiles_assechables2){
                    System.out.println(tuile.getNumero()+" "+tuile.getNom()+" "+tuile.getEtat());
                }
            
                while(deuxieme_assechage){
                    System.out.println("rentrez le numero de la tuile que vous voulez assécher");
                    int case_choisie = scanner.nextInt();


                    for (int i =0;i<tuiles_assechables2.size();i++){
                        if (case_choisie==tuiles_assechables2.get(i).getNumero()){
                            grille.setEtat(case_choisie, Etat.ASSECHEE);
                            deuxieme_assechage=false;  
                            System.out.println("la case numero :"+case_choisie+ " a bien été asséechée"+" "+grille.getTuile(case_choisie).getEtat());    

                        }else if(i==tuiles_assechables2.size()-1 && case_choisie != tuiles_assechables2.get(i).getNumero() && deuxieme_assechage==true){
                            System.out.println("Vous ne pouvez pas assécher cette tuile!");  
                            for (Tuile tuiles : tuiles_assechables2){       
                                System.out.println(tuiles.getNumero()+" "+tuiles.getNom()+" "+tuiles.getEtat());    
                            } //fin for   
                        }//fin
                    }//fin for    
                }//fin while
            }//fin if
        }//fin if 
    }//fin if

    public Grille getGrille() {
        return grille;
    }
    
    public ArrayList melangeCartes(ArrayList cartes){
        Collections.shuffle(cartes);
        return cartes;
        }
     
    /**
     * @return the joueurs
     */
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    
    
    
    public ArrayList<Tresor> getTrésors() {
        return trésors;
    }

    
    public Joueur getJoueurCourant(){
        return joueur_courant; 
    }
    
    public void setJoueurCourant(Joueur joueur) {
        this.joueur_courant=joueur;
    }
    public boolean jeuGagné(Grille grille) {
        
        Tuile heliport = null;
        for (int i = 0; i<grille.getTuiles().length;i++){
            
            if ((grille.getTuile(i).getNom())==("Heliport")) {
                heliport = grille.getTuile(i);
            }
        }
        int nbTresorsRecuperés = 0 ;
        for (Tresor tresor : trésors){
            
            if (tresor.estRecupere()){
                    nbTresorsRecuperés=nbTresorsRecuperés+1;
                    }
        }
                if (heliport.getJoueurspresents().size()==joueurs.size() && nbTresorsRecuperés == 4 ) {
                    return true;}
                else {
                    return false;}
        
    }
    public boolean jeuPerdu(Grille grille) {
        
        Tuile heliport = null;
        
        int nbTuileTresorTemple =0;
        int nbTuileTresorCaverne=0;
        int nbTuileTresorPalais=0;
        int nbTuileTresorJardin=0;
        
        int nbTuilesAdjNonDisp=0;
        boolean joueurNoyé=false;
        
        // Cette portion de code parcours toutes les tuiles de la grille 
        // afin de trouver la tuile Heliport
        for (int i = 0; i<grille.getTuiles().length;i++){
            if ((grille.getTuile(i).getNom())==("Heliport")) {
                heliport = grille.getTuile(i);
            }
        }
        // fin for
        //
        
        
        
        // Cette portion de code parcours toutes les tuiles de la grille 
        // et compte le nombre de tuiles "Tresor" disparues
         for (int i = 0; i<grille.getTuiles().length;i++){ 
            if ((grille.getTuile(i).getNom()).contains("Temple")&& grille.getTuile(i).getEtat().equals(Etat.DISPARUE)) {
                nbTuileTresorTemple=nbTuileTresorTemple+1 ;
            } else if ((grille.getTuile(i).getNom()).contains("Caverne")&& grille.getTuile(i).getEtat().equals(Etat.DISPARUE)) {
                nbTuileTresorCaverne=nbTuileTresorCaverne+1 ;
            } else if ((grille.getTuile(i).getNom()).contains("Palais")&& grille.getTuile(i).getEtat().equals(Etat.DISPARUE)) {
                nbTuileTresorPalais=nbTuileTresorPalais+1 ; }
            else if ((grille.getTuile(i).getNom()).contains("Jardin")&& grille.getTuile(i).getEtat().equals(Etat.DISPARUE)) {
                nbTuileTresorJardin=nbTuileTresorJardin+1 ; }
          //
         }//fin for 
         
         
         
        // Cette boucle parcours la collection de joueurs 
        // et si un joueur est sur une tuile Île qui sombre 
        // et qu’il n’y a pas de tuile adjacente où nager
        // alors un booléen joueurNoyé prend la valeur true
         for (int i =0; i<=joueurs.size();i++) {
             ArrayList<Tuile> tuilesAdjJoueurCourant;
             Tuile positionJoueurCourant=joueurs.get(i).getPosition();
             tuilesAdjJoueurCourant=grille.getTuilesAdjacentes(positionJoueurCourant);
            // Cette boucle parcours les tuiles adjacentes au joueur 
            // courant i de la collection de joueur
            // et compte le nombre de tuiles adjacentes non disparues
             for (int j = 0; j<tuilesAdjJoueurCourant.size();j++) {
                if (tuilesAdjJoueurCourant.get(j).getEtat().equals(Etat.ASSECHEE) ||tuilesAdjJoueurCourant.get(j).getEtat().equals(Etat.INONDEE)){
                    nbTuilesAdjNonDisp = nbTuilesAdjNonDisp+1;
                } else {}
            } // fin for
            if (nbTuilesAdjNonDisp == 0) {
                joueurNoyé=true;
            }
         } // fin for
        if (nbTuileTresorCaverne==2 || nbTuileTresorJardin==2 || nbTuileTresorPalais==2 || nbTuileTresorTemple==2) //Si les 2 tuiles « Temple », « Caverne », « Palais» ou « Jardin »
            {                                                                                                          //sombrent avant que vous n’ayez pris leurs trésors respectifs 
                return true;
            } else if (heliport.getEtat().equals(Etat.DISPARUE))                                                       //Si « l’héliport » sombre
            {
                return true;
            } else if (joueurNoyé) {
                return true;
            } else if (niveau_eau > 9) {
                return true;
            } else {
                return false;
            }
    } 
}
