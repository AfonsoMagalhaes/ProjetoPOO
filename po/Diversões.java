package po;

public class Diversões extends Parque {
    private int equipamentos;
    public Diversões(String nome,String horario,float entrada,float custoextra, int equipamentos){
        super(nome,horario,entrada,custoextra);
        this.equipamentos=equipamentos;
    }

    public int getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(int equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public String getTipo(){
        return "diversões";
    }

}
