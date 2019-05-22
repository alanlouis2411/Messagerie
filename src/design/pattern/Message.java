/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.pattern;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author alanl
 */
public class Message {
    protected String expediteur;
    protected int id;
    protected String contenu;
    protected String date_envoi;
    protected ArrayList<Employe> destinataires = new ArrayList();
    
    private Message(MessageBuilder mb){
        this.id = mb.id;
        this.contenu = mb.contenu;
        this.date_envoi = mb.date_envoi;
        this.expediteur = mb.expediteur;
        this.destinataires = mb.destinataires;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public int getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDate_envoi() {
        return date_envoi;
    }

    public ArrayList<Employe> getDestinataires() {
        return destinataires;
    }

    @Override
    public String toString() { 
        return "Message{" + "expediteur = " + expediteur + ", id = " + id + ", contenu = " + contenu + ", date_envoi = " + date_envoi + ", destinataires = " + destinataires + '}';
    }
    
    
    
    
    public static class MessageBuilder{
        protected String expediteur;
        protected int id;
        protected String contenu;
        protected String date_envoi;
        protected ArrayList<Employe> destinataires = new ArrayList();
        
        public MessageBuilder setId(int id){
            this.id = id;
            return this;
        }
        
        public MessageBuilder setExpediteur(String expediteur){
            this.expediteur = expediteur;
            return this;
        }
        public MessageBuilder setContenu(String contenu){
            this.contenu = contenu;
            return this;
        }
        public MessageBuilder setDateenvoi(String date_envoi){
            this.date_envoi = date_envoi;
            return this;
        }
        public MessageBuilder setDestinataires(ArrayList destinataires){
            this.destinataires = destinataires;
            return this;
        }
        public Message build() throws Exception{
            if(expediteur == null || contenu == null || date_envoi == null || destinataires.isEmpty())
                throw new Exception("Infos de construction incomplètes");
            return new Message(this);
        }
    }
}
