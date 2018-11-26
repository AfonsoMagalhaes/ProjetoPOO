public class Diversões extends Parque{
    int equipamentos;
    boolean animais;
    public Diversões(String nome,String horario,float entrada,float custoextra, int equipamentos, boolean animais){
        super(nome,horario,entrada,custoextra);
        this.equipamentos=equipamentos;
        this.animais=animais;
    }

    public int getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(int equipamentos) {
        this.equipamentos = equipamentos;
    }

    public boolean getAnimais() {
        return animais;
    }

    public void setAnimais(boolean animais) {
        this.animais = animais;
    }
}
