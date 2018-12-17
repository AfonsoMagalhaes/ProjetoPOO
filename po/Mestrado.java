package po;

import java.io.Serializable;

/**
 * Repreenta um aluno de mestrado.
 */
public class Mestrado extends Aluno implements Serializable {

    /**
     * Cria um aluno de mestrado.
     * @param nome String com o nome do aluno.
     * @param email String com o email do aluno.
     */
    public Mestrado(String nome, String email) {
        super(nome, email);
    }

    /**
     * Diz que o aluno é de mestrado.
     * @return true, pois o aluno e de mestrado.
     */
    @Override
    public boolean isMestrado() {
        return true;
    }

}
