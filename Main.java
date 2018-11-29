import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main extends JFrame{

    static Scanner in = new Scanner(System.in);
    static Scanner auxs = new Scanner(System.in);


    public static void main(String[] args){



        try {
            boolean flag = true;

            Ficheiro f = new Ficheiro();
            Gestor gestor = new Gestor();
            gestor.leficheiro();

            try {
                gestor = f.getInfoGestor(gestor);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
            }

            int option;


            //AQUI fazemos um do while p imprimir e selecionar a opção da pessoa
/*

        Tipo:
        do {

                printMenu();
                System.out.println("Escolha: ");
                option = Main.getInt();
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


        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        printMenu();
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
}
