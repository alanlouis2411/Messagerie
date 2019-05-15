package messagerie.DAO;
import messagerie.Bureau;
import java.sql.*;
import myconnections.DBConnection;
import java.util.*;

public class BureauDAO extends DAO<Bureau>{
    Statement stmt = null, stmt2 = null;
    ResultSet rs = null, rs2 = null;
    String sigle, tel = "";
    int id, nb_lig;
    Bureau b;
    public static String liste;
    public static String s, tele;
    public static String new_sig, new_tel;
    public static String list_desc;
    
    
    /*
        menu CRUD permettant de manipuler des objets de type Bureau en appelant chaque méthode sur un objet créé avec des 
        données entrées au clavier par l'utilisateur en vérifiant ce qu'il rentre(par ex vérifier qu'aucun employé n'est assigné
        à un bureau avant de le supprimer). 
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
            System.out.println("1) Créer un nouveau bureau");
            System.out.println("2) Voir un bureau (recherche par id)");
            System.out.println("3) Voir un bureau (recherche par sigle)");
            System.out.println("4) Modifier un bureau");
            System.out.println("5) Supprimer un bureau");
            System.out.println("6) Retour");
            System.out.println("\n Entrez votre choix : ");
            choix = sc.nextInt();
            if(choix < 1 || choix > 6){
                System.out.println("Choix incorrect");
            }
            else{
                switch (choix){
                case 1:
                    int flag;
                    System.out.println("Création d'un nouveau bureau : ");
                    do{
                        nb_lig = 1;
                        flag = 1;
                        stmt = dbConnect.createStatement();
                        rs = stmt.executeQuery("select * from bureau order by idbur desc");
                        if(rs.next()){
                            nb_lig = rs.getInt("IDBUR") + 1;
                        }
                        System.out.println("Sigle du nouveau bureau : ");
                        sigle = sc2.nextLine();
                        System.out.println("Numéro de téléphone du nouveau bureau : ");
                        tel = sc2.nextLine();
                        while(rs.next()){
                            String sig = rs.getString("SIGLE");
                            String t = rs.getString("TEL");                           
                            if((sig.equals(sigle))||(t.equals(tel))){
                                flag = 0;
                                break;
                            }
                        }
                        if(flag == 0){
                            System.out.println("Ce bureau existe déjà !");
                        }
                    }while(flag == 0);
                    b = new Bureau(nb_lig, sigle, tel);
                    create(b);
                    System.out.println(read(nb_lig));
                    System.out.println("Bureau créé!");   
                    
                    break;
                case 2:
                    int id2;
                    int flag3 = 0;
                    do{
                        System.out.println("Quel est l'id du bureau ?");                   
                        id2 = sc.nextInt();
                        stmt = dbConnect.createStatement();
                        rs = stmt.executeQuery("select * from bureau");
                        while(rs.next()){
                            if(id2 == rs.getInt("IDBUR")){
                                flag3 = 1;
                            }
                        }
                        if(flag3 == 0){
                            System.out.println("Cet id n'existe pas !");
                        }
                    }while(flag3 == 0);
                    System.out.println(read(id2));
                    break;
                case 3 :
                    System.out.println("Entrez votre recherche :");
                    String s = sc2.nextLine();
                    search(s);
                    break;
                case 4:                   
                    stmt = dbConnect.createStatement();
                    int flag4;
                    do{
                        System.out.println("Quel est l'id du bureau ?");
                        id = sc.nextInt();
                        flag4 = 0;
                        rs = stmt.executeQuery("select * from bureau");
                        while(rs.next()){
                            if(rs.getInt("IDBUR") == id){
                                sigle = rs.getString("SIGLE");
                                tel = rs.getString("TEL");
                                flag4 = 1;
                                break;
                            }
                        } 
                        if(flag4 == 0){
                            System.out.println("L'id entré ne correspond à aucun bureau.");
                        }
                    }while(flag4 == 0);
                    b = new Bureau(id, sigle, tel);
                    do{
                        flag4 = 0;
                        System.out.println("Nouveau sigle : ");
                        new_sig = sc2.nextLine();
                        System.out.println("Nouveau numéro : ");
                        new_tel = sc2.nextLine();
                        stmt = dbConnect.createStatement();
                        rs = stmt.executeQuery("select * from bureau");
                        while(rs.next()){
                            String sig = rs.getString("SIGLE");
                            String t = rs.getString("TEL");                           
                            if((sig.equals(new_sig))||(t.equals(new_tel))){
                                flag4 = 1;
                                break;
                            }
                        }
                        if(flag4 == 1){
                            System.out.println("Le sigle/n° de téléphone est déjà pris !");
                        }
                    }while(flag4 == 1);
                    update(b);
                    System.out.println("La modification à bien été effectuée.");
                    break;
                case 5:             
                    int flag2 = 0;
                    do{
                        System.out.println("Quel est l'id du bureau ?");
                        id = sc.nextInt();
                        stmt = dbConnect.createStatement();
                        rs = stmt.executeQuery("select * from bureau");
                        while(rs.next()){
                            if(id == rs.getInt("IDBUR")){
                                flag2 = 1;
                                break;
                            }
                        }
                        if(flag2 == 0){
                            System.out.println("L'id e correspond à aucun bureau");
                        }
                    }while(flag2 == 0);
                    flag2 = 0;
                    stmt = dbConnect.createStatement();
                    rs = stmt.executeQuery("select * from bureau");
                    String q = "select * from employe where idbur = ?";
                    while(rs.next()){
                        if(rs.getInt("IDBUR") == id){
                            sigle = rs.getString("SIGLE");
                            tel = rs.getString("TEL");
                            break;
                        }
                    }
                    try(PreparedStatement pstm = dbConnect.prepareStatement(q)){
                        pstm.setInt(1, id);
                        ResultSet rs2 = pstm.executeQuery();
                        while(rs2.next()){
                            String n = rs2.getString("NOM");
                            String p = rs2.getString("PRENOM");
                            System.out.println(p+" "+n+" fait partie de ce bureau.");
                            flag2 = 1;
                        }
                    }
                    if(flag2 == 0){
                        b = new Bureau(id, sigle, tel);
                        delete(b);
                        System.out.println("La suppression à bien été effectuée.");
                    }
                    else{
                        System.out.println("Assignez ces employés à un autre bureau avant de le supprimer.");
                    }
                    break;
                }
            }
        }while(choix != 6);
        DBConnection.closeConnection();
    }
    /*
        ajoute un nouveau bureau dans la table Bureau, on vérifie dans le menu que le bureau entré n'existe pas déjà.
    */
    @Override
    public Bureau create(Bureau obj) throws SQLException{
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String query = "INSERT INTO BUREAU(idbur,sigle,tel) values(?,?,?)";
        String query2 = "SELECT idbur FROM BUREAU WHERE sigle=? and tel=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query);PreparedStatement pstm2 = dbConnect.prepareStatement(query2)){
            pstm.setInt(1,obj.getIdbur());
            pstm.setString(2,obj.getSigle());
            pstm.setString(3,obj.getTel());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur de creation bureau, aucune ligne créée");
            }
            pstm2.setString(1, obj.getSigle());
            pstm2.setString(2, obj.getTel());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idbur = rs.getInt(1);
                    obj.setIdbur(idbur);
                    return read(idbur);
                }
                else{
                    throw new SQLException("erreur de création bureau, record introuvable");
                }
            }
        }

    }
    
    /* 
        Retourne les infos d'un bureau dont on entre l'id au clavier 
    */
    
    @Override
    public Bureau read(int idb) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String query = "select * from bureau where idbur = ?"; 
        String query2 = "select * from employe where idbur = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query); PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm.setInt(1, idb);
            pstm2.setInt(1, idb);
            try (ResultSet rs = pstm.executeQuery(); ResultSet rs2 = pstm2.executeQuery()) {
                liste = "\nEmployés affectés à ce bureau :";
                while(rs2.next()){
                    liste = liste + "\n " + rs2.getString("PRENOM") + " " + rs2.getString("NOM");
                }
                if (rs.next()) {
                    s = rs.getString("SIGLE");
                    tele = rs.getString("TEL");
                    //System.out.println("Id : " + idb + ", sigle : " + s + " N° de téléphone : " + tele+"\n\n");
                    return new Bureau(idb, s, tele);                 
                }
                else {
                    throw new SQLException("Code inconnu");
                }
                

            }
        } 
    }
    /* 
        Modifie les infos d'un bureau dont on entre l'id au clavier.
    */
    
    @Override
    public Bureau update(Bureau obj) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String query = "update bureau set sigle=?,tel=? where idbur=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(3, obj.getIdbur());
            pstm.setString(1, new_sig);
            pstm.setString(2, new_tel);
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne bureau mise à jour");
            }
            return read(obj.getIdbur());
        }
    }   
    /*
        Supprime un bureau de la table (on vérifie dans le menu qu'aucun employé ne soit assigné au bureau qu'on souhaite supprimer
        avant d'appeler la méthode sur le bureau en question).
    */

    @Override
    public void delete(Bureau obj) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String query = "delete from bureau where idbur= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {

            pstm.setInt(1, obj.getIdbur());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne bureau effacée");
            }

        }
    }
    
    public String search(String s) throws SQLException{
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        list_desc = "Bureau(x) correspondant(s) : ";
        String query = "select * from bureau where sigle like upper(?) or sigle like lower(?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, "%"+s+"%");
            pstm.setString(2, "%"+s+"%");
            rs = pstm.executeQuery();
            while(rs.next()){
                list_desc += "\nSigle : " + rs.getString("SIGLE") + ", n° de téléphone : " + rs.getString("TEL");
            }
        }
        System.out.println(list_desc);
        return list_desc;
    }
}
