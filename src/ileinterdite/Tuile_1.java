import java.util.ArrayList;

public class Tuile {
	private ArrayList<Aventurier__Abs_> _pionPrésents = new ArrayList<Aventurier__Abs_>();
	public Carte_Inondation _carte;
	public Etat _etat;
	private Trésor _tresorAssocié;
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
        
	public void MiseAJourDeplacement(Tuile aDepart, Tuile aArrivee, Aventurier__Abs_ aJoueur) {
		throw new UnsupportedOperationException();
	}

	public Etat GetEtat() {
		throw new UnsupportedOperationException();
	}
}