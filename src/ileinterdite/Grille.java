package ileinterdite;

import java.util.ArrayList;

public class Grille {
	private ArrayList<Tuile> tuiles;
        private Tuile tuilesType; 
        private ArrayList<Integer> numeros;
        private String [] noms;
        
    public Grille(){
      tuiles = new ArrayList<Tuile>(tuilesType.getNOM_TUILES()[]);
      
       setTuiles();
       
    }
    
    
    public ArrayList<Tuile> tuilesadjacentes = new ArrayList<>();

            /*
            |0 |1 |2 |3 |4 |5 |
            |6 |7 |8 |9 |10|11|
            |12|13|14|15|16|17|
            |18|19|20|21|22|23|
            |24|25|26|27|28|29|
            |31|31|32|33|34|35|
            */
            public Tuile[] getTuilesAdjacentesDiagonales(Tuile aCase_7) {
            }

            public ArrayList<Tuile> getCasesAdjacentes(Tuile tuile) {
                int num_tuile;

                num_tuile= tuile.getNumero();
                getTuile(num_tuile);
            }    

    public void setTuiles(){
            
            
            Tuile tuile0= new Tuile(0);
            Tuile tuile1= new Tuile(1);
            Tuile tuile2= new Tuile(2);
            Tuile tuile3= new Tuile(3);
            Tuile tuile4= new Tuile(4);
            Tuile tuile5= new Tuile(5);
            Tuile tuile6= new Tuile(6);
            Tuile tuile7= new Tuile(7);
            Tuile tuile8= new Tuile(8);
            Tuile tuile9= new Tuile(9);
            Tuile tuile10= new Tuile(10);
            Tuile tuile11= new Tuile(11);
            Tuile tuile12= new Tuile(12);
            Tuile tuile13= new Tuile(13);
            Tuile tuile14= new Tuile(14);
            Tuile tuile15= new Tuile(15);
            Tuile tuile16= new Tuile(16);
            Tuile tuile17= new Tuile(17);
            Tuile tuile18= new Tuile(18);
            Tuile tuile19= new Tuile(19);
            Tuile tuile20= new Tuile(20);
            Tuile tuile21= new Tuile(21);
            Tuile tuile22= new Tuile(22);
            Tuile tuile23= new Tuile(23);
            Tuile tuile24= new Tuile(24);
            Tuile tuile25= new Tuile(25);
            Tuile tuile26= new Tuile(26);
            Tuile tuile27= new Tuile(27);
            Tuile tuile28= new Tuile(28);
            Tuile tuile29= new Tuile(29);
            Tuile tuile30= new Tuile(30);
            Tuile tuile31= new Tuile(31);
            Tuile tuile32= new Tuile(32);
            Tuile tuile33= new Tuile(33);
            Tuile tuile34= new Tuile(34);
            Tuile tuile35= new Tuile(35);
            
            tuiles.add(tuile0);
            tuiles.add(tuile1);
            tuiles.add(tuile2);
            tuiles.add(tuile3);
            tuiles.add(tuile4);
            tuiles.add(tuile5);
            tuiles.add(tuile6);
            tuiles.add(tuile7);
            tuiles.add(tuile8);
            tuiles.add(tuile9);
            tuiles.add(tuile10);
            tuiles.add(tuile11);
            tuiles.add(tuile12);
            tuiles.add(tuile13);
            tuiles.add(tuile14);
            tuiles.add(tuile15);
            tuiles.add(tuile16);
            tuiles.add(tuile17);
            tuiles.add(tuile18);
            tuiles.add(tuile19);
            tuiles.add(tuile20);
            tuiles.add(tuile21);
            tuiles.add(tuile22);
            tuiles.add(tuile23);
            tuiles.add(tuile24);
            tuiles.add(tuile25);
            tuiles.add(tuile26);
            tuiles.add(tuile27);
            tuiles.add(tuile28);
            tuiles.add(tuile29);
            tuiles.add(tuile30);
            tuiles.add(tuile31);
            tuiles.add(tuile32);
            tuiles.add(tuile33);
            tuiles.add(tuile34);
            tuiles.add(tuile35);
            ;
            int random= (int)Math.random()*(getNOM_TUILES().length-0) ;
            tuile0.setNom(tuile0.getNOM_TUILES()[(int)Math.random()*(35-0)]);
        
    }
}