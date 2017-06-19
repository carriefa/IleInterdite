package Roles; 

import ileinterdite.Aventurier;
import ileinterdite.Etat;
import ileinterdite.Joueur;
import ileinterdite.Tuile;
import java.util.ArrayList;

public class Messager extends Aventurier {
private String role = "messager";

    @Override
    public String getRole() {
        return role;
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