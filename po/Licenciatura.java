package po;

import java.io.Serializable;

public class Licenciatura extends Pessoa implements Serializable{
    private String hot;

    public Licenciatura(String email, String nome,String hot) {
        super(email, nome);
        this.hot = hot;
    }


    public String GetHot() {
        return hot;
    }

    public void SetHot(String hot) {
        this.hot = hot;
    }

    public String toString() {
        return super.toString() + "Hot: " + hot;
    }
}