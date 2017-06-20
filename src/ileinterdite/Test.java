/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import Roles.*;
import ileinterdite.Utils.Pion;

public class Test {

    public static void main(String[] args) {
        // TODO code application logic here
        
       
          
        Controleur controleur = new Controleur();
        Message2 m = new Message2(); 
        m.type = TypesMessage.DEMARRER_PARTIE;
        controleur.traiterMessage(m);
      
    }
    
    
    
}
