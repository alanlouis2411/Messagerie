/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus_messagerie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import messagerie.Bureau;
import messagerie.DAO.BureauDAO;
import myconnections.DBConnection;

/**
 *
 * @author alanl
 */
public class MenuBureau {
    
    Statement stmt = null, stmt2 = null;
    ResultSet rs = null, rs2 = null;
    String sigle, tel = "";
    int id, nb_lig;
    Bureau b;
    
    public void menuBureau() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int choix = 0;
        do{
            Connection dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
                System.exit(1);
            }
            System.out.println("1) Créer un nouveau bureau");
            System.out.println("2) Voir les informations d'un bureau");
            System.out.println("3) Modifier un bureau");
            System.out.println("4) Supprimer un bureau");
            System.out.println("5) Retour");
            System.out.println("\n Entrez votre choix : ");
            choix = sc.nextInt();
            if(choix < 1 || choix > 5){
                System.out.println("Choix incorrect");
            }
            else{
                switch (choix) {
                    case 1 :
                    int flag;
                    System.out.println("Création d'un nouveau bureau : ");
                    do{
                        nb_lig = 0;
                        flag = 1;
                        stmt = dbConnect.createStatement();
                        rs = stmt.executeQuery("select * from bureau");
                        System.out.println("Sigle du nouveau bureau : ");
                        sigle = sc2.nextLine();
                        System.out.println("Numéro de téléphone du nouveau bureau : ");
                        tel = sc2.nextLine();
                        while(rs.next()){
                            nb_lig++;
                            String sig = rs.getString("SIGLE");
                            String t = rs.getString("TEL");                           
                            if((sig.equals(sigle))||(t.equals(tel))){
                                flag = 0;
                                break;
                            }
                        }
                        nb_lig++;
                        if(flag == 0){
                            System.out.println("Ce bureau existe déjà !");
                        }
                    }while(flag == 0);
                    b = new Bureau(nb_lig, sigle, tel);
                    //BureauDAO.create(b);
                    System.out.println("Bureau créé!");   
                        break;
                    case 2 :
                        break;
                    case 3 :
                        break;
                    case 4 :
                        break;
                }
            }
        }while(choix != 5);
    }
}
