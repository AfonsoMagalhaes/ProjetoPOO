package po;

import po.P_Interesse;

import java.util.ArrayList;

public class Universidade extends P_Interesse {
    private ArrayList<String> cursos;
    public Universidade(String nome, String horario, float entrada, float custoextra){
        super(nome, horario, entrada, custoextra);
        this.cursos=new ArrayList<>();
    }

    public ArrayList<String> getCursos() {
        return this.cursos;
    }

    public void setCursos(){

    }
}
