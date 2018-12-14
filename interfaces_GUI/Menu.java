package interfaces_GUI;


import po.Aluno;
import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Menu extends JFrame{
    private janelaInicio j;
    private Main m;
    private JButton b1, b2, b3, b4;
    private JLabel l1;
    private JComboBox<ArrayList> fromC;
    private ArrayList<Aluno> listaAlunos;
    private boolean mestrado;


    public Menu(Main m) {
        this.m = m;

        setTitle("A sua viagem de sonho!");
        setSize(530,400);
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

        l1.setBackground(Color.GRAY);
        l1.setOpaque(true);


        background.add(l1, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        listaAlunos = m.getAlunos();
        ArrayList<String> alunos = new ArrayList<String>();
        for (Aluno tmp : listaAlunos) {
            alunos.add(tmp.getNome());
        }
        fromC = new JComboBox(alunos.toArray());



        b2 = new JButton("Entrar");
        b2.addActionListener(new ButaoEntrar());


        b1=new JButton("Registo");

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Registo(e);
            }
        });



        b3 = new JButton("Locais mais populares");

        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                locaispopulares(e);
            }
        });

        b4 = new JButton("Sair");

        b4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        background.add(fromC, gbc);
        background.add(b2, gbc);
        background.add(b3, gbc);
        background.add(b1,gbc);
        background.add(b4, gbc);

        gbc.weighty = 1;



        this.setVisible(true);


    }

    private void locaispopulares(ActionEvent evt) {
        this.setVisible(false);
        new locaisPopulares(m).setVisible(true);
    }

    private void Registo(ActionEvent evt) {
        this.setVisible(false);
        new Registo(m).setVisible(true);
    }

    private void Entrar(ActionEvent evt) {
        for (Aluno tmp : listaAlunos) {
            if (tmp.getNome().equalsIgnoreCase((String) fromC.getSelectedItem())) {
                if (tmp.isMestrado() == true) {
                    mestrado = true;
                }
            } else {
                mestrado = false;
            }
        }


        this.setVisible(false);
        new Entrar(m, this).setVisible(true);
    }

    public boolean getMestrado() {
        return mestrado;
    }

    private class ButaoEntrar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 2; i++) {
                if (fromC.getSelectedIndex() == i) {
                    Entrar(e);
                }
            }
        }
    }

}

