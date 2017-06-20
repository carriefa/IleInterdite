package Roles;



import ileinterdite.Aventurier;
import ileinterdite.Controleur;
import ileinterdite.Etat;
import ileinterdite.Joueur;
import ileinterdite.Message2;
import ileinterdite.Tuile;
import ileinterdite.TypesMessage;
import java.util.ArrayList;

public class Navigateur extends Aventurier {
        private String role = "navigateur";

    
    @Override
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
        return super.getPosition().getGrille().getTuilesDeplacement(joueur);
    }

    @Override
    public void Deplacement(Tuile tuile) {
        super.setPosition(tuile);
    }
    
    
}