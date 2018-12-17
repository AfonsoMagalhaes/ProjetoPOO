package po;

import interfaces_GUI.janelaInicio;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa uma plicação de suporte ao planeamento de viagens.
 */
public class Main extends JFrame{


    private static ArrayList<Aluno> listaAlunos;
    private static ArrayList<Local> locais;
    private static ArrayList<pInteresse> pinteresse;
    private static ArrayList<String> maisVotados;
    private float deslocacao;

    /** Cria uma aplicação de suporte ao planeamento de viagens
     * @throws IOException
     */
    private Main() throws IOException {

        locais = new ArrayList<>();
        listaAlunos = new ArrayList<>();
        maisVotados = new ArrayList<>();

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
        if (!mestrado) {
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
    private void leFicheiro() {
        BufferedReader br;
        String st;
        try {
            br = new BufferedReader(new FileReader("locais.txt"));
            this.deslocacao=Integer.parseInt(br.readLine());
            String nome = null;
            int x = 0, y = 0;
            while ((st = br.readLine()) != null) {
                String[] tab = st.split(";");
                if (tab[0].equals("Local")) {
                    pinteresse = new ArrayList<>();
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
                    pinteresse.add(new Aquático(tab[1],tab[2], Float.parseFloat(tab[3]),Float.parseFloat(tab[4]), Integer.parseInt(tab[5]), Boolean.parseBoolean(tab[6]), Integer.parseInt(tab[7])));
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
    private void escreveAlunosObj() throws IOException {
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
    private void leAlunosObj() throws IOException {
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
    private void escreveLescolhidosObj() {
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
    private void leLescolhidoObj() throws IOException {
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
     * Adiciona um ponto de interesse votado a lista de pontos de interesse mais votados.
     * @param pInteresse ponto de interesse  adicionar a lista.
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
    private float custoTotal(Local local) {
        int custo=0;
        for (pInteresse i : local.getPInteresse()) {
                    custo+=i.getEntrada()+i.getCustoextra();// alterar de novo os atributos dos pontos de interesse
        }
        return custo;
    }

    /**
     * Calcula o custo total de um local, a partir do nome da cidade do mesmo.
     * @param local Nome da cidade do local.
     * @return Custo do local.
     */
    public float getCustoLocal(String local) {
        float custo = 0;
        for (Local l : locais) {
            if (local.equals(l.getCidade())) {
                custo=custoTotal(l);
                break;
            }
        }

        return custo;
    }

    /**
     * Calcula a distância em linha reta entre 2 locais, tendo em conta as suas coordenadas x e y.
     * @param local1 Local inicial.
     * @param local2 Local final.
     * @return Distância entre os 2 locais.
     */
    private double distanciasTotais(Local local1, Local local2) {
        return Math.sqrt(Math.pow(local1.getX()-local2.getX(),2)+Math.pow(local1.getY()-local2.getY(),2));
    }

    /**
     * Compara as distancias a ser percorridas entre os 3 locais e acha a menor delas.
     * @param local1 Nome da cidade do primeiro local.
     * @param local2 Nome da cidade do segundo local.
     * @param local3 Nome da cidade do terceiro local.
     * @return Menor distância possível a ser percorrida entre os 3 locais.
     */
    public double distancia3Locais(String local1, String local2, String local3) {
        double distA;
        double distB;
        double distC;
        double resultado;
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
    private int deslocaçãoLocais(Local local1, Local local2) { //custo por km no ficheiro de texto(inicio)
        return (int) (distanciasTotais(local1, local2) * deslocacao);
    }

    /**
     * Calcula o custo total da viagem, tendo em conta o preço total de cada local pertencente à mesma.
     * @param viagem Vetor com os 3 locais pertences à viagem.
     * @return Custo total da viagem.
     */
    private float custoViagem(Local[] viagem) {
        return custoTotal(viagem[0]) + custoTotal(viagem[1]) + custoTotal(viagem[2]) + deslocaçãoLocais(viagem[0], viagem[1]) + deslocaçãoLocais(viagem[1], viagem[2]);
    }

    /**
     * Devolve o local a evitar, escolhido por um aluno de mestrado.
     * @param hot String que contém o nome da cidade do local.
     * @return Local a evitar.
     */
    private Local localEvitar(String hot) {
        Local local = null;
        for(Local l: locais){
            if (hot.equals(l.getCidade())) {
                local = l;
            }
        }
        return local;
    }

    /**
     * Acha o local ao qual pertence um dado ponto de interesse, a partir do seu nome.
     * @param hot String que contém o nome do ponto de interesse.
     * @return Local onde se encontra o ponto de interesse dado.
     */
    private Local localHot(String hot) {
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
    }

    /**
     * Verifica se um certo local contém ou não um museu como ponto de interesse.
     * @param l Local que verificar se contém algum museu.
     * @return true se existe um museu no local, false se não existe nenhum museu no local.
     */
    private boolean existeMuseu(Local l) {
        ArrayList<pInteresse> pontosinteresse = l.getPInteresse();
        for (pInteresse pi : pontosinteresse) {
            if(pi.getTipo().equals("museu")){
                return true;
            }
        }
        return false;
    }

    /**
     * Compara 2 viagens local a local e verifica se contêm os mesmos locais ou não.
     * @param viagem1 Vetor que contém os 3 locais da primeira viagem.
     * @param viagem2 Vetor que contém os 3 locais da segunda viagem.
     * @return true se as 2 viagens contêm os 3 mesmos locais, false caso contenham algum local diferente.
     */
    private boolean compararViagens(Local[] viagem1, Local[] viagem2) {
        int locais_iguais=0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(viagem1[i].getCidade().equals(viagem2[j].getCidade())){
                    locais_iguais+=1;
                }
            }
        }
        return locais_iguais == 3;
    }

    /**
     * Verifica se uma certa viagem já exite numa lista de viagens.
     * @param viagem Vetor que contém os 3 locais da viagem.
     * @param viagens Lista de vetores de locais, em que cada um representa uma viagem, com 3 locais.
     * @return true se a viagem já existe na lista, false caso contrário.
     */
    private boolean compararViagemComLista(Local[] viagem, ArrayList<Local[]> viagens) {
        for(Local[] v: viagens){
            if(compararViagens(viagem, v)){
                return true;
            }
        }
        return false;
    }//Verifica se uma viagem ja existe numa lsita de viagens

    /**
     * Cria uma lista de viagens com custo não superior ao dado, que não contêm o local dado e contêm pelo menos um museu(usada par alunos de mestrado).
     * @param custo Custo máximo de cada viagem.
     * @param hot Nome da cidade do local a evitar.
     * @return Lista de vetores de locais, em que cada vetor corresponde a uma viagem, de 3 locais.
     */
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

    /**
     * Cria uma lista de viagens com custo não superior ao dado, que contêm o local onde se encontra o ponto de interesse dadod e pelo menos um museu(usada par alunos de licenciatura).
     * @param custo Custo máximo de cada viagem.
     * @param hot Nome do ponto de interesse a não perder.
     * @return Lista de vetores de locais, em que cada vetor corresponde a uma viagem, de 3 locais.
     */
    public ArrayList<Local[]> criaViagensLic(int custo, String hot) {
        ArrayList<Local[]> viagens = new ArrayList<>();
        for (Local m : locais) {
            if (existeMuseu(m) && !(m.getCidade().equals(localHot(hot).getCidade()))) {
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

    /**
     * Ordena as viagens de uma lista de viagens pelo seu custo, por ordem crescente ou decrescente.
     * @param viagens Lista da vetores de locais, em que cada um representa uma viagem, com 3 locais.
     * @param ordem true se for pra ordenar as viagens por ordem crescente de custo, false caso seja por ordem decrescente de custo.
     * @return lista de vetores de locais, em que cada vetor corresponde a uma viagem, de 3 locais, ordenadas pelo seu custo.
     */
    public ArrayList<Local[]> ordenaViagens(ArrayList<Local[]> viagens, boolean ordem){
        ArrayList<Float> viagensCustos = new ArrayList<>();
        ArrayList<Local[]> viagensOrdenadas = new ArrayList<>();
        for(Local[] v: viagens){
            viagensCustos.add(custoViagem(v));
        }
        if (ordem) {
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
    }

    /**
     * Cria uma String com os nomes da cidades dos locais de uma viagem e o custo da mesma.
     * @param viagem Vetor de locais que representa uma viagem, de 3 locais.
     * @return String que contém os nomes das cidade dos 3 locais da viagem e o custo desta.
     */
    public String viagemString(Local[] viagem){
        return viagem[0].getCidade()+", "+viagem[1].getCidade()+", "+viagem[2].getCidade()+", "+custoViagem(viagem)+"€";
    }

    /**
     * Cria uma lista com as strings de todas as viagens possíveis.
     * @param viagens Lista de vetores de locais, em que cada vetor representa uma viagem, de 3 locais.
     * @return Lista de Strings de toda as viagens possíveis(com os nomes das cidades dos locais de cada uma e o custo da mesma).
     */
    public ArrayList<String>  viagensPossiveis(ArrayList<Local[]> viagens){
        ArrayList<String> viagenspossiveis = new ArrayList<>();
        for(Local[] v: viagens){
            viagemString(v);
        }
        return viagenspossiveis;
    }

    /**
     * Vai buscar os locais existentes.
     * @return Lista de locais existentes.
     */
    public ArrayList<Local> getLocais() {
        return locais;
    }

    /**
     * Obtém a lista dos locais mais votados, tendo em conta o número de vezes que cada um foi escolhido.
     * @return Lista de Strings com os 3 locais mais escolhidos pelos alunos.
     */
    public ArrayList<String> getMaisVotados() {
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        int curr;
        String pi1 = null;
        String pi2 = null;
        String pi3 = null;
        Set<String> unique = new HashSet<>(maisVotados);

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
    }

    /**
     * Obtém os alunos registados.
     * @return Lista dos alunos registados.
     */
    public ArrayList<Aluno> getAlunos() {
        return listaAlunos;
    }

    /**
     * Obtém a lista dos nomes dos pontos de interesse de um local.
     * @param l Local ao qual vamos buscar os pontos de interesse.
     * @return Lista de Strings com nomes dos pontos de interesse do local.
     */
    public ArrayList<String> getPInteresse(Local l) {
        ArrayList<String> pInteresses = new ArrayList<>();
        for(pInteresse pi: l.getPInteresse()){
            pInteresses.add(pi.getNome());
        }
        return pInteresses;
    }

    /**
     * Obtém a lista dos nomes dos pontos de interesse de um local, a partir do nome da cidade desse local.
     * @param local String do nome da cidade do local.
     * @return Lista das Strings com os nomes dos pontos de interesse do local.
     */
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
    }

    /**
     * Obtém a lista dos custos dos pontos de interesse de um local, a partir do nome da cidade deste.
     * @param local String do nome da cidade do local.
     * @return Lista de Floats dos custos dos pontos de interesse do local.
     */
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

    /**
     * Obtém o nome de um local que contém um dado ponto de interesse, a partir do nome dele.
     * @param pi String com o nome do ponto de interesse.
     * @return String com o nome da cidade do local do ponto de interesse.
     */
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
