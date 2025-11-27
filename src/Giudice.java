import java.util.ArrayList;
import java.util.List;

public class Giudice {

    private Atleta vincitore = null;
    private GestoreFile gestoreFile = new GestoreFile();
    public static double lunghezzaPercorso;
    private final List<Atleta> classifica = new ArrayList<>();
    private  int atletiTotali;
    private int arrivati = 0;

    public Giudice(int numeroAtleti) {
        this.atletiTotali = numeroAtleti;
    }
    public Giudice(){
        this.gestoreFile=gestoreFile;
    }

    public void mostraClassificaPrecedente() {
        gestoreFile.leggiClassificaPrecedente();
    }

    public void dichiaraInizio() {
        System.out.println("La gara è iniziata.");
    }

    public synchronized void registraArrivo(Atleta atleta) {
        classifica.add(arrivati, atleta);
        arrivati++;
        if (vincitore == null) {
            vincitore = atleta;
        }
        verificaFineGara();
    }

    public synchronized void registraRitiro(Atleta atleta) {
        classifica.add(atleta);
        System.out.println(atleta.getNome() + " si è ritirato.");
        verificaFineGara();
    }

    private synchronized void verificaFineGara() {
        if (classifica.size() == atletiTotali) {
            dichiaraFine();
        }
    }

    private synchronized void dichiaraFine() {
        System.out.println("\nGara terminata.");

        if (vincitore != null) {
            System.out.println("Vincitore: " + vincitore.getNome());
        }

        gestoreFile.scriviClassifica(classifica, arrivati);
        stampaClassifica();
        verificaPodio();
    }

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

    public void verificaPodio() {
        System.out.println("\nPodio:");
        for (int i = 0; i < arrivati && i < 3; i++) {
            System.out.println((i + 1) + "° posto: " + classifica.get(i).getNome());
        }
    }
}
