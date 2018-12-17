package interfaces_GUI;


import po.Main;

import javax.swing.*;
import java.awt.*;

public class janelaInicio extends JFrame {
    private Main m;

    public janelaInicio(Main m) {
        this.m = m;

        setTitle("A sua viagem de sonho!");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new GridLayout());
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("bagagem.jpg")));

        add(background);

        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JButton b1 = new JButton("Entrar");
        b1.setBackground(Color.WHITE);
        b1.setOpaque(true);
        b1.setBorderPainted(false);

        b1.addActionListener(e -> iniciaMenu());


        background.add(b1, gbc);

        gbc.weighty = 1;

        this.setVisible(true);
    }

    private void iniciaMenu() {
        this.setVisible(false);
        new Menu(m).setVisible(true);
    }



}
