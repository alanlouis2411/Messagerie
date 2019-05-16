
package messagerie;

public class Bureau {
    protected int idbur;
    protected String sigle;
    protected String tel;
    protected String description;
    
    public Bureau(int idbur, String sigle, String tel, String description){
        this.idbur = idbur;
        this.sigle = sigle;
        this.tel = tel;
        this.description = description;
    }

    public int getIdbur() {
        return idbur;
    }

    public String getSigle() {
        return sigle;
    }

    public String getTel() {
        return tel;
    }
    
    public String getDescription() {
        return description;
    }

    public void setIdbur(int idbur) {
        this.idbur = idbur;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
     public void setDescription(String des) {
        this.description = des;
    }
    @Override
    public String toString() { 
        return "Bureau{" + "idbureau : " + idbur + ", sigle : " + sigle + ", téléphone : " + tel + ", description : " + description;
    }
}
