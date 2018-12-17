package interfaces_GUI;

import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

class Registo extends JFrame {

    private Main m;
    private JTextField n1,n2;
    private JComboBox<String> fromC;
    private boolean mestrado;


    Registo(Main m) {
        this.m=m;
        setTitle("A sua viagem de sonho!");
        setSize(900, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new GridLayout());
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("Menu.jpg")));

        add(background);

        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;


        JPanel canvas = new JPanel();
        canvas.setLayout(new GridLayout(4,2,10,10));
        JLabel l1 = new JLabel("Introduza o seu nome:", SwingConstants.RIGHT);
        l1.setFont(new Font("Serif", Font.BOLD, 17));
        canvas.add(l1);

         n1= new JTextField(10);
        canvas.add(n1);


        JLabel l2 = new JLabel("Intoduza o seu email:", SwingConstants.RIGHT);
        l2.setFont(new Font("Serif", Font.BOLD, 17));
        canvas.add(l2);

        n2 = new JTextField(10);
        canvas.add(n2);

        JLabel l4 = new JLabel("Selecione o grau do curso:", SwingConstants.RIGHT);
        l4.setFont(new Font("Serif", Font.BOLD, 17));
        canvas.add(l4);

        String[] items = {"Licenciatura", "Mestrado"};
        fromC = new JComboBox<>(items);

        canvas.add(fromC);


        JLabel l3 = new JLabel("");
        canvas.add(l3);

        JButton b1 = new JButton("Registar");
        b1.addActionListener(e -> {
            try {
                BtnRegista(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        canvas.add(b1);

        canvas.setOpaque(false);
        gbc.insets = new Insets(0, 400, 0, 0);
        background.add(canvas,gbc);






        gbc.anchor = GridBagConstraints.SOUTH;


        gbc.insets = new Insets(100, 500, 0, 0);
        JButton b2 = new JButton("Voltar");
        b2.addActionListener(this::iniciaMenu);


        background.add(b2,gbc);

        gbc.insets = new Insets(0, 500, 200, 0);
        JButton b3 = new JButton("Sair");

        b3.addActionListener(e -> System.exit(0));

        background.add(b3,gbc);



        this.setVisible(true);
    }

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);
        new Menu(m).setVisible(true);
    }

    private void Entrar(ActionEvent evt) {
        this.setVisible(false);
        new Entrar(m, this).setVisible(true);
    }

    private void BtnRegista(ActionEvent e) throws IOException {
        boolean registado;
        String nome = n1.getText();
        String email = n2.getText();
        mestrado = fromC.getSelectedIndex() == 1;

        registado = m.registo(nome, email, mestrado);


        if (!registado) {
            JOptionPane.showMessageDialog(null, "O utilizador já existe", "ERRO", JOptionPane.INFORMATION_MESSAGE);

        } else {
            this.setVisible(false);
            Entrar(e);
        }

    }

    boolean getMestrado() {
        return mestrado;
    }

}

