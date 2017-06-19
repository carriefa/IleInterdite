
package ileinterdite; 
import java.util.ArrayList;

public class Tresor {
	private ArrayList<Tuile> tuileAssociée = new ArrayList<Tuile>();
        private String nom_tresor;
        
        public Tresor(String nom){
            setNom_tresor(nom_tresor);
        }

    public void setNom_tresor(String nom_tresor) {
        this.nom_tresor = nom_tresor;
    }
}
