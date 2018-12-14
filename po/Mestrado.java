package po;

import java.io.Serializable;

public class Mestrado extends Aluno implements Serializable {


    public Mestrado(String nome, String email) {
        super(nome, email);
    }

    @Override
    public boolean isMestrado() {
        return true;
    }

}
