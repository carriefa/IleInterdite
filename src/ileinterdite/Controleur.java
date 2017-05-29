package ileinterdite;

import java.util.ArrayList;

public class Controleur {
	private Grille grille;
	private ArrayList<CarteTrésor_Abs_> cartes_trésor_defausse = new ArrayList<CarteTrésor_Abs_>();
	private ArrayList<Aventurier_Abs_> roles = new ArrayList<Aventurier_Abs_>();
	private ArrayList<Trésor> trésors = new ArrayList<Trésor>();
	private ArrayList<CarteTrésor_Abs_> cartes_trésor_pioche = new ArrayList<CarteTrésor_Abs_>();
	private ArrayList<Carte_Inondation> cartes_innondation_defausse= new ArrayList<Carte_Inondation>();
	private ArrayList<Carte_Inondation> cartes_innondation_pioche = new ArrayList<Carte_Inondation>();
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private ArrayList<Carte_Inondation> cartes_innodation_cimetiere = new ArrayList<Carte_Inondation>();

	public void TourJoueur(Joueur joueur) {
		
	}

	public void Deplacement(Joueur joueur) {
            String role_joueur_courant;
            Tuile position_joueur_courant;
            
            role_joueur_courant = joueur.getRole();
            position_joueur_courant = joueur.getPosition();
            
            grille.getCasesAdjacentes(position_joueur_courant);
            
            
	}

	public void DeplacementExplorateur(Joueur joueur) {
		
	}

	public void AssecherCase(Joueur joueur) {
		
	}
}
