package Cartes; 

import ileinterdite.Tresor;

public class Carte_Tresor extends Carte_Tresor_Abs {
	private Tresor tresorAssocié;
        private String type = "Carte tresor";
        
        public Carte_Tresor(Tresor tresorAssocié){
            setTresorAssocié(tresorAssocié);
        }
    /**
     * @return the tresorAssocié
     */
    public Tresor getTresorAssocié() {
        return tresorAssocié;
    }

    /**
     * @param tresorAssocié the tresorAssocié to set
     */
    public void setTresorAssocié(Tresor tresorAssocié) {
        this.tresorAssocié = tresorAssocié;
    }

    @Override
    public String getType() {
        return type+" ("+getTresorAssocié().getNom_tresor()+")";
    }
        
        
        
        
}
