package interfaces_GUI;


import po.Local;
import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

class Entrar extends JFrame {
    private Main m;
    private boolean mestrado;
    private String hot;
    private int custo;

    private JButton b1, b2, b3;
    private JLabel l1, l2;
    private JComboBox<String> fromC, pti;
    private JTextField number1;


    /**
     * Esta função é chamada depois de ser feito o registo
     *
     * @param m contem todas as funçoes do Main
     * @param j contem todos os dados referentes à janela de registo
     */
    Entrar(Main m, Registo j) {
        this.m = m;
        mestrado = j.getMestrado();
        if (mestrado) {
            initMes();
        } else {
            initLic();
        }

    }


    /**
     * Esta função é chamada depois de carregar em entrar no Menu
     * @param m recebe todas as funçoes do main
     * @param menu  recebe todos os dados relativos ao menu (se é mestrado ou não)
     */
    Entrar(Main m, Menu menu) {
        this.m = m;
        mestrado = menu.getMestrado();
        if (mestrado) {
            initMes();
        } else {
            initLic();
        }
    }

    /**
     * Esta função vai escolher verificar primeiro se é possivel criar viagem com o montante introduzido
     * Transmite função de erro caso não seja
     * Caso seja possivel, chama a janela seguinte e envia os parametros
     */
    private void escolheViagem() {
        ArrayList<Local[]> viagens;

        if (mestrado) {
            viagens = m.criaViagensMes(custo, hot);
        } else {
            viagens = m.criaViagensLic(custo, hot);
        }

        if (viagens.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existem viagens possíveis para esse valor!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.setVisible(false);
            new escolheViagem(m, this).setVisible(true);
        }
    }

    /**
     * função para voltar ao menu anterior
     */
    private void iniciaMenu() {

        this.setVisible(false);
        new Menu(m).setVisible(true);

    }

    /**
     * função chamada caso o utilizador seja de licenciatura
     */
    private void initLic() {
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
        gbc.anchor = GridBagConstraints.WEST;


        l1 = new JLabel("Escolha a cidade do seu pi:", SwingConstants.RIGHT);
        l1.setFont(new Font("Serif", Font.BOLD, 17));

        gbc.insets = new Insets(0, 600, 0, 0);
        background.add(l1, gbc);

        ArrayList<Local> listaLocais = m.getLocais();
        ArrayList<String> locais = new ArrayList<>();
        for (Local tmp : listaLocais) {
            locais.add(tmp.getCidade());
        }
        fromC = new JComboBox(locais.toArray());
        fromC.addActionListener(new mudaCombo());


        gbc.insets = new Insets(5, 600, 0, 0);
        background.add(fromC, gbc);

        l2 = new JLabel("Ponto de interesse a não perder:", SwingConstants.RIGHT);

        l2.setFont(new Font("Serif", Font.BOLD, 17));
        gbc.insets = new Insets(40, 600, 0, 0);
        background.add(l2, gbc);

        ArrayList<String> aux = new ArrayList<>();
        for (Local tmp : listaLocais) {
            if (tmp.getCidade().equalsIgnoreCase("Lisboa")) {
                aux = m.getPInteresse(tmp);
            }
        }
        pti = new JComboBox(aux.toArray());
        gbc.insets = new Insets(5, 600, 0, 0);
        background.add(pti, gbc);
        l2 = new JLabel("Máximo a gastar:", SwingConstants.RIGHT);

        l2.setFont(new Font("Serif", Font.BOLD, 17));

        gbc.insets = new Insets(40, 600, 0, 0);
        background.add(l2, gbc);

        number1 = new JTextField(10);
        gbc.insets = new Insets(5, 600, 0, 0);
        background.add(number1, gbc);


        b1 = new JButton("Calcular viagem");
        b1.addActionListener(new BtnCalcula());
        gbc.insets = new Insets(40, 600, 0, 0);
        background.add(b1, gbc);


        gbc.anchor = GridBagConstraints.SOUTH;


        gbc.insets = new Insets(80, 600, 0, 0);
        b2 = new JButton("Voltar");
        b2.addActionListener(e -> iniciaMenu());


        background.add(b2, gbc);

        gbc.insets = new Insets(0, 600, 0, 0);
        b3 = new JButton("Sair");

        b3.addActionListener(e -> System.exit(0));

        background.add(b3, gbc);


        this.setVisible(true);
    }

    /**
     * função chamada caso o aluno seja de mestrado
     */
    private void initMes() {
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
        gbc.anchor = GridBagConstraints.WEST;


        l1 = new JLabel("Escolha a cidade a evitar:", SwingConstants.RIGHT);
        l1.setFont(new Font("Serif", Font.BOLD, 17));
        gbc.insets = new Insets(0, 600, 0, 0);
        background.add(l1, gbc);

        ArrayList<Local> listaLocais = m.getLocais();
        ArrayList<String> locais = new ArrayList<>();
        for (Local tmp : listaLocais) {
            locais.add(tmp.getCidade());
        }
        fromC = new JComboBox(locais.toArray());
        gbc.insets = new Insets(5, 600, 0, 0);
        background.add(fromC, gbc);


        l2 = new JLabel("Máximo a gastar:", SwingConstants.RIGHT);
        l2.setFont(new Font("Serif", Font.BOLD, 17));

        gbc.insets = new Insets(40, 600, 0, 0);
        background.add(l2, gbc);

        number1 = new JTextField(10);
        gbc.insets = new Insets(5, 600, 0, 0);
        background.add(number1, gbc);


        b1 = new JButton("Calcular viagem");
        b1.addActionListener(new BtnCalcula());
        gbc.insets = new Insets(40, 600, 0, 0);
        background.add(b1, gbc);


        gbc.anchor = GridBagConstraints.SOUTH;


        gbc.insets = new Insets(80, 600, 0, 0);
        b2 = new JButton("Voltar");
        b2.addActionListener(e -> iniciaMenu());


        background.add(b2, gbc);

        gbc.insets = new Insets(0, 600, 0, 0);
        b3 = new JButton("Sair");

        b3.addActionListener(e -> System.exit(0));

        background.add(b3, gbc);


        this.setVisible(true);
    }

    /**
     * @return função que devolve true caso seja de mestrado ou false caso não seja
     */
    boolean getMestrado() {
        return mestrado;
    }

    /**
     * @return devolve o hot
     */
    String getHot() {
        return hot;
    }

    /**
     * @return devolve o custo introduzido pelo utilizador
     */
    int getCusto() {
        return custo;
    }

    /**
     * muda o conteudo da comboBox dos pis em função da cidade escolhida
     */
    private class mudaCombo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Local> listaLocais = m.getLocais();
            ArrayList<String> aux = new ArrayList<>();
            String cidade = Objects.requireNonNull(fromC.getSelectedItem()).toString();

            for (Local tmp : listaLocais) {
                if (tmp.getCidade().equals(cidade)) {
                    aux = m.getPInteresse(tmp);
                }
            }

            DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(aux.toArray());
            pti.setModel(defaultComboBoxModel);
        }
    }

    /**
     * recolhe os dados do utilizador e chama a função para ir para a janela seguinte
     */
    private class BtnCalcula implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!mestrado) {
                hot = (String) pti.getSelectedItem();
            } else {
                hot = (String) fromC.getSelectedItem();
            }
            custo = Integer.parseInt(number1.getText());
            escolheViagem();

        }
    }

}
