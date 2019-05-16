/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus_messagerie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import messagerie.Bureau;
import messagerie.DAO.BureauDAO;
import static messagerie.DAO.BureauDAO.new_sig;
import static messagerie.DAO.BureauDAO.new_tel;
import myconnections.DBConnection;

/**
 *
 * @author alanl
 */
public class MenuBureau {
    Statement stmt = null, stmt2 = null;
    ResultSet rs = null, rs2 = null;
    String sigle, tel = "", descrip="";
    int id, nb_lig;
    Bureau b;
    public static String liste;
    public static String s, tele;
    public static String new_sig, new_tel, new_desc;
    public static String list_desc;
    
    public void menu() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        BureauDAO bdao = new BureauDAO();
        int choix = 0;
        do{
            Connection dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
                System.exit(1);
            }
            System.out.println("1) Créer un nouveau bureau");
            System.out.println("2) Voir un bureau (recherche par id)");
            System.out.println("3) Voir un bureau (recherche par description)");
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
                        System.out.println("Description : ");
                        descrip = sc2.nextLine();
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
                    b = new Bureau(nb_lig, sigle, tel, descrip);
                    bdao.create(b);
                    System.out.println(bdao.read(nb_lig));
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
                    System.out.println(bdao.read(id2));
                    break;
                case 3 :
                    System.out.println("Entrez votre recherche :");
                    String s = sc2.nextLine();
                    bdao.search(s);
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
                                descrip = rs.getString("DESCRIPTION");
                                flag4 = 1;
                                break;
                            }
                        } 
                        if(flag4 == 0){
                            System.out.println("L'id entré ne correspond à aucun bureau.");
                        }
                    }while(flag4 == 0);
                    b = new Bureau(id, sigle, tel, descrip);
                    do{
                        flag4 = 0;
                        System.out.println("Nouveau sigle : ");
                        new_sig = sc2.nextLine();
                        System.out.println("Nouveau numéro : ");
                        new_tel = sc2.nextLine();
                        System.out.println("Nouvelle description : ");
                        new_desc = sc2.nextLine();
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
                    bdao.update(b);
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
                            descrip = rs.getString("DESCRIPTION");
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
                        b = new Bureau(id, sigle, tel, descrip);
                        bdao.delete(b);
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
    
}
