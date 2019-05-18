package messagerie.DAO;
import messagerie.Bureau;
import java.sql.*;
import menus_messagerie.MenuBureau;
import myconnections.DBConnection;


public class BureauDAO extends DAO<Bureau>{
    Statement stmt = null, stmt2 = null;
    ResultSet rs = null, rs2 = null;
    String sigle, tel = "";
    int id, nb_lig;
    Bureau b;
    public static String liste;
    public static String s, tele, descrip, description;
    public static String new_sig, new_tel;
    public static String list_desc;
    
    
    /*
        ajoute un nouveau bureau dans la table Bureau, on vérifie dans le menu que le bureau entré n'existe pas déjà.
    */
    @Override
    public Bureau create(Bureau obj) throws SQLException{
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String query = "INSERT INTO BUREAU(idbur,sigle,tel,description) values(?,lower(?),?,lower(?))";
        String query2 = "SELECT idbur FROM BUREAU WHERE sigle=lower(?) and tel=lower(?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query);PreparedStatement pstm2 = dbConnect.prepareStatement(query2)){
            pstm.setInt(1,obj.getIdbur());
            pstm.setString(2,obj.getSigle());
            pstm.setString(3,obj.getTel());
            pstm.setString(4,obj.getDescription());
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
                    description = rs.getString("DESCRIPTION");
                    //System.out.println("Id : " + idb + ", sigle : " + s + " N° de téléphone : " + tele+"\n\n");
                    return new Bureau(idb, s, tele, description);                 
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
        String query = "update bureau set sigle=lower(?),tel=?,description=lower(?) where idbur=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(4, obj.getIdbur());
            pstm.setString(1, MenuBureau.new_sig);
            pstm.setString(2, MenuBureau.new_tel);
            pstm.setString(3, MenuBureau.new_desc);
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
        String query = "select * from bureau where DESCRIPTION like upper(?) or DESCRIPTION like lower(?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, "%"+s+"%");
            pstm.setString(2, "%"+s+"%");
            rs = pstm.executeQuery();
            while(rs.next()){
                list_desc += "\nSigle : " + rs.getString("SIGLE") + ", n° de téléphone : " + rs.getString("TEL") + ", description : " + rs.getString("DESCRIPTION");
            }
        }
        System.out.println(list_desc);
        return list_desc;
    }
}
