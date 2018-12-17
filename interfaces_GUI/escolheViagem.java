package interfaces_GUI;

import po.Local;
import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class escolheViagem extends JFrame {
    private Main m;
    private JComboBox<String> fromC;
    private String escolha;
    private ArrayList<Local[]> viagens;

    escolheViagem(Main m, Entrar j) {
        this.m = m;

        viagens = new ArrayList<>();
        boolean mestrado = j.getMestrado();
        String hot = j.getHot();
        int custo = j.getCusto();


        if (mestrado) {
            viagens = m.criaViagensMes(custo, hot);
        } else {
            m.escreveMaisVotado(hot);
            viagens = m.criaViagensLic(custo, hot);
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


//        String[] items = {"Lisboa, Coimbra, Porto, 100€", "Faro, Viseu, Braga, 90€"};
//        fromC = new JComboBox<>(items);
        ArrayList<String> locais = new ArrayList<>();

        for (Local[] tmp : viagens) {

            locais.add(m.viagemString(tmp));

        }
        fromC = new JComboBox(locais.toArray());
        gbc.insets = new Insets(0, 450, 0, 0);
        background.add(fromC, gbc);


        JButton ordena = new JButton("Reordena Locais por custo");
        ordena.addActionListener(e -> {
            ArrayList<Local[]> viagensOrdenadas = m.ordenaViagens(viagens, true);
            ArrayList<String> locais1 = new ArrayList<>();

            for (Local[] tmp : viagensOrdenadas) {
                locais1.add(m.viagemString(tmp));

            }
            DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(locais1.toArray());
            fromC.setModel(defaultComboBoxModel);
        });
        gbc.insets = new Insets(40, 600, 0, 0);
        background.add(ordena, gbc);

        JButton b1 = new JButton("Calcular viagem");
        b1.addActionListener(new BtnCalcula());

        gbc.insets = new Insets(10, 600, 0, 0);
        background.add(b1, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;


        gbc.insets = new Insets(80, 600, 0, 0);


        JButton b2 = new JButton("Menu principal");
        b2.addActionListener(e -> iniciaMenu());
        background.add(b2, gbc);

        gbc.insets = new Insets(0, 600, 0, 0);
        JButton b3 = new JButton("Sair");

        b3.addActionListener(e -> System.exit(0));

        background.add(b3, gbc);


        this.setVisible(true);

    }

    private void visualizarViagem() {
        this.setVisible(false);
        new visualizarViagem(m, this).setVisible(true);
    }

    private void iniciaMenu() {
        this.setVisible(false);
        new Menu(m).setVisible(true);
    }

    String getEscolha() {
        return escolha;
    }

    private class BtnCalcula implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            escolha = fromC.getSelectedItem().toString();
            visualizarViagem();
        }
    }
}
