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
        
        Grille grille = new Grille();
        
        grille.setEtat(14, Etat.DISPARUE);
        grille.setEtat(20, Etat.DISPARUE);
        grille.setEtat(26, Etat.DISPARUE);
        grille.setEtat(22, Etat.DISPARUE);
        grille.setEtat( 3, Etat.INONDEE);
        grille.setEtat(19, Etat.INONDEE);
        grille.setEtat(21, Etat.INONDEE);
        grille.setEtat(23, Etat.INONDEE);
        grille.setEtat(33, Etat.INONDEE);
        IHMileInterdite2 ihm = new IHMileInterdite2();
        JeuIleInterdite jeu = new JeuIleInterdite();
        ihm.InitFenetrePrincipale(grille);
        jeu.Jeu();

    }
    
}
