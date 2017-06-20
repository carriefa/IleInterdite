/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import Roles.*;
import ileinterdite.Utils.Pion;
import java.util.ArrayList;

public class Test {
            private ArrayList<Tresor> trésors;
            
    public static void main(String[] args) {
        Tresor tresor = new Tresor();
        trésors = tresor.getTresors();
        // TODO code application logic here
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
        
        Controleur controleur = new Controleur();
        Message2 m = new Message2(); 
        m.type = TypesMessage.DEMARRER_PARTIE;
        controleur.traiterMessage(m);
      
    }
}
