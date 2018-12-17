package po;

/**
 * Representa um parque aquático.
 */
public class Aquático extends Diversões {
    private int piscinas;
    private boolean animais;

    /**
     * Cria um parque aquático
     * @param nome String com o nome do parque aquátioo.
     * @param horario String com o horário do parque aquático.
     * @param entrada Float com o custo de entrada no parque aquático.
     * @param custoextra Float com o custo extra do parque aquático.
     * @param equipamentos Integer com o número de equipamentos do parque aquático.
     * @param animais true se possui espetáculo de animais, false se não possui.
     * @param piscinas Integer com o número de piscinas do parque aquático.
     */
    public Aquático(String nome,String horario,float entrada,float custoextra, int equipamentos, boolean animais, int piscinas){
        super(nome,horario,entrada,custoextra, equipamentos);
        this.piscinas=piscinas;
        this.animais=animais;
    }

    /**
     * Obtém o número de piscinas do parque aquático
     * @return Integer com o número de piscinas do parque aquático.
     */
    public int getPiscinas() {
        return piscinas;
    }

    /**
     * Define o número de piscinas do parque aquático
     * @param piscinas Integer com o número de piscinas do parque aquático.
     */
    public void setPiscinas(int piscinas) {
        this.piscinas = piscinas;
    }

    /**
     * Diz se o parque aquático tem espetáculo animais ou não.
     * @return true se tem espetáculo de animais, false caso contrário.
     */
    public boolean getAnimais() {
        return animais;
    }

    /**
     * Define se o parque aquático tem ou não espetáculo de animais.
     * @param animais true sem tem espetáculo de animais, false caso contrário.
     */
    public void setAnimais(boolean animais) {
        this.animais = animais;
    }

    /**
     * Diz que o ponto de interesse é um parque aquático.
     * @return String com o tipo do ponto de interesse(parque aquático).
     */
    @Override
    public String getTipo(){
        return "aquático";
    }
}
