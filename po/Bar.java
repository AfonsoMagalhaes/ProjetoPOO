package po;

public class Bar extends pInteresse {
    private float classificacao;
    public Bar(String nome, String horario,float entrada, float custoextra, float classificacao){
        super(nome,horario, entrada, custoextra);
        this.classificacao=classificacao;
    }

    public float getClassificacao(){
        return this.classificacao;
    }

    public void setClassificacao(float classificacao){
        this.classificacao=classificacao;
    }

    @Override
    public String getTipo(){
        return "bar";
    }
}
