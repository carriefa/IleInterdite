/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import static ileinterdite.TypesMessage.DEMARRER_PARTIE;

/**
 *
 * @author carriefa
 */
public class Controleur implements Observateur {

    private JeuIleInterdite jeu ;
    private IHMileInterdite2 ihm ;
    private Grille grille;
    public Controleur(){
        
    }
    @Override
    public void traiterMessage(Message2 msg) {
        switch(msg.getType()) {
            case DEMARRER_PARTIE:
                grille = new Grille();
                ihm = new IHMileInterdite2();
                ihm.setObservateur(this);
                ihm.InitFenetreDepart();
                
            break ;
            case MOUVEMENT:
                ihm.setActionCourante("mouvement");
            
            break;
            case ASSECHER: 
                ihm.setActionCourante("assecher");
            break;
            case AUTREACTION :
                
                for(Joueur j : ihm.getJoueurs()){
                    System.out.println(j.getNom());
                    System.out.println(j.getAventurier());
                    System.out.println(j.getPion());
                }
             
            break;
            case TERMINERTOUR :
                
            break;
            
            
                
        }
    }

    
}
