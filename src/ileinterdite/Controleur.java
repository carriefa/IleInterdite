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
    private IHMileInterdite ihm ;
            
    public Controleur(){
        jeu = new JeuIleInterdite() ;
        ihm = new IHMileInterdite() ;
    }
    @Override
    public void traiterMessage(Message msg) {
        switch(msg.getType()) {
            case DEMARRER_PARTIE:
                
            break ;
            case MOUVEMENT:
            
            break;
            case ASSECHER:
                
            break;
            case AUTREACTION :
            
            break;
            case TERMINERTOUR :
                
            break;    
        }
    }

    
}
