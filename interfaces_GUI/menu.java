package interfaces_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class menu extends JFrame{
    private JButton b1, b2, b3, b4;
    private JLabel l1;
    private JComboBox<String> fromC;



    private void entrar(ActionEvent evt) {
        this.setVisible(false);
        new entrar().setVisible(true);
    }

    private void locaispopulares(ActionEvent evt) {
        this.setVisible(false);
        new locaispopulares().setVisible(true);
    }

    private void registo(ActionEvent evt) {
        this.setVisible(false);
        new registo().setVisible(true);
    }

    private class ButaoEntrar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //temos de alterar o 2 pelo numero de utilizadores registados
            for (int i=0;i<2;i++){
                if(fromC.getSelectedIndex()==i){
                    entrar(e);
                }
            }
        }
    }

    public menu() {
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
        //alterar em função dos ficheiros de objeto
        String[] items = {"Aqui vamos ler", "os nomes registados"};
        fromC = new JComboBox<>(items);



        b2 = new JButton("Entrar");
        b2.addActionListener(new ButaoEntrar());


        b1=new JButton("Registo");

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                registo(e);
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


    public static void main(String[] args) {
        new menu();
    }

}

