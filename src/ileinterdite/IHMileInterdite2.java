package ileinterdite;

import Roles.Explorateur;
import Roles.Ingenieur;
import Roles.Messager;
import Roles.Navigateur;
import Roles.Pilote;
import Roles.Plongeur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    
    private ActionListener a; 
       
    //boutons

    private JButton Assechement = new JButton("Assechement");
    private JButton Deplacement = new JButton("Deplacement");
    private JButton Recuparation = new JButton("Récuperation");

    //grille

    private Grille grille;
    private Case[] cases;
    private JPanel panelCentre = new JPanel(new GridLayout(6,6));
    
    private ArrayList<Tuile> tuilesDeplacement = new ArrayList<>();
     Tuile tTemp;
     
     boolean checkClicCase =true;

      //Options

    private JPanel status = new JPanel();
    private JPanel panelOptions = new JPanel(new BorderLayout());
    private JLabel joueurCourant = new JLabel();
    private JLabel actionCourante = new JLabel();
    private boolean deplacementPossible = true;
    private boolean clikDeplacement;
    private Tuile tuileDeplacementChoisi;
   

        
       

// joueurs generés


    private Joueur[] joueurs ;
    private String NomJoueur1="Joueur 1";
    private String NomJoueur2="Joueur 2";
    private String NomJoueur3="Joueur 3";
    private String NomJoueur4="Joueur 4";
    private String nbJoueursString;
    private String nomJoueur;
    private ArrayList<String> nomsjoueurs = new ArrayList<>();
    private ArrayList<Joueur> joueurs_crees = new ArrayList<>();
        
    private Integer nbJoueursChoisis;

    
    private Utils.Pion pionChoisi; 
    private Aventurier roleChoisi;
    private ArrayList<String> roles = new ArrayList<>();
    private ArrayList<Utils.Pion> pions = new ArrayList<>();
    //private Utils.Pion[] pions = new Utils.Pion[6-pions_utilises.size()];
    //private Aventurier[] roles = new Aventurier[6-roles_utilises.size()];
    
    
    
   //=============================================================================================
   //POP UP INGENIEUR
    private JFrame PopUpIngenieur= new JFrame("Voulez vous assécher une autre tuile?");
    
    
    
    public void PopUpIngenieur () {
        PopUpIngenieur.setLayout(new BorderLayout());
        JPanel panelPrincipal = new JPanel(new GridLayout(1,2));
        JButton OuiPU= new JButton("Oui");
        JButton NonPU= new JButton("Non");
        PopUpIngenieur.add(panelPrincipal);
        PopUpIngenieur.setResizable(false);
        
        panelPrincipal.add(OuiPU);
        panelPrincipal.add(NonPU);
        
        
        
        //Partie superieure : Label
        
        panelPrincipal.setVisible(true);
        PopUpIngenieur.setVisible(true);
        PopUpIngenieur.setSize(400,100);
        
        OuiPU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Message2 m = new Message2();
                m.type = TypesMessage.OUI_2EME_ASSECHAGE;
                observateur.traiterMessage(m);
            }
        });
        
        NonPU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Message2 m = new Message2();
                m.type = TypesMessage.NON_2EME_ASSECHAGE;
                observateur.traiterMessage(m);
            }
        });
    }
        
    
    
    
    
    


    public IHMileInterdite2(){

        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(IHMileInterdite2.class.getName()).log(Level.SEVERE, null, ex);
        }
        setRoles();
        setPions();
        
        InitFenetreDepart();
    }
    
     public void setObservateur(Observateur observateur){
        this.observateur = observateur;  
     }
     
     public void setRoles(){
        roles.add("ingénieur");
        roles.add("explorateur");
        roles.add("messager");
        roles.add("plongeur");
        roles.add("pilote");
        roles.add("navigateur");
   
     }
     
     public void setPions(){
        pions.add(Utils.Pion.BLEU);
        pions.add(Utils.Pion.VERT);
        pions.add(Utils.Pion.JAUNE);
        pions.add(Utils.Pion.ORANGE);
        pions.add(Utils.Pion.ROUGE);
        pions.add(Utils.Pion.VIOLET);
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
        
        
            Deplacement.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (deplacementPossible){
                    
                        Message2 m = new Message2();
                        m.type = TypesMessage.MOUVEMENT;
                        observateur.traiterMessage(m);
                        clikDeplacement = true;
                        
                    
                
                }else{
                        actionCourante.setText("Déplacement Impossible");
                    } 
                }
            });
        
        
        
        
        cases = new Case[24];

        int a = 0;
        
            for(int i = 0; i<grille.getTuiles().length; i++){
              if(grille.getTuile(i).getNom() != null){
                cases[a] = new Case((grille.getTuile(i).getNom())+(grille.getTuile(i).getNumero()));
                cases[a].setTuileAssocié(grille.getTuile(i));
                
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


        
        
    }
    
    public void setTuilesDeplacement (ArrayList<Tuile> tuiles){
        tuilesDeplacement = tuiles;
        
    }
                      
   public void majGrille(Grille grille){
       int a = 0 ;
       this.grille = grille; 
       
           
       
       for (int i = 0;i< grille.getTuiles().length; i++){
           if (grille.getTuile(i).getEtat() == Etat.DISPARUE){
               cases[a].setBackground( new Color(0,0,55));
               a=a+1;
           }
           else if (grille.getTuile(i).getEtat() == Etat.INONDEE){
               cases[a].setBackground( new Color(0,0,155));
                              cases[a].setText("<html>"+grille.getTuile(i).getNom()+"<br>"+grille.getTuile(i).getPionsPrésentsAffichage()+"<html />");

               a=a+1;
           }
           else if (grille.getTuile(i).getEtat() == Etat.ASSECHEE){
               cases[a].setBackground( Color.MAGENTA);
               cases[a].setText("<html>"+grille.getTuile(i).getNom()+"<br>"+grille.getTuile(i).getPionsPrésentsAffichage()+"<html />");
               
               // Affiche les Cases ou peut se déplcaer le joueur
               
               a=a+1;

           }
       }
   }
   
   public void choixDeplacement(ArrayList<Tuile> tuilesPossibles){
       
       a = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(checkClicCase){
               Case t = (Case) e.getSource();
               
                                   tTemp = t.getTuileAssocie();
                                   //System.out.println(tTemp.getNom());
                                   majGrille(grille);
                                   tuileDeplacementChoisi = t.getTuileAssocie();
                                   Message2 m = new Message2();
                                    m.type = TypesMessage.VALIDER_MOUVEMENT;
                                    System.out.println(tuileDeplacementChoisi.getNom()+" tuile choisie");
                                    m.setTuileChoisie(tuileDeplacementChoisi);
                                    observateur.traiterMessage(m);
                                    deleteActionMouv();
                                    t.removeActionListener(this);
                                   
                                   checkClicCase = false; 
           }}
       };
       JButton b = new JButton();
       
       for (int i = 0; i< grille.getTuiles().length; i++){
               for (int j = 0; j<tuilesPossibles.size(); j++){
                   
                   if(tuilesPossibles.get(j).getNumero() == grille.getTuile(i).getNumero()){
                       
                       if (i<=3){
                           
                           cases[tuilesPossibles.get(j).getNumero()-2].setBackground(Color.green);
                           cases[tuilesPossibles.get(j).getNumero()-2].addActionListener(a);
                             
                       } else if (i>=7 && i<=10){
                           cases[tuilesPossibles.get(j).getNumero()-5].setBackground(Color.green);
                           cases[tuilesPossibles.get(j).getNumero()-5].addActionListener(a);
                       } else if (i>=12 && i<=23){
                           cases[tuilesPossibles.get(j).getNumero()-6].setBackground(Color.green);
                           cases[tuilesPossibles.get(j).getNumero()-6].addActionListener(a);
                       } else if(i>=25 && i<=28){
                           cases[tuilesPossibles.get(j).getNumero()-7].setBackground(Color.green);
                           cases[tuilesPossibles.get(j).getNumero()-7].addActionListener(a);
                       } else if (i>=32 && i<=33){
                           cases[tuilesPossibles.get(j).getNumero()-10].setBackground(Color.green);
                           cases[tuilesPossibles.get(j).getNumero()-10].addActionListener(a);
                       }
                       
          
                       
                   }
                 
               }
               
       }
       
       
      
      
       
       
       
   }
   
   public void deleteActionMouv(){
       for(int i = 0; i<cases.length; i++){
           cases[i].removeActionListener(a);
           System.out.println("delete");
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

          
          //champs de texte pour le nom
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
                
            }
        };
        
        
        TextField_joueur1.addCaretListener(caretupdate1);

         CaretListener caretupdate2 = new CaretListener() {
            @Override
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                NomJoueur2=TextField_joueur2.getText();
                //nomsjoueurs.add(NomJoueur2);
            }
        };
        TextField_joueur2.addCaretListener(caretupdate2);


         CaretListener caretupdate3 = new CaretListener() {
            @Override
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                NomJoueur3=TextField_joueur3.getText();
                //nomsjoueurs.add(NomJoueur3);
            }
        };
        TextField_joueur3.addCaretListener(caretupdate3);

         CaretListener caretupdate4 = new CaretListener() {
            @Override
            public void caretUpdate(javax.swing.event.CaretEvent e) {
                JTextField text = (JTextField)e.getSource();
                NomJoueur4=TextField_joueur4.getText();
                //nomsjoueurs.add(NomJoueur4);
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
                    
                    //ajout des joueurs
                    switch (nbJoueursChoisis){
                        case 2 :
                            nomsjoueurs.add(NomJoueur1);
                            nomsjoueurs.add(NomJoueur2);
                        break;
                        case 3 :
                            nomsjoueurs.add(NomJoueur1);
                            nomsjoueurs.add(NomJoueur2);
                            nomsjoueurs.add(NomJoueur3);
                        break;
                        case 4 :
                            nomsjoueurs.add(NomJoueur1);
                            nomsjoueurs.add(NomJoueur2);
                            nomsjoueurs.add(NomJoueur3);
                            nomsjoueurs.add(NomJoueur4);
                        break;
                    }
                    fenetreChoixJoueur(1, nbJoueursChoisis);
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

 }
   
        
        
    public void fenetreChoixJoueur(int numJoueur, int nbJoueurs){
        nbJoueurs=nbJoueursChoisis;
        

        windowRoles = new JFrame("Choix du Role de :"+nomsjoueurs.get(numJoueur-1));
        windowRoles.setLayout(new BorderLayout());
        
        
        windowRoles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowRoles.setVisible(true);
        windowRoles.setSize(400, 500);
        windowRoles.setResizable(false);
        
        
        JButton aleatoire = new JButton("Aléatoire");
        //JButton choix = new JButton("Choix");
        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");

        JComboBox choixRole = new JComboBox();
        for(String role : roles){
            choixRole.addItem(role);
        }
        
        choixRole.setPreferredSize(new Dimension(100, 20));
        
        JComboBox choixPion = new JComboBox(pions.toArray());
        
        choixPion.setPreferredSize(new Dimension(100, 20));

        
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelCentre = new JPanel (new GridLayout(4,2));
        JPanel panelHaut = new JPanel(new GridLayout(2,3));
        JPanel panelBas = new JPanel(new GridLayout(2,5));
        panelPrincipal.add(panelHaut, BorderLayout.NORTH);
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
        panelPrincipal.add(panelBas, BorderLayout.SOUTH);
        
        for (int i = 0; i <6; i++){
            if(i ==4){
                panelHaut.add(aleatoire);
            
            }else{
                panelHaut.add(new JLabel());
            }
        }

        for (int i = 0; i<8; i++){
            if(i == 2){
                panelCentre.add(new JLabel("Role Joueur : "));
            }else if (i == 4){
                panelCentre.add(new JLabel("Pion : "));
            }else if(i == 3){
                panelCentre.add(choixRole);
            }else if(i == 5){
                panelCentre.add(choixPion);
            }else {
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
        windowRoles.add(panelPrincipal);
        
        if (numJoueur<nbJoueurs){
            aleatoire.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nomJoueur = nomsjoueurs.get(numJoueur-1);
                    String role_temp;
                    Collections.shuffle(roles);
                    Collections.shuffle(pions);
                    
                    role_temp=roles.get(1);
                    pionChoisi=pions.get(1);
                    switch (role_temp){
                        case"explorateur":
                            roleChoisi= new Explorateur();
                        break;
                        
                        case"ingénieur":
                            roleChoisi= new Ingenieur();
                        break;
                        
                        case"messager":
                            roleChoisi= new Messager();
                        break;
                        
                        case"navigateur":
                            roleChoisi= new Navigateur();
                        break;
                        
                        case"pilote":
                            roleChoisi= new Pilote();
                        break;
                        
                        case"plongeur":
                            roleChoisi= new Plongeur();
                        break;
                    }
                    
                    roles.remove(roleChoisi.getRole());
                    pions.remove(pionChoisi);
                    
                    joueurs[numJoueur-1] = new Joueur(nomJoueur,roleChoisi, pionChoisi);
                    windowRoles.dispose();
                    System.out.println(joueurs[numJoueur-1].getNom()+joueurs[numJoueur-1].getAventurier()+joueurs[numJoueur-1].getPion());
                    joueurs_crees.add(joueurs[numJoueur-1]);
                    //windowRoles.dispose();
                    fenetreChoixJoueur(numJoueur+1, nbJoueursChoisis);
                    
                }
            });
        }else{
            aleatoire.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nomJoueur = nomsjoueurs.get(numJoueur-1);
                    String role_temp;
                    Collections.shuffle(roles);
                    Collections.shuffle(pions);
                    
                    role_temp=roles.get(1);
                    pionChoisi=pions.get(1);
                    switch (role_temp){
                        case"explorateur":
                            roleChoisi= new Explorateur();
                        break;
                        
                        case"ingénieur":
                            roleChoisi= new Ingenieur();
                        break;
                        
                        case"messager":
                            roleChoisi= new Messager();
                        break;
                        
                        case"navigateur":
                            roleChoisi= new Navigateur();
                        break;
                        
                        case"pilote":
                            roleChoisi= new Pilote();
                        break;
                        
                        case"plongeur":
                            roleChoisi= new Plongeur();
                        break;
                    }
                    
                    roles.remove(roleChoisi.getRole());
                    pions.remove(pionChoisi);
                    
                    joueurs[numJoueur-1] = new Joueur(nomJoueur,roleChoisi, pionChoisi);
                    
                    joueurs_crees.add(joueurs[numJoueur-1]);
                    windowRoles.dispose();
                    Message2 m = new Message2();
                    m.type = TypesMessage.DEMARRER_PARTIE;
                    m.setJoueurs(joueurs_crees);
                    observateur.traiterMessage(m);
                }
            });
        }
        
         if (numJoueur<nbJoueurs){
            valider.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    nomJoueur = nomsjoueurs.get(numJoueur-1);
                    pionChoisi = (Utils.Pion) choixPion.getSelectedItem();
                    pions.remove(pionChoisi);
                    
                    switch ((String) choixRole.getSelectedItem()){
                        case"explorateur":
                            roleChoisi= new Explorateur();
                        break;
                        
                        case"ingénieur":
                            roleChoisi= new Ingenieur();
                        break;
                        
                        case"messager":
                            roleChoisi= new Messager();
                        break;
                        
                        case"navigateur":
                            roleChoisi= new Navigateur();
                        break;
                        
                        case"pilote":
                            roleChoisi= new Pilote();
                        break;
                        
                        case"plongeur":
                            roleChoisi= new Plongeur();
                        break;
                    }
                    
                    roles.remove(roleChoisi.getRole());

                    joueurs[numJoueur-1] = new Joueur(nomJoueur, roleChoisi, pionChoisi);
                    joueurs_crees.add(joueurs[numJoueur-1]);
                    windowRoles.dispose();
                    fenetreChoixJoueur(numJoueur+1,nbJoueursChoisis);
                    //windowRoles.dispose();
                    
                }
            });
        }else{
            valider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nomJoueur = nomsjoueurs.get(numJoueur-1);
                    pionChoisi = (Utils.Pion) choixPion.getSelectedItem();
                    pions.remove(pionChoisi);
                    
                     switch ((String) choixRole.getSelectedItem()){
                        case"explorateur":
                            roleChoisi= new Explorateur();
                        break;
                        
                        case"ingénieur":
                            roleChoisi= new Ingenieur();
                        break;
                        
                        case"messager":
                            roleChoisi= new Messager();
                        break;
                        
                        case"navigateur":
                            roleChoisi= new Navigateur();
                        break;
                        
                        case"pilote":
                            roleChoisi= new Pilote();
                        break;
                        
                        case"plongeur":
                            roleChoisi= new Plongeur();
                        break;
                    }
                     
                    roles.remove(roleChoisi.getRole());

                    joueurs[numJoueur-1] = new Joueur(nomJoueur, roleChoisi, pionChoisi);
                    joueurs_crees.add(joueurs[numJoueur-1]); 
                    for(Joueur joueur : joueurs_crees){
                        System.out.println(joueur.getNom());
                    }
                    windowRoles.dispose();
                    Message2 m = new Message2();
                    m.type=TypesMessage.DEMARRER_PARTIE;
                    //windowRoles.dispose();
                    m.setJoueurs(joueurs_crees);
                    observateur.traiterMessage(m);
                    
                 }
             });
         }

    }
    
    public Joueur[] getJoueurs() {
        return joueurs;
    }
    
    public void setJourCourant(Joueur joueur){
        joueurCourant.setText(joueur.getNom());
    }
    
    public String getJoueurCourant() {
    return nomJoueur; 
}
}
