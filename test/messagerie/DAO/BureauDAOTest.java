/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerie.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import messagerie.Bureau;
import myconnections.DBConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alanl
 */
public class BureauDAOTest {
    
   static Connection dbConnect;
    public BureauDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        DBConnection.closeConnection();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class ClientDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Bureau obj = new Bureau(0,"Test","000000000","");
        BureauDAO instance = new BureauDAO();
        instance.setConnection(dbConnect);
        Bureau expResult = new Bureau(0,"Test","000000000","");
        Bureau result = instance.create(obj);
        
        assertEquals("sigles différents",expResult.getSigle(), result.getSigle());
        assertEquals("tel différents",expResult.getTel(), result.getTel());
        //etc
        assertNotEquals("id non généré",expResult.getIdbur(),result.getIdbur());
        int idclient=result.getIdbur();
        obj=new Bureau(0,"Test","000000000","");
        try{
            Bureau result2 = instance.create(obj);
            fail("exception de doublon non déclenchée");
            instance.delete(result2);
        }
        catch(SQLException e){}
        instance.delete(result);
        
          obj=new Bureau(0,"Test2","000000001","");
        try{
            Bureau result3 = instance.create(obj);
            fail("exception de code postal non déclenchée");
            instance.delete(result3);
        }
        catch(SQLException e){}
       
    }

    /**
     * Test of read method, of class ClientDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int idbureau = 0;
        BureauDAO instance = new BureauDAO();
        instance.setConnection(dbConnect);
        Bureau obj = new Bureau(0,"Test","000000000","");
        Bureau expResult = instance.create(obj);
        idbureau=expResult.getIdbur();
        Bureau result = instance.read(idbureau);
       assertEquals("sigles différents",expResult.getSigle(), result.getSigle());
        assertEquals("tel différents",expResult.getTel(), result.getTel());
        //etc
        assertEquals("id différents",expResult.getIdbur(),result.getIdbur());
        try{
            result=instance.read(0);
            fail("exception d'id inconnu non générée");
        }
        catch(SQLException e){}
       instance.delete(result);
    }

    /**
     * Test of update method, of class ClientDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Bureau obj = new Bureau(0,"Test","000000000","");
        BureauDAO instance = new BureauDAO();
        instance.setConnection(dbConnect);
        obj = instance.create(obj);
        obj.setSigle("Test2");
        //etc
        obj.setTel("000000001");
        //etc
        Bureau expResult=obj;
        Bureau result = instance.update(obj);
        assertEquals(expResult.getSigle(), result.getSigle());
        //etc
        assertEquals(expResult.getTel(), result.getTel());
        //etc
        instance.delete(obj);
        //TODO verifier que si met à jour vers un doublé sigle-tel déjà existant, on a une exception
    }

    /**
     * Test of delete method, of class ClientDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Bureau obj = new Bureau(0,"Test","000000000","");
        BureauDAO instance = new BureauDAO();
        instance.setConnection(dbConnect);
        obj = instance.create(obj);
        instance.delete(obj);
        try {
            instance.read(obj.getIdbur());
            fail("exception de record introuvable non générée");
        }
        catch(SQLException e){}
        //TODO vérifier qu'on a bien une exception en cas de record parent de clé étrangère
    }

    /**
     * Test of rechNom method, of class ClientDAO.
     */
    @Test
    public void testSearch() throws Exception {
        System.out.println("rechNom");
        Bureau obj1 = new Bureau(0,"Test","000000000","");
        Bureau obj2 = new Bureau(0,"Test2","000000001","");
        String nomrech = "Test";
        BureauDAO instance = new BureauDAO();
        instance.setConnection(dbConnect);
        obj1=instance.create(obj1);
        obj2=instance.create(obj2);
        
      
        String result = instance.search(nomrech);
        if(result.contains(obj1.getSigle())) fail("record introuvable "+obj1);
        if(result.contains(obj2.getSigle())) fail("record introuvable "+obj2);
        instance.delete(obj1);
        instance.delete(obj2);
    }   
}
