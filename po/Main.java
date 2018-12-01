package po;

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


    public Main() {
        this.mestrado = new ArrayList<>();
        this.licenciatura = new ArrayList<>();
        this.locais = new ArrayList<>();
        this.p_interesse = new ArrayList<>();
    }

    public static void leficheiro() {
        BufferedReader br = null;
        FileReader fr = null;
        String st;

        try {


            br = new BufferedReader(new FileReader("locais.txt"));

            while ((st = br.readLine()) != null) {
                String[] tab = st.split(";");
                int x = Integer.parseInt(tab[4]);
                int y = Integer.parseInt(tab[5]);
                Local local = new Local(tab[0], tab[1], tab[2], tab[3], x, y);
                locais.add(local);
            }
        } catch (IOException e) {
            System.out.println("Excepcao a carregar ficheiro txt: " + e);
        }
    }


    public static void printMenu() {


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
        viagem.printMenu();

        System.out.println("LISTA LOCAIS");
        for(Local tmp : viagem.locais)
            System.out.println(tmp.toString());



        //AQUI fazemos um do while p imprimir e selecionar a opção da pessoa
/*

        Tipo:

        boolean flag = true;
        int option;
        do {

                printMenu();
                System.out.println("Escolha: ");
                option = po.Main.getInt();
                switch (option) {
                    case 1:
                        gestor.criaPessoa();
                        break;
                    case 2:

                    ....
                    case 0:
                        flag = false;
                }
                f.escreveGestor(gestor);
            } while (flag);
 */


    }
}
