
package design.pattern.observer;

public class Messagerie {
    public static void main(String [] args){
        Bureau b1 = new Bureau(1, "heph", "065123456", "haute école");
        Bureau b2 = new Bureau(2, "umons", "065654321", "université");
        Employe e1 = new Employe(1, "10", "louis", "alan", 1);
        Employe e2 = new Employe(2, "20", "duprez", "jennifer", 2);
        b1.addObserver(e1);
        b2.addObserver(e2);
        b1.setTel("test");
        b2.setTel("testo");
    }
}
