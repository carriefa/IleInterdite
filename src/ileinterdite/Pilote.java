package ileinterdite;

import ileinterdite.Tuile;
import ileinterdite.Aventurier;
import java.util.ArrayList;

public class Pilote extends Aventurier {
    private String role = "pilote";
    
    @Override
    public String getRole() {
        return role;
    }
    
    public Tuile getPosition(){
        return super.getPosition();
    }

    @Override
    public Joueur getJoueur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Tuile> getTuilesAssechables(Joueur joueur) {
        return super.getPosition().getGrille().getTuilesAssechables(joueur);
    }

    @Override
    public void AssecherTuile(Tuile tuile) {
        tuile.SetEtat(Etat.ASSECHEE);
                
                
    }

   



}