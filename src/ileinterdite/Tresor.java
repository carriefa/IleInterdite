
package ileinterdite; 
import java.util.ArrayList;

public class Tresor {

    /**
     * @return the nom_tresor
     */

	private ArrayList<Tuile> tuileAssociée ;
        private String nom_tresor;
        private tresors = new ArrayList();
        
        public Tresor(String nom){
            setNom_tresor(nom_tresor);
            tuileAssociée = new ArrayList<Tuile>();     
        }
    

    public void setNom_tresor(String nom_tresor) {
        this.nom_tresor = nom_tresor;
    }
    
    public void addTuile(Tuile tuile){
        tuileAssociée.add(tuile);
    }
    
    public String getNom_tresor() {
        return nom_tresor;
    }
        
    public ArrayList<Tresor> getTresors() {
        return tresors ;
    }}

