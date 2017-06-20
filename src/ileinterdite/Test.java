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
        grille.setEtat(16, Etat.DISPARUE);       
        IHMileInterdite2 ihm = new IHMileInterdite2();
        JeuIleInterdite jeu = new JeuIleInterdite();
        //ihm.InitFenetrePrincipale(grille);
        //jeu.Jeu();
        grille.setEtat(15, Etat.INONDEE);
        
        
        //tests anas
        ihm.InitFenetreDepart();
    }
    
}
