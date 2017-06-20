/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import Roles.Explorateur;
import Roles.Ingenieur;
import Roles.Messager;
import Roles.Navigateur;
import Roles.Pilote;
import Roles.Plongeur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.CaretListener;
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


    private Joueur[] joueurs ;
    private String NomJoueur1="Joueur 1";
    private String NomJoueur2="Joueur 2";
    private String NomJoueur3="Joueur 3";
    private String NomJoueur4="Joueur 4";
    private String nbJoueursString;
    private ArrayList<String> nomsjoueurs = new ArrayList<>();
        
    private Integer nbJoueursChoisis;

    
    private Utils.Pion pionChoisi; 
    private Aventurier roleChoisi;
    private Aventurier[] roles = new Aventurier[6];
    private Utils.Pion[] pions = new Utils.Pion[6];
    private int controle_boucle_role =1;
    


    public IHMileInterdite2(){

        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(IHMileInterdite2.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("bebelelebl");
        
    }
    
     public void setObservateur(Observateur observateur){
        this.observateur = observateur;  
     }

    public void InitFenetrePrincipale(Grille grille){
        
        // Fenetre jeu
        windowJeu = new JFrame("Ile Interdite");
        windowJeu.setLayout(new BorderLayout());

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

        int a = 0;
        
            for(int i = 0; i<grille.getTuiles().length; i++){
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
        //Fenetre regles
        windowRules = new JFrame("Règles du jeu");
        windowRules.setLayout(new BorderLayout());
    }
    
    public void InitFenetreDepart() {
        
       
            //Fenetre de depart
        windowStart = new JFrame("Ile Interdite");
        windowStart.setLayout(new BorderLayout());
        
       // InitFenetrePrincipale();

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

          
          JTextField TextField_joueur1 = new JTextField("Joueur 1");
          TextField_joueur1.setColumns(10);
          JTextField TextField_joueur2 = new JTextField("Joueur 2");
          TextField_joueur2.setColumns(10);
          JTextField TextField_joueur3 = new JTextField("Joueur 3");
          TextField_joueur3.setColumns(10);
          JTextField TextField_joueur4 = new JTextField("Joueur 4");
          TextField_joueur4.setColumns(10);

          
          
          
          CaretListener caretupdate1 = new CaretListener() {
            @Override
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                NomJoueur1=TextField_joueur1.getText();
                nomsjoueurs.add(NomJoueur1);
            }
        };

        TextField_joueur1.addCaretListener(caretupdate1);

         CaretListener caretupdate2 = new CaretListener() {
            @Override
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                NomJoueur2=TextField_joueur2.getText();
                nomsjoueurs.add(NomJoueur2);
            }
        };
        TextField_joueur2.addCaretListener(caretupdate2);


         CaretListener caretupdate3 = new CaretListener() {
            @Override
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                NomJoueur3=TextField_joueur3.getText();
                nomsjoueurs.add(NomJoueur3);
            }
        };
        TextField_joueur3.addCaretListener(caretupdate3);

         CaretListener caretupdate4 = new CaretListener() {
            @Override
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                NomJoueur4=TextField_joueur4.getText();
                nomsjoueurs.add(NomJoueur4);
            }
        };
        TextField_joueur4.addCaretListener(caretupdate4);
        
        





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
          panelCentre.add(TextField_joueur1);
          panelCentre.add(TextField_joueur2);
          panelCentre.add(TextField_joueur3);TextField_joueur3.setEnabled(false);
          panelCentre.add(TextField_joueur4);TextField_joueur4.setEnabled(false);


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
          });
          
          // listener bouton valider
          valider.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
                  windowStart.setVisible(false);
                  nbJoueursString = choixNBJoueur.getSelectedItem().toString();
                  nbJoueursChoisis = Integer.parseUnsignedInt(nbJoueursString);
                  joueurs = new Joueur[nbJoueursChoisis];
                  if (nbJoueursChoisis==2){
                      Message2 m = new Message2();
                      m.type=TypesMessage.CHOIXJ1;
                      m.type=TypesMessage.CHOIXJ2; 
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
               TextField_joueur1.setEnabled(true);
               TextField_joueur2.setEnabled(true);
               TextField_joueur3.setEnabled(false);
               TextField_joueur4.setEnabled(false);
           } else if (choixNBJoueur.getSelectedItem()=="3") {
               TextField_joueur1.setEnabled(true);
               TextField_joueur2.setEnabled(true);
               TextField_joueur3.setEnabled(true);
               TextField_joueur4.setEnabled(false);
           } else {
               TextField_joueur1.setEnabled(true);
               TextField_joueur2.setEnabled(true);
               TextField_joueur3.setEnabled(true);
               TextField_joueur4.setEnabled(true);
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
        

        //Fenetre choix roles
        windowRoles = new JFrame("Choix Role");
        windowRoles.setLayout(new BorderLayout());
        
        
        
        
        windowRoles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowRoles.setVisible(true);
        windowRoles.setSize(400, 500);
        windowRoles.setResizable(false);
        roles[0]= new Ingenieur();
        roles[1]= new Explorateur();
        roles[2]= new Messager();
        roles[3]= new Plongeur();
        roles[4]= new Pilote();
        roles[5]= new Navigateur();
        
        pions[0] = Utils.Pion.VERT;
        pions[1] = Utils.Pion.BLEU;
        pions[2] = Utils.Pion.ROUGE;
        pions[3] = Utils.Pion.JAUNE;
        pions[4] = Utils.Pion.ORANGE;
        pions[5] = Utils.Pion.VIOLET;
        
        
        
        JButton aleatoire = new JButton("Aléatoire");
        JButton choix = new JButton("Choix");
        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        
        JLabel LabelJoueur1 = new JLabel();
        JLabel LabelJoueur2 = new JLabel();
        JLabel LabelJoueur3 = new JLabel();
        JLabel LabelJoueur4 = new JLabel();
        
        
        JComboBox choixRole = new JComboBox(roles);
        choixRole.setPreferredSize(new Dimension(100, 20));
           choixRole.addItem( roles[0]);
           choixRole.addItem("Messager");
           choixRole.addItem("Navigateur");
           choixRole.addItem("Pilote");
           choixRole.addItem("Plongeur");
        JComboBox choixPion = new JComboBox(pions);
        choixPion.setPreferredSize(new Dimension(100, 20));
           choixPion.addItem("Rouge");
           choixPion.addItem("Vert");
           choixPion.addItem("Bleu");
           choixPion.addItem("Orange");
           choixPion.addItem("Violet");

       

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelCentre = new JPanel (new GridLayout(4,2));
        JPanel panelHaut = new JPanel(new GridLayout(2,5));
        JPanel panelBas = new JPanel(new GridLayout(2,5));
        panelPrincipal.add(panelHaut, BorderLayout.NORTH);
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
        panelPrincipal.add(panelBas, BorderLayout.SOUTH);
        windowRoles.add(panelPrincipal);
        
        
       for (int i = 0; i <10; i++){
           if(i ==6){
               panelHaut.add(aleatoire);
           } else if(i == 8){
               panelHaut.add(choix);
           } else {
               panelHaut.add(new JLabel());
           }
       }
       
       for (int i = 0; i<8; i++){
            if(i == 2){
               panelCentre.add(new JLabel("Role Joueur : "));
           }else if (i == 4){
               panelCentre.add(new JLabel("Pion : "));
           }
            else if(i == 3){
               panelCentre.add(choixRole);
           }else if(i == 5){
               panelCentre.add(choixPion);
           }
           else {
               panelCentre.add(new JLabel());
           }
       }
        for (int i = 0; i <10; i++){
           if(i == 1){
               panelBas.add(valider);
           } else if(i == 3){
               panelBas.add(annuler);
           } else {
               panelBas.add(new JLabel());
           }
       }
        for(String nom : nomsjoueurs){
            System.out.println(nom);
        }
            boolean bool = true;
          while (controle_boucle_role<=nbJoueursChoisis && bool){//controle_boucle_role = 1
                windowRoles = new JFrame ("Choix du rôle & pion du joueur "+nomsjoueurs.get(controle_boucle_role-1));
                
                valider.addActionListener(new ActionListener(){

                    @Override
                      public void actionPerformed(ActionEvent e) {
                          pionChoisi = (Utils.Pion) choixPion.getSelectedItem();
                          roleChoisi = (Aventurier) choixRole.getSelectedItem();
                          joueurs[controle_boucle_role-1] = new Joueur(nomsjoueurs.get(controle_boucle_role-1), roleChoisi, pionChoisi);
                          Message2 m = new Message2();
                          m.type = TypesMessage.AUTREACTION;
                          observateur.traiterMessage(m);
                          System.out.println(pionChoisi);
                          System.out.println(roleChoisi);
                          controle_boucle_role=controle_boucle_role+1;
                      }
                });
                

          }
        
                
        }
        public void fenetreChoixJoueur(int numJoueur){
            windowRoles = new JFrame("Choix Role");
        windowRoles.setLayout(new BorderLayout());
        
        
        
        
        windowRoles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowRoles.setVisible(true);
        windowRoles.setSize(400, 500);
        windowRoles.setResizable(false);
        roles[0]= new Ingenieur();
        roles[1]= new Explorateur();
        roles[2]= new Messager();
        roles[3]= new Plongeur();
        roles[4]= new Pilote();
        roles[5]= new Navigateur();
        
        pions[0] = Utils.Pion.VERT;
        pions[1] = Utils.Pion.BLEU;
        pions[2] = Utils.Pion.ROUGE;
        pions[3] = Utils.Pion.JAUNE;
        pions[4] = Utils.Pion.ORANGE;
        pions[5] = Utils.Pion.VIOLET;
        
        JButton aleatoire = new JButton("Aléatoire");
        JButton choix = new JButton("Choix");
        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        
        JLabel LabelJoueur1 = new JLabel();
        JLabel LabelJoueur2 = new JLabel();
        JLabel LabelJoueur3 = new JLabel();
        JLabel LabelJoueur4 = new JLabel();
        
        
        JComboBox choixRole = new JComboBox(roles);
        choixRole.setPreferredSize(new Dimension(100, 20));
           choixRole.addItem( roles[0].getRole());
           choixRole.addItem("Messager");
           choixRole.addItem("Navigateur");
           choixRole.addItem("Pilote");
           choixRole.addItem("Plongeur");
        JComboBox choixPion = new JComboBox(pions);
        choixPion.setPreferredSize(new Dimension(100, 20));
           choixPion.addItem("Rouge");
           choixPion.addItem("Vert");
           choixPion.addItem("Bleu");
           choixPion.addItem("Orange");
           choixPion.addItem("Violet");

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelCentre = new JPanel (new GridLayout(4,2));
        JPanel panelHaut = new JPanel(new GridLayout(2,5));
        JPanel panelBas = new JPanel(new GridLayout(2,5));
        panelPrincipal.add(panelHaut, BorderLayout.NORTH);
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
        panelPrincipal.add(panelBas, BorderLayout.SOUTH);
        windowRoles.add(panelPrincipal);
        
        
       for (int i = 0; i <10; i++){
           if(i ==6){
               panelHaut.add(aleatoire);
           } else if(i == 8){
               panelHaut.add(choix);
           } else {
               panelHaut.add(new JLabel());
           }
       }
       
       for (int i = 0; i<8; i++){
            if(i == 2){
               panelCentre.add(new JLabel("Role Joueur : "));
           }else if (i == 4){
               panelCentre.add(new JLabel("Pion : "));
           }
            else if(i == 3){
               panelCentre.add(choixRole);
           }else if(i == 5){
               panelCentre.add(choixPion);
           }
           else {
               panelCentre.add(new JLabel());
           }
       }
        for (int i = 0; i <10; i++){
           if(i == 1){
               panelBas.add(valider);
           } else if(i == 3){
               panelBas.add(annuler);
           } else {
               panelBas.add(new JLabel());
           }
       }
        }
        //Parametrage comboBox
        


        // Partie superieure de la fenetre
        

//
//        //Partie centrale de la fenetre
//
//

         
          
        
        
        
        
        
    
       
     
    
    
 // public void CreeJoueur(String nom, int num) {

   //   joueurs[num-1] = new Joueur(nom,null,null);
  //}

    /**
     * @return the joueurs
     */
    public Joueur[] getJoueurs() {
        return joueurs;
    }


}    
        
 
    
