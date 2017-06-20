/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;


/**
 *
 * @author lucadeslot
 */
public class IHMileInterdite2 {
    
    
     private JFrame windowStart;
    private JFrame windowJeu;
    private JFrame windowRules;
    private JFrame windowRoles;
    private JPanel commandes;
    private Observateur observateur;
       
        //boutons
        
        private JButton Assechement = new JButton("Assechement");
        private JButton Deplacement = new JButton("Deplacement");
        private JButton Recuparation = new JButton("Récuperation");
        
        //grille
        
        private JButton[] cases;
        private JPanel panelCentre = new JPanel(new GridLayout(6,6));
        
          //Options
        
        private JPanel status = new JPanel();
        private JPanel panelOptions = new JPanel(new BorderLayout());
        private JLabel joueurCourant = new JLabel();
        private JLabel actionCourante = new JLabel();
        
        
        private JLabel joueur = new JLabel();
        
    

// joueurs generés
        
        
    private Joueur[] joueurs;
       
    private String n;
    private Integer j;
    
     
        
        
    public IHMileInterdite2(){
        
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(IHMileInterdite2.class.getName()).log(Level.SEVERE, null, ex);
        }
//        
            //Fenetre de depart
        windowStart = new JFrame("Ile Interdite");
        windowStart.setLayout(new BorderLayout());
        
