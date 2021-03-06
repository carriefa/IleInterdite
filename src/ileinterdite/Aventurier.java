package ileinterdite;

import Cartes.Carte_Tresor_Abs;
import ileinterdite.TypesMessage;
import ileinterdite.Utils.Pion;
import java.util.ArrayList;

public abstract class Aventurier {
    public ArrayList<Carte_Tresor_Abs> main;
    private Joueur joueur_associé;
    private Tuile position_actuelle;
    private Tuile position_precedente;
    private Pion pion;
    
    public Aventurier (){
            main = new ArrayList<>();
    }
    
    public abstract String getRole();
    
    public Tuile getPosition(){
        return position_actuelle;
    }

    public Joueur getJoueur() {
        return joueur_associé;
    }
    
    public void setPosition(Tuile tuile){
        if (position_actuelle != null){
                   this.position_precedente = this.position_actuelle;
                  for (int i = 0 ; i< this.position_precedente.getJoueurspresents().size();i++){
                 if (this.position_precedente.getJoueurspresents().get(i) == this){
                     this.position_precedente.getJoueurspresents().remove(i);
                    }
               };
        }
        this.position_actuelle=tuile;
        tuile.addAventurier(this);

    
    }
    
    public abstract ArrayList<Tuile> getTuilesAssechables(Joueur joueur);
    public abstract void AssecherTuile(Tuile tuile);
    public abstract int getControleAssechable();
    public abstract void setControleAssechable(int i);
    public abstract ArrayList<Tuile> getTuilesDeplacement(Joueur joueur);
    
    public abstract void Deplacement(Tuile tuile);
    
    public Grille getGrille(){
        return this.getPosition().getGrille();
    }
    /**
     * @param joueur_associé the joueur_associé to set
     */
    public void setJoueur_associé(Joueur joueur_associé) {
        this.joueur_associé = joueur_associé;
    }
    
    /**
     * @param pion the pion to set
     */
    public void setPion(Pion pion) {
        this.pion = pion;
    }

    
   public Pion getPion(){
       return pion;
   }
    /**
     * @return the grille
     */
    }

