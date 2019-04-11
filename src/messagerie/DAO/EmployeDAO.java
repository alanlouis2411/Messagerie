package messagerie.DAO;

import myconnections.DBConnection;
import java.sql.*;
import java.util.*;
import messagerie.Employe;

public class EmployeDAO extends DAO<Employe>{
    Statement stmt = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    int idemp, idbur;
    String matricule, nom, prenom;
    
         
    /*
        menu CRUD permettant de manipuler des objets de type Employé en appelant chaque méthode sur un objet créé avec des 
        données entrées au clavier par l'utilisateur en vérifiant ce qu'il rentre(par ex vérifier qu'un matricule ne soit pas 
        entré 2 fois. 
    */
    
    public void menu() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int choix = 0;
        do{
            Connection dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
                System.exit(1);
            }
            System.out.println("1) Entrer un nouvel employé");
            System.out.println("2) Voir les informations relatives à un employé (+ messages envoyés)");
            System.out.println("3) Modifier les informations d'un employé");
            System.out.println("4) Virer un employé");
            System.out.println("5) Retour");
            System.out.println("\n Entrez votre choix : ");
            choix = sc.nextInt();
            if(choix < 1 || choix > 5){
                System.out.println("Choix incorrect");
            }
            else{
                switch (choix){
                    case 1 :
                        int flag;
                        System.out.println("Insertion d'un nouvel employé : ");
                        do{
                            idemp = 0;
                            flag = 1;
                            stmt = dbConnect.createStatement();
                            rs = stmt.executeQuery("select * from employe");
                            System.out.println("Matricule du nouvel employé : ");
                            matricule = sc2.nextLine();
                            while(rs.next()){
                                idemp++;
                                String matricule2 = rs.getString("MATRICULE");
                                if((matricule.equals(matricule2))){
                                    flag = 0;
                                    break;
                                }                          
                            }
                            idemp++;
                            if(flag == 0){
                                System.out.println("Matricule déjà pris !");
                            }
                        }while(flag == 0);
                        System.out.println("Nom : ");
                        nom = sc2.nextLine();
                        System.out.println("Prénom : ");
                        prenom = sc2.nextLine();
                        do{
                            flag = 0;
                            stmt = dbConnect.createStatement();
                            rs = stmt.executeQuery("select * from bureau");
                            System.out.println("Id de son bureau : ");
                            idbur = sc.nextInt();
                            while(rs.next()){
                                if(rs.getInt("IDBUR") == idbur){
                                    flag = 1;
                                    break;
                                }
                            }
                            if(flag == 0){
                                System.out.println("Ce bureau n'existe pas");
                            }
                        }while(flag == 0);
                        Employe e = new Employe(idemp, matricule, nom, prenom, idbur);
                        create(e);
                        System.out.println("Employé ajouté !");
                        DBConnection.closeConnection();
                        break;
                    case 2 : 
                        int id2;
                        System.out.println("Quel est l'id de l'employé ?");
                        id2 = sc.nextInt();
                        read(id2);
                        DBConnection.closeConnection();
                        break;
                    case 3 : 
                        System.out.println("Quel est l'id de l'employé ?");
                        idemp = sc.nextInt();
                        stmt = dbConnect.createStatement();
                        rs = stmt.executeQuery("select * from employe");
                        while(rs.next()){
                            if(rs.getInt("IDEMP") == idemp){
                                matricule = rs.getString("MATRICULE");
                                nom = rs.getString("NOM");
                                prenom = rs.getString("PRENOM");
                                idbur = rs.getInt("IDBUR");
                                break;
                            }
                        }                   
                        e = new Employe(idemp, matricule, nom, prenom, idbur);
                        update(e);
                        System.out.println("La modification à bien été effectuée.");
                        DBConnection.closeConnection();
                        break;
                    case 4 : 
                        System.out.println("Quel est l'id de l'employé ");
                        idemp = sc.nextInt();
                        stmt = dbConnect.createStatement();
                        rs = stmt.executeQuery("select * from employe");
                        while(rs.next()){
                            if(rs.getInt("IDEMP") == idemp){
                                matricule = rs.getString("MATRICULE");
                                nom = rs.getString("NOM");
                                prenom = rs.getString("PRENOM");
                                idbur = rs.getInt("IDBUR");
                                break;
                            }
                        }
                        e = new Employe(idemp, matricule, nom, prenom, idbur);
                        delete(e);
                        System.out.println("La suppression à bien été effectuée.");
                        DBConnection.closeConnection();
                        break;
                }
            }
        }while(choix != 5);
    }
    
    /*
        Crée un employé et l'ajoute dans la table Employe 
    */
    
    @Override
    public Employe create(Employe obj) throws SQLException {  
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String query = "INSERT INTO EMPLOYE(idemp,matricule,nom,prenom,idbur) values(?,?,?,?,?)";
        String query2 = "SELECT idemp FROM EMPLOYE WHERE idbur=? AND matricule=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query);PreparedStatement pstm2 = dbConnect.prepareStatement(query2)){
            pstm.setInt(1,idemp);
            pstm.setString(2,matricule);
            pstm.setString(3,nom);
            pstm.setString(4,prenom);
            pstm.setInt(5,idbur);
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur de creation employé, aucune ligne créée");
            }
            pstm2.setInt(1, obj.getIdbur());
            pstm2.setString(2, obj.getMatricule());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idem = rs.getInt("IDEMP");
                    obj.setIdemp(idem);
                    return read(idem);
                }
                else{
                    throw new SQLException("erreur de création employé, record introuvable");
                }
            }
        }
    }
    
    /*
        Retourne les infos d'un employé dont l'id à été entré au clavier ainsi que les messages envoyés par cet employé
    */
    @Override
    public Employe read(int id) throws SQLException {     
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String message, date;
        String query = "select * from employe where idemp = ?";  
        String query2 = "select * from message where idemp = ? order by idmsg desc";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query);PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm.setInt(1, id);
            pstm2.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery();ResultSet rs2 = pstm2.executeQuery()) {
                if (rs.next() || rs2.next()) {
                    String mat = rs.getString("MATRICULE");
                    String name = rs.getString("NOM");
                    String prename = rs.getString("PRENOM");
                    int idb = rs.getInt("IDBUR");
                    System.out.println(mat+" "+name+" "+prename+" du bureau n° "+idb);
                    System.out.println("Message(s) envoyé(s) : ");
                    while(rs2.next()){
                        message = rs2.getString("CONTENU");
                        date = rs2.getString("DATEENVOI");
                        System.out.println(message+", envoyé le "+date);
                    }       
                    System.out.println("\n\n");
                    return new Employe(id, mat, name, prename, idb);
                } else {
                    throw new SQLException("Code inconnu");
                }
            }
        }
    }

    /*
        Modifie les infos d'un employé dont l'id à été entré au clavier
    */
    
    @Override
    public Employe update(Employe obj) throws SQLException {       
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String name, prename;
        int b;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String query = "update employe set nom=?, prenom=?, idbur=? where idemp=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            System.out.println("Nouveau nom : ");
            name = sc.nextLine();
            System.out.println("Nouveau prénom : ");
            prename = sc.nextLine();
            System.out.println("Id du bureau : ");
            b = sc2.nextInt();
            pstm.setString(1, name);
            pstm.setString(2, prename);
            pstm.setInt(3, b);
            pstm.setInt(4, obj.getIdemp());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne client mise à jour");
            }
            return read(obj.getIdbur());
        }
    }
    
    /*
        Supprime de la table un employé dont l'id à été entré au clavier.
    */
    @Override
    public void delete(Employe obj) throws SQLException {   
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String query = "delete from employe where idemp = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, obj.getIdemp());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne employé effacée");
            }

        }
    }
}
