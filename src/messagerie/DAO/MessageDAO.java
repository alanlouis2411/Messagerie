
package messagerie.DAO;

import java.sql.*;
import java.util.Scanner;
import messagerie.Message;
import myconnections.DBConnection;

public class MessageDAO extends DAO<Message>{
    Statement stmt = null;
    Statement stmt2 = null;
    Statement stmt3 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    int idmsg, nb_lig;
    public static int idemp2;
    public static int id_destinataire;
    public static String boite = "";
    int id;
    String contenu, date_envoi = "", matricule;
    

    public void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        int flag;
        do{
           Connection dbConnect = DBConnection.getConnection();
           if (dbConnect == null) {
               System.exit(1);
           }
            System.out.println("1) Envoyer message");
            System.out.println("2) Boite de réception");
            System.out.println("3) Se déconnecter");
            System.out.println("\nEntrez votre choix : ");
            choix = sc.nextInt();
            sc.skip("\n");
            switch (choix){
                case 1 :
                    System.out.println("Entrez votre message (50 caractères max) : ");
                    contenu = sc.nextLine();
                    do{
                        flag = 0;
                        stmt = dbConnect.createStatement();
                        stmt2 = dbConnect.createStatement();
                        stmt3 = dbConnect.createStatement();
                        rs = stmt.executeQuery("select * from employe");
                        rs2 = stmt2.executeQuery("select * from message");
                        rs3 = stmt3.executeQuery("select * from employe order by idemp");
                        System.out.println("Entrez le matricule de votre destinataire : ");
                        while(rs3.next()){
                            System.out.println(rs3.getString("PRENOM")+" "+rs3.getString("NOM")+" : "+rs3.getString("MATRICULE"));
                        }
                        System.out.println("--> ");
                        matricule = sc.nextLine();
                        while(rs.next()){
                            if(rs.getString("MATRICULE").equals(matricule)){
                                flag = 1;
                                id_destinataire = rs.getInt("IDEMP");
                                break;
                            }
                        }
                        if(flag == 0){
                            System.out.println("Le matricule entré n'existe pas");
                        }
                    }while(flag == 0);
                    while(rs2.next()){
                        nb_lig++;
                    }
                    nb_lig++;
                    Message mess = new Message(nb_lig, contenu, date_envoi, idemp2);
                    create(mess);
                    System.out.println("Le message à bien été envoyé ! =) ");
                    break;
                case 2 : 
                    read(idemp2);
                    break;
            }
        }while(choix != 3);
        DBConnection.closeConnection();
    }

    /*
        Crée un objet Message et l'ajoute dans la table Message ainsi que dans la table infos (la date de lecture est mise à NULL 
        dans infos, elle prendra la valeur sysdate quand le destinataire ira consulter sa boite de réception.
    */
    @Override
    public Message create(Message obj) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        Scanner sc = new Scanner(System.in);
        String c;
        String query = "INSERT INTO message(idmsg,contenu,dateenvoi,idemp) VALUES(?,?,sysdate,?)";
        String query2 = "INSERT INTO INFOS(datelecture,idemp,idmsg) VALUES(?,?,?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query);PreparedStatement pstm2 = dbConnect.prepareStatement(query2)){
            pstm.setInt(1, obj.getIdmsg());
            pstm.setString(2, obj.getContenu());
            pstm.setInt(3, obj.getIdemp());
            pstm2.setString(1, null);
            pstm2.setInt(2, id_destinataire);
            pstm2.setInt(3, obj.getIdmsg());
            int n = pstm.executeUpdate();
            int n2 = pstm2.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur de creation message, aucune ligne créée");
            }
            return new Message(obj.getIdmsg(), obj.getContenu(), obj.getDate_envoi(), obj.getIdemp());
        }
    }
    
    /*
        Affiche les messages reçus par un employé qui se sera connecté au préalable avec son matricule.
        Dès qu'il choisit de consulter ses messages reçus, ceux dont la date de lecture était NULL auront alors
        sysdate comme date de lecture. Les messages reçus s'affichent avec le contenu et l'envoyeur, et apparaissent
        du plus récent au plus ancien.
    */
    @Override
    public Message read(int id) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String c = "", date = "", nom, prenom;
        int ide;
        int flag = 0;
        String query = "select * from infos where idemp = ?";
        String query2 = "update infos set datelecture = sysdate where idemp = ? and datelecture is null";
        String query3 = "select * from message where idmsg = ?";
        String query4 = "select prenom, nom from employe where idemp = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query);PreparedStatement pstm2 = dbConnect.prepareStatement(query2);PreparedStatement pstm3 = dbConnect.prepareStatement(query3);PreparedStatement pstm4 = dbConnect.prepareStatement(query4)) {
            pstm.setInt(1, id);
            pstm2.setInt(1, id);
            pstm2.executeUpdate();
            try (ResultSet rs = pstm.executeQuery()) {               
                    System.out.println("Message(s) reçu(s) : \n");
                    boite = "";
                    while(rs.next()){
                        flag = 1;
                        int idmsg2 = rs.getInt("IDMSG"); 
                        pstm3.setInt(1, idmsg2);
                        ResultSet rs3 = pstm3.executeQuery();
                        rs3.next();
                        c = rs3.getString("CONTENU");
                        date = rs3.getString("DATEENVOI");
                        ide = rs3.getInt("IDEMP");
                        pstm4.setInt(1, ide);
                        ResultSet rs4 = pstm4.executeQuery();
                        rs4.next();
                        nom = rs4.getString("NOM");
                        prenom = rs4.getString("PRENOM");
                        boite = boite + "\n" + c+", envoyé le "+date+" par "+prenom+" "+nom+"\n";
                        System.out.println(c+", envoyé le "+date+" par "+prenom+" "+nom+"\n");
                    }
                if(flag == 1){
                return new Message(idmsg, c, date, idemp2);
                }
                else {
                    throw new SQLException("Code inconnu");
                }                
            }
        } 
    }

    @Override
    public Message update(Message obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Message obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
