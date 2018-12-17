package po;

import java.util.ArrayList;

/**
 * Representa uma universidade.
 */
public class Universidade extends pInteresse {
    private ArrayList<String> cursos;

    /**
     * Cria uma universidade.
     * @param nome String com o nome da universidade.
     * @param horario String com o horário da universidade.
     * @param entrada Float com o custo de entrada na universidade.
     * @param custoextra Float com o custo extra da universidade.
     */
    public Universidade(String nome, String horario, float entrada, float custoextra){
        super(nome, horario, entrada, custoextra);
        this.cursos=new ArrayList<>();
    }

    /**
     * Obtém a lista de cursos da universidade.
     * @return Lista com os cursos da universidade.
     */
    public ArrayList<String> getCursos() {
        return this.cursos;
    }

    /**
     * Define a lista de cursos da universidade.
     * @param Cursos Lista com os cursos da unviersidade.
     */
    public void setCursos(ArrayList<String> Cursos){
        this.cursos=Cursos;
    }

    /**
     * Diz que o ponto de interesse é uma universidade.
     * @return String com o tipo de ponto de interesse(universidade).
     */
    @Override
    public String getTipo(){
        return "universidade";
    }
}


