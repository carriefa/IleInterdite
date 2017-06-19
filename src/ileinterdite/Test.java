/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import ileinterdite.Utils.Pion;

public class Test {

    public static void main(String[] args) {
        // TODO code application logic here
        Grille grille = new Grille();
        grille.setEtat(16, Etat.DISPARUE);       
        IHMileInterdite2 ihm = new IHMileInterdite2();
        ihm.InitFenetrePrincipale(grille);
        grille.setEtat(15, Etat.INONDEE);    
    }
    
}
