package po;

public class Aquático extends Diversões {
    private int piscinas;
    private boolean animais;
    public Aquático(String nome,String horario,float entrada,float custoextra, int equipamentos, boolean animais){
        super(nome,horario,entrada,custoextra, equipamentos);
        this.piscinas=piscinas;
        this.animais=animais;
    }

    public int getPiscinas() {
        return piscinas;
    }

    public void setPiscinas(int piscinas) {
        this.piscinas = piscinas;
    }

    public boolean getAnimais() {
        return animais;
    }

    public void setAnimais(boolean animais) {
        this.animais = animais;
    }
}
