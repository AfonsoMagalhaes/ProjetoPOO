package interfaces_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela_inicio extends JFrame {
    //private final JComboBox<String> fromC;
    private JPanel canvas;
    private JButton butao1;
    private JLabel img;




    public Janela_inicio() {


        setTitle("A sua viagem de sonho!");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        butao1 = new JButton("Entrar");

        JButton button1 = new JButton("Entrar");
        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("bagagem.jpg"));
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(480, 320, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        img=new JLabel(imageIcon);

        canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        butao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciaMenu(e);

            }
        });

        canvas.add(img,BorderLayout.NORTH);
        canvas.add(butao1,BorderLayout.SOUTH);

        this.add(canvas);
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
