import java.util.*;

public class Gara {

    static void main(String[] args) {
        int MIN_PART = 2;
        int MAX_PART = 4;
        int MIN_LUNGH = 10;
        Scanner sc = new Scanner(System.in);
        int nPartecipanti;
        int lunghezzaPercorso;

        System.out.println("Benvenuto alla gara atletica");

        // Scegli numero partecipanti
        do {
            System.out.print("Numero partecipanti: ");
            nPartecipanti = sc.nextInt();
            if (nPartecipanti < MIN_PART || nPartecipanti > MAX_PART) {
                System.out.printf("I partecipanti devono essere almeno %d e massimo %d\n", MIN_PART, MAX_PART);
            }
        } while(nPartecipanti < MIN_PART || nPartecipanti > MAX_PART);
        sc.nextLine();

        // Scegli lunghezza percorso
        do {
            System.out.printf("Lunghezza del percorso (min %d metri): ", MIN_LUNGH);
            lunghezzaPercorso = sc.nextInt();
            if(lunghezzaPercorso < MIN_LUNGH)
                System.out.printf("La lunghezza del percorso deve essere di almeno %d metri", MIN_LUNGH);
        } while(lunghezzaPercorso < MIN_LUNGH);
        Giudice.lunghezzaPercorso = Giudice.lunghezzaPercorso;

        Giudice giudice = new Giudice(nPartecipanti);
        List<Atleta> listaAtleti = new ArrayList<>();

        for (int i = 0; i < nPartecipanti; i++) {
            System.out.printf("Nome atleta %d: ", (i + 1));
            String nome = sc.next();

            System.out.printf("Numero atleta %d: ", (i + 1));
            int numero = sc.nextInt();

            listaAtleti.add(new Atleta(nome, numero, giudice));
        }

        giudice.dichiaraInizio();

        for (int i = 0; i < listaAtleti.size(); i++) {
            Thread t = new Thread(listaAtleti.get(i));
            t.start();
        }
        sc.close();
    }
}