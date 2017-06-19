/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import java.awt.BorderLayout;
import java.awt.Color;
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
    private Controleur controleur;
       
        //boutons
        
        private JButton Assechement = new JButton("Assechement");
        private JButton Deplacement = new JButton("Deplacement");
        private JButton Recuparation = new JButton("Récuperation");
        
        //grille
        
        private JPanel panelCentre = new JPanel(new GridLayout(6,6));
        private JButton[] cases;
        
        //Options
        
        private JPanel status = new JPanel();
        private JPanel panelOptions = new JPanel(new BorderLayout());
        private JLabel joueurCourant = new JLabel();
        private JLabel actionCourante = new JLabel();
        
        
        
    
    public IHMileInterdite(){
        
        cases = new JButton[24];
        
    }
        
    public void InitFenetrePrincipale(Grille grille){
        
        windowJeu = new JFrame("Ile Interdite");
        windowJeu.setLayout(new BorderLayout());
        
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
        
        
        
        
        int a = 0;
           
        for(int i = 0; i< grille.getTuiles().length; i++){
            
              if(grille.getTuile(i).getNom() != null){
                cases[a] = new JButton(grille.getTuile(i).getNom());
                cases[a].setName(Integer.toString(a));
                panelCentre.add(cases[a]);
                
                a = a + 1;
            } else {            
                panelCentre.add(new JLabel(""));
               
            }
        }
        
        
        
        
        
        actionCourante.setText("Aucune");
        joueurCourant.setText("Joueur 1");
        status.add(new JLabel("C'est à : "));
        status.add(joueurCourant);
        status.add(new JLabel("Action courante : "));
        status.add(actionCourante); 
        panelOptions.add(status, BorderLayout.WEST);
        windowJeu.setSize(1000,1000);
        windowJeu.add(commandes, BorderLayout.NORTH);
        windowJeu.add(panelCentre, BorderLayout.CENTER);
        windowJeu.add(panelOptions, BorderLayout.SOUTH); 
        
        windowJeu.setVisible(true);
        
    }
    
    public void majGrille(Grille grille){
     
        int a = 0;
        for(int i = 0; i< grille.getTuiles().length; i++){
            
              if(grille.getTuile(i).getNom() != null){
                  if(grille.getTuile(i).getEtat() == Etat.DISPARUE){
                      cases[a].setBackground(new Color(000036));
                      
                  }
                
                a = a+1;
            } else {            
                panelCentre.add(new JLabel(""));
               
            }
        }
        
    }
    
    
    
    
    
}
