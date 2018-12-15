package po;


import interfaces_GUI.janelaInicio;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Main extends JFrame{


    private static ArrayList<Aluno> listaAlunos;
    private static ArrayList<Local> locais;
    private static ArrayList<pInteresse> pinteresse;
    private static ArrayList<String> maisVotados;
    private float deslocacao; //meti a 0 se nao o custo era muito grande, depois vejo um valor pra isto

    public Main() throws IOException {

        this.locais = new ArrayList<>();
        this.listaAlunos = new ArrayList<>();
        this.maisVotados = new ArrayList<>();

        leFicheiro();
        leFicheiroObj();
        leLescolhidoObj();
        /*for(Local tmp : locais)
            System.out.println(tmp.toString()+"\n");
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno.toString());
        }*/
        ArrayList<Local[]> viagens = criaViagensLic(200, "Torre de Belém");
        for(Local[] v: viagens){
            System.out.println(v[0] + "||" + v[1] + "||" + v[2] + "\n");
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
    }   //regista o aluno

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
//            System.out.println(locais.toString());
        } catch (IOException e) {
            System.out.println("Excepcao a carregar ficheiro txt: " + e);
        }
    }   //le o ficheiro de texto dos locais

    public void escreveFicheiroObj() throws IOException {
        try {
            ObjectOutputStream walunos = new ObjectOutputStream(new FileOutputStream("alunos.data"));
            walunos.writeObject(listaAlunos);
            walunos.close();
        } catch (FileNotFoundException var5) {
            System.out.println("File not found");
        } catch (IOException var6) {
            System.out.println("Error initializing stream");
        }

    }   //adiciona ao ficheiro de objeto

    public void leFicheiroObj() throws IOException {
        File ficheiroAlunos = new File("alunos.data");
        if (ficheiroAlunos.exists()) {
            try {
                ObjectInputStream ralunos = new ObjectInputStream(new BufferedInputStream(new FileInputStream("alunos.data")));
                listaAlunos = (ArrayList) ralunos.readObject();
                ralunos.close();
            } catch (ClassNotFoundException var9) {
                var9.printStackTrace();
            }
        }

    }   //le o ficheiro de obejtos dos alunos

    public void escreveLescolhidosObj() {
        try {
            ObjectOutputStream lEscolhidos = new ObjectOutputStream(new FileOutputStream("locaisEscolhidos.data"));
            lEscolhidos.writeObject(maisVotados);
            lEscolhidos.close();
        } catch (FileNotFoundException var5) {
            System.out.println("File not found");
        } catch (IOException var6) {
            System.out.println("Error initializing stream");
        }
    }   //atualiza a lista nos ficheiros de objeto

    public void leLescolhidoObj() throws IOException {
        File fLescolhidos = new File("locaisEscolhidos.data");
        if (fLescolhidos.exists()) {
            try {
                ObjectInputStream lEscolhidos = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locaisEscolhidos.data")));
                maisVotados = (ArrayList) lEscolhidos.readObject();
                lEscolhidos.close();
            } catch (ClassNotFoundException var9) {
                var9.printStackTrace();
            }
        }
    } //Le os pi escolhidos anteriormente

    public void escreveMaisVotado(String pInteresse) {

        maisVotados.add(pInteresse);
        escreveLescolhidosObj();
    } //adiciona o pI votado à lista

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
        return custoTotal(viagem[0]) + custoTotal(viagem[1]) + custoTotal(viagem[2]) + deslocaçãoLocais(viagem[0], viagem[1]) + deslocaçãoLocais(viagem[1], viagem[2]);
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

    public Local localHot(String hot) {
        Local local = null;
        loop:
        for(Local l: locais){
            for (pInteresse i : l.getPInteresse()) {
                if (hot.equals(i.getNome())) {
                    local = l;
                    break loop;
                }
            }
        }
        return local;
    } //Vai buscar o local do ponto de interesse hot

    public boolean museu(Local l){
        ArrayList<pInteresse> pontosinteresse = l.getPInteresse();
        for (pInteresse pi : pontosinteresse) {
            if(pi.getTipo().equals("museu")){
                return true;
            }
        }
        return false;
    } // Verifica se o local tem um museu

    public boolean compararViagens(Local[] viagem1, Local[] viagem2){
        for(int i=0; i<3; i++){
            boolean locais_iguais=false;
            for(int j=0; j<3; j++){
                if(viagem1[i].getCidade().equals(viagem2[j].getCidade())){
                    locais_iguais=true;
                }
            }
            if(locais_iguais==false){
                return false;
            }
        }
        return true;
    } //Veririfca se as viagens são iguais

    public boolean compararViagemComLista(Local[] viagem, ArrayList<Local[]> viagens){
        for(Local[] v: viagens){
            if(compararViagens(viagem, v)== true){
                return true;
            }
        }
        return false;
    }//Verifica se uma viagem ja existe numa lsita de viagens

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
                                    if (custoViagem(viagem) <= custo && compararViagemComLista(viagem, viagens)==false) {
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
        viagem[0] = localHot(hot);
        for (Local m : locais) {
            if (museu(m) == true && !(m.getCidade().equals(viagem[0].getCidade()))) {
                viagem[1] = m;
                for (Local l : locais) {
                    if (l.getCidade().equals(m.getCidade()) || l.getCidade().equals(viagem[0].getCidade())) {
                        continue;
                    } else {
                        viagem[2] = l;
                        if (custoViagem(viagem) <= custo  && compararViagemComLista(viagem, viagens)==false) {
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
    }   //obtem lista dos locais

    public ArrayList<String> getMaisVotados() {
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        int curr = 0;
        String pi1 = null;
        String pi2 = null;
        String pi3 = null;
        Set<String> unique = new HashSet<String>(maisVotados);

        for (String key : unique) {
            curr = Collections.frequency(maisVotados, key);
            if (max3 < curr) {
                if (max2 < curr) {
                    if (max1 < curr) {
                        max3 = max2;
                        pi3 = pi2;
                        max2 = max1;
                        pi2 = pi1;
                        max1 = curr;
                        pi1 = key;
                    } else {
                        max3 = max2;
                        pi3 = pi2;
                        max2 = curr;
                        pi2 = key;
                    }
                } else {
                    max3 = curr;
                    pi3 = key;
                }
            }
        }

        ArrayList<String> resultado = new ArrayList<>();
        resultado.add(pi1);
        resultado.add(pi2);
        resultado.add(pi3);


        return resultado;
    }   //obtem lista dos locais mais votados

    public ArrayList<Aluno> getAlunos() {
        return listaAlunos;
    }   //obtem lista dos alunos

    public void addAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    public ArrayList<String> getPInteresse(Local l) {
        ArrayList<String> pInteresses = new ArrayList<>();
        for(pInteresse pi: l.getPInteresse()){
            pInteresses.add(pi.getNome());
        }
        return pInteresses;
    }

    public static void main(String[] args) throws IOException {
        Main viagem = new Main();
    }

}
