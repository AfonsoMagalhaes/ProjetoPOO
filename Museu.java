public class Museu extends P_Interesse{
    private String tematica;

    public Museu(String nome, String horario, float entrada, String tematica){
        super(nome,horario,entrada);
        this.tematica=tematica;
    }

    public String getTematica() {
        return tematica;
    }
    public void setTematica(String tematica){
        this.tematica=tematica;
    }
}
