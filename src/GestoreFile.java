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
        System.out.println("\nUltima classifica salvata:");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("Fine della classifica precedente.\n");

        } catch (IOException e) {
            System.err.println("Errore nella lettura della classifica: " + e.getMessage());
        }
    }
    public synchronized void scriviClassifica(List<Atleta> classifica, int arrivati) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            writer.println("CLASSIFICA FINALE:");

            for (int i = 0; i < classifica.size(); i++) {
                Atleta atleta = classifica.get(i);

                String riga = (i + 1) + "° " + atleta.getNome();

                if (i >= arrivati) {
                    riga += " (ritirato - DNF)";
                }

                writer.println(riga);
            }

        } catch (IOException e) {
            System.err.println("Errore nella scrittura della classifica: " + e.getMessage());
        }
    }
}
