package po;

import java.io.Serializable;

abstract class Pessoa  implements Serializable {
    private String nome;
    private String email;

    public Pessoa(String nome, String email){
        this.email = email;
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "\n-Nome: " + nome + "\n-E-mail: " + email;
    }
}
