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
public class Tuile {
    private int numero;
    private String nom;
    
    public Tuile(int numero,String nom){
        setNumero(numero);
        setNom(nom);
        
        
    }

    public void setNumero(int numero) {
        this.numero=numero;
    }

    public void setNom(String nom) {
        this.nom=nom;
    }
    
}
