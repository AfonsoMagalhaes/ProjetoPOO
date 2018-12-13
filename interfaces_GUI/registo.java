package interfaces_GUI;

import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registo extends JFrame{

    private JPanel canvas;
    private Main m;
    private JButton b1, b2, b3;
    private JLabel l1,l2,l3,l4,l5;
    private JTextField n1,n2;
    private JComboBox<String> fromC;



    private void entrar(ActionEvent evt) {
        this.setVisible(false);
        new Entrar(m,this).setVisible(true);
    }

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);
//        new Menu(m, this).setVisible(true);
    }

    private void BtnRegista(ActionEvent e) {
            String nome,email;
            boolean mestrado;
            boolean registado;
            nome=n1.getText();
            email=n2.getText();
            mestrado = fromC.getSelectedIndex() == 1;

            registado=Main.registo(nome,email,mestrado);
            System.out.println(registado);

            if(!registado){
                JOptionPane.showMessageDialog(null,"O utilizador já existe","ERRO",1);

            } else{
                this.setVisible(false);
                entrar(e);
            }

    }


    public Registo(Main m){
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
        canvas.setLayout(new GridLayout(4,2,10,10));
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

        l4 = new JLabel("Selecione o grau do curso:",SwingConstants.CENTER);
        l4.setBackground(Color.gray);
        l4.setOpaque(true);
        canvas.add(l4);

        String[] items = {"Licenciatura", "Mestrado"};
        fromC = new JComboBox<>(items);

        canvas.add(fromC);


        l3 = new JLabel("");
        canvas.add(l3);

        b1 = new JButton("Registar");
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BtnRegista(e);
            }
        });
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

//    public static void main(String args[]) {
//        new Registo();
//    }

}

