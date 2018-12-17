package po;

/**
 * Representa um parque cultural.
 */
public class Cultural extends Parque {

    /**
     * Cria um parque cultural.
     * @param nome String com o nome do parque cultural.
     * @param horario String com o horário do parque cultural.
     * @param entrada Float com o custo de entrada no parque cultural.
     * @param custoextra Float com o custo extra do parque cultural.
     */
    public Cultural(String nome, String horario, float entrada, float custoextra){
        super(nome, horario, entrada, custoextra);
    }

    /**
     * Diz que o ponto de interesse é um parque cultural.
     * @return String com o tipo do ponto de interesse(parque cultural).
     */
    @Override
    public String getTipo(){
        return "cultural";
    }
}
