package interfaces_GUI;


import po.Aluno;
import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


class Menu extends JFrame {
    private Main m;
    private JComboBox<ArrayList> fromC;
    private ArrayList<Aluno> listaAlunos;
    private boolean mestrado;


    Menu(Main m) {
        this.m = m;

        setTitle("A sua viagem de sonho!");
        setSize(900, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new GridLayout());
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("Menu.jpg")));

        add(background);

        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 500, 0, 0);
        JLabel l1 = new JLabel("<html><h1><strong><b><font color=\"black\">A sua viagem de sonho!</font></b></strong></h1><hr></html>");

        background.add(l1, gbc);

        JPanel aux = new JPanel();
        aux.setLayout(new GridLayout(1, 2, 10, 10));
        aux.setOpaque(false);



        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        listaAlunos = m.getAlunos();
        ArrayList<String> alunos = new ArrayList<>();
        for (Aluno tmp : listaAlunos) {
            alunos.add(tmp.getNome());
        }
        fromC = new JComboBox(alunos.toArray());
        aux.add(fromC);

        gbc.insets = new Insets(30, 500, 0, 0);

        JButton b2 = new JButton("Entrar");
        b2.addActionListener(new ButaoEntrar());
        aux.add(b2);
        background.add(aux, gbc);


        gbc.insets = new Insets(20, 500, 0, 0);

        JButton b1 = new JButton("Registo");

        b1.addActionListener(e -> Registo());
        background.add(b1, gbc);
        gbc.insets = new Insets(0, 500, 0, 0);

        JButton b3 = new JButton("Locais mais populares");

        b3.addActionListener(e -> locaispopulares());
        background.add(b3, gbc);

        JButton b4 = new JButton("Sair");

        b4.addActionListener(e -> System.exit(0));
        gbc.insets = new Insets(0, 500, 200, 0);
        background.add(b4, gbc);

        gbc.weighty = 1;


        this.setVisible(true);

    }

    private void locaispopulares() {
        this.setVisible(false);
        new locaisPopulares(m).setVisible(true);
    }

    private void Registo() {
        this.setVisible(false);
        new Registo(m).setVisible(true);
    }

    private void Entrar() {
        for (Aluno tmp : listaAlunos) {
            if (tmp.getNome().equalsIgnoreCase((String) fromC.getSelectedItem())) {
                if (tmp.isMestrado()) {
                    mestrado = true;
                }
            } else {
                mestrado = false;
            }
        }


        this.setVisible(false);
        new Entrar(m, this).setVisible(true);
    }

    boolean getMestrado() {
        return mestrado;
    }

    private class ButaoEntrar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Aluno tmp : listaAlunos) {
                if (tmp.getNome().equalsIgnoreCase((String) fromC.getSelectedItem())) {
                    Entrar();
                }
            }
        }
    }

}

