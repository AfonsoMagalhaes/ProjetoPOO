package interfaces_GUI;

import javax.swing.*;
import java.awt.*;

public class menu extends JFrame {
    //private final JComboBox<String> fromC;
    private JPanel canvas;
    private JButton butao1;
    private JLabel img;




    public menu() {


        setTitle("A sua viagem de sonho!");
        setSize(300,300);
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

        canvas.add(img,BorderLayout.NORTH);
        canvas.add(butao1,BorderLayout.SOUTH);

        this.add(canvas);
        this.setVisible(true);
    }


    public static void main(String args[]) {
        new Janela_inicio();
    }

}

