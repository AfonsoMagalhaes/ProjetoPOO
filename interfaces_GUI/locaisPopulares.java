package interfaces_GUI;

import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class locaisPopulares extends JFrame {
    private Main m;
    private JPanel canvas;
    private ArrayList<String> maisVotados;
    private JButton b2, b3;
    private JLabel l1, l2, l3;

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);
        new Menu(m).setVisible(true);
    }

    public locaisPopulares(Main m){
        this.m = m;
        maisVotados = new ArrayList<>();
        maisVotados = m.getMaisVotados();
//        System.out.println(Arrays.toString(new ArrayList[]{maisVotados}));

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


        l1 = new JLabel(maisVotados.get(0), SwingConstants.CENTER);
        l1.setOpaque(true);
        background.add(l1,gbc);

        l2 = new JLabel(maisVotados.get(1), SwingConstants.CENTER);
        l2.setOpaque(true);
        background.add(l2, gbc);

        l3 = new JLabel(maisVotados.get(2), SwingConstants.CENTER);
        l3.setOpaque(true);
        background.add(l3, gbc);

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

//    public static void main(String args[]) {
//        new locaisPopulares();
//    }
}
