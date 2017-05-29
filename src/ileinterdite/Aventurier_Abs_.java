package ileinterdite;

import java.util.ArrayList;

public abstract class Aventurier_Abs_ {
	public ArrayList<CarteTrésor_Abs_> main = new ArrayList<CarteTrésor_Abs_>();
	private Joueur joueur_associé;
	private Tuile position_actuelle;

	public abstract String getRole();
        
        public Tuile getPosition(){
            return position_actuelle;
        }
        
        public Joueur getJoueur(){
            return joueur_associé;
        }

    public void setPosition(Tuile position_actuelle) {
        this.position_actuelle = position_actuelle;
    }
        
}