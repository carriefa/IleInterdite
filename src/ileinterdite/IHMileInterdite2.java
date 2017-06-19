/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;


/**
 *
 * @author lucadeslot
 */
public class IHMileInterdite2 {
    
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
        Grille grille ;
        
        
        
    
    public IHMileInterdite2(){
        System.out.println("Normal");
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(IHMileInterdite2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    public void InitFenetrePrincipale(Grille grille){
        
        this.grille = grille ;
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
        Deplacement.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                majGrille(grille);
            }
        });
        cases = new JButton[24];
        
        System.out.println(grille.getTuile(0));
        
        int a = 0;
        
        
        
        for(int i = 0; i< grille.getTuiles().length; i++){
            
            
            
              if(grille.getTuile(i).getNom() != null){
                cases[a] = new JButton(grille.getTuile(i).getNom());
                  System.out.println(grille.getTuile(i).getNom());
                
                panelCentre.add(cases[a]);
                a = a+1;
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
        majGrille(grille);
} 
    
   public void majGrille(Grille grille){
       int a = 0 ;
       for (int i = 0;i< grille.getTuiles().length; i++){
           if (grille.getTuile(i).getEtat() == Etat.DISPARUE){
               cases[a].setBackground( new Color(0,0,55));
               a=a+1;
           } 
           else if (grille.getTuile(i).getEtat() == Etat.INONDEE){
               cases[a].setBackground( new Color(0,0,155));
               a=a+1;
           }
           else if (grille.getTuile(i).getEtat() == Etat.ASSECHEE){
               cases[a].setBackground( Color.MAGENTA);
               a=a+1;
           }
       }
   }
    
    
    
    
}
