/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import Cartes.Carte_Inondation;
import Cartes.Carte_Tresor;
import Cartes.Carte_Tresor_Abs;
import Cartes.Helicoptere;
import Cartes.SacDeSable;
import Roles.*;
import ileinterdite.Utils.Pion;
import java.util.ArrayList;

public class Test {
            private ArrayList<Tresor> trésors;
            private Carte_Inondation[] cartesInondation;
            
    public static void main(String[] args) {
        
        Carte_Tresor_Abs[] cartestrésors = new  Carte_Tresor_Abs[28];
        Tresor tresor1 = new Tresor("La Pierre Sacrée");
        Tresor tresor2 = new Tresor("La Statue Du Zéphyr");
        Tresor tresor3 = new Tresor("Le Cristal Ardent");
        Tresor tresor4 = new Tresor("Le Calice De L’onde");
       
        
        // TODO code application logic here
        
         for (int i = 0 ; i < 27 ; i++){
            if (i <=4){
                cartestrésors[i]=new Carte_Tresor(tresor1);
            }
            if (4 < i && i<=9 ){
                cartestrésors[i]=new Carte_Tresor(tresor2);
            }
             if (9 < i && i<=14 ){
                cartestrésors[i]=new Carte_Tresor(tresor3);
            }
              if (14 < i && i<=19 ){
                cartestrésors[i]=new Carte_Tresor(tresor4);
            }
             if (19 < i && i<=22){
                 cartestrésors[i]=new Montee_Des_Eaux();
             }
             if (22< i && i<=25){
                 cartestrésors[i]=new Helicoptere();
             }
             if (25 < i && i<=27){
                 cartestrésors[i]=new SacDeSable();
             }
        }
        
        int a = 0 ;
             for(int i = 0; i < grille.getTuiles().length; i++){
              if(grille.getTuile(i).getNom() != null){
                      cartesInondation[a] = new Carte_Inondation(grille.getTuile(i));
                a = a+1;
              }}
        
        
        Plongeur av1 = new Plongeur();

        Joueur j1 = new Joueur("John",av1, Pion.VERT);
        //System.out.println(j1.getNom());
        av1.setJoueur_associé(j1);
        //System.out.println(av1.getJoueur().getNom());
        Grille grille = new Grille();
        j1.setPostition(grille.getTuile(27));
        grille.setEtat(14, Etat.DISPARUE);
        grille.setEtat(20, Etat.DISPARUE);
        grille.setEtat(26, Etat.DISPARUE);
        grille.setEtat(19, Etat.INONDEE);
        grille.setEtat(21, Etat.INONDEE);
        IHMileInterdite2 ihm = new IHMileInterdite2();
        ihm.InitFenetrePrincipale(grille);
        for (Tuile tuile : j1.getAventurier().getTuilesDeplacement(j1)){
            System.out.println(tuile.getNumero()+tuile.getNom());
        }  
        
        
        
    }
    
    
    
}
