package po;

public class Cultural extends Parque {
    public Cultural(String nome, String horario, float entrada, float custoextra){
        super(nome, horario, entrada, custoextra);
    }

    @Override
    public String getTipo(){
        return "cultural";
    }
}
