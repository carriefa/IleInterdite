package ileinterdite;

import java.util.ArrayList;

public abstract class Aventurier {
    public ArrayList<Carte_Tresor_Abs_> main = new ArrayList<Carte_Tresor_Abs_>();
    private Joueur joueur_associé;
    private Tuile position_actuelle;
    
    
    public abstract String getRole();
    
    public Tuile getPosition(){
        return position_actuelle;
    }
    public abstract Joueur getJoueur();
    
    public void setPosition(Tuile tuile){
        this.position_actuelle=tuile;
    }
    
    public abstract ArrayList<Tuile> getTuilesAssechables(Joueur joueur);
    public abstract void AssecherTuile(Tuile tuile);

    /**
     * @return the grille
     */
    }

