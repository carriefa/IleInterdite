package Roles;


import ileinterdite.Aventurier;
import ileinterdite.Controleur;
import ileinterdite.Etat;
import ileinterdite.Joueur;
import ileinterdite.Message2;
import ileinterdite.Tuile;
import ileinterdite.TypesMessage;
import ileinterdite.Utils;
import java.util.ArrayList;

public class Explorateur extends Aventurier {
        private String role;

    public Explorateur() {
        super();
        role = "explorateur";
    }


    
    @Override
    public String getRole() {
        return role;
    }

    
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Tuile getPosition() {
         return super.getPosition();
    }

    @Override
    public ArrayList<Tuile> getTuilesAssechables(Joueur joueur) {
        return super.getPosition().getGrille().getTuilesAssechablesExplorateur(joueur);
    }

    @Override
    public void AssecherTuile(Tuile tuile) {
        tuile.SetEtat(Etat.ASSECHEE);
    }

    @Override
    public ArrayList<Tuile> getTuilesDeplacement(Joueur joueur) {
        return super.getPosition().getGrille().getTuilesDeplacementExplorateur(joueur);
    }

    @Override
    public void Deplacement(Tuile tuile) {
        super.setPosition(tuile);
    }
    
//    @Override
//    public Joueur getJoueur(){
//            return super.getJoueur();
//    }


}
