package po;

import interfaces_GUI.Janela_inicio;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main extends JFrame{

    static ArrayList<Mestrado> mestrado;
    static ArrayList<Licenciatura> licenciatura;
    public static ArrayList<Local> locais;
    public static ArrayList<P_Interesse> p_interesse;
    float x;

    public Main() {
        this.mestrado = new ArrayList<>();
        this.licenciatura = new ArrayList<>();
        this.locais = new ArrayList<>();
        this.p_interesse = new ArrayList<>();
        this.x=x; // ??
    }

    public static void leficheiro() {
        BufferedReader br = null;
        FileReader fr = null;
        String st;

        try {


            br = new BufferedReader(new FileReader("locais.txt"));

            while ((st = br.readLine()) != null) {
                String[] tab = st.split(";");
                int x = Integer.parseInt(tab[13]);
                int y = Integer.parseInt(tab[14]);
                Local local = new Local(tab[0], tab[1], tab[5], tab[9], x, y);
                locais.add(local);
                for(int i=3; i<13; i+=4) {
                    int entrada = Integer.parseInt(tab[i]);
                    int extra = Integer.parseInt(tab[i+1]);
                    P_Interesse p_i = new P_Interesse(tab[1], tab[2], entrada, extra);
                    p_interesse.add(p_i);
                }
            }
        } catch (IOException e) {
            System.out.println("Excepcao a carregar ficheiro txt: " + e);
        }
    }


    public int custo_local(Local local){
        int custo=0;
        for(P_Interesse i: p_interesse){
            if(i.getNome()==local.getPi1() || i.getNome()==local.getPi2() || i.getNome()==local.getPi3()){
                custo+=i.getentrada()+i.getCustoextra();
            }
        }
        return custo;
    }

    public double distancia_locais(Local local1, Local local2){
        return Math.sqrt(Math.pow(local1.getX()-local2.getX(),2)+Math.pow(local1.getY()-local2.getY(),2));
    }

    public int deslocação_locais(Local local1, Local local2){ //??
        return (int) (distancia_locais(local1, local2)*x);
    }

    public int custo_viagem(Local local1, Local local2, Local local3){
        return custo_local(local1)+custo_local(local2)+custo_local(local3)+deslocação_locais(local1, local2)+ deslocação_locais(local2, local3);
    }

    public void viagens_possiveis(int custo_maximo, String preferencia){

    }

    public void printRegisto(){
        JFrame frame = new JFrame();
        frame.setTitle("A sua viagem de sonho!");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton button1 = new JButton("Registo de utilizadores");
        JButton button2 = new JButton("Preferencias de utilizador");
        JButton button3 = new JButton("Montante máximo a gastar");
        JButton button4 = new JButton("Mostrar viagens com pelo menos 1 museu");
        JButton button5 = new JButton("Mostrar viagem selecionada");
        JButton button6 = new JButton("Locais e pontos de interesse mais populares");
        JLabel label = new JLabel("Escolha uma das opcoes:", SwingConstants.CENTER);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,1));


        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        Main viagem = new Main();
        viagem.leficheiro();
        //viagem.printInicial();

        System.out.println("LISTA LOCAIS");
        for(Local tmp : viagem.locais)
            System.out.println(tmp.toString());

        new Janela_inicio().setVisible(true);


    }
}
