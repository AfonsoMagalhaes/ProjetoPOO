package po;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa um local.
 */
public class Local implements Serializable {
    private String cidade;
    private ArrayList<pInteresse> pInteresse;
    private int X;
    private  int Y;

    /**
     * Cria um local.
     * @param cidade String com o nome da cidade do local.
     * @param X Integer com a coordenada x do local.
     * @param Y Integer com a coordenada y do local.
     */
    public Local (String cidade, int X,int Y){
        this.cidade=cidade;
        this.pInteresse = new ArrayList<>();
        this.X=X;
        this.Y=Y;
    }

    /**
     * Obtém o nome da cidade do local
     * @return String com o nome da cidade do local.
     */
    public String getCidade(){
        return cidade;
    }

    /**
     * Obtém os pontos de interesse do local.
     * @return Lista de pontos de interesse do local.
     */
    public ArrayList<pInteresse> getPInteresse() {

        return pInteresse;
    }

    /**
     * Define os pontos de interesse do local.
     * @param pInteresse Lista dos pontos de interesse do local.
     */
    public void setPInteresse(ArrayList<pInteresse> pInteresse) {
        this.pInteresse = pInteresse;
    }

    /**
     * Obtém a coordenada x do local.
     * @return Integer com o valor de x.
     */
    public int getX(){
        return X;
    }

    /**
     * Obtém a coordenada y do local.
     * @return Integer com o valor de y.
     */
    public int getY(){
        return Y;
    }

    /**
     * Devolve uma string com os dados do local.
     * @return String com o nome da cidade, coordenadas(x e y) e pontos de interesse do local.
     */
    @Override
    public String toString() {

        return "[Local] Cidade: " + cidade + "; " + "Coordenadas: " + X + "," + Y + ";" + "Pontos de Interesse: " + pInteresse;
    }
}

