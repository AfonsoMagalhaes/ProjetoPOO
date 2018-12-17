package interfaces_GUI;

import po.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class visualizarViagem extends JFrame {

    private Main m;


    visualizarViagem(Main m, escolheViagem j) {
        this.m = m;

        String escolha = j.getEscolha();

        String[] tab = escolha.split(", ");


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
        JLabel l1 = new JLabel("<html><h1><strong><b><font color=\"black\">A sua viagem</font></b></strong></h1><hr></html>");
        background.add(l1, gbc);

        //faz grid
        JPanel pis = new JPanel();
        pis.setLayout(new GridLayout(3, 1, 5, 5));
        pis.setOpaque(false);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel local1 = new JLabel(tab[0] + "   " + m.getCustoLocal(tab[0]) + "€");
        local1.setFont(new Font("Serif", Font.BOLD, 17));
        gbc.insets = new Insets(20, 330, 0, 0);
        background.add(local1, gbc);

        JPanel aux1 = new JPanel();
        aux1.setLayout(new GridLayout(1, 2, 10, 10));
        aux1.setOpaque(false);

        ArrayList<String> pI = m.getPInteresseS(tab[0]);

        JLabel pi11 = new JLabel(pI.get(0));
        pis.add(pi11);
        JLabel pi12 = new JLabel(pI.get(1));
        pis.add(pi12);
        JLabel pi13 = new JLabel(pI.get(2));
        pis.add(pi13);

        aux1.add(pis);


        JPanel pis123 = new JPanel();
        pis123.setLayout(new GridLayout(3, 1, 5, 5));
        pis123.setOpaque(false);

        ArrayList<Float> pICusto = m.getPIntCusto(tab[0]);

        pi11 = new JLabel(pICusto.get(0) + "€");
        pis123.add(pi11);
        pi12 = new JLabel(pICusto.get(1) + "€");
        pis123.add(pi12);
        pi13 = new JLabel(pICusto.get(2) + "€");
        pis123.add(pi13);

        aux1.add(pis123);

        gbc.insets = new Insets(10, 400, 0, 0);
        background.add(aux1, gbc);





        //2

        //faz grid

        JPanel pis2 = new JPanel();
        pis2.setLayout(new GridLayout(3, 1, 5, 5));
        pis2.setOpaque(false);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel local2 = new JLabel(tab[1] + "   " + m.getCustoLocal(tab[1]) + "€");
        local2.setFont(new Font("Serif", Font.BOLD, 17));
        gbc.insets = new Insets(40, 330, 0, 0);
        background.add(local2, gbc);


        JPanel aux2 = new JPanel();
        aux2.setLayout(new GridLayout(1, 2, 10, 10));
        aux2.setOpaque(false);

        pI = m.getPInteresseS(tab[1]);
        JLabel pi21 = new JLabel(pI.get(0));
        pis2.add(pi21);
        JLabel pi22 = new JLabel(pI.get(1));
        pis2.add(pi22);
        JLabel pi23 = new JLabel(pI.get(2));
        pis2.add(pi23);

        aux2.add(pis2);

        JPanel pis2b = new JPanel();
        pis2b.setLayout(new GridLayout(3, 1, 0, 0));
        pis2b.setOpaque(false);


        pICusto = m.getPIntCusto(tab[1]);

        pi21 = new JLabel(pICusto.get(0) + "€");
        pis2b.add(pi21);
        pi22 = new JLabel(pICusto.get(1) + "€");
        pis2b.add(pi22);
        pi23 = new JLabel(pICusto.get(2) + "€");
        pis2b.add(pi23);

        aux2.add(pis2b);

        gbc.insets = new Insets(10, 400, 0, 0);
        background.add(aux2, gbc);





        //3

        //faz grid
        JPanel pis3 = new JPanel();
        pis3.setLayout(new GridLayout(3, 1));
        pis3.setOpaque(false);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel local3 = new JLabel(tab[2] + "   " + m.getCustoLocal(tab[2]) + "€");
        local3.setFont(new Font("Serif", Font.BOLD, 17));
        gbc.insets = new Insets(40, 330, 0, 0);
        background.add(local3, gbc);

        JPanel aux3 = new JPanel();
        aux3.setLayout(new GridLayout(1, 2, 10, 10));
        aux3.setOpaque(false);

        pI = m.getPInteresseS(tab[2]);
        pi21 = new JLabel(pI.get(0));
        pis3.add(pi21);
        pi22 = new JLabel(pI.get(1));
        pis3.add(pi22);
        pi23 = new JLabel(pI.get(2));
        pis3.add(pi23);

        aux3.add(pis3);

        JPanel pis3b = new JPanel();
        pis3b.setLayout(new GridLayout(3, 1, 0, 0));
        pis3b.setOpaque(false);

        pICusto = m.getPIntCusto(tab[2]);

        pi21 = new JLabel(pICusto.get(0) + "€");
        pis3b.add(pi21);
        pi22 = new JLabel(pICusto.get(1) + "€");
        pis3b.add(pi22);
        pi23 = new JLabel(pICusto.get(2) + "€");
        pis3b.add(pi23);

        aux3.add(pis3b);
        gbc.insets = new Insets(10, 400, 0, 0);
        background.add(aux3, gbc);


        JLabel dist = new JLabel("Distancia total: " + (int) m.distancia3Locais(tab[0], tab[1], tab[2]) + "km");
        dist.setFont(new Font("Serif", Font.BOLD, 17));
        gbc.insets = new Insets(30, 330, 0, 0);
        background.add(dist, gbc);

        gbc.insets = new Insets(50, 400, 0, 0);


        JButton b2 = new JButton("Menu principal");
        b2.addActionListener(e -> iniciaMenu());


        background.add(b2, gbc);

        gbc.insets = new Insets(0, 400, 0, 0);
        JButton b3 = new JButton("Sair");

        b3.addActionListener(e -> System.exit(0));

        background.add(b3,gbc);



        this.setVisible(true);
    }

    private void iniciaMenu() {
        this.setVisible(false);

        new Menu(m).setVisible(true);
    }

}
