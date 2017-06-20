package ileinterdite;

import Cartes.Carte_Tresor;
import Cartes.SacDeSable;
import Cartes.Helicoptere;
import Cartes.Carte_Inondation;
import Cartes.Carte_Tresor_Abs;
import Roles.Plongeur;
import Roles.Pilote;
import Roles.Navigateur;
import Roles.Ingenieur;
import Roles.Explorateur;
import Roles.Messager;
import com.sun.glass.ui.SystemClipboard;
import ileinterdite.Utils.Pion;

import java.util.ArrayList;
import java.util.Collection;
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
    public JeuIleInterdite(){
        // init des tresors 
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
        grille = new Grille(getTrésors());
        roles = new ArrayList<>();
        trésors = new ArrayList<>();
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
    
    
     public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
     
     
    public void initCartesJoueurs(){
        
        for(int i = 0; i<joueurs.size(); i++){
            for(int j = 0; j<cartestrésors.size(); i++){
                joueurs.get(i);
            }
            
        }
    }
    
     
    public void Jeu (){
        boolean partiecontinue = true ;
        
        InitJoueur();
        while (partiecontinue){
        for (Joueur joueur : getJoueurs()) {
            TourJoueur(joueur);
        }}
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
                    Deplacement(joueur);
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
    
    public void InitJoueur(){
        System.out.println("Rentrez le nombre de joueurs (de 1 à 4): ");
        int nb_joueurs = scanner.nextInt();
        scanner.nextLine();
        
        if (nb_joueurs<1){
            System.out.println("Il n'y a pas assez de joueurs.");
        }else if(nb_joueurs>4){
            System.out.println("Il y a trop de joueurs.");
        }else{
            String nom_joueur;
            Aventurier pilote = new Pilote();
            Aventurier ingenieur = new Ingenieur();
            Aventurier messager = new Messager();
            Aventurier explorateur = new Explorateur();
            Aventurier plongeur = new Plongeur();
            Aventurier navigateur = new Navigateur();

            ArrayList<Pion> pions = new ArrayList<>();
            ArrayList<Aventurier> aventuriers = new ArrayList<>();
            ArrayList<Aventurier> role_non_uitlises = aventuriers;
            Aventurier aventurier = null;
            Pion pion = null;

            pions.add(Pion.BLEU);
            pions.add(Pion.JAUNE);
            pions.add(Pion.ORANGE);
            pions.add(Pion.ROUGE);
            pions.add(Pion.VERT);
            pions.add(Pion.VIOLET);

            aventuriers.add(pilote);
            aventuriers.add(ingenieur);
            aventuriers.add(messager);
            aventuriers.add(explorateur);
            aventuriers.add(plongeur);
            aventuriers.add(navigateur);


            ArrayList<Pion> pions_non_utilises = pions;

            for (int i = 1; i<=nb_joueurs;i++){
               
                
                System.out.println("rentrez le nom du joueur :");
                nom_joueur = scanner.nextLine(); //on définie le nom du joueur
                

                System.out.println("quel rôle voulez vous prendre :");
                for (int j =0 ; j<role_non_uitlises.size();j++){
                    System.out.println(j+" "+role_non_uitlises.get(j).getRole());
                }
                int num_aventurier_choisie = scanner.nextInt();scanner.nextLine();
                if (num_aventurier_choisie>=0 && num_aventurier_choisie<7){
                    aventurier=role_non_uitlises.get(num_aventurier_choisie);
                    role_non_uitlises.remove(aventurier);
                }else{
                    while (num_aventurier_choisie<0 || num_aventurier_choisie>=7){
                        System.out.println("Il n'y a aucun rôle correspondant à ce chiffre, veuillez en rentrer un autre :");
                        for (int j =1 ; j<=role_non_uitlises.size();j++){
                            System.out.println(j+" "+role_non_uitlises.get(j).getRole());
                            num_aventurier_choisie=scanner.nextInt();scanner.nextLine();
                        }
                    }//fin while
                }//fin if
                
                System.out.println("Quelle couleur de pion voulez-vous avoir? :");
                for (int j =0 ; j<pions_non_utilises.size();j++){
                    System.out.println(j+" "+pions_non_utilises.get(j));
                }
                int num_pion_choisie = scanner.nextInt();scanner.nextLine();
                if (num_pion_choisie>=0 && num_pion_choisie<7){
                    pion=pions_non_utilises.get(num_pion_choisie);
                    pions_non_utilises.remove(pion);
                }else{
                    while (num_pion_choisie<0 || num_pion_choisie>=7){
                        System.out.println("Il n'y a aucun pion correspondant à ce chiffre, veuillez en rentrer un autre :");
                        for (int j =1 ; j<=pions_non_utilises.size();j++){
                            System.out.println(j+" "+pions_non_utilises.get(j));
                            num_pion_choisie=scanner.nextInt();
                            scanner.nextLine();
                        }
                    }//fin while
                }//fin if
                Joueur joueur = new Joueur(nom_joueur,aventurier,pion);
                
                if (grille.getNumTuilePion(joueur.getPion())!=36){
                    //joueur.setPostition(grille.getTuile(grille.getNumTuilePion(joueur.getPion())));
                    joueur.setPostition(grille.getTuile(32));
                }
                
                getJoueurs().add(joueur);
                //tests
                /*
                System.out.println(grille.getNumTuilePion(joueur.getPion()));
                for(Pion pion12 : pions){
                    System.out.println(grille.getNumTuilePion(pion12));
                }
                System.out.println(joueur.getPosition().getNumero()+"beuleubeuleu");
                */
            }//fin for
            
        }
        
    }

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
    
    public void DeplacementPlongeur(Joueur joueur){
        ArrayList<Tuile> tuiles_deplacements = joueur.getAventurier().getTuilesDeplacement(joueur);
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
    
    public void DeplacementPilote(Joueur joueur){
        
    }
    
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

    /**
     * @return the trésors
     */
    public ArrayList<Tresor> getTrésors() {
        return trésors;
    }
}
