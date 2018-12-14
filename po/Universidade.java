package po;

import java.util.ArrayList;

public class Universidade extends pInteresse {
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

    @Override
    public String getTipo(){
        return "universidade";
    }
}


