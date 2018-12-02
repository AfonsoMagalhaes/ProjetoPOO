package po;

import java.io.Serializable;

public class Mestrado extends Pessoa implements Serializable{
    private String hotEvitar;

    public Mestrado(String email, String nome, String hotEvitar) {
        super(email, nome);
        this.hotEvitar = hotEvitar;
    }


    public String GetEvitar() {
        return hotEvitar;
    }

    public void SetEvitar(String hot) {
        this.hotEvitar = hot;
    }

    public String toString() {
        return super.toString() + "Hot: " + hotEvitar;
    }
}
