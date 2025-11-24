import java.io.*;
import java.util.List;

public class GestoreFile {

    private final String fileName = "classifica_gara.txt";

    // Legge l'ultima classifica salvata (se esiste)
    public synchronized void leggiClassificaPrecedente() {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Nessuna classifica precedente trovata.");
            return;
        }
