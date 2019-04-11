
package messagerie;

public class Bureau {
    protected int idbur;
    protected String sigle;
    protected String tel;
    
    public Bureau(int idbur, String sigle, String tel){
        this.idbur = idbur;
        this.sigle = sigle;
        this.tel = tel;
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

    public void setIdbur(int idbur) {
        this.idbur = idbur;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    @Override
    public String toString() { 
        return "Bureau{" + "idbureau : " + idbur + ", sigle : " + sigle + ", téléphone : " + tel;
    }
}
