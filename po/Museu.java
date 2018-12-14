package po;

public class Museu extends pInteresse {
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

    @Override
    public String getTipo(){
        return "museu";
    }
}
