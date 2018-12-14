package interfaces_GUI;

import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class locaispopulares extends JFrame {
    private Main m;
    private JPanel canvas;

    private JButton b2, b3;
    private JLabel l1;

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);
//        new Menu(m, this).setVisible(true);
    }

    public locaispopulares(){
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

        l1 =new JLabel("<html><h1><strong><b><font color=\"black\">OS LOCAIS MAIS POPULARES SÃO:</font></b></strong></h1><hr></html>");

        l1.setBackground(Color.GRAY);
        l1.setOpaque(true);


        background.add(l1, gbc);

        gbc.anchor = GridBagConstraints.CENTER;


        canvas = new JPanel();
        canvas.setLayout(new GridLayout(3,2,10,10));





        //aqui temos de chamar a função que calcula e imprimir os dados;







        l1 = new JLabel("LOCAL 1", SwingConstants.CENTER);
        l1.setOpaque(true);
        background.add(l1,gbc);

        gbc.anchor = GridBagConstraints.SOUTH;


        gbc.insets = new Insets(50, 0, 0, 0);
        b2 = new JButton("Voltar");
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                iniciaMenu(e);
            }
        });


        background.add(b2,gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
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

    public static void main(String args[]) {
        new locaispopulares();
    }
}
