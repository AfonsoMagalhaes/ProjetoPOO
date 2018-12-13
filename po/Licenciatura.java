package po;

import java.io.Serializable;

public class Licenciatura extends Pessoa implements Serializable{


    public Licenciatura(String nome, String email) {
        super(nome, email);

    }

    @Override
    public boolean isMestrado() {
        return false;
    }

}