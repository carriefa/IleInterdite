package ileinterdite;
import ileinterdite.Aventurier;
import ileinterdite.Tuile;
import ileinterdite.Utils.Pion;

public class Joueur {
    private Aventurier aventurier;
    private String nom;
    private Pion pion;

    public Joueur(String nom, Aventurier aventurier,Pion pion){
        this.nom=nom;
        this.aventurier=aventurier;
        this.pion=pion;
    }

    public Aventurier getAventurier() {
            return this.aventurier;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Tuile getPosition() {
         return aventurier.getPosition();
    }
    
    public void setPostition(Tuile tuile){
        aventurier.setPosition(tuile);
    }

    /**
     * @param aventurier the aventurier to set
     */
    public void setAventurier(Aventurier aventurier) {
        this.aventurier = aventurier;
    }

    /**
     * @return the pion
     */
    public Pion getPion() {
        return pion;
    }

    /**
     * @param pion the pion to set
     */
    public void setPion(Pion pion) {
        this.pion = pion;
    }
        
        
}