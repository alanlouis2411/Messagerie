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
    
    public BureauDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("Erreur de connexion");
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
     * Test of menu method, of class BureauDAO.
     */
    @Test
    public void testMenu() throws Exception {
        System.out.println("menu");
        BureauDAO instance = new BureauDAO();
        instance.menu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class BureauDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Bureau obj = new Bureau(0, "test", "00000");
        BureauDAO instance = new BureauDAO();
        Connection dbConnect = DBConnection.getConnection();
        instance.setConnection(dbConnect);
        Bureau expResult = new Bureau(0, "test", "00000");
        Bureau result = instance.create(obj);
        assertEquals("Sigles différents",expResult.getSigle(), result.getSigle());
        assertEquals("Num de tel différents",expResult.getTel(), result.getTel());
        
        assertNotEquals("Id non généré", expResult.getIdbur(), result.getIdbur());
        int idbur = result.getIdbur();
        obj = new Bureau(0,"test2","000002");
        try{
            Bureau result2 = instance.create(obj);
            fail("Exception non déclenchée");
            instance.delete(result2);
        }
        catch(SQLException e){}
        instance.delete(result);
        obj = new Bureau(0,"test2","000002");
        /*try{
            Bureau result3 = instance.create(obj);
            
        }*/
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class BureauDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int idb = 0;
        BureauDAO instance = new BureauDAO();
        Bureau expResult = null;
        Bureau result = instance.read(idb);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class BureauDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Bureau obj = null;
        BureauDAO instance = new BureauDAO();
        Bureau expResult = null;
        Bureau result = instance.update(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class BureauDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Bureau obj = null;
        BureauDAO instance = new BureauDAO();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
