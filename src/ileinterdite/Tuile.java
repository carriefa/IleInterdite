package ileinterdite;

import Cartes.Carte_Inondation;
import java.util.ArrayList;
import ileinterdite.Etat;
import ileinterdite.Utils.Pion;

public class Tuile {
    private Pion pion_associe;
    private ArrayList<Aventurier> joueurspresents = new ArrayList<Aventurier>();
    public Carte_Inondation carte;
    private Etat etat;
    private Tresor tresor_associe;
    private int numero;
    private String nom;
    private Grille grille;
    private final String [] NOM_TUILES ={"Le Jardin Des Murumues", "La Tour Du Guet", "Le Val Du Crépuscule", "Le Palais Des Marees", 
                                   "Le Temple de La Lune", "Le Temple Du Soleil", "La Caverne Du Brasier","Le Rocher Fantôme", "Observatoire", 
                                   "Le Marais Brumeux", "Le Lagon Perdu", "La Forêt Pourpre", 
                                   "Le Jardin Des Hurlements","La Porte De Cuivre","Heliport","Les Dunes De l'Illusion","La Porte d'Argent","Le Palais De Corail",
                                   "Les Falaises De l'Oubli","La Porte d'Or","La Porte De Fer","La Caverne Des Ombres","La Porte De Bronze","Le Pont Des Abîmes"};
    private final int [] NUM_TUILES_VIDE ={0,1,6,4,5,11,24,29,30,31,34,35};

    public Tuile(int numero){
        setEtat(numero);
        setNumero(numero);
        joueurspresents = new ArrayList();
    }

    public Tuile(){

    }

    public void setNumero(int numero){
        this.numero=numero;
    }

    public void setNom(String nom) {
    this.nom = nom;
    }

    public int getNumero() {
    return numero;
    }

    public String getNom() {
        return nom;
    }

    public void MiseAJourDeplacement(Tuile aDepart, Tuile aArrivee, Aventurier aJoueur) {
        //à compléter
    }
/*
    public ArrayList<Aventurier_Abs_> getPionsPrésents(){
        for (Aventurier_Abs_ aventurier : pions){ //pour chaque aventurier
            if (aventurier.getPosition().getNumero()==this.getNumero()){ //on regarde le numério de la tuile sur laquelle il est, et on le compare a la tuile actuelle
                pionsPrésents.add(aventurier); // si le numéro est le même, on ajoute l'aventurier a l'ArrayList pionsPrésents
            }

        }
        return pionsPrésents;
    }
*/        
    public String[] getNOM_TUILES(){
        return NOM_TUILES;
    }    

    public void setEtat(int numero) { // utilisée pour l'initialisation
        int i;
        for ( i=0; i<getNUM_TUILES_VIDE().length && numero != getNUM_TUILES_VIDE()[i];i++ ){}
        if (i!=getNUM_TUILES_VIDE().length ){
            this.etat=Etat.VIDE;
        }else{
            this.etat=Etat.ASSECHEE;
        }   
    }
    
    public void SetEtat(Etat etat){  
        this.etat=etat;
    }

    public int[] getNUM_TUILES_VIDE() {
        return NUM_TUILES_VIDE;
    }

    public Etat getEtat() {
        return etat;
    }

    public Tresor getTresorAssocié() {
        return tresor_associe;
    }

    public void setTresorAssocié(ArrayList<Tresor> tresors) {
        if (this.getNom().equals("Le Temple De La Lune") ||this.getNom().equals("Le Temple Du Soleil")){
            for (Tresor tresor : tresors){
                if (tresor.getNom_tresor() == ("La Pierre Sacrée")) {
                 tresor_associe = tresor;
                 tresor.addTuile(this);
                }  
            }
        }else if(this.getNom().equals("Le Jardin Des Murmures") || this.getNom().equals("Le Jardin Des Hurlements")){
               for (Tresor tresor : tresors){
                if (tresor.getNom_tresor() == ("La Statue Du Zéphyr")) {
                 tresor_associe = tresor;
                 tresor.addTuile(this);
                }
            }
        }else if(this.getNom().equals("La Caverne du Brasier") || this.getNom().equals("La Caverne Des Ombres")){
               for (Tresor tresor : tresors){
                if (tresor.getNom_tresor() == ("Le Cristal Ardent")) {
                 tresor_associe = tresor;
                 tresor.addTuile(this);
                }
            }
        }else if(this.getNom().equals("Le Palais De Corail") || this.getNom().equals("Le Palais Des Marees")){
               for (Tresor tresor : tresors){
                if (tresor.getNom_tresor() == ("Le Calice De L’onde")) {
                 tresor_associe = tresor;
                 tresor.addTuile(this);
                }
                
            }
        }else{
            tresor_associe=null;
        }
        
    }

    public Pion getPion() {
        return pion_associe;
    }

    public void setPionAssocie() {
        if (this.getNom()!=null){
            if ((this.getNom()).equals("La Porte De Bronze")){
                pion_associe = Pion.ROUGE;
            }else if(this.getNom().equals("La Porte De Fer")){
                pion_associe = Pion.VIOLET;
            }else if(this.getNom().equals("La Porte d'Or")){
                pion_associe = Pion.JAUNE;
            }else if(this.getNom().equals("La Porte d'Argent")){
                pion_associe = Pion.ORANGE;
            }else if(this.getNom().equals("L'héliport")){
                pion_associe = Pion.BLEU;
            }else if(this.getNom().equals("La Porte De Cuivre")){
                pion_associe = Pion.VERT;
            }else{
                pion_associe = null;
            }
        }else{
            pion_associe=null;
        }
    }
    
    public void setGrille(Grille grille){
        this.grille=grille;
    }

    public Grille getGrille() {
        return grille;
    }

    /**
     * @return the pionsPrésents
     */

    public String getPionsPrésentsAffichage() {
        String nomspresents ="" ;
        ArrayList<String> noms = new ArrayList();
      
        for (Aventurier joueurspresent : joueurspresents) {
           noms.add(joueurspresent.getJoueur().getNom()+"("+joueurspresent.getPion().toString()+")");
        };
        if (!noms.isEmpty()){
             {   for (String nom : noms) {
                 nomspresents = nomspresents + nom +" <br>";
                 }
}}
    return nomspresents;
    
    


}

    void addPion(Aventurier pion) {
       joueurspresents.add(pion); 
    }
}
