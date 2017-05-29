import ileinterdite.Aventurier_Abs_;

public class Explorateur extends Aventurier_Abs_ {
        private String role = "explorateur";

    
    @Override
    public String getRole() {
        return role;
    }

    
    public void setRole(String role) {
        this.role = role;
    }
    
}