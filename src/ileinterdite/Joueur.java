
import ileinterdite.Aventurier_Abs_;
import ileinterdite.Tuile;

public class Joueur {
	private Aventurier_Abs_ role;
        

	public Tuile GetPosition() {
		return this.role.getPosition();
	}

	public Aventurier_Abs_ getRole() {
		return this.role;
	}
        
        
}