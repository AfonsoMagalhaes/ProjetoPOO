package po;

import java.io.Serializable;
import java.util.ArrayList;


public class Local implements Serializable {
    private String cidade;
    private ArrayList<pInteresse> pInteresse;
    private int X;
    private  int Y;

    public Local (String cidade, int X,int Y){
        this.cidade=cidade;
        this.pInteresse = new ArrayList<>();
        this.X=X;
        this.Y=Y;
    }

    public String getCidade(){
        return cidade;
    }

    public ArrayList<pInteresse> getPInteresse() {

        return pInteresse;
    }


    public void setPInteresse(ArrayList<pInteresse> pInteresse) {
        this.pInteresse = pInteresse;
    }


    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    @Override
    public String toString() {

        return "[Local] Cidade: " + cidade + "; " + "Coordenadas: " + X + "," + Y + ";" + "Pontos de Interesse: " + pInteresse;
    }
}

