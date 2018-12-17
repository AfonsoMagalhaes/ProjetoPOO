package po;

/**
 * Representa um museu.
 */
public class Museu extends pInteresse {
    private String tematica;

    /**
     * Cria um museu.
     * @param nome String com o nome do museu.
     * @param horario String com o horário do museu.
     * @param entrada Float com o custo de entrada do museu.
     * @param custoextra Float com o custo de entrada do museu.
     * @param tematica String com a temática do museu.
     */
    public Museu(String nome, String horario, float entrada, float custoextra, String tematica){
        super(nome,horario, entrada, custoextra);
        this.tematica=tematica;
    }

    /**
     * Obtém o custo de entrada do museu.
     * @return Float com o custo de entrada do museu.
     */
    public float getEntrada(){
        return entrada;
    }

    /**
     * Define o custo de entrada do museu.
     * @param entrada Float com o custo de entrada do museu.
     */
    public void setEntrada(float entrada){this.entrada=entrada;}

    /**
     * Obtém a temática do museu.
     * @return String com a temática do museu.
     */
    public String getTematica() {
        return tematica;
    }

    /**
     * Define a temática do museu.
     * @param tematica String com a temática do museu.
     */
    public void setTematica(String tematica){
        this.tematica=tematica;
    }

    /**
     * Diz que o ponto de interesse é um museu.
     * @return String com o tipo de ponto de interesse(museu).
     */
    @Override
    public String getTipo(){
        return "museu";
    }
}
