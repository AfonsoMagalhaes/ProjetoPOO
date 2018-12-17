package po;

/**
 * Representa um ponto de interesse.
 */
abstract public class pInteresse {
    private String nome;
    private String horario;
    float entrada, custoextra;

    /**
     * Cria um ponto de interesse.
     * @param nome String com o nome do ponto de interesse.
     * @param horario String com o horário do ponto de interesse.
     * @param entrada Float com o custo de entrada do ponto de interesse.
     * @param custoextra Float com o custo extra do ponto de interesse.
     */
    public pInteresse(String nome, String horario, float entrada, float custoextra) {
        this.nome = nome;
        this.horario = horario;
        this.entrada = entrada;
        this.custoextra= custoextra;
    }

    /**
     * Obtém o nome do ponto de interesse.
     * @return String com o nome do ponto de interesse.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do ponto de interesse.
     * @param nome String com o nome do ponto de interesse.
     */
    public void setNome(String nome){
        this.nome=nome;
    }

    /**
     * Obtém o horário do ponto de interesse.
     * @return String com o horário do ponto de interesse.
     */
    public String getHorario(){
        return horario;
    }

    /**
     * Define o horário do ponto de interesse.
     * @param horario String com o horário do ponto de interesse.
     */
    public void setHorario(String horario){
        this.horario=horario;
    }

    /**
     * Obtém o custo de entrada do ponto de interesse
     * @return Float com o custo de entrada do ponto de interesse.
     */
    public float getEntrada(){
        return entrada;
    }

    /**
     * Define o custo de entrada do ponto de interesse.
     * @param entrada Float com o custo de entrada do ponto de interesse.
     */
    public void setEntrada(float entrada){this.entrada=entrada;}

    /**
     * Obtém o custo extra do ponto de interesse.
     * @return Float com o custo extra do ponto de interesse.
     */
    public float getCustoextra(){
        return custoextra;
    }

    /**
     * Define o custo extra do ponto de interesse.
     * @param custoextra Float com o custo extra do ponto de interesse.
     */
    public void setCustoextra(float custoextra){this.custoextra=custoextra;}

    /**
     * Devolve uma string dos dados do ponto de interesse
     * @return String com o nome, horário e custos de entrada e extra do ponto de interesse.
     */
    public String toString(){
        return "\n-Nome: " + nome + ", -Horário: " + horario + ", -Entrada: " +  entrada + ", -Custo Extra: " + custoextra;
    }

    /**
     * Diz o tipo de ponto de interesse.
     * @return String com o tipo de ponto de interesse.
     */
    public abstract String getTipo();
}
