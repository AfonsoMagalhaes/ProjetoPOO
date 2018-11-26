import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ficheiro {

    public Ficheiro() throws IOException {

        File Ficheiro_cliente = new File("teste.txt");
        if (!(Ficheiro_cliente.exists())) {
            Ficheiro_cliente.createNewFile();
        }

    }

    public Gestor getInfoGestor(Gestor g) throws IOException, ClassNotFoundException {
        try {
            FileInputStream is = new FileInputStream(new File("teste.txt"));
            try (ObjectInputStream aux = new ObjectInputStream(is)) {
                g = (Gestor) aux.readObject();
            }
        } catch (java.io.EOFException e) {
            System.out.println("Ficheiro Vazio");
        } catch (java.io.InvalidClassException s) {
            PrintWriter writer = new PrintWriter("teste.txt");
            writer.print("");
            writer.close();
            System.out.println("Ficheiro corrompido!\nFicheiro foi limpo e foi criado um novo!");
        } catch (java.io.StreamCorruptedException k) {
            PrintWriter writer = new PrintWriter("teste.txt");
            writer.print("");
            writer.close();
            System.out.println("Ficheiro corrompido!\nFicheiro foi limpo e foi criado um novo!");
        }
        return g;

    }

    public void escreveGestor(Gestor d) throws IOException {

        try {
            FileOutputStream os = new FileOutputStream(new File("teste.txt"));
            ObjectOutputStream b = new ObjectOutputStream(os);
            b.writeObject(d);
            b.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}