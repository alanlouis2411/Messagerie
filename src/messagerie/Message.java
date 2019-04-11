
package messagerie;

public class Message {
    protected int idmsg, idemp;
    protected String contenu, date_envoi;
    
    public Message(int idmsg, String contenu, String date_envoi, int idemp){
        this.idmsg = idmsg;
        this.contenu = contenu;
        this.date_envoi = date_envoi;
        this.idemp = idemp;
    }

    public int getIdmsg() {
        return idmsg;
    }

    public int getIdemp() {
        return idemp;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDate_envoi() {
        return date_envoi;
    }

    public void setIdmsg(int idmsg) {
        this.idmsg = idmsg;
    }

    public void setIdemp(int idemp) {
        this.idemp = idemp;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate_envoi(String date_envoi) {
        this.date_envoi = date_envoi;
    }
    
    @Override
    public String toString() { 
        return "Message{" + "idmessage : " + idmsg + ", contenu : " + contenu + ", date d'envoi : " + date_envoi + ", id de l'envoyeur : " + idemp;
    }
}
