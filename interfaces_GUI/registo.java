package interfaces_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registo extends JFrame{

    private JPanel canvas;

    private JButton b1, b2, b3;
    private JLabel l1,l2,l3;
    private JTextField n1,n2;



    private void entrar(ActionEvent evt) {
        this.setVisible(false);
        new entrar().setVisible(true);
    }

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);
        new menu().setVisible(true);
    }

    private class BtnRegista implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            entrar(e);

        }
    }

    public registo(){
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
        gbc.anchor = GridBagConstraints.CENTER;




        canvas = new JPanel();
        canvas.setLayout(new GridLayout(3,2,10,10));
        l1 = new JLabel("Introduza o seu nome:", SwingConstants.CENTER);
        l1.setBackground(Color.gray);
        l1.setOpaque(true);
        canvas.add(l1);

         n1= new JTextField(10);
        canvas.add(n1);

        l2 = new JLabel("Intoduza o seu email:", SwingConstants.CENTER);
        l2.setBackground(Color.gray);
        l2.setOpaque(true);
        canvas.add(l2);

        n2 = new JTextField(10);
        canvas.add(n2);

        l3 = new JLabel("");
        canvas.add(l3);

        b1 = new JButton("Registar");
        b1.addActionListener(new BtnRegista());
        canvas.add(b1);

        canvas.setOpaque(false);

        background.add(canvas,gbc);

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
        new registo();
    }

}

