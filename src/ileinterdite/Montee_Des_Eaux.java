package ileinterdite;

import Cartes.Carte_Tresor_Abs;

public class Montee_Des_Eaux extends Carte_Tresor_Abs {
    
    private String type = "Montee_Des_Eaux";
    
    @Override
    public String getType(){
        return type; 
    }
}