package po;


import interfaces_GUI.janelaInicio;
import interfaces_GUI.janelaInicio;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class Main extends JFrame{


    private static ArrayList<Pessoa> listaComunidade;
    private static ArrayList<Local> locais;
    private static ArrayList<Local> museus;
    private static ArrayList<P_Interesse> pinteresse;
    private float deslocacao;

    public Main() {

        this.locais = new ArrayList<>();
        this.museus = new ArrayList<>();
        this.listaComunidade = new ArrayList<>();

        leficheiro();


        System.out.println("LISTA LOCAIS");
        for(Local tmp : locais)
            System.out.println(tmp.toString());

        new janelaInicio(this).setVisible(true);
    }

    public void leficheiro() {
        BufferedReader br = null;
        FileReader fr = null;
        String st;
        try {
            br = new BufferedReader(new FileReader("locais.txt"));
            this.deslocacao=Integer.parseInt(br.readLine());

            String nome = null;
            int x = 0, y = 0;
            boolean museu=false;
            while ((st = br.readLine()) != null) {
                String[] tab = st.split(";");
                if (tab[0].equals("Local")) {
                    pinteresse = new ArrayList<P_Interesse>();
                    x = Integer.parseInt(tab[2]);
                    y = Integer.parseInt(tab[3]);
                    nome = tab[1];
                } else if (tab[0].equals("Museu")) {
                    pinteresse.add(new Museu(tab[1],tab[2],Float.parseFloat(tab[3]), Float.parseFloat(tab[4]),tab[5]));
                    museu=true;
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
                } else if(st.equals("\n")){
                    System.out.println(nome);
                    Local l = new Local(nome, x, y);
                    l.setP_interesse(pinteresse);
                    locais.add(l);
                    if(museu=true){
                        museus.add(l);
                        museu=false;
                    }
                } else{continue;}
            }
        } catch (IOException e) {
            System.out.println("Excepcao a carregar ficheiro txt: " + e);
        }
    }


    public void escreveCicheirobj() throws IOException {
        try {
            ObjectOutputStream oolic = new ObjectOutputStream(new FileOutputStream("licenciatura.txt"));
            oolic.writeObject(listaComunidade);
            oolic.close();
            ObjectOutputStream oolocais = new ObjectOutputStream(new FileOutputStream("locais.txt"));
            oolocais.writeObject(locais);
            oolocais.close();
            ObjectOutputStream oomuseus = new ObjectOutputStream(new FileOutputStream("museus.txt"));
            oomuseus.writeObject(museus);
            oomuseus.close();
        } catch (FileNotFoundException var5) {
            System.out.println("File not found");
        } catch (IOException var6) {
            System.out.println("Error initializing stream");
        }

    }

    public void leCicheiroobj() throws IOException {
        File ficheiroLicenciatura = new File("licenciatura.txt");
        File ficheiroMestrado = new File("mestrado.txt");
        File ficheiroLocais = new File("locais.txt");
        File ficheiroMuseus = new File("museus.txt");
        ObjectInputStream oimuseus;
        if (ficheiroLicenciatura.exists()) {
            try {
                ObjectInputStream oilic = new ObjectInputStream(new BufferedInputStream(new FileInputStream("licenciatura.txt")));
                listaComunidade = (ArrayList)oilic.readObject();
                oilic.close();
            } catch (ClassNotFoundException var9) {
                var9.printStackTrace();
            }
        }

        if (ficheiroLocais.exists()) {
            try {
                ObjectInputStream oilocais = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locais.txt")));
                locais = (ArrayList)oilocais.readObject();
                oilocais.close();
            } catch (ClassNotFoundException var7) {
                var7.printStackTrace();
            }
        }

        if (ficheiroMuseus.exists()) {
            try {
                oimuseus = new ObjectInputStream(new BufferedInputStream(new FileInputStream("museus.txt")));
                museus = (ArrayList)oimuseus.readObject();
                oimuseus.close();
            } catch (ClassNotFoundException var6) {
                var6.printStackTrace();
            }
        }

    }

    public float custoCocal(Local local){
        int custo=0;
        for(P_Interesse i :local.getPInteresse()){
                    custo+=i.getEntrada()+i.getCustoextra();// alterar de novo os atributos dos pontos de interesse
        }
        return custo;
    } // custo de um local

    public double distanciaCocais(Local local1, Local local2){
        return Math.sqrt(Math.pow(local1.getX()-local2.getX(),2)+Math.pow(local1.getY()-local2.getY(),2));
    } //Distância entre 2 locais

    public int deslocaçãoCocais(Local local1, Local local2){ //custo por km no ficheiro de texto(inicio)
        return (int) (distanciaCocais(local1, local2)*this.deslocacao);
    } //Custo da deslocação entre 2 locais

    public float custoCiagem(Local[] viagem){
        return custoCocal(viagem[0])+custoCocal(viagem[1])+custoCocal(viagem[3])+deslocaçãoCocais(viagem[0], viagem[1])+ deslocaçãoCocais(viagem[1], viagem[2]);
    } //Calcula o custo da viagem

    public Local pinteresseCot(String hot){
        Local x = null;
        for(Local l: locais){
            for(P_Interesse i : l.getPInteresse()){
                if(hot.equals(i.getNome())){
                    x = l;
                }
            }
        }
        return x;
    } //Vai buscar o local do ponto de interesse hot

    public Local localCvitar(String hot){
        Local x = null;
        for(Local l: locais){
            if(hot.equals(l.getCidade())){
                x=l;
            }
        }
        return x;
    } //Vai buscar do local a evitar

    public ArrayList<Local[]> criaCiagensCic(int custo, String hot){
        ArrayList<Local[]> viagens = new ArrayList<>();
        Local[] viagem = new Local[3];
        viagem[0] = pinteresseCot(hot);
        for(Local m: museus) {
            viagem[1]= m;
            for(Local l: locais){
                if(m.getCidade().equals(l.getCidade())){
                    continue;
                }else{
                    viagem[2]=l;
                    if(custoCiagem(viagem)<=custo){
                        viagens.add(viagem);
                    }
                }
            }
        }
        return viagens;
    } //Cria lista das viagens que satisfazem o custo máximo e o ponto de interesse

    public ArrayList<Local[]> criaCiagensCes(int custo, String hot){
        ArrayList<Local[]> viagens = new ArrayList<>();
        Local[] viagem = new Local[3];
        Local X = localCvitar(hot);
        for(Local m: museus){
            if(X.getCidade().equals(m.getCidade())){
                continue;
            }else{
                viagem[0]=m;
            }
            for(Local l1: locais){
                if(X.getCidade().equals(l1.getCidade())){
                    continue;
                }else{
                    viagem[1]=l1;
                }
                for(Local l2: locais){
                    if(X.getCidade().equals(l2.getCidade()) || l1.getCidade().equals(l2.getCidade())){
                        continue;
                    }else{
                        viagem[2]=l2;
                        if(custoCiagem(viagem)<=custo) {
                            viagens.add(viagem);
                        }
                    }
                }
            }
        }
        return viagens;
    } //Cria lista das viagens que satisfazem o custo máximo e o local a evitar

    public String viagemCelecionada(Local[] viagem){
        return "Viagem(custo: " + custoCiagem(viagem) + ")\nLocais:\n" + viagem[0].toString() + "\n" + viagem[1].toString() + "\n" + viagem[2].toString() + "\n" +
                "Distâncias:\n" + viagem[0] + "a" + viagem[1] + " - " + distanciaCocais(viagem[0], viagem[1]) + "\n" + viagem[1] + "a" + viagem[2] + " - " + distanciaCocais(viagem[1], viagem[2]) + "\n" +
                "Custos:\n" + viagem[0] + " - " + custoCocal(viagem[0]) + "\n" + viagem[1] + " - " + custoCocal(viagem[1]) + "\n" + viagem[2] + " - " + custoCocal(viagem[2]);

    } //Ponto 5 do projeto



    public static boolean registo(String nome, String email, boolean mestrado){
        //verifica se a pessoa já está registada
        for(Pessoa tmp : listaComunidade){
            System.out.println(tmp.getNome());
            if(tmp.getNome().equalsIgnoreCase(nome)){
                return false;
            }
        }
        if(mestrado==false) {
            System.out.println(email);
            System.out.println(nome);
            Pessoa novo = new Licenciatura(nome, email);
            listaComunidade.add(novo);
        }
        else{
            Pessoa novo = new Mestrado(nome, email);
            listaComunidade.add(novo);
        }

        return true;
    }


    public static void main(String[] args){
        Main viagem = new Main();



    }

    public ArrayList<Local> getLocais() {
        return locais;
    }
}
