/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import Roles.*;
import ileinterdite.Utils.Pion;

public class Test {

    public static void main(String[] args) {
        // TODO code application logic here
         Explorateur av1 = new Explorateur();
        Joueur j1 = new Joueur("John",av1, Pion.VERT);
        System.out.println(j1.getNom());
        System.out.println(av1.getJoueur().getNom());
        Grille grille = new Grille();
        j1.setPostition(grille.getTuile(15));
        grille.setEtat(16, Etat.DISPARUE);       
        IHMileInterdite2 ihm = new IHMileInterdite2();
        JeuIleInterdite jeu = new JeuIleInterdite();
        ihm.InitFenetrePrincipale(grille);
        grille.setEtat(15, Etat.INONDEE);
        
        
        //tests anas
        ihm.InitFenetreDepart();
    }
    
}
