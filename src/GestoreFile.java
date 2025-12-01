import java.io.*;
import java.util.List;

/**
 * La classe {@code GestoreFile} si occupa della lettura e scrittura
 * della classifica della gara su file di testo.
 */
public class GestoreFile {

    /**
     * Nome del file utilizzato per salvare la classifica.
     */
    private final String fileName = "classifica_gara.txt";

    /**
     * Legge la classifica salvata in precedenza, se il file esiste.
     * Ogni riga del file viene stampata sulla console.
     * Se il file non è presente, viene mostrato un messaggio informativo.
     */
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

    /**
     * Scrive la classifica finale su file.
     * Gli atleti che non hanno terminato vengono etichettati come DNF.
     *
     * @param classifica lista degli atleti in ordine di arrivo o ritiro
     * @param arrivati   numero di atleti che hanno completato la gara
     */
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