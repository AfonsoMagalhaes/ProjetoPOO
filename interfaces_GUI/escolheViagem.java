package interfaces_GUI;

import po.Local;
import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class escolheViagem extends JFrame {
    private Entrar j;
    private Main m;
    private JComboBox<String> fromC;
    private JButton b1, b2, b3, ordena;
    private boolean mestrado;
    private String hot;
    private int custo;
    private String escolha;
    private ArrayList<Local[]> viagens;

    public escolheViagem(Main m, Entrar j) {
        this.m = m;
        this.j = j;

        viagens = new ArrayList<>();
        mestrado = j.getMestrado();
        hot = j.getHot();
        custo = j.getCusto();


        System.out.println(mestrado);
        System.out.println(hot);
        System.out.println(custo);
        if (mestrado == true) {
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
        JLabel background = new JLabel(new ImageIcon(this.getClass().getResource("Menu.jpg")));

        add(background);

        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(0, 600, 0, 0);


        String[] items = {"Lisboa, Coimbra, Porto, 100€", "Faro, Viseu, Braga, 90€"};
        fromC = new JComboBox<>(items);
        background.add(fromC, gbc);


        ordena = new JButton("Reordena Locais por custo");
        ordena.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //iniciaMenu(e);
            }
        });
        gbc.insets = new Insets(40, 600, 0, 0);
        background.add(ordena, gbc);

        b1 = new JButton("Calcular viagem");
        b1.addActionListener(new BtnCalcula());

        gbc.insets = new Insets(10, 600, 0, 0);
        background.add(b1, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;


        gbc.insets = new Insets(80, 600, 0, 0);


        b2 = new JButton("Menu principal");
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                iniciaMenu(e);
            }
        });
        background.add(b2, gbc);

        gbc.insets = new Insets(0, 600, 0, 0);
        b3 = new JButton("Sair");

        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        background.add(b3, gbc);


        this.setVisible(true);

    }

    private void visualizarViagem(ActionEvent evt) {
        this.setVisible(false);
        new visualizarViagem(m, this).setVisible(true);
    }

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);
        new Menu(m).setVisible(true);
    }

    public String getEscolha() {
        return escolha;
    }

    private class BtnCalcula implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            escolha = fromC.getSelectedItem().toString();
            visualizarViagem(e);
        }
    }
}