        //Fenetre choix roles
        windowRoles = new JFrame ("Choix des rôles");
        windowRoles.setLayout(new BorderLayout());
        //Fenetre regles
        windowRules = new JFrame("Règles du jeu");
        windowRules.setLayout(new BorderLayout());
        // Fenetre jeu
        windowJeu = new JFrame("Ile Interdite");
        windowJeu.setLayout(new BorderLayout());
       // InitFenetrePrincipale();
        
        
        
    }
    
    public void setObservateur(Observateur observateur){
        this.observateur = observateur;
    }
        
    public void InitFenetrePrincipale(Grille grille){
        
        
        
        
        //initialisation commandes
        commandes = new JPanel(new GridLayout(5, 1));
        
        for(int i = 0; i<5; i++){
            if (i == 0){
                commandes.add(Deplacement);
            } else if (i == 1){
                commandes.add(Assechement);
            } else if (i == 2){
                commandes.add(Recuparation);
            } else {
                commandes.add(new JLabel(""));
            }
        }
        
        cases = new JButton[24];
        
        System.out.println(grille.getTuile(0));
        
        int a = 0;
        
        
        
        for(int i = 0; i < grille.getTuiles().length; i++){
                      
              if(grille.getTuile(i).getNom() != null){
                cases[a] = new JButton(grille.getTuile(i).getNom());
  
                panelCentre.add(cases[a]);
                a = a+1;
            } else {            
                panelCentre.add(new JLabel(""));
               
            }
        }
        
        
        actionCourante.setText("Aucune");
        joueurCourant.setText("Joueur 1    ");
        status.add(new JLabel("C'est à : "));
        status.add(joueurCourant);
        status.add(new JLabel("Action courante : "));
        status.add(actionCourante); 
        panelOptions.add(status, BorderLayout.WEST);
        windowJeu.setSize(1000,1000);
        windowJeu.add(commandes, BorderLayout.EAST);
        windowJeu.add(panelCentre, BorderLayout.CENTER);
        windowJeu.add(panelOptions, BorderLayout.SOUTH); 
        
        windowJeu.setVisible(true);
        majGrille(grille);
        
        Assechement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message2 m = new Message2();
                
                m.type = TypesMessage.ASSECHER;
                observateur.traiterMessage(m);
                
                
            }
        });
        
        Deplacement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message2 m = new Message2();
               
                m.type = TypesMessage.MOUVEMENT;
                observateur.traiterMessage(m);
                
               
            }
        });
        
        
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
               cases[a].setText("<html>"+grille.getTuile(i).getNom()+"<br>"+grille.getTuile(i).getPionsPrésentsAffichage()+"<html />");
               a=a+1;
               
           }
           System.out.println("alllo");
       }
   }
   
   public void setActionCourante(String action){
       actionCourante.setText(action);
   }
    
    
    
    

  
  
     
        
    
    
     
    public void InitFenetreRegles() { 
        windowStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
    }
    
     
    
    
    public void InitFenetreDepart() {
        
          windowStart.setResizable(false);
          JLabel labelCombo = new JLabel("Nombre de joueurs :");
          JComboBox choixNBJoueur = new JComboBox();
          
          JButton valider = new JButton("Valider");
          JButton quitter = new JButton("Quitter");
          JButton regles = new JButton("Règles");
          
          
          JPanel panelPrincipal = new JPanel(new BorderLayout());
          JPanel panelCentre = new JPanel (new GridLayout(3,3));
          JPanel panelHaut = new JPanel(new GridLayout(2,4));
          JPanel panelInterHaut = new JPanel(new GridLayout(2,4));
          JPanel panelBas = new JPanel(new GridLayout(2,3));
          panelPrincipal.add(panelHaut, BorderLayout.NORTH);
          panelPrincipal.add(panelCentre, BorderLayout.CENTER);
          panelPrincipal.add(panelBas, BorderLayout.SOUTH);
          windowStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
          JTextField joueur1 = new JTextField("Joueur 1");
          joueur1.setColumns(10); 
          JTextField joueur2 = new JTextField("Joueur 2");
          joueur2.setColumns(10); 
          JTextField joueur3 = new JTextField("Joueur 3");
          joueur3.setColumns(10); 
          JTextField joueur4 = new JTextField("Joueur 4");
          joueur4.setColumns(10); 
        
          
          choixNBJoueur.setPreferredSize(new Dimension(100, 20));
          windowStart.setSize(600, 300);
          windowStart.setContentPane(panelPrincipal);
          windowStart.setVisible(true);
          
          // Partie superieure de la fenetre
          panelHaut.add(panelInterHaut,BorderLayout.CENTER);
          panelInterHaut.add(new JLabel());
          panelInterHaut.add(new JLabel());
          panelInterHaut.add(new JLabel());
          panelInterHaut.add(new JLabel());
          panelInterHaut.add(new JLabel());
          panelInterHaut.add(labelCombo);
          panelInterHaut.add(choixNBJoueur);
          panelInterHaut.add(new JLabel());
          
          // Partie centre de la fenetre
          panelCentre.add(new JLabel());
          panelCentre.add(new JLabel("Saisissez les nom des joueurs"));
          panelCentre.add(new JLabel());
          panelCentre.add(joueur1);
          panelCentre.add(joueur2);
          panelCentre.add(joueur3);joueur3.setEnabled(false);
          panelCentre.add(joueur4);joueur4.setEnabled(false);
         
          
          // Partie inferieure de la fenetre
          panelBas.add(new JLabel());
          panelBas.add(new JLabel());
          panelBas.add(new JLabel());
          panelBas.add(new JLabel());
          panelBas.add(new JLabel());
          panelBas.add(new JLabel());
          panelBas.add(new JLabel());
          panelBas.add(new JLabel());
          panelBas.add(new JLabel());
          panelBas.add(valider);
          panelBas.add(quitter);
          panelBas.add(regles);
          panelBas.add(new JLabel());
          panelBas.add(new JLabel());
        
          
         // listener du bouton quitter
          quitter.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
                  if(e.getSource()==quitter){
                   windowStart.setVisible(false);
                   windowStart.dispose();
                   System.exit(0);
                    }
              }
                  
              
          // listener du bouton valider    
          });
          valider.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
                  windowStart.setVisible(false);
                  n = choixNBJoueur.getSelectedItem().toString();
                  j = Integer.parseUnsignedInt(n);
                  for (int i =0; i <= j;i++) {
                      CreeJoueur(choixNBJoueur.getSelectedItem().toString(),i);
                  }
                  InitFenetreRoles();
                  
              }
              
          });
          // Listener du bouton regles
           regles.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  windowRules.setVisible(true);  
              } 
          });
          
           choixNBJoueur.setPreferredSize(new Dimension(100, 20));
           choixNBJoueur.addItem("2");
           choixNBJoueur.addItem("3");
           choixNBJoueur.addItem("4");
       
           
    choixNBJoueur.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
                  if (choixNBJoueur.getSelectedItem()=="2") {
               joueur1.setEnabled(true);
               joueur2.setEnabled(true);
               joueur3.setEnabled(false);
               joueur4.setEnabled(false);
           } else if (choixNBJoueur.getSelectedItem()=="3") {
               joueur1.setEnabled(true);
               joueur2.setEnabled(true);
               joueur3.setEnabled(true);
               joueur4.setEnabled(false);
           } else {
               joueur1.setEnabled(true);
               joueur2.setEnabled(true);
               joueur3.setEnabled(true);
               joueur4.setEnabled(true);  
            }    
              }
          });
    
    
    
    // FENETRE CHOIX DES ROLES 
    //
    //
    //
    //
        
        

    
    }
    
    public void InitFenetreRoles() { 
        windowRoles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowRoles.setVisible(true);
        windowRoles.setSize(400, 500);
        windowRoles.setResizable(false);
        
        JButton aleatoire = new JButton("Aléatoire");
        JButton choix = new JButton("Choix");
        //Parametrage comboBox
        JComboBox choixRole = new JComboBox();
        choixRole.setPreferredSize(new Dimension(100, 20));
           choixRole.addItem("Ingénieur");
           choixRole.addItem("Messager");
           choixRole.addItem("Navigateur");
           choixRole.addItem("Pilote");
           choixRole.addItem("Plongeur");
        
           
           
           
           ArrayList<JLabel> joueursLab= new ArrayList();
           for (int i =0; i <= j;i++) {
                joueursLab.get(i).setText(joueurs[i]+" : ");
                 }
           
    
        
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelCentre = new JPanel (new GridLayout(2,4));
        JPanel panelHaut = new JPanel(new GridLayout(2,2));
        JPanel panelBas = new JPanel(new GridLayout(2,2));
        panelPrincipal.add(panelHaut, BorderLayout.NORTH);
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
        panelPrincipal.add(panelBas, BorderLayout.SOUTH);
        
        
        // Partie superieure de la fenetre
        panelHaut.add(new JLabel());
        panelHaut.add(new JLabel());
        panelHaut.add(new JLabel());
        panelHaut.add(new JLabel());
        panelHaut.add(new JLabel());
        panelHaut.add(aleatoire);
        panelHaut.add(choix);
        panelHaut.add(new JLabel());
        
       
        
        //Partie centrale de la fenetre
         
            
           
                // panelCentre.add(joueursLab.get(1));
                 //panelCentre.add(choixRole);
                  
       
        
        
        //Partie inferieure de la fenetre
        
        
        
        
        
         // listener du bouton aleatoire
          aleatoire.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
                  
                    }
              });
          
           // listener du bouton choix
          choix.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
                  
                    }
              });
          
        
        
        
        
        windowRoles.add(panelPrincipal);
        
    
       
    } 
    
    
  public void CreeJoueur(String nom, int num) {
      String nomj = nom;
      joueurs= new Joueur[4];
      joueurs[num] = new Joueur(nom,null,null);
  }  
  
}
