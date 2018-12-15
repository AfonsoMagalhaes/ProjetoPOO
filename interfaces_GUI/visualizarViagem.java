package interfaces_GUI;

import po.Local;
import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class visualizarViagem extends JFrame {

    private final Entrar j;
    private JPanel canvas;
    private Main m;
    private JButton b2, b3, b1;
    private JLabel l1;
    private JLabel local1, local2, local3;
    private JLabel pi11, pi12, pi13, pi21, pi22, pi23, pi31, pi32, pi33;
    private boolean mestrado;
    private String hot;
    private int custo;
    private ArrayList<Local[]> viagens;


    public visualizarViagem(Main m, Entrar j) {
        this.m = m;
        this.j = j;

        viagens = new ArrayList<>();
        mestrado = j.getMestrado();
        hot = j.getHot();
        custo = j.getCusto();




        System.out.println(mestrado);
        System.out.println(hot);
        System.out.println(custo);
        if(mestrado==true) {
            System.out.println("Vai calcular a viagem:");
            ArrayList<Local[]> viagens = m.criaViagensMes(custo, hot);
            for (Local[] v : viagens) {
                System.out.println(v[0] + "\n\n" + v[1] + "\n\n" + v[2] + "\n");
            }
        } else {
            m.escreveMaisVotado(hot);
            System.out.println("Vai calcular a viagem:");
            ArrayList<Local[]> viagens = m.criaViagensLic(custo, hot);
            for (Local[] v : viagens) {
                System.out.println(v[0] + "\n\n" + v[1] + "\n\n" + v[2] + "\n");
            }
        }


        setTitle("A sua viagem de sonho!");
        setSize(900, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new GridLayout());
        JLabel background = new JLabel(new ImageIcon(this.getClass().getResource("img.jpg")));

        add(background);

        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(0, 400, 0, 0);
        l1 = new JLabel("<html><h1><strong><b><font color=\"black\">A sua viagem</font></b></strong></h1><hr></html>");
        background.add(l1, gbc);

        //faz grid
        JPanel local = new JPanel();
        local.setLayout(new GridLayout(1, 4, 10, 10));
        local.setOpaque(false);
        JPanel pis = new JPanel();
        pis.setLayout(new GridLayout(3, 1, 10, 10));
        pis.setOpaque(false);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        local1 = new JLabel("Local1");
        local1.setFont(new Font("Serif", Font.BOLD, 17));
        local.add(local1);

        local1 = new JLabel("custo total");
        local1.setFont(new Font("Serif", Font.BOLD, 17));
        local.add(local1);

        pi11 = new JLabel("pi1");
        pis.add(pi11);
        pi12 = new JLabel("pi12");
        pis.add(pi12);
        pi13 = new JLabel("pi13");
        pis.add(pi13);

        local.add(pis);

        pi11 = new JLabel("custo1");
        pis.add(pi11);
        pi12 = new JLabel("custo2");
        pis.add(pi12);
        pi13 = new JLabel("custo3");
        pis.add(pi13);

        local.add(pis);

        gbc.insets = new Insets(30, 400, 0, 0);
        background.add(local, gbc);


        //2

        //faz grid
        JPanel local2 = new JPanel();
        local2.setLayout(new GridLayout(1, 4, 10, 10));
        local2.setOpaque(false);
        JPanel pis2 = new JPanel();
        pis2.setLayout(new GridLayout(3, 1, 10, 10));
        pis2.setOpaque(false);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        local1 = new JLabel("Local1");
        local1.setFont(new Font("Serif", Font.BOLD, 17));
        local2.add(local1);

        local1 = new JLabel("custo total");
        local1.setFont(new Font("Serif", Font.BOLD, 17));
        local2.add(local1);

        pi21 = new JLabel("pi1");
        pis2.add(pi21);
        pi22 = new JLabel("pi12");
        pis2.add(pi22);
        pi23 = new JLabel("pi13");
        pis2.add(pi23);

        local2.add(pis2);

        pi21 = new JLabel("custo1");
        pis2.add(pi21);
        pi22 = new JLabel("custo2");
        pis2.add(pi22);
        pi23 = new JLabel("custo3");
        pis2.add(pi23);

        local2.add(pis2);


        background.add(local2, gbc);


        //3

        //faz grid
        JPanel local3 = new JPanel();
        local3.setLayout(new GridLayout(1, 4, 10, 10));
        local3.setOpaque(false);
        JPanel pis3 = new JPanel();
        pis3.setLayout(new GridLayout(3, 1, 10, 10));
        pis3.setOpaque(false);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        local1 = new JLabel("Local1");
        local1.setFont(new Font("Serif", Font.BOLD, 17));
        local3.add(local1);

        local1 = new JLabel("custo total");
        local1.setFont(new Font("Serif", Font.BOLD, 17));
        local3.add(local1);

        pi21 = new JLabel("pi1");
        pis3.add(pi21);
        pi22 = new JLabel("pi12");
        pis3.add(pi22);
        pi23 = new JLabel("pi13");
        pis3.add(pi23);

        local3.add(pis3);

        pi21 = new JLabel("custo1");
        pis3.add(pi21);
        pi22 = new JLabel("custo2");
        pis3.add(pi22);
        pi23 = new JLabel("custo3");
        pis3.add(pi23);

        local3.add(pis3);


        background.add(local3, gbc);


        gbc.insets = new Insets(100, 400, 0, 0);

        b1 = new JButton("Ordenar por custo");
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                iniciaMenu(e);
            }
        });

        background.add(b1, gbc);
        b2 = new JButton("Voltar");
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                iniciaMenu(e);
            }
        });

        gbc.insets = new Insets(0, 400, 0, 0);
        background.add(b2,gbc);


        b3= new JButton("Sair");

        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        background.add(b3,gbc);



        this.setVisible(true);
    }

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);

        new Menu(m).setVisible(true);
    }

}
