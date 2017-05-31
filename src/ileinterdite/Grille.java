package ileinterdite;

import java.util.ArrayList;

public class Grille {
	private ArrayList<Tuile> tuiles;
        private ArrayList<String> Nom_Tuiles_Init;
        private Tuile tuilesType = new Tuile(); 
        private ArrayList<Integer> numeros;
        private String [] noms;
        private Tuile [] t1 = new Tuile [36]; 
        
    public Grille(){
      for(int i = 0; i <= tuilesType.getNOM_TUILES().length; i++){
          Nom_Tuiles_Init.add(tuilesType.getNOM_TUILES()[i]);
      }
      
       initTuiles();
       
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

    public void initTuiles(){
            int alea;
            int a = 36;
            
            for(int i = 0; i < (tuilesType.getNOM_TUILES().length)&&(i < t1.length); i++){
                t1[i] = new Tuile (i);
                alea = (int) (Math.random() * (a - 0));
                t1[i].setNom(Nom_Tuiles_Init.get(alea));
                Nom_Tuiles_Init.remove(alea);
                a = a-1;
            }
            
            Tuile tuile0= new Tuile(0);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile1= new Tuile(1);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile1.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile2= new Tuile(2);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile2.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile3= new Tuile(3);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile3.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile4= new Tuile(4);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile4.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile5= new Tuile(5);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile5.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile6= new Tuile(6);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile6.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile7= new Tuile(7);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile7.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile8= new Tuile(8);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile8.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile9= new Tuile(9);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile9.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile10= new Tuile(10);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile10.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile11= new Tuile(11);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile11.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile12= new Tuile(12);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile13= new Tuile(13);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile14= new Tuile(14);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile15= new Tuile(15);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile16= new Tuile(16);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile17= new Tuile(17);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile18= new Tuile(18);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile19= new Tuile(19);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile20= new Tuile(20);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile21= new Tuile(21);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile22= new Tuile(22);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile23= new Tuile(23);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile24= new Tuile(24);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile25= new Tuile(25);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile26= new Tuile(26);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile27= new Tuile(27);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile28= new Tuile(28);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile29= new Tuile(29);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile30= new Tuile(30);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile31= new Tuile(31);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile32= new Tuile(32);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile33= new Tuile(33);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile34= new Tuile(34);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            Tuile tuile35= new Tuile(35);
            alea = (int) (Math.random() * (Nom_Tuiles_Init.size() - 0));
            tuile0.setNom(Nom_Tuiles_Init.get(alea));
            Nom_Tuiles_Init.remove(alea);
            
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
            
            
          
        
    }
}