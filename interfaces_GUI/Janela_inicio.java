package interfaces_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela_inicio extends JFrame {
    private JButton b1;

    public Janela_inicio() {
        setTitle("A sua viagem de sonho!");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new GridLayout());
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("bagagem.jpg")));

        add(background);

        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        b1 = new JButton("Entrar");
        b1.setBackground(Color.WHITE);
//        b1.setForeground(Color.lightGray);
        b1.setOpaque(true);
        b1.setBorderPainted(false);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciaMenu(e);
            }
        });


        background.add(b1, gbc);

        gbc.weighty = 1;

        this.setVisible(true);
    }

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);
        new menu().setVisible(true);
    }

    public static void main(String args[]) {
        new Janela_inicio();
    }

}
