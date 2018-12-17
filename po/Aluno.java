package po;

import java.io.Serializable;

/**
 * Representa um aluno.
 */
public abstract class Aluno implements Serializable {
    private String nome;
    private String email;

    /**
     * Cria um aluno.
     * @param nome String com o nome do aluno.
     * @param email String com o email do aluno.
     */
    public Aluno(String nome, String email) {
        this.email = email;
        this.nome = nome;
    }

    /**
     * Obtém o nome do aluno
     * @return String com o nome do aluno
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do aluno
     * @param nome String com o nome do aluno.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email do aluno.
     * @return String do email do aluno.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do aluno.
     * @param email String com o email do aluno.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devolve uma string com o nome e o email do aluno.
     * @return String com o nome e o email do aluno.
     */
    @Override
    public String toString(){
        return "\n-Nome: " + nome + "\n-E-mail: " + email;
    }

    /**
     * Diz se o alunos e de mestrado ou licenciatura.
     * @return true se o aluno é de mestrado, false se é de licenciatura.
     */
    public abstract boolean isMestrado();
}
