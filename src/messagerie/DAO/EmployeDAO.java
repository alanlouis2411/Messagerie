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
    public static String liste_msgs = "";
    public static String new_nom, new_prenom;
    public static int new_id_bur;
    
         
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
                        System.out.println("Nouveau nom : ");
                        new_nom = sc.nextLine();
                        System.out.println("Nouveau prénom : ");
                        new_prenom = sc.nextLine();
                        System.out.println("Id du bureau : ");
                        new_id_bur = sc2.nextInt();
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
            pstm.setInt(1,obj.getIdemp());
            pstm.setString(2,obj.getMatricule());
            pstm.setString(3,obj.getNom());
            pstm.setString(4,obj.getPrenom());
            pstm.setInt(5,obj.getIdbur());
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
        String query2 = "select * from message where idemp = ? order by idmsg";
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
                    liste_msgs += "\n" +mat+" "+name+" "+prename+" du bureau n° "+idb;
                    System.out.println("Message(s) envoyé(s) : ");
                    liste_msgs = liste_msgs + "\nMessage(s) envoyé(s) : ";
                    while(rs2.next()){
                        message = rs2.getString("CONTENU");
                        date = rs2.getString("DATEENVOI");
                        System.out.println(message+", envoyé le "+date);
                        liste_msgs += "\n" + message + ", envoyé le " + date;
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
        String query = "update employe set nom=?, prenom=?, idbur=? where idemp=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, new_nom);
            pstm.setString(2, new_prenom);
            pstm.setInt(3, new_id_bur);
            pstm.setInt(4, obj.getIdemp());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne client mise à jour");
            }
            return read(obj.getIdemp());
        }
    }
    
    /*
        Supprime de la table un employé dont l'id à été entré au clavier.
    */
    @Override
    public void delete(Employe obj) throws SQLException {
        int id_virer = obj.getIdemp();
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String query = "delete from employe where idemp = ?";
        String query2 = "select * from message where idemp = ?";
        String query3 = "delete from message where idmsg = ?";
        String query4 = "delete from infos where idmsg = ?";
        String query5 = "select * from infos where idemp = ?";
        String query6 = "delete from infos where idmsg = ?";
        String query7 = "delete from message where idmsg = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query);PreparedStatement pstm2 = dbConnect.prepareStatement(query2);PreparedStatement pstm3 = dbConnect.prepareStatement(query3);PreparedStatement pstm4 = dbConnect.prepareStatement(query4);
                PreparedStatement pstm5 = dbConnect.prepareStatement(query5);PreparedStatement pstm6 = dbConnect.prepareStatement(query6);PreparedStatement pstm7 = dbConnect.prepareStatement(query7)) {
            pstm.setInt(1, id_virer);
            pstm2.setInt(1, id_virer);
            ResultSet rs = pstm2.executeQuery();
            while(rs.next()){
                int idmsg = rs.getInt("IDMSG");
                pstm4.setInt(1, idmsg);
                pstm4.executeUpdate();
                pstm3.setInt(1, idmsg);
                pstm3.executeUpdate();
            }
            pstm5.setInt(1, id_virer);
            rs = pstm5.executeQuery();
            while(rs.next()){
                int idmsg = rs.getInt("IDMSG");
                pstm6.setInt(1, idmsg);
                pstm6.executeUpdate();
                pstm7.setInt(1, idmsg);
                pstm7.executeUpdate();
            }
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne employé effacée");
            }

        }
    }
}
