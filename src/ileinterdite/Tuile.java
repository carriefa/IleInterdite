package ileinterdite;

import java.util.ArrayList;

public class Tuile {
        private ArrayList<Aventurier_Abs_> pions;
	private ArrayList<Aventurier_Abs_> pionsPrésents = new ArrayList<Aventurier_Abs_>();
	public Carte_Inondation carte;
	public Etat etat;
	private Trésor tresorAssocié;
        private int numero;
        private String nom;
    
        public Tuile(int numero,String nom){
            setNumero(numero);
            setNom(nom);
        }
        
        
        

        public void setNumero(int numero) {
            this.numero=numero;
        }

        public void setNom(String nom) {
            this.nom=nom;
        }
        
	public void MiseAJourDeplacement(Tuile aDepart, Tuile aArrivee, Aventurier_Abs_ aJoueur) {
	}

	public Etat GetEtat() {
            return etat;
	}
        
        public void setPionsPrésents(){
            for (Aventurier_Abs_ aventurier : pions){
                if (aventurier.getPosition().getNumero()==this.getNumero()){
                    pionsPrésents.add(aventurier);
                }
                
            }
        }
        
        public ArrayList<Aventurier_Abs_> getPionsPrésents(){
            return pionsPrésents;
        }

    public int getNumero() {
        return numero;
    }


    public String getNom() {
        return nom;
    }
}