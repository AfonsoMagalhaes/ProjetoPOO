import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
                frame.setTitle("Preferência do utilizador");
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel lista= new JLabel("Lista de Pontos de Interesse:");
                JList list_interesse = new JList((ListModel) p_interesse);
                list_interesse.setVisibleRowCount(4);
                list_interesse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                add(new JScrollPane(list_interesse));
                JButton hot_interesse = new JButton("Hot");

                JPanel panel1 = new JPanel();
                panel.setLayout(new BorderLayout());

                panel1.add(lista, BorderLayout.NORTH);
                panel1.add(list_interesse, BorderLayout.CENTER);
                panel1.add(hot_interesse, BorderLayout.SOUTH);

                frame1.add(panel1);
                frame.setVisible(false);
                frame1.setVisible(true);
                hot_interesse.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lic.add(new Licenciatura(nome.getText(),email.getText(), (String) list_interesse.getSelectedValue()));
                    }
                });
            }

        });
        mestrado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                frame.setTitle("Preferência do utilizador");
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel lista= new JLabel("Lista de Locais:");
                JList list_locais = new JList((ListModel) locais);
                list_locais.setVisibleRowCount(4);
                list_locais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                add(new JScrollPane(list_locais));
                JButton hot_local = new JButton("Hot");

                JPanel panel1 = new JPanel();
                panel.setLayout(new BorderLayout());

                panel1.add(lista, BorderLayout.NORTH);
                panel1.add(list_locais, BorderLayout.CENTER);
                panel1.add(hot_local, BorderLayout.SOUTH);

                frame1.add(panel1);
                frame.setVisible(false);
                frame1.setVisible(true);
                hot_local.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mes.add(new Mestrado(nome.getText(),email.getText(), (String) list_locais.getSelectedValue()));
                    }
                });
            }

        });
    }

}
