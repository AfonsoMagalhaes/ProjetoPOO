package interfaces_GUI;

import po.Local;
import po.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class visualizarViagem extends JFrame {

    private final Entrar j;
    private JPanel canvas;
    private Main m;
    private JButton b2, b3;
    private JLabel l1;
    private boolean mestrado;
    private String hot;
    private int custo;
    private ArrayList<Local[]> viagens;

    public visualizarViagem(Main m, Entrar j) {
        this.m = m;
        this.j = j;
        mestrado = j.getMestrado();
        hot = j.getHot();
        custo = j.getCusto();
        System.out.println(mestrado);
        System.out.println(hot);
        System.out.println(custo);
//        if(mestrado==true) {
//            viagens = m.criaViagensMes(custo, hot);
//        } else {
//            viagens = m.criaViagensLic(custo, hot);
//        }
//aqui da erro a chamar, os meus valores tao bem, foi o print que te mandei
        //mais ali em baixo digo onde tens de fazer o print



        setTitle("A sua viagem de sonho!");
        setSize(530,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new GridLayout());
        JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("bagagem.jpg")));

        add(background);

        background.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;


        canvas = new JPanel();
        canvas.setLayout(new GridLayout(3,2,10,10));

        //depois é só fazer um print aqui no l1 de qq cena e ver se deu

        l1 = new JLabel("Locais:\n", SwingConstants.CENTER);
        l1.setOpaque(true);
        background.add(l1,gbc);

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

    private void iniciaMenu(ActionEvent evt) {
        this.setVisible(false);

        new Menu(m).setVisible(true);
    }

//    public static void main(String args[]) {
//        new visualizarViagem();
//    }
}
