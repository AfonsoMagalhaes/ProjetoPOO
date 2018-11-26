import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;


public class Gestor extends JFrame implements Serializable  {

    private ArrayList<Mestrado> mes;
    private ArrayList<Licenciatura> lic;
    private ArrayList<Local> locais;
    private ArrayList<P_Interesse> p_interesse;


    public Gestor() {
        this.mes = new ArrayList<>();
        this.lic = new ArrayList<>();
        this.locais = new ArrayList<>();
        this.p_interesse = new ArrayList<>();
    }


    //Aqui vamos colocar as funções todas
    //
    public void registo(){
        JFrame frame = new JFrame();
        frame.setTitle("Registo");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Nome:");
        JTextField nome = new JTextField(10);
        JLabel label2 = new JLabel("Email:");
        JTextField email = new JTextField(10);
        JButton licenciatura = new JButton("Licenciatura");
        JButton mestrado = new JButton("Mestrado");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));

        panel.add(label1);
        panel.add(nome);
        panel.add(label2);
        panel.add(email);
        panel.add(licenciatura);
        panel.add(mestrado);

        frame.add(panel);
        frame.setVisible(true);
        licenciatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame.setTitle("Preferência");
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JList hot = new JList(p_interesse);

                JPanel panel1 = new JPanel();
                panel.setLayout(new BorderLayout());

            }
        });
    }

}
