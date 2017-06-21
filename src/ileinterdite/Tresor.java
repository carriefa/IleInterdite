
package ileinterdite; 
import java.util.ArrayList;

public class Tresor {

    /**
     * @return the nom_tresor
     */

       private ArrayList<Tuile> tuileAssociée ;
        private String nom_tresor;
        private boolean recupere;
        
        public Tresor(String nom){
            setNom_tresor(nom);
            recupere= false ;
            tuileAssociée = new ArrayList();
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
    
    public void addTuile(Tuile tuile){
        getTuileAssociée().add(tuile);
    }
    
    public String getNom_tresor() {
        return nom_tresor;
    }

    /**
     * @return the tuileAssociée
     */
    public ArrayList<Tuile> getTuileAssociée() {
        return tuileAssociée;
    }
        
    }

