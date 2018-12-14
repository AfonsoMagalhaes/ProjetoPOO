package po;

import java.io.Serializable;
import java.util.ArrayList;


public class Local implements Serializable {
    private String cidade;
    private ArrayList<P_Interesse> p_interesse;
    private int X;
    private  int Y;

    public Local (String cidade, int X,int Y){
        this.cidade=cidade;
        this.p_interesse = new ArrayList<>();
        this.X=X;
        this.Y=Y;
    }

    public String getCidade(){
        return cidade;
    }

    public ArrayList<P_Interesse> getPInteresse(){
        return p_interesse;
    }
    public void setP_interesse(ArrayList<P_Interesse> p_interesse){
        this.p_interesse=p_interesse;
    }


    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    @Override
    public String toString() {

        return "[Local] Cidade: "+cidade+ "; " + "Coordenadas: "+X+","+Y+";" + "Pontos de Interesse: " + p_interesse;
    }
}

