import java.util.*;

/**
 * La classe {@code Gara} contiene il metodo principale che gestisce
 * l'intera esecuzione del programma. Si occupa di chiedere i dati
 * iniziali all'utente, creare gli atleti, avviare la gara e mostrare
 * eventuali classifiche precedenti.
 */
public class Gara {

    /**
     * Metodo principale del programma.
     * Permette di configurare la gara, creare gli atleti e avviarne i thread.
     *
     * @param args argomenti da riga di comando (non utilizzati)
     */
    public static void main(String[] args) {

        int MIN_PART = 2;
        int MAX_PART = 8;
        int MIN_LUNGH = 10;

        Scanner sc = new Scanner(System.in);

        int nPartecipanti;
        int lunghezzaPercorso;

        Giudice giudice = new Giudice();

        // Mostra eventuale classifica salvata in precedenza
        giudice.mostraClassificaPrecedente();

        System.out.println("Benvenuto alla gara atletica");

        // Scelta del numero di partecipanti
        do {
            System.out.print("Numero partecipanti: ");
            nPartecipanti = sc.nextInt();

            if (nPartecipanti < MIN_PART || nPartecipanti > MAX_PART) {
                System.out.printf("I partecipanti devono essere almeno %d e massimo %d\n",
                        MIN_PART, MAX_PART);
            }
        } while (nPartecipanti < MIN_PART || nPartecipanti > MAX_PART);

        sc.nextLine();

        // Creazione del giudice con numero atleti
        giudice = new Giudice(nPartecipanti);

        // Scelta lunghezza percorso
        do {
            System.out.printf("Lunghezza del percorso (min %d metri): ", MIN_LUNGH);
            lunghezzaPercorso = sc.nextInt();

            if (lunghezzaPercorso < MIN_LUNGH) {
                System.out.printf("La lunghezza del percorso deve essere di almeno %d metri\n", MIN_LUNGH);
            }

        } while (lunghezzaPercorso < MIN_LUNGH);

        // Impostazione lunghezza percorso
        Giudice.lunghezzaPercorso = lunghezzaPercorso;

        List<Atleta> listaAtleti = new ArrayList<>();

        // Inserimento informazioni degli atleti
        for (int i = 0; i < nPartecipanti; i++) {
            System.out.printf("Nome atleta %d: ", (i + 1));
            String nome = sc.next();

            System.out.printf("Numero atleta %d: ", (i + 1));
            int numero = sc.nextInt();

            listaAtleti.add(new Atleta(nome, numero, giudice));
        }

        // Inizio gara
        giudice.dichiaraInizio();

        // Avvio dei thread degli atleti
        for (Atleta atleta : listaAtleti) {
            Thread t = new Thread(atleta);
            t.start();
        }

        sc.close();
    }
}