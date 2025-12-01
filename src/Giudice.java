import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code Giudice} gestisce lo svolgimento della gara.
 * Si occupa di registrare arrivi e ritiri, dichiarare il vincitore,
 * e salvare o mostrare la classifica tramite il GestoreFile.
 */
public class Giudice {

    /**
     * Atleta che ha vinto la gara.
     */
    private Atleta vincitore = null;

    /**
     * Oggetto per gestire la lettura e scrittura delle classifiche.
     */
    private GestoreFile gestoreFile = new GestoreFile();

    /**
     * Lunghezza del percorso di gara.
     */
    public static double lunghezzaPercorso;

    /**
     * Lista degli atleti nell'ordine in cui arrivano o si ritirano.
     */
    private final List<Atleta> classifica = new ArrayList<>();

    /**
     * Numero totale degli atleti iscritti alla gara.
     */
    private int atletiTotali;

    /**
     * Numero degli atleti che hanno terminato regolarmente la gara.
     */
    private int arrivati = 0;

    /**
     * Costruttore che inizializza il giudice con il numero di atleti partecipanti.
     *
     * @param numeroAtleti numero totale degli atleti
     */
    public Giudice(int numeroAtleti) {
        this.atletiTotali = numeroAtleti;
    }

    /**
     * Costruttore vuoto.
     * Inizializza solamente il gestore dei file.
     */
    public Giudice() {
        this.gestoreFile = gestoreFile;
    }

    /**
     * Mostra la classifica salvata in precedenza.
     */
    public void mostraClassificaPrecedente() {
        gestoreFile.leggiClassificaPrecedente();
    }

    /**
     * Comunica l'inizio ufficiale della gara.
     */
    public void dichiaraInizio() {
        System.out.println("La gara è iniziata.");
    }

    /**
     * Registra l'arrivo di un atleta.
     * Aggiorna la classifica e imposta il vincitore se è il primo arrivato.
     *
     * @param atleta l'atleta che ha concluso il percorso
     */
    public synchronized void registraArrivo(Atleta atleta) {
        classifica.add(arrivati, atleta);
        arrivati++;

        if (vincitore == null) {
            vincitore = atleta;
        }

        verificaFineGara();
    }

    /**
     * Registra il ritiro di un atleta.
     * L'atleta viene comunque inserito in classifica.
     *
     * @param atleta l'atleta ritirato
     */
    public synchronized void registraRitiro(Atleta atleta) {
        classifica.add(atleta);
        System.out.println(atleta.getNome() + " si è ritirato.");
        verificaFineGara();
    }

    /**
     * Controlla se tutti gli atleti hanno concluso o si sono ritirati.
     * In tal caso dichiara la fine della gara.
     */
    private synchronized void verificaFineGara() {
        if (classifica.size() == atletiTotali) {
            dichiaraFine();
        }
    }

    /**
     * Dichiara la conclusione della gara, stampa il vincitore
     * e salva la classifica su file.
     */
    private synchronized void dichiaraFine() {
        System.out.println("\nGara terminata.");

        if (vincitore != null) {
            System.out.println("Vincitore: " + vincitore.getNome());
        }

        gestoreFile.scriviClassifica(classifica, arrivati);

        stampaClassifica();
        verificaPodio();
    }

    /**
     * Stampa la classifica finale della gara.
     * Gli atleti ritirati vengono indicati con la dicitura DNF.
     */
    public void stampaClassifica() {
        System.out.println("\nClassifica finale:");

        for (int i = 0; i < classifica.size(); i++) {
            String riga = (i + 1) + "° " + classifica.get(i).getNome();

            if (i >= arrivati) {
                riga = riga + " (ritirato - DNF)";
            }

            System.out.println(riga);
        }
    }

    /**
     * Stampa il podio dei primi tre classificati,
     * se il numero degli atleti arrivati lo permette.
     */
    public void verificaPodio() {
        System.out.println("\nPodio:");

        for (int i = 0; i < arrivati && i < 3; i++) {
            System.out.println((i + 1) + "° posto: " + classifica.get(i).getNome());
        }
    }
}
