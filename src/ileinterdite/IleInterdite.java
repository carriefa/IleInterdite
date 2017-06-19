/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

/**
 *
 * @author carriefa
 */
public class IleInterdite implements Observateur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println();
    }

    @Override
    public void traiterMessage(Message2 msg) {
        if (msg.type==TypesMessage.MOUVEMENT){
            System.out.println("Deplacement ");
        }else if (msg.type==TypesMessage.ASSECHER){
            System.out.println("Assechement");
        }else if (msg.type==TypesMessage.AUTREACTION){
            System.out.println("Autre action");
        }else {
            System.out.println("Terminer tour");
        }
        
        
    }
    
}
