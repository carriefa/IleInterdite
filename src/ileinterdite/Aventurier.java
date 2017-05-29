package ileinterdite;

import java.util.ArrayList;

public abstract class Aventurier {
	public ArrayList<CarteTrésor_Abs_> _main = new ArrayList<CarteTrésor_Abs_>();
	private Joueur _joueurassocié;
	private Tuile _positionActuelle;

	public Aventurier getRole() {
		throw new UnsupportedOperationException();
	}
        
        
}