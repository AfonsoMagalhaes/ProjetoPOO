package po;

import po.P_Interesse;

abstract public class Parque extends P_Interesse {
    public Parque(String nome, String horario, float entrada, float custoextra){
        super(nome, horario, entrada, custoextra);
    }
}
