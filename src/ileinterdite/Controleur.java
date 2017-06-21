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
   
            
    public Controleur(){
        
        ArrayList<Joueur> joueurs = new ArrayList<>();
        Aventurier ingé = new Roles.Explorateur();
        jeu = new JeuIleInterdite();
        
        Joueur j1 = new Joueur("jean",new Roles.Explorateur() , Utils.Pion.VERT); 
        Joueur j2 = new Joueur("jiji", new Roles.Messager(), Utils.Pion.ROUGE);
        joueurs.add(j1);
        joueurs.add(j2);
        jeu.setJoueurs(joueurs);
     
        

    }
    @Override
    public void traiterMessage(Message2 msg) {
        switch(msg.getType()) {
            case DEMARRER_PARTIE:
                
                ihm = new IHMileInterdite2();
                ihm.setObservateur(this);
                ihm.InitFenetreDepart();
                
            break ;
            case MOUVEMENT:
                ihm.setActionCourante("Bopnjour");
            
            break;
            case ASSECHER: 
                ihm.setActionCourante("essecher");
            break;
            case AUTREACTION :
                System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
                System.out.println(ihm.getJoueurs().length);
                for(Joueur j : ihm.getJoueurs()){
                    System.out.println(j.getNom());
                    System.out.println(j.getAventurier());
                    System.out.println(j.getPion());
                }
             
            break;
            case DONNER:
                ihm.getJoueurs(); 
                    
            
            case TERMINERTOUR :
                
            break;    
        }
    }

    
}
