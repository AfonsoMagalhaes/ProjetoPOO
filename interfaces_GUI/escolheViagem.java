package interfaces_GUI;

import po.Local;
import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * menu que apresenta a janela para escolher uma das viagens possiveis
 */
class escolheViagem extends JFrame {
    private Main m;
    private JComboBox<String> fromC;
    private String escolha;
    private ArrayList<Local[]> viagens;

    /**
     * função chamada para criar a janela
     *
     * @param m contem as funções relativas ao main
     * @param j contem os dados obtidos na janela anterior
     */
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


        ArrayList<String> locais = new ArrayList<>();

        for (Local[] tmp : viagens) {

            locais.add(m.viagemString(tmp));

        }
        fromC = new JComboBox(locais.toArray());
        gbc.insets = new Insets(0, 450, 0, 0);
        background.add(fromC, gbc);


        JButton ordenaA = new JButton("Reordenar por ordem ascendente");
        ordenaA.addActionListener(e -> {
            ArrayList<Local[]> viagensOrdenadas = m.ordenaViagens(viagens, true);
            ArrayList<String> locais1 = new ArrayList<>();

            for (Local[] tmp : viagensOrdenadas) {
                locais1.add(m.viagemString(tmp));

            }
            DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(locais1.toArray());
            fromC.setModel(defaultComboBoxModel);
        });
        gbc.insets = new Insets(20, 500, 0, 0);
        background.add(ordenaA, gbc);

        JButton ordenaD = new JButton("Reordenar por ordem descendente");
        ordenaD.addActionListener(e -> {
            ArrayList<Local[]> viagensOrdenadas = m.ordenaViagens(viagens, false);
            ArrayList<String> locais1 = new ArrayList<>();

            for (Local[] tmp : viagensOrdenadas) {
                locais1.add(m.viagemString(tmp));

            }
            DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(locais1.toArray());
            fromC.setModel(defaultComboBoxModel);
        });

        gbc.insets = new Insets(0, 500, 0, 0);
        background.add(ordenaD, gbc);

        JButton b1 = new JButton("Calcular viagem");
        b1.addActionListener(new BtnCalcula());

        gbc.insets = new Insets(50, 600, 0, 0);
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

    /**
     * chama a janela de visualizar a Viagem
     */
    private void visualizarViagem() {
        this.setVisible(false);
        new visualizarViagem(m, this).setVisible(true);
    }

    /**
     * Chama a janela do menu inicial
     */
    private void iniciaMenu() {
        this.setVisible(false);
        new Menu(m).setVisible(true);
    }

    /**
     * @return devolve a escolha feita pelo utilizador
     */
    String getEscolha() {
        return escolha;
    }

    /**
     * le a escolha feita pelo utilizador e chama a função para ir para a janela seguinte
     */
    private class BtnCalcula implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            escolha = fromC.getSelectedItem().toString();
            visualizarViagem();
        }
    }
}
