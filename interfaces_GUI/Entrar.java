package interfaces_GUI;


import po.Local;
import po.Main;
import po.pInteresse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Entrar extends JFrame{
    private Registo j;
    private Main m;
    private Menu menu;
    private JPanel canvas;
    private boolean mestrado;
    private String hot;
    private int custo;

    private JButton b1, b2, b3;
    private JLabel l1, l2, l3, l4;
    private JComboBox<String> fromC, pti;
    private JTextField number1;


    private void visualizarViagem(ActionEvent evt) {
        this.setVisible(false);
        new visualizarViagem(m,this).setVisible(true);
    }



    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);
        new Menu(m).setVisible(true);
    }

    public Entrar(Main m, Registo j) {
        this.m = m;
        this.j = j;
        mestrado = j.getMestrado();
        init();

    }

    public Entrar(Main m, Menu menu) {
        this.m = m;
        this.menu = menu;
        mestrado = menu.getMestrado();
        init();
    }

    private void mudaCombo(ActionEvent event) {
        ArrayList<Local> listaLocais = m.getLocais();
        ArrayList<ArrayList<pInteresse>> aux = new ArrayList<>();

        for (Local tmp : listaLocais) {
            if (tmp.getCidade() == fromC.getActionCommand()) {
                aux.add(tmp.getPInteresse());
            }
        }
        pti = new JComboBox(aux.toArray());
    }

    private void init() {
        setTitle("A sua viagem de sonho!");
        setSize(530, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new GridLayout());
        JLabel background = new JLabel(new ImageIcon(this.getClass().getResource("Menu.jpg")));

        add(background);

        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;


        canvas = new JPanel();
        canvas.setLayout(new GridLayout(4, 2, 10, 10));

        l1 = new JLabel("Escolha a cidade do seu pi:", SwingConstants.CENTER);

        l1.setBackground(Color.gray);
        l1.setOpaque(true);
        canvas.add(l1);

        ArrayList<Local> listaLocais = m.getLocais();
        ArrayList<String> locais = new ArrayList<String>();
        for (Local tmp : listaLocais) {
            locais.add(tmp.getCidade());
        }
        fromC = new JComboBox(locais.toArray());
//        fromC.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent event){
//                mudaCombo(event);
//            }
//
//        });
        canvas.add(fromC);

        if (mestrado == true) {
            l2 = new JLabel("Ponto de interesse a evitar:", SwingConstants.CENTER);
        } else {
            l2 = new JLabel("Ponto de interesse a não perder:", SwingConstants.CENTER);
        }

        l2.setBackground(Color.gray);
        l2.setOpaque(true);
        canvas.add(l2);


        //________________________________________________________________________________________________________________


       /* ArrayList<String> aux=new ArrayList<>();

        for (Local tmp : listaLocais) {
            if(tmp.getCidade()==fromC.getActionCommand()){
                aux.add(tmp.getPInteresse());
            }
        }
        pti = new JComboBox(aux.toArray());
        */


        //deixei isto so para funcionar, mas é para usar o que está comentado em cima
        pti = new JComboBox(locais.toArray());


        canvas.add(pti);

        //_________________________________________________________________________________________________________________


        l2 = new JLabel("Máximo a gastar:", SwingConstants.CENTER);
        l2.setBackground(Color.gray);
        l2.setOpaque(true);
        canvas.add(l2);

        number1 = new JTextField(10);
        canvas.add(number1);

        l3 = new JLabel("");
        canvas.add(l3);

        b1 = new JButton("Calcula viagem");
        b1.addActionListener(new BtnCalcula());
        canvas.add(b1);

        canvas.setOpaque(false);

        background.add(canvas, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;


        gbc.insets = new Insets(50, 0, 0, 0);
        b2 = new JButton("Voltar");
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                iniciaMenu(e);
            }
        });


        background.add(b2, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        b3 = new JButton("Sair");

        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        background.add(b3, gbc);


        this.setVisible(true);
    }

    public boolean getMestrado() {
        return mestrado;
    }

    public String getHot() {
        return hot;
    }

    public int getCusto() {
        return custo;
    }

    private class BtnCalcula implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            hot = (String) fromC.getSelectedItem();
            custo = Integer.parseInt(number1.getText());
            visualizarViagem(e);

        }
    }

}
