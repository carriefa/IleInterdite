package ileinterdite; 

public class Carte_Tresor extends Carte_Tresor_Abs {
	private Tresor tresorAssocié;
        
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
        
        
        
        
}