/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import java.util.ArrayList;

/**
 *
 * @author carriefa
 */
public class Message2 {
   public TypesMessage type ;
   private ArrayList<Joueur> joueurs ;
   private Tuile tuileChoisie ;
   private Joueur joueur ;
   private boolean choix ;
    
    public Message2(){
        joueurs = new ArrayList();
    }
    
    public TypesMessage getType() {
        return type;
    }

    public void setType(TypesMessage type) {
        this.type = type;
    }

    /**
     * @return the joueurs
     */
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
        
    }

    /**
     * @param joueurs the joueurs to set
     */
    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * @return the tuileChoisie
     */
    public Tuile getTuileChoisie() {
        return tuileChoisie;
    }

    /**
     * @param tuileChoisie the tuileChoisie to set
     */
    public void setTuileChoisie(Tuile tuileChoisie) {
        this.tuileChoisie = tuileChoisie;
    }

    /**
     * @return the joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * @param joueur the joueur to set
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * @return the choix
     */
    public boolean getChoix() {
        return choix;
    }

    /**
     * @param choix the choix to set
     */
    public void setChoix(boolean choix) {
        this.choix = choix;
    }
    
    
}
