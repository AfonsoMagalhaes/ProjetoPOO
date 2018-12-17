package po;

import java.io.Serializable;

/**
 * Representa um aluno de licenciatura.
 */
public class Licenciatura extends Aluno implements Serializable {

    /**
     * Cria um aluno de licenciatura.
     * @param nome String com o nome do aluno.
     * @param email String com o email do aluno.
     */
    public Licenciatura(String nome, String email) {
        super(nome, email);

    }

    /**
     * Diz que o aluno é de licenciatura.
     * @return false, pois o aluno é de licenciatura.
     */
    @Override
    public boolean isMestrado() {
        return false;
    }

}