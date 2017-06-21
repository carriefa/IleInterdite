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
    public Controleur(){
        
            jeu = new JeuIleInterdite();
            ihm = new IHMileInterdite2();
            ihm.setObservateur(this);
            grille = jeu.getGrille();
            joueurs = new ArrayList();
            
    }
    @Override
    public void traiterMessage(Message2 msg) {
        switch(msg.getType()) {
            case DEMARRER_PARTIE:
                ArrayList<Joueur> joueurs  = msg.getJoueurs();  // Validé 
                jeu.setJoueurs(joueurs);
                ihm.InitFenetrePrincipale(grille);
                majJeu();
            break;
            case MOUVEMENT:
                Tuile t1 = new Tuile(3);
                Tuile t2 = new Tuile(14);
                ArrayList<Tuile> a = new ArrayList<>();
                a.add(t1);
                a.add(t2);
                ihm.setActionCourante("mouvement");
                
                ihm.choixDeplacement(a);
                
                
                //ihm.choixDeplacement(jeu.getJoueurCourant().getAventurier().getTuilesDeplacement(jeu.getJoueurCourant()));
            break;
                
            case VALIDER_MOUVEMENT:
                
                Tuile tuilechoisie =  msg.getTuileChoisie();
                System.out.println(msg.getTuileChoisie().getNom());
                //jeu.getJoueurCourant().getAventurier().Deplacement(tuilechoisie);
                majJeu();
                break;
            
            case ASSECHER:
                if(jeu.getJoueurCourant().getAventurier().getRole().equals("ingénieur")){ //si c'est un ingénieur
                    if(jeu.getJoueurCourant().getAventurier().getControleAssechable()==1){ //si c'est la premiere fois qu'il asseche.
                        ihm.setActionCourante("assecher");
                        ihm.PopUpIngenieur();
                        
                    }else if(jeu.getJoueurCourant().getAventurier().getControleAssechable()==2){
                        
                    }
                }else{
                    ihm.setActionCourante("assecher");
                }
                majJeu();
            break;
            case AUTREACTION :
            
             
            break;
            case TERMINERTOUR :
                
                majJeu();
            break;
            
            case OUI_2EME_ASSECHAGE:
                jeu.getJoueurCourant().getAventurier().setControleAssechable(2);
                
        }
      }
     public void majJeu(){
          grille = jeu.getGrille();
         ihm.majGrille(grille);
    }

    
}
