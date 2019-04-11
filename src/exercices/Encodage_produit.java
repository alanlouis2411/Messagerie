package exercices;
import java.sql.*;
import myconnections.DBConnection;
import java.util.*;
public class Encodage_produit {
    
    public void encoder(){
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query1 = "INSERT INTO API_PRODUIT(numprod,description,phtva,stock)" + "VALUES(?,?,?,?)";
        String query2 = "SELECT idproduit FROM API_PRODUIT WHERE numprod = ? AND description = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            int numcli = 0;
            System.out.println("Nouveau produit !");
            System.out.print("Numero :");
            String num = sc.nextLine();
            System.out.print("Description :");
            String desc = sc2.nextLine();
            System.out.print("Prix HTVA :");
            int prix = sc3.nextInt();
            sc.skip("\n");
            System.out.print("Stock :");
            int stock = sc4.nextInt();
            pstm1.setString(1, num);
            pstm1.setString(2, desc);
            pstm1.setInt(3, prix);
            pstm1.setInt(4, stock);
            int nl = pstm1.executeUpdate();
            System.out.println(nl + "ligne insérée");
            pstm2.setString(1, num);
            pstm2.setString(2, desc);
            pstm2.setInt(3, prix);
            pstm2.setInt(4,stock);
            
            try (ResultSet rs = pstm2.executeQuery()) {

                if (rs.next()) {
                    int nc = rs.getInt(1);
                    System.out.println("numero de produit =" + nc);

                } else {
                    System.out.println("erreur lors de l'insertion ,numero de produit introuvable");
                }

            }
        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
         DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        Encodage_produit pgm = new Encodage_produit();
        pgm.encoder();
    }
}

