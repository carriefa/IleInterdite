package ileinterdite;

import java.util.ArrayList;
import ileinterdite.Etat;
import ileinterdite.Utils.Pion;

public class Tuile {
    private Pion pion_associe;
    private ArrayList<Aventurier> pionsPrésents = new ArrayList<Aventurier>();
    public Carte_Inondation carte;
    private Etat etat;
    private Tresor tresor_associe;
    private int numero;
    private String nom;
    private Grille grille;
    private final String [] NOM_TUILES ={"La Caverne Des Ombres", "Le Val Du Crépuscule", "La Porte De Cuivre", "Le Marais Brumeux", 
                                   "Le Jardin Des Murmures", "La Caverne du Brasier", "La Porte d'Or","La Porte De Fer", "Le Temple Du Soleil", 
                                   "Le Pont Des Abîmes", "La Porte d'Argent", "Le Jardin Des Hurlements", 
                                   "Les Dunes De L'illusion","Les Falaises De l'Oubli","L'Observatoire","Le Palais Des Marees","L'héliport","Le Rocher Fantôme",
                                   "Le Temple De La Lune","La forêt Pourpre","Le Lagon Perdu","La Porte De Bronze","Le Palais De Corail","La Tour De Guet"};
    private final int [] NUM_TUILES_VIDE ={0,1,6,4,5,11,24,29,30,31,34,35};

    public Tuile(int numero){
        setEtat(numero);
        setNumero(numero);   
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

    public void setTresorAssocié() {
        if (this.getNom().equals("Le Temple De La Lune") ||this.getNom().equals("Le Temple Du Soleil")){
            tresor_associe = new Tresor("La Pierre Sacrée");
        }else if(this.getNom().equals("Le Jardin Des Murmures") || this.getNom().equals("Le Jardin Des Hurlements")){
            tresor_associe = new Tresor("La Statue Du Zéphyr");
        }else if(this.getNom().equals("La Caverne du Brasier") || this.getNom().equals("La Caverne Des Ombres")){
            tresor_associe = new Tresor("Le Cristal Ardent");
        }else if(this.getNom().equals("Le Palais De Corail") || this.getNom().equals("Le Palais Des Marees")){
            tresor_associe = new Tresor("Le Calice De L’onde");
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

    /**
     * @return the grille
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * @return the pionsPrésents
     */
    public String getPionsPrésentsAffichage() {
        String constantation ="abdel" ;
        ArrayList<String> papa = new ArrayList();
        pionsPrésents.stream().forEach((a) -> {
            papa.add(a.getJoueur().getNom());
        });
        if (!papa.isEmpty()){
             {   for (String papa1 : papa) {
                 constantation = constantation + papa1;
                 }
}}
    return constantation;
    
    


}}