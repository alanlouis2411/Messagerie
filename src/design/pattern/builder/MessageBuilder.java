
package design.pattern.builder;

import design.pattern.Message;
import java.util.ArrayList;
import messagerie.Employe;

public class MessageBuilder {
    public static void main(String [] args){
        ArrayList<Employe> arr = new ArrayList();
        Employe emp = new Employe(1, "10", "louis", "alan", 1);
        arr.add(emp);
        try{
            Message m1 = new Message.MessageBuilder().
            setContenu("bonjour").
            setDateenvoi("test").
            setExpediteur("Alan").
            setId(1).
            setDestinataires(arr).
            build();
            System.out.println(m1);
        }
        catch(Exception e){
            System.out.println("Erreur : " + e);
        }
        
        try{
            Message m2 = new Message.MessageBuilder().
            setContenu("bonjour").
            setDateenvoi("test").
            setId(1).
            setDestinataires(arr).
            build();
            System.out.println(m2);
        }
        catch(Exception e){
            System.out.println("Erreur : " + e);
        }
    }
}
