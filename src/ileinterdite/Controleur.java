/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import static ileinterdite.TypesMessage.DEMARRER_PARTIE;
import java.util.ArrayList;

/**
 *
 * @author carriefa
 */
public class Controleur implements Observateur {

    private JeuIleInterdite jeu ;
    private IHMileInterdite2 ihm ;
    private Grille grille; 
    private ArrayList<Joueur> joueurs ;
    private Joueur joueur_courant ;
    private Joueur[] joueursTab;
    private int numjoueurcourant;
    private int nummax;
    
    public Controleur(){
        
            jeu = new JeuIleInterdite();
            ihm = new IHMileInterdite2();
            ihm.setObservateur(this);
            jeu.setObservateur(this);
            grille = jeu.getGrille();
            joueurs = new ArrayList();
            
    }
    @Override
    public void traiterMessage(Message2 msg) {
        System.out.println(msg.type.name());
        jeu.VerifGagnéPerdu();
        switch(msg.getType()) {
            
            // Partie IHM
            case DEMARRER_PARTIE:
                joueurs  = msg.getJoueurs();  // Validé 
                jeu.setJoueurs(joueurs);
                ihm.InitFenetrePrincipale(grille);
                joueursTab = new Joueur[joueurs.size()];
                for (int i = 0; i<joueurs.size(); i++){
                    joueursTab[i] = joueurs.get(i);
                }
             //jeu.initCartesJoueurs();
             nummax = joueursTab.length - 1;
             majJeu();
             joueur_courant = joueursTab[0];
             jeu.setJoueurCourant(joueur_courant);
             jeu.TourJoueur(joueur_courant);
             numjoueurcourant = 0 ;
            break;
            case MOUVEMENT:
                
               ihm.setActionCourante("mouvement");
               ihm.choixDeplacement(jeu.getGrille().getTuilesDeplacement(jeu.getJoueurCourant()));
                
            break;
                
            case VALIDER_MOUVEMENT:
                
                Tuile tuilechoisie =  msg.getTuileChoisie();
                jeu.getJoueurCourant().getAventurier().Deplacement(tuilechoisie);
                jeu.UtiliserAction();

                majJeu();
                break;
            case ASSECHER: 
                ihm.setActionCourante("assecher");
           
                if(jeu.getJoueurCourant().getAventurier().getRole().equals("ingénieur")){ //si c'est un ingénieur
                    if(jeu.getJoueurCourant().getAventurier().getControleAssechable()==1){ //si c'est la premiere fois qu'il asseche.
                        ihm.setActionCourante("assecher");
                        ihm.PopUpIngenieur();
                        
                    }else if(jeu.getJoueurCourant().getAventurier().getControleAssechable()==2){
                        
                    }
                }else{
                    ihm.setActionCourante("assecher");
                }
                jeu.UtiliserAction();
                majJeu();
            break;
            case AUTREACTION :
            
             
            break;
            case TERMINERTOUR :
                
        
            if (numjoueurcourant == nummax){
                numjoueurcourant = 0 ;
                jeu.TourJoueur(joueursTab[numjoueurcourant]);
                ihm.setJourCourant(joueursTab[numjoueurcourant]);
                
            }
            else {
                numjoueurcourant = numjoueurcourant + 1;
                jeu.TourJoueur(joueursTab[numjoueurcourant]);
                ihm.setJourCourant(joueursTab[numjoueurcourant]);
            }
        
            break;
            
            case MAIN :
                ihm.fenetreCartesMain();
                break;
            case OUI_2EME_ASSECHAGE:
                jeu.getJoueurCourant().getAventurier().setControleAssechable(2);
            break;
                
                
                // Partie Jeu 
                
            case DEBUT_TOUR : 
                ihm.setJourCourant(msg.getJoueur());
                majJeu();
                break;
                
            case GAGNER:
                ihm.fenetreVictoire();
                break;
                
            case PERDU:
                ihm.fenetreDefaite();
                break;
        }
      }
     public void majJeu(){
          grille = jeu.getGrille();
         ihm.majGrille(grille);
    }

     
     
    
}
                
