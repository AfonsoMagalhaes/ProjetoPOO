package po;

/**
 * Representa um parque.
 */
abstract public class Parque extends pInteresse {
    /**
     * Cria um parque.
     * @param nome String com o nome do parque.
     * @param horario String com o horário do parque.
     * @param entrada Float com a entrada do parque.
     * @param custoextra Float com o custoextra do parque.
     */
    public Parque(String nome, String horario, float entrada, float custoextra){
        super(nome, horario, entrada, custoextra);
    }
}
