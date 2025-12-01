import java.util.Random;

/**
 * La classe {@code Atleta} rappresenta un partecipante a una gara.
 * Ogni atleta viene eseguito come thread separato tramite l'interfaccia Runnable.
 * L'atleta avanza lungo il percorso finché completa la gara
 * oppure si ritira a causa di un incidente.
 */
public class Atleta implements Runnable {

    /**
     * Numero identificativo dell'atleta.
     */
    private final int numero;

    /**
     * Nome dell'atleta.
     */
    private final String nome;

    /**
     * Metri percorsi dall'atleta durante la gara.
     */
    private double metri = 0;

    /**
     * Indica se l'atleta si è ritirato per un incidente.
     */
    private boolean ritirato = false;

    /**
     * Indica se l'atleta ha concluso il percorso.
     */
    private boolean concluso = false;

    /**
     * Riferimento al giudice che gestisce arrivi e ritiri.
     */
    private final Giudice giudice;

    /**
     * Generatore di valori casuali per velocità e incidenti.
     */
    private final Random random = new Random();

    /**
     * Costruttore che inizializza un atleta con nome, numero e giudice assegnato.
     *
     * @param nome    il nome dell'atleta
     * @param numero  il numero identificativo dell'atleta
     * @param giudice il giudice che controlla la gara
     */
    public Atleta(String nome, int numero, Giudice giudice) {
        this.numero = numero;
        this.nome = nome;
        this.giudice = giudice;
    }

    /**
     * Restituisce il nome dell'atleta.
     *
     * @return il nome dell'atleta
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo che definisce il comportamento dell'atleta durante la gara.
     * L'atleta avanza di una distanza casuale ogni secondo.
     * Raggiunta la fine del percorso, viene registrato l'arrivo.
     * In alcuni casi può verificarsi un incidente che porta al ritiro.
     */
    @Override
    public void run() {
        while (!ritirato && !concluso) {

            // Calcolo della distanza percorsa nel ciclo
            double velocita = random.nextDouble() * 10;
            metri += velocita;

            System.out.printf("%s metri percorsi: %.2f\n", nome, metri);

            // Controllo se l'atleta ha completato il percorso
            if (metri >= giudice.lunghezzaPercorso) {
                concluso = true;
                giudice.registraArrivo(this);
                break;
            }

            // Probabilità del 2% di subire un incidente
            if (random.nextInt(100) < 2) {
                ritirato = true;
                giudice.registraRitiro(this);
                break;
            }

            // Attesa tra un avanzamento e l'altro
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Errore durante la pausa del thread.");
            }
        }
    }
}