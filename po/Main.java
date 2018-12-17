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
    private float deslocacao;

    /** Cria uma aplicação de suporte ao planeamento de viagens
     * @throws IOException
     */
    public Main() throws IOException {

        this.locais = new ArrayList<>();
        this.listaAlunos = new ArrayList<>();
        this.maisVotados = new ArrayList<>();

        leFicheiro();
        leAlunosObj();
        leLescolhidoObj();
        /*for (Local tmp : locais) {
            System.out.println(tmp.getCidade());
            System.out.println("\n");
        }
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno.toString());
        }
        ArrayList<Local[]> viagens = criaViagensMes(200, "Lisboa");
        ArrayList<Local[]> viagensOrdenadas = ordenaViagens(viagens, true);
        for(Local[] v: viagensOrdenadas){
            System.out.println(viagemString(v));
        }*/
        new janelaInicio(this).setVisible(true);
    }

    /**
     * Adiciona um aluno à lista de alunos(Regista o aluno).
     * @param nome Nome do aluno.
     * @param email Email do aluno.
     * @param mestrado true se o aluno for de mestrado, false se o aluno for de licenciatura.
     * @return true se o aluno foi adicionado a lista, false se o aluno já estava na lista(não sendo adicionado).
     * @throws IOException
     */
    public boolean registo(String nome, String email, boolean mestrado) throws IOException {
        //verifica se a pessoa já está registada
        for (Aluno tmp : listaAlunos) {
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
        escreveAlunosObj();
        return true;
    }

    /**
     * Lê o ficheiro de texto dos locais e seus pontos de interesse e o preço da deslocação por km.
     */
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

    /**
     * Escreve a lista de alunos num ficheiro de objetos.
     * @throws IOException
     */
    public void escreveAlunosObj() throws IOException {
        try {
            ObjectOutputStream walunos = new ObjectOutputStream(new FileOutputStream("alunos.data"));
            walunos.writeObject(listaAlunos);
            walunos.close();
        } catch (FileNotFoundException var5) {
            System.out.println("File not found");
        } catch (IOException var6) {
            System.out.println("Error initializing stream");
        }

    }

    /**
     * Lê o ficheiro de objetos dos alunos registados para a lista de alunos.
     * @throws IOException
     */
    public void leAlunosObj() throws IOException {
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

    }

    /**
     * Escreve a lista dos pontos de interesse mais populares num ficheiro de objetos.
     */
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
    }

    /**
     * Lê o ficheiro de objetos dos pontos de interesse mais escolhidos para a lista dos locais mais populares.
     * @throws IOException
     */
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
    }

    /**
     * Adiciona um ponto de interesse votado a lista de pontos de interesse mais votados
     * @param pInteresse
     */
    public void escreveMaisVotado(String pInteresse) {

        maisVotados.add(pInteresse);
        escreveLescolhidosObj();
    } //adiciona o pI votado à lista

    /**
     * Calcula o custo total de um local, pela soma dos custos dos seus pontos de interesse.
     * @param local Local que vamos calcular o custo.
     * @return Custo do local.
     */
    public float custoTotal(Local local) {
        int custo=0;
        for (pInteresse i : local.getPInteresse()) {
                    custo+=i.getEntrada()+i.getCustoextra();// alterar de novo os atributos dos pontos de interesse
        }
        return custo;
    }

    public float getCustoLocal(String local) {
        float custo = 0;

        for (Local l : locais) {
            if (local.equals(l.getCidade())) {
                for (pInteresse i : l.getPInteresse()) {
                    custo += i.getEntrada() + i.getCustoextra();// alterar de novo os atributos dos pontos de interesse
                }
            }
        }

        return custo;
    } // custo de um local

    /**
     * Calcula a distância em linha reta entre 2 locais, tendo em conta as suas coordenadas x e y.
     * @param local1 Local inicial.
     * @param local2 Local final.
     * @return Distância entre os 2 locais.
     */
    public double distanciasTotais(Local local1, Local local2) {
        return Math.sqrt(Math.pow(local1.getX()-local2.getX(),2)+Math.pow(local1.getY()-local2.getY(),2));
    }

    public double distancia3Locais(String local1, String local2, String local3) {
        double distA = 0;
        double distB = 0;
        double distC = 0;
        double resultado = 0;
        ArrayList<Float> pInteresses = new ArrayList<>();
        Local local1a = null;
        Local local2a = null;
        Local local3a = null;
        for (Local l : locais) {
            if (local1.equals(l.getCidade())) {
                local1a = l;
            }
            if (local2.equals(l.getCidade())) {
                local2a = l;
            }
            if (local3.equals(l.getCidade())) {
                local3a = l;
            }
        }
        distA = distanciasTotais(local1a, local2a);
        distB = distanciasTotais(local1a, local3a);
        distC = distanciasTotais(local2a, local3a);
        resultado = distA + distB;
        if (distA + distC < resultado) {
            resultado = distA + distC;
        }
        if (distB + distC < resultado) {
            resultado = distB + distC;
        }
        return resultado;
    }

    /**
     * Calcula o preço da deslocação entre 2 locais, tendo em conta a distância entre eles e o preço da deslocação por km.
     * @param local1 Local inicial.
     * @param local2 Local final.
     * @return Preço da deslocação entre os 2 locais.
     */
    public int deslocaçãoLocais(Local local1, Local local2){ //custo por km no ficheiro de texto(inicio)
        return (int) (distanciasTotais(local1, local2) * deslocacao);
    }

    /**
     * Calcula o custo total da viagem, tendo em conta o preço total de cada local pertencente à mesma.
     * @param viagem Vetor com os 3 locais pertences à viagem.
     * @return Custo total da viagem.
     */
    public float custoViagem(Local[] viagem){
        return custoTotal(viagem[0]) + custoTotal(viagem[1]) + custoTotal(viagem[2]) + deslocaçãoLocais(viagem[0], viagem[1]) + deslocaçãoLocais(viagem[1], viagem[2]);
    }

    /**
     * Devolve o local a evitar, escolhido por um aluno de mestrado.
     * @param hot Nome do local.
     * @return Local a evitar.
     */
    public Local localEvitar(String hot) {
        Local local = null;
        for(Local l: locais){
            if (hot.equals(l.getCidade())) {
                local = l;
            }
        }
        return local;
    }

    /**
     * Devolve o local do ponto de interesse a não perder, escolhido pelo aluno de licenciatura.
     * @param hot Nome do ponto de interesse.
     * @return
     */
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

    public boolean existeMuseu(Local l){
        ArrayList<pInteresse> pontosinteresse = l.getPInteresse();
        for (pInteresse pi : pontosinteresse) {
            if(pi.getTipo().equals("museu")){
                return true;
            }
        }
        return false;
    } // Verifica se o local tem um museu

    public boolean compararViagens(Local[] viagem1, Local[] viagem2){
        int locais_iguais=0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(viagem1[i].getCidade().equals(viagem2[j].getCidade())){
                    locais_iguais+=1;
                }
            }
        }
        if(locais_iguais==3){
            return true;
        }else {
            return false;
        }
    } //Veririfca se as viagens são iguais

    public boolean compararViagemComLista(Local[] viagem, ArrayList<Local[]> viagens){
        for(Local[] v: viagens){
            if(compararViagens(viagem, v)){
                return true;
            }
        }
        return false;
    }//Verifica se uma viagem ja existe numa lsita de viagens

    public ArrayList<Local[]> criaViagensMes(int custo, String hot){
        ArrayList<Local[]> viagens = new ArrayList<>();
        for(Local m: locais){
            if(existeMuseu(m) && !(m.getCidade().equals(localEvitar(hot).getCidade()))){
                for (Local l1 : locais) {
                    if (!(l1.getCidade().equals(localEvitar(hot).getCidade())) && !(l1.getCidade().equals(m.getCidade()))) {
                        for (Local l2 : locais) {
                            if (!(l2.getCidade().equals(localEvitar(hot).getCidade())) && !(l2.getCidade().equals(m.getCidade())) && !(l2.getCidade().equals(l1.getCidade()))) {
                                Local[] viagem = new Local[3];
                                viagem[0] = m;
                                viagem[1] = l1;
                                viagem[2] = l2;
                                if (custoViagem(viagem) <= custo && !(compararViagemComLista(viagem, viagens))) {
                                    viagens.add(viagem);
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
        for (Local m : locais) {
            if (existeMuseu(m) == true && !(m.getCidade().equals(localHot(hot).getCidade()))) {
                for (Local l : locais) {
                    if (!(l.getCidade().equals(m.getCidade())) && !(l.getCidade().equals(localHot(hot).getCidade()))) {
                        Local[] viagem = new Local[3];
                        viagem[0]=localHot(hot);
                        viagem[1]=m;
                        viagem[2] = l;
                        if(custoViagem(viagem)<=custo && !(compararViagemComLista(viagem, viagens))) {
                            viagens.add(viagem);
                        }
                    }
                }
            }
        }
        return viagens;
    } //Cria lista das viagens que satisfazem o custo máximo e o ponto de interesse

    public ArrayList<Local[]> ordenaViagens(ArrayList<Local[]> viagens, boolean ordem){
        ArrayList<Float> viagensCustos = new ArrayList<>();
        ArrayList<Local[]> viagensOrdenadas = new ArrayList<>();
        for(Local[] v: viagens){
            viagensCustos.add(custoViagem(v));
        }
        if(ordem==true){
            //ordem crescente
            Collections.sort(viagensCustos);
            for(float custo: viagensCustos){
                for(Local[] v: viagens){
                    if(custoViagem(v)==custo && !(viagensOrdenadas.contains(v))){
                        viagensOrdenadas.add(v);
                        break;
                    }
                }
            }
        }else{
            //ordem decrescente
            Collections.sort(viagensCustos, Collections.reverseOrder());
            for(float custo: viagensCustos){
                for(Local[] v: viagens){
                    if(custoViagem(v)==custo && !(viagensOrdenadas.contains(v))){
                        viagensOrdenadas.add(v);
                        break;
                    }
                }
            }
        }
        return viagensOrdenadas;
    } //ordena a lista de viagens por ordem crescente(ordem=true) ou decrescente(ordem=false)

    public String viagemString(Local[] viagem){
        return viagem[0].getCidade()+", "+viagem[1].getCidade()+", "+viagem[2].getCidade()+", "+custoViagem(viagem)+"€";
    } // string de uma viagem com o preço

    public ArrayList<String>  viagensPossiveis(ArrayList<Local[]> viagens){
        ArrayList<String> viagenspossiveis = new ArrayList<>();
        for(Local[] v: viagens){
            viagemString(v);
        }
        return viagenspossiveis;
    } // listas das strings das viagens possiveis com o preco de cada

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
    } //adiciona aluno a lista de alunos

    public ArrayList<String> getPInteresse(Local l) {
        ArrayList<String> pInteresses = new ArrayList<>();
        for(pInteresse pi: l.getPInteresse()){
            pInteresses.add(pi.getNome());
        }
        return pInteresses;
    } //devolve a lista dos pontos de interesse de um local

    public ArrayList<String> getPInteresseS(String local) {
        ArrayList<String> pInteresses = new ArrayList<>();
        for (Local l : locais) {
            if (local.equals(l.getCidade())) {
                for (pInteresse pi : l.getPInteresse()) {
                    pInteresses.add(pi.getNome());
                }
            }
        }
        return pInteresses;
    } //devolve a lista dos pontos de interesse de um local


    public ArrayList<Float> getPIntCusto(String local) {
        ArrayList<Float> pInteresses = new ArrayList<>();
        for (Local l : locais) {
            if (local.equals(l.getCidade())) {
                for (pInteresse pi : l.getPInteresse()) {
                    float custo = pi.getEntrada() + pi.getCustoextra();
                    pInteresses.add(custo);
                }
            }
        }
        return pInteresses;
    } //devolve a lista do custo dos pontos de interesse de um local


    public String getLocal(String pi){
        String local=null;
        loop:
        for(Local l: locais){
            for(pInteresse p: l.getPInteresse()){
                if(p.getNome().equals(pi)){
                    local=l.getCidade();
                    break loop;
                }
            }
        }
        return local;
    } //Recebe um ponto de interesse e devolve o local a que ele pertence

    public static void main(String[] args) throws IOException {
        Main viagem = new Main();
    }

}
