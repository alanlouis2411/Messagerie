/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.pattern;

/**
 *
 * @author alanl
 */
public class Bureau {
    protected String sigle;
    protected int id;
    protected String tel;
    protected String descrip;
    
    private Bureau(BureauBuilder bb){
        this.id = bb.id;
        this.sigle = bb.sigle;
        this.tel = bb.tel;
        this.descrip = bb.descrip;
    }

    public String getSigle() {
        return sigle;
    }

    public int getId() {
        return id;
    }

    public String getTel() {
        return tel;
    }

    public String getDescrip() {
        return descrip;
    }
    
    public static class BureauBuilder{
        protected String sigle;
        protected int id;
        protected String tel;
        protected String descrip;
        
        public BureauBuilder setIdbureau(int id){
            this.id = id;
            return this;
        }
        
        public BureauBuilder setSigle(String sigle){
            this.sigle = sigle;
            return this;
        }
        public BureauBuilder setTel(String tel){
            this.tel = tel;
            return this;
        }
        public BureauBuilder setDescrip(String descrip){
            this.descrip = descrip;
            return this;
        }
        public Bureau build() throws Exception{
            if(sigle == null || tel == null)
                throw new Exception("Infos de construction incomplètes");
            return new Bureau(this);
        }
    }
}
