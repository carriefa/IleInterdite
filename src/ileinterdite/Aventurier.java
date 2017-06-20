package ileinterdite;

import ileinterdite.TypesMessage;
import java.util.ArrayList;

public abstract class Aventurier {
    public ArrayList<Carte_Tresor_Abs> main = new ArrayList<Carte_Tresor_Abs>();
    private Joueur joueur_associé;
    private Tuile position_actuelle;
    
    
    public abstract String getRole();
    
    public Tuile getPosition(){
        return position_actuelle;
    }
    public Joueur getJoueur(){
        return joueur_associé;
    }
    
    public void setPosition(Tuile tuile){
        this.position_actuelle=tuile;
    }
    
    public abstract ArrayList<Tuile> getTuilesAssechables(Joueur joueur);
    public abstract void AssecherTuile(Tuile tuile);
    
    public abstract ArrayList<Tuile> getTuilesDeplacement(Joueur joueur);
    
    public abstract void Deplacement(Tuile tuile);
    
    public Grille getGrille(){
        return this.getPosition().getGrille();
    }
    /**
     * @return the grille
     */
    }

