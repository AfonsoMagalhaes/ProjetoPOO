package interfaces_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class menu extends JFrame{
    private JFrame menu;
    private JPanel canvas;
    private JButton b1, b2, b3, b4;
    private JLabel l1;

    //Ter uma combobox com os utilizadores ja inscritos pra fazer o login
    //O botao de criar viagem e so mostrado depois de registar ou fazer login
    //So depois do registo ou do login e que se escolhe o hot e o custo maximo para se criar a viagem


    public menu() {
        setTitle("A sua viagem de sonho!");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new GridLayout());
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("Menu.jpg")));

        add(background);

        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        l1 =new JLabel("<html><h1><strong><b><font color=\"black\">A sua viagem de sonho!</font></b></strong></h1><hr></html>");

        l1.setBackground(Color.cyan);
        l1.setOpaque(true);


        background.add(l1, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        b1=new JButton("Registo");

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //aqui temos de ter uma funçao para registar (eu faço)
            }
        });

        b2 = new JButton("Criar Viagem");

        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //aqui depois tenho de fazer outro menu que peço ao utilizador para meter coisas
                //faz as tu as funçoes que depois vão ser utilizadas
            }
        });

        b3 = new JButton("Locais mais populares");

        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //aqui temos de ter uma funçao para imprimir os locais populares
            }
        });

        b4 = new JButton("Sair");

        b4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        background.add(b1, gbc);
        background.add(b2, gbc);
        background.add(b3,gbc);
        background.add(b4, gbc);

        gbc.weighty = 1;



        this.setVisible(true);
    }



//    public menu() {
//
//
//        setTitle("A sua viagem de sonho!");
//        setSize(600, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        butao1 = new JButton("Sign up");
//        butao3 = new JButton("Locais mais populares");
//
//        String[] opcoes = {"Introduzir preferências", "Introduzir montante máximo"};
//
//        butao2 = new JButton("Calcular Viagem");
//
//        Image img = Toolkit.getDefaultToolkit().createImage("background.jpg");
//        canvas.drawImage(img, 0, 0, null);
//
//        canvas = new JPanel();
//        canvas.setLayout(new BorderLayout());
//
//        //canvas.add(img,BorderLayout.NORTH);
//        canvas.add(butao1, BorderLayout.SOUTH);
//
//        this.add(canvas);
//        this.setVisible(true);
//    }


    public static void main(String[] args) {
        new menu();
    }

} //

