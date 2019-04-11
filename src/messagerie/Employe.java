
package messagerie;

public class Employe {
    protected int idemp, idbur;
    protected String matricule, nom, prenom;
    
    public Employe(int idemp, String matricule, String nom, String prenom, int idbur){
        this.idemp = idemp;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.idbur = idbur;
    }

    public int getIdemp() {
        return idemp;
    }

    public int getIdbur() {
        return idbur;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setIdemp(int idemp) {
        this.idemp = idemp;
    }

    public void setIdbur(int idbur) {
        this.idbur = idbur;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    @Override
    public String toString() { 
        return "Employe{" + "idemployé : " + idemp + ", matricule : " + matricule + ", nom : " + nom + ", prénom : " + prenom + ", idbureau : " + idbur;
    }
}
