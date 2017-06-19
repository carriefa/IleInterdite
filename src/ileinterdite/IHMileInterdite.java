/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lucadeslot
 */
public class IHMileInterdite {
    
    //fenêtre principal
    
    private JFrame windowJeu;
    private JPanel commandes;
       
        //boutons
        
        private JButton Assechement = new JButton("Assechement");
        private JButton Deplacement = new JButton("Deplacement");
        private JButton Recuparation = new JButton("Récuperation");
        
        //grille
        
        private JButton[] cases;
        
    
    
    public IHMileInterdite() {
        
        windowJeu = new JFrame("Ile Interdite");
        windowJeu.setLayout(new BorderLayout());
        InitFenetrePrincipale();
        
        
    }
    
        
    public void InitFenetrePrincipale(){
        
        //initialisation commandes
        commandes = new JPanel(new GridLayout(2, 6));
        
        for(int i = 0; i<12; i++){
            if (i == 0){
                commandes.add(Deplacement);
            } else if (i == 1){
                commandes.add(Assechement);
            } else if (i == 6){
                commandes.add(Recuparation);
            } else {
                commandes.add(new JLabel(""));
            }
        }
        
        cases = new JButton[35];
        
        for(int i = 0; i<36; i++){
            if ( 
        }
        
        
        
        windowJeu.add(commandes, BorderLayout.NORTH);
        
        
        windowJeu.setSize(1000,1000);
        windowJeu.setVisible(true);
        
    }
    
    
    
    
}
