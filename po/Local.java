package po;

import java.io.Serializable;


public class Local implements Serializable {
    private String cidade;
    private String pi1;
    private String pi2;
    private String pi3;
    private int X;
    private  int Y;

    public Local (String cidade,String pi1,String pi2,String pi3, int X,int Y){
        this.cidade=cidade;
        this.pi1=pi1;
        this.pi2=pi2;
        this.pi3=pi3;
        this.X=X;
        this.Y=Y;
    }

    public String getCidade(){
        return cidade;
    }

    public String getPi1(){
        return pi1;
    }

    public String getPi2(){
        return pi2;
    }

    public String getPi3(){
        return pi3;
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    @Override
    public String toString() {
        return "[Local] Cidade: "+cidade+ ": "+pi1+" "+pi2+" "+pi3;
    }
}
