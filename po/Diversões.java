package po;

/**
 * Representa um parque de diversões.
 */
public class Diversões extends Parque {
    private int equipamentos;

    /**
     * Cria um parque de diversões.
     * @param nome String com o nome do parque de diversões.
     * @param horario String com o horário do parque de diversões.
     * @param entrada Float com o custo de entrada no parque de diversões.
     * @param custoextra Float com o custo extra do parque de diversões.
     * @param equipamentos Integer com o número de equipamentos do parque de diversões.
     */
    public Diversões(String nome,String horario,float entrada,float custoextra, int equipamentos){
        super(nome,horario,entrada,custoextra);
        this.equipamentos=equipamentos;
    }

    /**
     * Obtém o número de equipamentos do parque de diversões.
     * @return Integer com o número de equipamentos do parque de diversões.
     */
    public int getEquipamentos() {
        return equipamentos;
    }

    /**
     * Define o número de equipamentos do parque de diversões.
     * @param equipamentos Integer com o número de equipamentos do parque de diversões.
     */
    public void setEquipamentos(int equipamentos) {
        this.equipamentos = equipamentos;
    }

    /**
     * Diz que o ponto de interesse é um parque de diversões.
     * @return String com o tipo do ponto de interesse(parque de diversões).
     */
    @Override
    public String getTipo(){
        return "diversões";
    }

}
