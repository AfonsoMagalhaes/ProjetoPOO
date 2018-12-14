package po;


import interfaces_GUI.janelaInicio;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class Main extends JFrame{


    private static ArrayList<Aluno> listaAlunos;
    private static ArrayList<Local> locais;
    private static ArrayList<pInteresse> pinteresse;
    private static List maisVotados;
    private float deslocacao;

    public Main() throws IOException {

        this.locais = new ArrayList<>();
        this.listaAlunos = new ArrayList<>();

        leFicheiro();
        leFicheiroObj();
        for(Local tmp : locais)
            System.out.println(tmp.toString()+"\n");
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno.toString());
        }
        new janelaInicio(this).setVisible(true);
    }

    public boolean registo(String nome, String email, boolean mestrado) throws IOException {
        //verifica se a pessoa já está registada
        for (Aluno tmp : listaAlunos) {
            System.out.println(tmp.getNome());
            if (tmp.getNome().equalsIgnoreCase(nome)) {
                return false;
            }
        }
        if (mestrado == false) {
            Aluno novo = new Licenciatura(nome, email);
            listaAlunos.add(novo);
        } else {
            Aluno novo = new Mestrado(nome, email);
            novo.isMestrado();
            listaAlunos.add(novo);
        }
        escreveFicheiroObj();
        return true;
    }

    public void leFicheiro() {
        BufferedReader br = null;
        FileReader fr = null;
        String st;
        try {
            br = new BufferedReader(new FileReader("locais.txt"));
            this.deslocacao=Integer.parseInt(br.readLine());
            String nome = null;
            int x = 0, y = 0;
            while ((st = br.readLine()) != null) {
                String[] tab = st.split(";");
                if (tab[0].equals("Local")) {
                    pinteresse = new ArrayList<pInteresse>();
                    x = Integer.parseInt(tab[2]);
                    y = Integer.parseInt(tab[3]);
                    nome = tab[1];
                } else if (tab[0].equals("Museu")) {
                    pinteresse.add(new Museu(tab[1],tab[2],Float.parseFloat(tab[3]), Float.parseFloat(tab[4]),tab[5]));
                } else if (tab[0].equals("Universidade")){
                    pinteresse.add(new Universidade(tab[1], tab[2], Float.parseFloat(tab[3]), Float.parseFloat(tab[4])));
                } else if (tab[0].equals("Parque Cultural")){
                    pinteresse.add(new Cultural(tab[1],tab[2], Float.parseFloat(tab[3]),Float.parseFloat(tab[4])));
                } else if (tab[0].equals("Parque Diversões")){
                    pinteresse.add(new Diversões(tab[1],tab[2],Float.parseFloat(tab[3]),Float.parseFloat(tab[4]), Integer.parseInt(tab[5])));
                } else if (tab[0].equals("Parque Aquático")){
                    pinteresse.add(new Aquático(tab[1],tab[2], Float.parseFloat(tab[3]),Float.parseFloat(tab[4]), Integer.parseInt(tab[5]), Boolean.parseBoolean(tab[6])));
                } else if (tab[0].equals("Bar")){
                    pinteresse.add(new Bar(tab[1],tab[2],Float.parseFloat(tab[3]),Float.parseFloat(tab[4]), Float.parseFloat(tab[5])));
                } else if(st.equals("x")){
                    Local l = new Local(nome, x, y);
                    l.setPInteresse(pinteresse);
                    locais.add(l);
                } else{continue;}
            }
        } catch (IOException e) {
            System.out.println("Excepcao a carregar ficheiro txt: " + e);
        }
    }

    public void escreveFicheiroObj() throws IOException {
        try {
            ObjectOutputStream walunos = new ObjectOutputStream(new FileOutputStream("alunosobj.txt"));
            walunos.writeObject(listaAlunos);
            walunos.close();
        } catch (FileNotFoundException var5) {
            System.out.println("File not found");
        } catch (IOException var6) {
            System.out.println("Error initializing stream");
        }

    }

    public void leFicheiroObj() throws IOException {
        File ficheiroAlunos = new File("alunosobj.txt");
        if (ficheiroAlunos.exists()) {
            try {
                ObjectInputStream ralunos = new ObjectInputStream(new BufferedInputStream(new FileInputStream("alunosobj.txt")));
                listaAlunos = (ArrayList) ralunos.readObject();
                ralunos.close();
            } catch (ClassNotFoundException var9) {
                var9.printStackTrace();
            }
        }

    }


    public float custoTotal(Local local) {
        int custo=0;
        for (pInteresse i : local.getPInteresse()) {
                    custo+=i.getEntrada()+i.getCustoextra();// alterar de novo os atributos dos pontos de interesse
        }
        return custo;
    } // custo de um local

    public double distanciasTotais(Local local1, Local local2) {
        return Math.sqrt(Math.pow(local1.getX()-local2.getX(),2)+Math.pow(local1.getY()-local2.getY(),2));
    } //Distância entre 2 locais

    public int deslocaçãoLocais(Local local1, Local local2){ //custo por km no ficheiro de texto(inicio)
        return (int) (distanciasTotais(local1, local2) * this.deslocacao);
    } //Custo da deslocação entre 2 locais

    public float custoViagem(Local[] viagem){
        return custoTotal(viagem[0]) + custoTotal(viagem[1]) + custoTotal(viagem[3]) + deslocaçãoLocais(viagem[0], viagem[1]) + deslocaçãoLocais(viagem[1], viagem[2]);
    } //Calcula o custo da viagem

    public Local localEvitar(String hot) {
        Local x = null;
        for(Local l: locais){
            if (hot.equals(l.getCidade())) {
                x = l;
            }
        }
        return x;
    } //Vai buscar do local a evitar

    public Local pInteresseHot(String hot) {
        Local x = null;
        for(Local l: locais){
            for (pInteresse i : l.getPInteresse()) {
                if (hot.equals(i.getNome())) {
                    x = l;
                }
            }
        }
        return x;
    } //Vai buscar o local do ponto de interesse hot

    public boolean museu(Local l){
        ArrayList<pInteresse> pontosinteresse = l.getPInteresse();
        for (pInteresse pi : pontosinteresse) {
            if(pi.getTipo()=="museu"){
                return true;
            }
        }
        return false;
    } // Verifica se o local tem um museu

    public ArrayList<Local[]> criaViagensMes(int custo, String hot){
        ArrayList<Local[]> viagens = new ArrayList<>();
        Local[] viagem = new Local[3];
        Local X = localEvitar(hot);
        for(Local m: locais){
            if(m.getCidade().equals(X.getCidade())){
                continue;
            }else{
                if (museu(m)==true) {
                    viagem[0] = m;
                    for (Local l1 : locais) {
                        if (l1.getCidade().equals(X.getCidade()) || l1.getCidade().equals(m.getCidade())) {
                            continue;
                        } else {
                            viagem[1] = l1;
                            for (Local l2 : locais) {
                                if (l2.getCidade().equals(X.getCidade()) || l2.getCidade().equals(m.getCidade()) || l2.getCidade().equals(l1.getCidade())) {
                                    continue;
                                } else {
                                    viagem[2] = l2;
                                    if (custoViagem(viagem) <= custo) {
                                        viagens.add(viagem);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        return viagens;
    } //Cria lista das viagens que satisfazem o custo máximo e o local a evitar

    public ArrayList<Local[]> criaViagensLic(int custo, String hot) {
        ArrayList<Local[]> viagens = new ArrayList<>();
        Local[] viagem = new Local[3];
        viagem[0] = pInteresseHot(hot);
        for (Local m : locais) {
            if (museu(m) == true && m.getCidade() != viagem[0].getCidade()) {
                viagem[1] = m;
                for (Local l : locais) {
                    if (l.getCidade().equals(m.getCidade()) || l.getCidade().equals(viagem[0].getCidade())) {
                        continue;
                    } else {
                        viagem[2] = l;
                        if (custoViagem(viagem) <= custo) {
                            viagens.add(viagem);
                        }
                    }
                }
            }
        }
        return viagens;
    } //Cria lista das viagens que satisfazem o custo máximo e o ponto de interesse

    public String viagemSelecionada(Local[] viagem){
        return "Viagem(custo: " + custoViagem(viagem) + ")\nLocais:\n" + viagem[0].toString() + "\n" + viagem[1].toString() + "\n" + viagem[2].toString() + "\n" +
                "Distâncias:\n" + viagem[0] + "a" + viagem[1] + " - " + distanciasTotais(viagem[0], viagem[1]) + "\n" + viagem[1] + "a" + viagem[2] + " - " + distanciasTotais(viagem[1], viagem[2]) + "\n" +
                "Custos:\n" + viagem[0] + " - " + custoTotal(viagem[0]) + "\n" + viagem[1] + " - " + custoTotal(viagem[1]) + "\n" + viagem[2] + " - " + custoTotal(viagem[2]);

    } //Ponto 5 do projeto

    public ArrayList<Local> getLocais() {
        return locais;
    }

    public ArrayList<Aluno> getAlunos() {
        return listaAlunos;
    }

    public void addAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }


    public static void main(String[] args) throws IOException {
        Main viagem = new Main();
    }

}
