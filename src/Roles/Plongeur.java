package Roles;


import ileinterdite.Aventurier;
import ileinterdite.Etat;
import ileinterdite.Joueur;
import ileinterdite.Tuile;
import java.util.ArrayList;

public class Plongeur extends Aventurier {
    private String role = "plongeur";

    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
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
        if(super.getPosition()== null){
            System.out.println("suce mon énorme queue");
        }
        System.out.println(super.getPosition().getNom());
        return super.getPosition().getGrille().getTuilesDeplacementPlongeur(joueur);
    }

    @Override
    public void Deplacement(Tuile tuile) {
        super.setPosition(tuile);
    }
}