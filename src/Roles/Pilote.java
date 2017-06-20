package Roles;

import ileinterdite.Aventurier;
import ileinterdite.Etat;
import ileinterdite.Joueur;
import ileinterdite.Tuile;
import ileinterdite.Utils;
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
    public ArrayList<Tuile> getTuilesAssechables(Joueur joueur) {
        return super.getPosition().getGrille().getTuilesAssechables(joueur);
    }

    @Override
    public void AssecherTuile(Tuile tuile) {
        tuile.SetEtat(Etat.ASSECHEE);
    }

    @Override
    public ArrayList<Tuile> getTuilesDeplacement(Joueur joueur) {
        return super.getPosition().getGrille().getTuilesDeplacement(joueur);
    }

    @Override
    public void Deplacement(Tuile tuile) {
        super.setPosition(tuile);
    }

   



}