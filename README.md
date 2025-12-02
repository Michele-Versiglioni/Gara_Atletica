# Gara Atletica in Java

## Descrizione del progetto
Questo programma simula una gara atletica utilizzando i thread in Java. 
Ogni atleta è rappresentato da un thread separato che avanza lungo il percorso in modo casuale. 
La gara viene gestita da un giudice, che registra arrivi e ritiri, dichiara il vincitore e salva la classifica su file. 
Il programma permette inoltre di visualizzare la classifica di gare precedenti, se disponibile.

## Funzionalità principali

1. **Gestione degli atleti**
   - Ogni atleta ha un nome e un numero identificativo.
   - Ogni atleta si muove di una distanza casuale ogni secondo.
   - Possibilità di ritiro per incidente con probabilità del 2%.

2. **Gestione della gara**
   - La gara è gestita da un oggetto Giudice.
   - Registrazione degli arrivi e dei ritiri.
   - Determinazione del vincitore (il primo atleta che termina il percorso).
   - Stampa della classifica finale e del podio (primi 3 atleti).
   - Salvataggio della classifica su file di testo (`classifica_gara.txt`).

3. **Gestione file**
   - Lettura della classifica precedente se presente.
   - Scrittura della classifica finale, indicando gli atleti ritirati come DNF (Did Not Finish).

4. **Interattività**
   - Scelta del numero di partecipanti (da 2 a 8).
   - Scelta della lunghezza del percorso (minimo 10 metri).
   - Inserimento del nome e numero di ogni atleta.

## Esecuzione

1. Il programma mostrerà la classifica precedente se già esiste.

2. L’utente sceglie:
   - Numero di partecipanti
   - Lunghezza del percorso
   - Nome e numero di ogni atleta
   - Durante la gara si vedranno aggiornamenti in tempo reale dei metri percorsi da ciascun atleta

3. Alla fine della gara verranno visualizzati:
   - Vincitore
   - Classifica finale (con la sigla DNF per chi si è ritirato)
   - Podio dei primi 3 classificati
  
## Crediti
Per svolgere questo progetto, in particolare la classe GestoreFile, ho preso spunto dal progetto per la gestione della partita di pallavolo fatto dal professore Amendola Francesco:🔗https://github.com/amendola-scuola/Match.


Il progetto scolastico Gara_Atletica è stato svolto da me (studente del 5 AINF) presso l'ITTS A. Volta di Perugia 

