package po;

import interfaces_GUI.Janela_inicio;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main extends JFrame{

    private static ArrayList<Mestrado> mestrado;
    private static ArrayList<Licenciatura> licenciatura;
    private static ArrayList<Pessoa> lista_comunidade;
    private static ArrayList<Local> locais;
    private static ArrayList<Local> museus;
    private float deslocacao;

    public Main() {
        this.mestrado = new ArrayList<>();
        this.licenciatura = new ArrayList<>();
        this.locais = new ArrayList<>();
        this.museus = new ArrayList<>();
        this.lista_comunidade = new ArrayList<>();
    }

    public void leficheiro() {
        BufferedReader br = null;
        FileReader fr = null;
        String st;
        try {
            br = new BufferedReader(new FileReader("locais.txt"));
            this.deslocacao=Integer.parseInt(br.readLine());
            ArrayList<P_Interesse> pinteresse = null;
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

    /*public void escreve_ficheirobj(){

    }*/

    /*public void le_ficheiroobj(){

    }*/

    /*public ArrayList<Local> ordena_locais_crescente(Local[] viagem){

    }*/ //vai ordenar os locais de cada viagem por ordem crescente

    /*public ArrayList<Local> ordena_locais_decrescente(Local[] viagem){

    }*/ //vai ordenar os locais de cada viagem por ordem decrescente

    public float custo_local(Local local){
        int custo=0;
        for(P_Interesse i :local.getPInteresse()){
                    custo+=i.getEntrada()+i.getCustoextra();// alterar de novo os atributos dos pontos de interesse
        }
        return custo;
    } // custo de um local

    public double distancia_locais(Local local1, Local local2){
        return Math.sqrt(Math.pow(local1.getX()-local2.getX(),2)+Math.pow(local1.getY()-local2.getY(),2));
    } //Distância entre 2 locais

    public int deslocação_locais(Local local1, Local local2){ //custo por km no ficheiro de texto(inicio)
        return (int) (distancia_locais(local1, local2)*this.deslocacao);
    } //Custo da deslocação entre 2 locais

    public float custo_viagem(Local[] viagem){
        return custo_local(viagem[0])+custo_local(viagem[1])+custo_local(viagem[3])+deslocação_locais(viagem[0], viagem[1])+ deslocação_locais(viagem[1], viagem[2]);
    } //Calcula o custo da viagem

    public Local pinteresse_hot(String hot){
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

    public Local local_evitar(String hot){
        Local x = null;
        for(Local l: locais){
            if(hot.equals(l.getCidade())){
                x=l;
            }
        }
        return x;
    } //Vai buscar do local a evitar

    public ArrayList<Local[]> cria_viagens_lic(int custo, String hot){
        ArrayList<Local[]> viagens = new ArrayList<>();
        Local[] viagem = new Local[3];
        viagem[0] = pinteresse_hot(hot);
        for(Local m: museus) {
            viagem[1]= m;
            for(Local l: locais){
                if(m.getCidade().equals(l.getCidade())){
                    continue;
                }else{
                    viagem[2]=l;
                    if(custo_viagem(viagem)<=custo){
                        viagens.add(viagem);
                    }
                }
            }
        }
        return viagens;
    } //Cria lista das viagens que satisfazem o custo máximo e o ponto de interesse

    public ArrayList<Local[]> cria_viagens_mes(int custo, String hot){
        ArrayList<Local[]> viagens = new ArrayList<>();
        Local[] viagem = new Local[3];
        Local X = local_evitar(hot);
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
                        if(custo_viagem(viagem)<=custo) {
                            viagens.add(viagem);
                        }
                    }
                }
            }
        }
        return viagens;
    } //Cria lista das viagens que satisfazem o custo máximo e o local a evitar

    public String viagem_selecionada(Local[] viagem){
        return "Viagem(custo: " + custo_viagem(viagem) + ")\nLocais:\n" + viagem[0].toString() + "\n" + viagem[1].toString() + "\n" + viagem[2].toString() + "\n" +
                "Distâncias:\n" + viagem[0] + "a" + viagem[1] + " - " + distancia_locais(viagem[0], viagem[1]) + "\n" + viagem[1] + "a" + viagem[2] + " - " + distancia_locais(viagem[1], viagem[2]) + "\n" +
                "Custos:\n" + viagem[0] + " - " + custo_local(viagem[0]) + "\n" + viagem[1] + " - " + custo_local(viagem[1]) + "\n" + viagem[2] + " - " + custo_local(viagem[2]);

    } //Ponto 5 do projeto

    public void add_lic(Licenciatura pessoa){
        licenciatura.add(pessoa);
    } //Não sei se e preciso para quando fizeres o registo meter a pessoa na lista

    public void add_mes(Mestrado pessoa){
        mestrado.add(pessoa);
    } //Não sei se e preciso para quando fizeres o registo meter a pessoa na lista

    /*public void printRegisto(){

    }*/

    public static boolean registo(String nome, String email, boolean mestrado){
        //verifica se a pessoa já está registada
        for(Pessoa tmp : lista_comunidade)
            if(tmp.getNome().equalsIgnoreCase(nome)){
                return false;
            }
        if(mestrado==false) {
            Pessoa novo = new Licenciatura(email, nome);
            lista_comunidade.add(novo);
        }
        else{
            Pessoa novo = new Mestrado(email, nome);
            lista_comunidade.add(novo);
        }

        return true;
    }


    public static void main(String[] args){
        Main viagem = new Main();
        viagem.leficheiro();


        System.out.println("LISTA LOCAIS");
        for(Local tmp : viagem.locais)
            System.out.println(tmp.toString());

        new Janela_inicio().setVisible(true);


    }
}
