/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.pattern;

/**
 *
 * @author alanl
 */
public class Employe {
    protected String matricule;
    protected int id;
    protected String nom;
    protected String prenom;
    protected int idbureau;
    
    private Employe(EmployeBuilder eb){
        this.id = eb.id;
        this.matricule = eb.matricule;
        this.nom = eb.nom;
        this.prenom = eb.prenom;
        this.idbureau = eb.idbureau;
    }

    public String getMatricule() {
        return matricule;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getIdbureau() {
        return idbureau;
    }
    
    public static class EmployeBuilder{
        protected String matricule;
        protected int id;
        protected String nom;
        protected String prenom;
        protected int idbureau;
        
        public EmployeBuilder setIdemploye(int id){
            this.id = id;
            return this;
        }
        
        public EmployeBuilder setIdbureau(int idbureau){
            this.idbureau = idbureau;
            return this;
        }
        
        public EmployeBuilder setMatricule(String matricule){
            this.matricule = matricule;
            return this;
        }
        
        public EmployeBuilder setNom(String nom){
            this.nom = nom;
            return this;
        }
        
        public EmployeBuilder setPrenom(String prenom){
            this.prenom = prenom;
            return this;
        }
        public Employe build() throws Exception{
            if(matricule == null || nom == null || prenom == null || idbureau <= 0)
                throw new Exception("Infos de construction incomplètes");
            return new Employe(this);
        }
    }
}
