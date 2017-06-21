
package ileinterdite;

import ileinterdite.Utils.Pion;
import java.util.ArrayList;

 public class Grille {
        private Tuile tuile_type ;
        private ArrayList<String> nom_tuiles_init;
        private Tuile [] instance_tuile;  
        private final Integer [] num_tuile_colonne_gauche = {00,06,12,18,24,30};
        private final Integer [] num_tuile_colonne_droite = {05,11,17,23,29,35};
        
         
     public Grille(ArrayList<Tresor> tresor){
         /* |00|01|02|03|04|05|
            |06|07|08|09|10|11|
            |12|13|14|15|16|17|
            |18|19|20|21|22|23|
            |24|25|26|27|28|29|
            |30|31|32|33|34|35|
         */
         
        tuile_type= new Tuile();
        nom_tuiles_init = new ArrayList<>();
                
        for(int i = 0; i < (tuile_type.getNOM_TUILES().length); i++){
            nom_tuiles_init.add(tuile_type.getNOM_TUILES()[i]); 
        }
        
        instance_tuile = new Tuile [36];
        initTuiles(tresor);
     }
     

    public void initTuiles(ArrayList<Tresor> tresors){
        
        int alea;
        int a = tuile_type.getNOM_TUILES().length-1;
        int num = 0;
        
             
        for(int i = 0; i < getTuiles().length; i++){ // boucle qui parcoute les nom des tuiles
            alea = (int) (Math.random() * (a - 0)); // alea un nombre netre 0 et 35
            //alea=a; // A MODIFIER!!!!
            instance_tuile[i] = new Tuile(alea);
            instance_tuile[i].setNumero(num);// set le numéro
            instance_tuile[i].setEtat(num); // set l'état  (num)

            if (instance_tuile[i].getEtat()==Etat.VIDE){
                instance_tuile[i].setNom(null);
            }else{
               instance_tuile[i].setNom(nom_tuiles_init.get(alea));
               instance_tuile[i].setTresorAssocié(tresors);
               
               nom_tuiles_init.remove(alea);
               
               a = a-1;
            }
            num= num+1;
            System.out.println(instance_tuile[i].getNom());
        }
        for(Tuile tuile : instance_tuile){
            tuile.setPionAssocie();
            tuile.setGrille(this);
        }
        setEtat(16, Etat.DISPARUE);
        setEtat(14, Etat.DISPARUE);
        setEtat(20, Etat.DISPARUE);
        setEtat(26, Etat.DISPARUE);
        setEtat(19, Etat.INONDEE);
        setEtat(21, Etat.INONDEE);
        
    }
    
    public void setEtat(int num_tuile, Etat etat){
        this.getTuile(num_tuile).SetEtat(etat);
    }
    
    public Tuile[] getTuiles() {
        return instance_tuile;
    }
    
    public Tuile getTuile(int numero_tuile){
        Tuile resultat = null;
        for (Tuile tuile : instance_tuile){
            if (numero_tuile==tuile.getNumero()){
                resultat = tuile;
            }
        }
        return resultat;
    }
    
    public int getNumTuilePion(Pion pion){
        int num_tuile = 36;
        for(Tuile tuile : instance_tuile){
            if (tuile.getPion()==pion){
                num_tuile=tuile.getNumero();
            }
        }
        return num_tuile;
    }
    
    public ArrayList<Tuile> getTuilesAdjacentes(Tuile tuile){
        ArrayList<Tuile> tuiles_adjacentes = new ArrayList<>();
        int num_tuile_courante;
        num_tuile_courante = tuile.getNumero();
        Tuile tuile_haut;
        Tuile tuile_bas ;
        Tuile tuile_gauche = getTuile(num_tuile_courante-1);
        Tuile tuile_droite = getTuile(num_tuile_courante+1);
        
        //----------------------------------------------------------------------------------------------------------------------//
        
        if (num_tuile_courante>=30){
            tuile_bas=null;
        }else if(getTuile(num_tuile_courante+6).getEtat()==Etat.VIDE){
            tuile_bas=null;
        }else {
            tuile_bas=getTuile(num_tuile_courante+6);
        }
        
        //--------------------------------------------------------------------------------------------------------------------------//
        
        if (num_tuile_courante<=5){
            tuile_haut=null;
        }
        else if(getTuile(num_tuile_courante-6).getEtat()==Etat.VIDE){
            tuile_haut=null;
        }else{
            tuile_haut=getTuile(num_tuile_courante-6);
        }
        
        //---------------------------------------------------------------------------------------------------------------------------//
        
        for (int i =0; i< num_tuile_colonne_gauche.length && tuile_gauche != null;i++){
            if (num_tuile_courante==num_tuile_colonne_gauche[i]){
                tuile_gauche=null;
            }else if(getTuile(num_tuile_courante-1).getEtat()==Etat.VIDE){
                tuile_gauche=null;
            }else{
                tuile_gauche=getTuile(num_tuile_courante-1);
            }
        }
        
        //--------------------------------------------------------------------------------------------------------------------------//
        
        for (int i=0; i<num_tuile_colonne_droite.length && tuile_droite != null;i++){
            if (num_tuile_courante==num_tuile_colonne_droite[i]){
                tuile_droite=null;
            }else if(getTuile(num_tuile_courante+1).getEtat()==Etat.VIDE){
                tuile_droite=null;
            }else{
                tuile_droite=getTuile(num_tuile_courante+1);
            }
        }

       // tuiles_adjacentes.add(tuile);
       if ((tuile_bas!=null)){
           tuiles_adjacentes.add(tuile_bas);
       }
       if ((tuile_haut !=null)){
           tuiles_adjacentes.add(tuile_haut);
       }
       if ((tuile_gauche !=null)){
           tuiles_adjacentes.add(tuile_gauche);
       }
       if ((tuile_droite != null)){
           tuiles_adjacentes.add(tuile_droite);
       }
        return tuiles_adjacentes;
    }
    
    public ArrayList<Tuile> getTuilesAdjacentesDiagonales(Tuile tuile){
        ArrayList<Tuile> tuiles_adjacentes_diagonales = new ArrayList<>();
        int   num_tuile_courante;
        num_tuile_courante = tuile.getNumero();
        Tuile tuile_haut ;
        Tuile tuile_bas;
        Tuile tuile_gauche = getTuile(num_tuile_courante-1);
        Tuile tuile_droite = getTuile(num_tuile_courante+1);
        Tuile tuile_haut_gauche;
        Tuile tuile_haut_droite;
        Tuile tuile_bas_gauche;
        Tuile tuile_bas_droite;
        
        
        
        //--------------------------------------------------BAS----------------------------------------------------------------//
        
        if (num_tuile_courante>=30){
            tuile_bas=null;
        }else if(getTuile(num_tuile_courante+6).getEtat()==Etat.VIDE){
            tuile_bas=null;
        }else {
            tuile_bas=getTuile(num_tuile_courante+6);
        }
        
        //--------------------------------------------------HAUT-----------------------------------------------------------------//
        
        if (num_tuile_courante<=5){
            tuile_haut=null;
        }
        else if(getTuile(num_tuile_courante-6).getEtat()==Etat.VIDE){
            tuile_haut=null;
        }else{
            tuile_haut=getTuile(num_tuile_courante-6);
        }
        
        //----------------------------------------------------GAUCHE------------------------------------------------------------------//
        
        for (int i =0; i< num_tuile_colonne_gauche.length && tuile_gauche != null;i++){
            if (num_tuile_courante==num_tuile_colonne_gauche[i]){
                tuile_gauche=null;
            }else if(getTuile(num_tuile_courante-1).getEtat()==Etat.VIDE){
                tuile_gauche=null;
            }else{
                tuile_gauche=getTuile(num_tuile_courante-1);
            }
        }
        
        //-----------------------------------------------------DROITE---------------------------------------------------------------//
        
        for (int i=0; i<num_tuile_colonne_droite.length && tuile_droite != null;i++){
            if (num_tuile_courante==num_tuile_colonne_droite[i]){
                tuile_droite = null;
            }else if(getTuile(num_tuile_courante+1).getEtat()==Etat.VIDE){
                tuile_droite = null;
            }else{
                tuile_droite=getTuile(num_tuile_courante+1);
            }
        }
        
        //------------------------------------------------------HAUT GAUCHE---------------------------------------------------------//
        if (tuile_haut == null || tuile_gauche==null){
            tuile_haut_gauche = null;
        }else if(getTuile((num_tuile_courante-6)-1).getEtat()==Etat.VIDE){
            tuile_haut_gauche = null;
        }else{
            tuile_haut_gauche = getTuile((num_tuile_courante-6)-1);
        }
        //------------------------------------------------------HAUT DROITE---------------------------------------------------------//
        if (tuile_haut == null || tuile_droite==null){
            tuile_haut_droite = null;
        }else if(getTuile((num_tuile_courante-6)+1).getEtat()==Etat.VIDE){
            tuile_haut_droite = null;
        }else{
            tuile_haut_droite = getTuile((num_tuile_courante-6)+1);
        }
        //------------------------------------------------------BAS GAUCHE---------------------------------------------------------//
        if (tuile_bas == null || tuile_gauche==null){
            tuile_bas_gauche = null;
        }else if(getTuile((num_tuile_courante+6)-1).getEtat()==Etat.VIDE){
            tuile_bas_gauche = null;
        }else{
            tuile_bas_gauche = getTuile((num_tuile_courante+6)-1);
        }
        //------------------------------------------------------BAS DROIT---------------------------------------------------------//
        if (tuile_bas == null || tuile_droite==null){
            tuile_bas_droite = null;
        }else if(getTuile((num_tuile_courante+6)+1).getEtat()==Etat.VIDE){
            tuile_bas_droite = null;
        }
        else{
            tuile_bas_droite = getTuile((num_tuile_courante+6)+1);
        }
        //-----------------------------------------------------------------------------------------------------------------------//
       // tuiles_adjacentes.add(tuile);
       if ((tuile_bas!=null)){
           tuiles_adjacentes_diagonales.add(tuile_bas);
       }
       if ((tuile_haut !=null)){
           tuiles_adjacentes_diagonales.add(tuile_haut);
       }
       if ((tuile_gauche !=null)){
           tuiles_adjacentes_diagonales.add(tuile_gauche);
       }
       if ((tuile_droite != null)){
           tuiles_adjacentes_diagonales.add(tuile_droite);
       }
       if ((tuile_haut_gauche != null)){
           tuiles_adjacentes_diagonales.add(tuile_haut_gauche);
       }
       if ((tuile_haut_droite != null)){
           tuiles_adjacentes_diagonales.add(tuile_haut_droite);
       }
       if ((tuile_bas_gauche != null)){
           tuiles_adjacentes_diagonales.add(tuile_bas_gauche);
       }
       if ((tuile_bas_droite != null)){
           tuiles_adjacentes_diagonales.add(tuile_bas_droite);
       }
        return tuiles_adjacentes_diagonales;
    }
    
    
    public ArrayList<Tuile> getTuilesDeplacement(Joueur joueur){
        Tuile tuile_joueur =joueur.getPosition();
        String role = joueur.getAventurier().getRole();
        ArrayList<Tuile> tuiles_deplacement = new ArrayList<>();
        
        for(Tuile tuile : getTuilesAdjacentes(tuile_joueur)){
            if (tuile.getEtat()== Etat.ASSECHEE || tuile.getEtat()==Etat.INONDEE){
                tuiles_deplacement.add(tuile);
            }
        }
       return tuiles_deplacement;
    }
    
    public ArrayList<Tuile> getTuilesDeplacementExplorateur(Joueur joueur){
        Tuile tuile_joueur =joueur.getPosition();
        String role = joueur.getAventurier().getRole();
        ArrayList<Tuile> tuiles_deplacement = new ArrayList<>();
        
        for(Tuile tuile : getTuilesAdjacentesDiagonales(tuile_joueur)){
            if (tuile.getEtat()== Etat.ASSECHEE || tuile.getEtat()==Etat.INONDEE){
                tuiles_deplacement.add(tuile);
            }
        }
        return tuiles_deplacement;
    }
    
    public ArrayList<Tuile> getTuilesDeplacementPlongeur(Joueur joueur){
        Tuile tuile_joueur = joueur.getAventurier().getPosition();
        ArrayList<Tuile> tuiles_tout = new ArrayList<>();
        ArrayList<Tuile> tuiles_innondee_a_faire = new ArrayList<>();
        ArrayList<Tuile> tuiles_innondee_a_faire2 = new ArrayList<>();
        ArrayList<Tuile> tuiles_innondes_OK = new ArrayList<>();
        ArrayList<Tuile> tuiles_disparue_a_faire = new ArrayList<>();
        ArrayList<Tuile> tuiles_disparues_a_faire2 = new ArrayList<>();
        ArrayList<Tuile> tuiles_disparues_OK = new ArrayList<>();
        ArrayList<Tuile> tuiles_assechees = new ArrayList<>();
        ArrayList<Tuile> tuiles_finales = new ArrayList<>();
        ArrayList<Tuile> tuiles_vides = new ArrayList<>();
        
        for(Tuile tuile : this.getTuilesAdjacentes(tuile_joueur)){
            tuiles_tout.add(tuile);
            if (tuile.getEtat()==Etat.INONDEE){
                tuiles_innondee_a_faire.add(tuile);
            }else if(tuile.getEtat()==Etat.ASSECHEE){
                tuiles_assechees.add(tuile);
            }else if(tuile.getEtat()==Etat.DISPARUE){
                tuiles_disparue_a_faire.add(tuile);
            }else{
                tuiles_vides.add(tuile);
            }
            
        }
        tuiles_tout.add(tuile_joueur);
        while(!tuiles_disparue_a_faire.isEmpty() || !tuiles_innondee_a_faire.isEmpty()){ //tant qu'il y a des tuiles inondées et disparues
            
            for(Tuile tuile_inondee : tuiles_innondee_a_faire){ //parcourt de toutes les tuiles inondees
                
                for(Tuile tuile_adj : this.getTuilesAdjacentes(tuile_inondee)){ // on regarde toutes les cases adjacentes des cases innondées a faire
                    System.out.println("test");
                    System.out.println(tuile_adj.getNumero()+" : tuile adj premier for");
                    
                    if (tuile_adj.getEtat()==Etat.ASSECHEE && !tuiles_tout.contains(tuile_adj) &&  tuiles_assechees.contains(tuile_adj)==false){
                        tuiles_assechees.add(tuile_adj);
                        System.out.println("premier for ASSECHEE");
                    }else if(tuile_adj.getEtat()==Etat.INONDEE && !tuiles_tout.contains(tuile_adj) &&  tuiles_innondee_a_faire.contains(tuile_adj)==false && tuiles_innondee_a_faire2.contains(tuile_adj)==false && tuiles_innondes_OK.contains(tuile_adj)==false){
                        tuiles_innondee_a_faire2.add(tuile_adj);
                        System.out.println("premier for INONDEE");
                    }else if (tuile_adj.getEtat()==Etat.DISPARUE && !tuiles_tout.contains(tuile_adj) &&  tuiles_disparue_a_faire.contains(tuile_adj)==false && tuiles_disparues_a_faire2.contains(tuile_adj)==false && tuiles_disparues_OK.contains(tuile_adj)==false){
                        tuiles_disparues_a_faire2.add(tuile_adj);
                        System.out.println("premier for DISPARUE");
                    }else if (!tuiles_tout.contains(tuile_adj)) {
                        tuiles_vides.add(tuile_adj);
                        System.out.println(tuile_adj.getEtat()+"premier for VIDE");
                    }
                    
                    if(!tuiles_tout.contains(tuile_adj)){
                       tuiles_tout.add(tuile_adj); 
                    }
                    
                    //System.out.println(this.getTuilesAdjacentes(tuile_inondee).size()+" tuiles adj inondees");
                }
                //System.out.println(getTuile(i).getNumero()+" numero tuiles_inonndees_a_faire");
                
                if (!tuiles_tout.contains(tuile_inondee)){
                    tuiles_tout.add(tuile_inondee);
                }
                
                tuiles_innondes_OK.add(tuile_inondee);
                //tuiles_innondee_a_faire.remove(getTuile(i));
            }
            
            for(Tuile tuile_disparue : tuiles_disparue_a_faire){
                System.out.println("test-2");
                
                for(Tuile tuile_adj : this.getTuilesAdjacentes(tuile_disparue)){
                    System.out.println(tuile_adj.getNumero()+" : tuile adj deuxieme for");
                    
                    if (tuile_adj.getEtat()==Etat.ASSECHEE && !tuiles_tout.contains(tuile_adj) &&  tuiles_assechees.contains(tuile_adj)==false){
                        tuiles_assechees.add(tuile_adj);
                        System.out.println("deuxieme for ASSECHEE");
                    }else if(tuile_adj.getEtat()==Etat.INONDEE && !tuiles_tout.contains(tuile_adj) &&  tuiles_innondee_a_faire.contains(tuile_adj)==false && tuiles_innondee_a_faire2.contains(tuile_adj)==false && tuiles_innondes_OK.contains(tuile_adj)==false){
                        tuiles_innondee_a_faire2.add(tuile_adj);
                        System.out.println("deuxieme for INONDEE");
                    }else if (tuile_adj.getEtat()==Etat.DISPARUE && !tuiles_tout.contains(tuile_adj) && tuiles_disparue_a_faire.contains(tuile_adj)==false && tuiles_disparues_a_faire2.contains(tuile_adj)==false && tuiles_disparues_OK.contains(tuile_adj)==false){
                        tuiles_disparues_a_faire2.add(tuile_adj);
                        System.out.println("deuxieme for DISPARUE");
                    }else if (!tuiles_tout.contains(tuile_adj)){
                        tuiles_vides.add(tuile_adj);
                        System.out.println(tuile_adj.getEtat()+"deuxieme for VIDE");
                    }
                    
                    if (!tuiles_tout.contains(tuile_adj)){
                        tuiles_tout.add(tuile_adj);
                    }
                }
                tuiles_disparues_OK.add(tuile_disparue);
                
                if (!tuiles_tout.contains(tuile_disparue)){
                    tuiles_tout.add(tuile_disparue);
                }
                
            }
            
            tuiles_innondee_a_faire.clear();
            if(tuiles_disparues_a_faire2.size()>0){
                for(Tuile tuile : tuiles_innondee_a_faire2){
                tuiles_innondee_a_faire.add(tuile);
                }
                tuiles_innondee_a_faire2.clear();
            }
            tuiles_disparue_a_faire.clear();
            if (tuiles_disparues_a_faire2.size()>0){
                for(Tuile tuile : tuiles_disparues_a_faire2){
                     tuiles_disparue_a_faire.add(tuile);
                }
                tuiles_disparues_a_faire2.clear();
            }

        }
        System.out.println("\n");
        for(Tuile tuiletout : tuiles_tout){
            if((tuiletout != tuile_joueur) &&(tuiletout.getEtat()!=Etat.DISPARUE) && (tuiletout.getEtat()!=Etat.VIDE)){
                tuiles_finales.add(tuiletout);
            }
        }
        return tuiles_finales;
    }
    
    public ArrayList<Tuile> getTuilesDeplacementPilote(Joueur joueur){
        Tuile tuile_joueur = joueur.getPosition();
        ArrayList<Tuile> tuiles_deplacement = new ArrayList<>();
        for (Tuile tuile : getTuiles()){
            if (tuile.getEtat()!= Etat.VIDE && tuile.getEtat() != Etat.DISPARUE && tuile!=tuile_joueur){
                tuiles_deplacement.add(tuile);
            }
        }        
        return tuiles_deplacement;
    }
    
    public ArrayList<Tuile> getTuilesAssechables(Joueur joueur){
        Tuile tuile_joueur = joueur.getPosition();
        ArrayList<Tuile> tuiles_assechable = new ArrayList<>();

        if (tuile_joueur.getEtat()==Etat.INONDEE){
            tuiles_assechable.add(tuile_joueur);
        }

        for (Tuile tuile_act : getTuilesAdjacentes(tuile_joueur)){
            if (tuile_act.getEtat()==Etat.INONDEE){
                tuiles_assechable.add(tuile_act);
            }//fin if
        }//fin for
        if(tuiles_assechable==null){
            tuiles_assechable.add(null);
        }
     return tuiles_assechable;
    }
    
    public ArrayList<Tuile> getTuilesAssechablesExplorateur(Joueur joueur){
        Tuile tuile_joueur = joueur.getPosition();
        String role = joueur.getAventurier().getRole();
        ArrayList<Tuile> tuiles_assechable = new ArrayList<>();

        if (tuile_joueur.getEtat()==Etat.INONDEE){
            tuiles_assechable.add(tuile_joueur);
        }

        for (Tuile tuile_act : getTuilesAdjacentesDiagonales(tuile_joueur)){
            if (tuile_act.getEtat()==Etat.INONDEE){
                tuiles_assechable.add(tuile_act);
            }//fin if
        }//fin if  
        
     return tuiles_assechable;
    }
 }
 
