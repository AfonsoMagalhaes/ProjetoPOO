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
    private JLabel l1, l4, l2, l3;

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);
        new Menu(m).setVisible(true);
    }

    public locaisPopulares(Main m){
        this.m = m;
        maisVotados = new ArrayList<>();
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

        l4 = new JLabel("<html><u>Locais mais populares</u></html>");
        l4.setFont(new Font("Serif", Font.BOLD, 40));


        gbc.insets = new Insets(0, 400, 0, 0);
        background.add(l4, gbc);

        gbc.anchor = GridBagConstraints.WEST;


        canvas = new JPanel();
        canvas.setLayout(new GridLayout(3,2,10,10));

        gbc.insets = new Insets(50, 400, 0, 0);


        l1 = new JLabel("1. " + maisVotados.get(0) + ", " + m.getLocal(maisVotados.get(0)), SwingConstants.CENTER);

        l1.setFont(new Font("Serif", Font.PLAIN, 35));

        background.add(l1, gbc);

        l2 = new JLabel("2. " + maisVotados.get(1) + ", " + m.getLocal(maisVotados.get(1)), SwingConstants.CENTER);

        l2.setFont(new Font("Serif", Font.PLAIN, 30));
        gbc.insets = new Insets(10, 400, 0, 0);
        background.add(l2, gbc);

        l3 = new JLabel("3. " + maisVotados.get(2) + ", " + m.getLocal(maisVotados.get(2)), SwingConstants.CENTER);

        l3.setFont(new Font("Serif", Font.PLAIN, 25));

        background.add(l3, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;


        gbc.insets = new Insets(100, 400, 0, 0);
        b2 = new JButton("Voltar");
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                iniciaMenu(e);
            }
        });


        background.add(b2,gbc);

        gbc.insets = new Insets(0, 400, 0, 0);
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
