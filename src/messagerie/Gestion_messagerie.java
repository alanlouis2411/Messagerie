
package messagerie;

import java.sql.*;
import java.util.Scanner;
import menus_messagerie.MenuBureau;
import messagerie.DAO.BureauDAO;
import messagerie.DAO.EmployeDAO;
import messagerie.DAO.MessageDAO;
import myconnections.DBConnection;

/**
 *
 * @author alanl
 */
public class Gestion_messagerie {
    Statement stmt = null;
    ResultSet rs = null;
    String matricule, nom, prenom;
    EmployeDAO empdao = new EmployeDAO();
    BureauDAO burdao = new BureauDAO();
    MessageDAO messdao = new MessageDAO();
    MenuBureau mb = new MenuBureau();
    int flag;
    
    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
       Gestion_messagerie gm = new Gestion_messagerie();
       gm.menu_messagerie();
    } 
   
    /**
     *
     * @throws SQLException
     */
    public void menu_messagerie() throws SQLException{
       Scanner sc = new Scanner(System.in);
       int choix = 0;
       int choix2 = 0;
       do{
           Connection dbConnect = DBConnection.getConnection();
           if (dbConnect == null) {
               System.exit(1);
           }
           System.out.println("1) Administrateur");
           System.out.println("2) Employé");
           System.out.println("3) Quitter");
           System.out.println("\nEntrez votre choix : ");
           choix = sc.nextInt();
           if(choix < 1 || choix > 3){
               System.out.println("Choix incorrect");
           }
           else{
               switch (choix){
                   case 1 :
                       do{
                           System.out.println("1) Gestion bureaux");
                           System.out.println("2) Gestion employés");
                           System.out.println("3) Revenir au menu principal");
                           System.out.println("\nEntrez votre choix : ");
                           choix2 = sc.nextInt();
                           if(choix2 < 1 || choix2 > 3){
                               System.out.println("Choix incorrect");
                           }
                           else{
                               switch (choix2){
                                   case 1 : 
                                       //burdao.menu();
                                       mb.menu();
                                       break;
                                   case 2 :
                                       empdao.menu();
                                       break;
                               }
                           }
                       }while(choix2 != 3);
                       break;
                   case 2 : 
                       empConnect();
                       messdao.menu();
                       break;
               }
           }
       }while(choix != 3);
       DBConnection.closeConnection();
       System.out.println("Vous avez quitté");
   }
   
    /**
     *
     * @throws SQLException
     */
    public void empConnect() throws SQLException{
       Connection dbConnect = DBConnection.getConnection();
       if (dbConnect == null) {
           System.exit(1);
       }
       Scanner sc = new Scanner(System.in);
       do{
            stmt = dbConnect.createStatement();
            rs = stmt.executeQuery("select * from employe");
            flag = 0;
            System.out.println("Entrez votre matricule : ");
            matricule = sc.nextLine();
            while(rs.next()){
                if(rs.getString("MATRICULE").equals(matricule)){
                    flag = 1;
                    MessageDAO.idemp2 = rs.getInt("IDEMP");
                    nom = rs.getString("NOM");
                    prenom = rs.getString("PRENOM");
                    break;
                }
            }
            if(flag == 0){
                System.out.println("Aucun matricule correspondant. Réessayez.");
            }
        }while(flag == 0);
        System.out.println("\nUtilisateur connecté : " +nom+ "  " +prenom+ "  " +matricule);
   }
}
