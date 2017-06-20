
package ileinterdite; 
import java.util.ArrayList;

public class Tresor {
        private ArrayList<Tuile> tuileAssociée = new ArrayList<Tuile>();
        private String nom_tresor;
        private boolean recupere;
        
        public Tresor(String nom){
            setNom_tresor(nom_tresor);
            recupere= false ;
        }

        public void recuperation(){
            recupere = true ;
        }
        
        public boolean estRecupere(){
            return recupere;
        }
    public void setNom_tresor(String nom_tresor) {
        this.nom_tresor = nom_tresor;
    }
}
