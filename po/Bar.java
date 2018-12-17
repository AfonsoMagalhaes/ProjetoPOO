package po;

/**
 * Representa um bar.
 */
public class Bar extends pInteresse {
    private float classificacao;

    /**
     * Cria um bar.
     * @param nome String com o nome do bar.
     * @param horario String com o horário do bar.
     * @param entrada Float com o custo de entrada no bar.
     * @param custoextra Float com o custo extra do bar.
     * @param classificacao Float com a classificação do bar.
     */
    public Bar(String nome, String horario,float entrada, float custoextra, float classificacao){
        super(nome,horario, entrada, custoextra);
        this.classificacao=classificacao;
    }

    /**
     * Obtém a classficação do bar.
     * @return Float com classificação do bar.
     */
    public float getClassificacao(){
        return this.classificacao;
    }

    /**
     * Define a classificação do bar
     * @param classificacao Float com a classificação do bar.
     */
    public void setClassificacao(float classificacao){
        this.classificacao=classificacao;
    }

    /**
     * Diz que o ponto de interesse é um bar.
     * @return String com o tipo de ponto de interesse(bar).
     */
    @Override
    public String getTipo(){
        return "bar";
    }
}
