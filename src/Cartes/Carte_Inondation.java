package Cartes;

import ileinterdite.Tuile;

public class Carte_Inondation {
	private Tuile tuileassociée;
        
        public Carte_Inondation(Tuile tuile){
            tuileassociée = tuile ;
        }

    /**
     * @return the tuileassociée
     */
    public Tuile getTuileassociée() {
        return tuileassociée;
    }
}