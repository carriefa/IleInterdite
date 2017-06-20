package ileinterdite;

import ileinterdite.TypesMessage;
import ileinterdite.Utils.Pion;
import java.util.ArrayList;

public abstract class Aventurier {
    public ArrayList<Carte_Tresor_Abs> main = new ArrayList<Carte_Tresor_Abs>();
    private Joueur joueur_associé;
    private Tuile position_actuelle;
    private Pion pion;
    
    public Aventurier (){
    }
    
    public abstract String getRole();
    
    public Tuile getPosition(){
        return position_actuelle;
    }
    
    
    
    public  Joueur getJoueur(){
        return joueur_associé;
    }
    
    public void setPosition(Tuile tuile){
        this.position_actuelle=tuile;
        tuile.addPion(this);
    }
    
    public abstract ArrayList<Tuile> getTuilesAssechables(Joueur joueur);
    public abstract void AssecherTuile(Tuile tuile);
    
    public abstract ArrayList<Tuile> getTuilesDeplacement(Joueur joueur);
    public abstract void Deplacement(Tuile tuile);

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

    /**
     * @return the grille
     */
    }

