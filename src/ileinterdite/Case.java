package ileinterdite;


import ileinterdite.Tuile;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucadeslot
 */
public class Case extends JButton {
    private Tuile tuileAssocié;
    
    public Case(String nom){
        super(nom);
        
    }
    
    public void setTuileAssocié(Tuile tuile){
        tuileAssocié = tuile; 
    }
    
    public Tuile getTuileAssocie(){
        return tuileAssocié;
    }
    
}
