public class Aquático extends Diversões{
    int piscinas;
    public Aquático(String nome,String horario,float entrada,float custoextra, int equipamentos, boolean animais){
        super(nome,horario,entrada,custoextra, equipamentos, animais);
        this.piscinas=piscinas;
    }

    public int getPiscinas() {
        return piscinas;
    }

    public void setPiscinas(int piscinas) {
        this.piscinas = piscinas;
    }
}
