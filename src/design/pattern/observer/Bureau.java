
package design.pattern.observer;

public class Bureau extends Subject{
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
        notifyObservers();
    }
    
    public void setDescription(String des) {
        this.description = des;
    }
    @Override
    public String toString() { 
        return "Bureau{" + "idbureau : " + idbur + ", sigle : " + sigle + ", téléphone : " + tel + ", description : " + description;
    }
    @Override
    public String getNotification() {
        return "Nouveau numéro du bureau numéro " + idbur + " = " + tel;
    }
}
