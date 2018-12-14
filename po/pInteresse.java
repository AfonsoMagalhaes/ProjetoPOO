package po;

abstract public class pInteresse {
    private String nome;
    private String horario;
    float entrada, custoextra;

    public pInteresse(String nome, String horario, float entrada, float custoextra) {
        this.nome = nome;
        this.horario = horario;
        this.entrada = entrada;
        this.custoextra= custoextra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public String getHorario(){
        return horario;
    }

    public void setHorario(String horario){
        this.horario=horario;
    }

    public float getEntrada(){
        return entrada;
    }

    public void setEntrada(float entrada){this.entrada=entrada;}

    public float getCustoextra(){
        return custoextra;
    }

    public void setCustoextra(float custoextra){this.custoextra=custoextra;}

    public String toString(){
        return "\n-Nome: " + nome + ", -Horário: " + horario + ", -Entrada: " +  entrada + ", -Custo Extra: " + custoextra;
    }

    public abstract String getTipo();
}
