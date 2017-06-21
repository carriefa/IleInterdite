package ileinterdite;
import Cartes.Carte_Tresor_Abs;
import ileinterdite.Aventurier;
import ileinterdite.Tuile;
import ileinterdite.Utils.Pion;
import java.util.ArrayList;

public class Joueur {
    private Aventurier aventurier;
    private String nom;
    private Pion pion;
    private ArrayList<Cartes.Carte_Tresor_Abs> main;

    public Joueur(String nom, Aventurier aventurier,Pion pion){
        this.nom=nom;
        this.aventurier=aventurier;
        this.pion=pion;
        aventurier.setJoueur_associé(this);
        aventurier.setPion(pion);
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
    
    public void addCarteMain(Carte_Tresor_Abs cartes){
        getMain().add(cartes);
    }

    /**
     * @return the main
     */
    public ArrayList<Cartes.Carte_Tresor_Abs> getMain() {
        return main;
    }
    
    public void supprimerCarte(Carte_Tresor_Abs carte){
        main.remove(carte);
    }
        
        
}