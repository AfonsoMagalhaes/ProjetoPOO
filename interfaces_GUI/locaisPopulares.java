package interfaces_GUI;

import po.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class locaisPopulares extends JFrame {
    private Main m;

    locaisPopulares(Main m) {
        this.m = m;
        ArrayList<String> maisVotados;
        maisVotados = m.getMaisVotados();


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


        gbc.anchor = GridBagConstraints.WEST;

        JLabel l4 = new JLabel("<html><u>Locais mais populares</u></html>");
        l4.setFont(new Font("Serif", Font.BOLD, 40));


        gbc.insets = new Insets(0, 400, 0, 0);
        background.add(l4, gbc);

        gbc.anchor = GridBagConstraints.WEST;


        JPanel canvas = new JPanel();
        canvas.setLayout(new GridLayout(3,2,10,10));

        gbc.insets = new Insets(50, 400, 0, 0);


        JLabel l1 = new JLabel("1. " + maisVotados.get(0) + ", " + m.getLocal(maisVotados.get(0)), SwingConstants.CENTER);

        l1.setFont(new Font("Serif", Font.PLAIN, 35));

        background.add(l1, gbc);

        JLabel l2 = new JLabel("2. " + maisVotados.get(1) + ", " + m.getLocal(maisVotados.get(1)), SwingConstants.CENTER);

        l2.setFont(new Font("Serif", Font.PLAIN, 30));
        gbc.insets = new Insets(10, 400, 0, 0);
        background.add(l2, gbc);

        JLabel l3 = new JLabel("3. " + maisVotados.get(2) + ", " + m.getLocal(maisVotados.get(2)), SwingConstants.CENTER);

        l3.setFont(new Font("Serif", Font.PLAIN, 25));

        background.add(l3, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;


        gbc.insets = new Insets(100, 400, 0, 0);
        JButton b2 = new JButton("Voltar");
        b2.addActionListener(e -> iniciaMenu());


        background.add(b2,gbc);

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
