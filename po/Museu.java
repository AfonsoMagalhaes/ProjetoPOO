package po;

public class Museu extends P_Interesse{
    private String tematica;
    public Museu(String nome, String horario, float entrada, float custoextra, String tematica){
        super(nome,horario, entrada, custoextra);
        this.tematica=tematica;
    }


    public float getEntrada(){
        return entrada;
    }
    public void setEntrada(float entrada){this.entrada=entrada;}
    public String getTematica() {
        return tematica;
    }
    public void setTematica(String tematica){
        this.tematica=tematica;
    }
}
