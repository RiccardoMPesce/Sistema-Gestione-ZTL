import java.util.ArrayList;
import java.util.Scanner;

public class InterfacciaUtente {
    static SistemaGestioneZTL sistema;
    static ArrayList<Utente> utenti;

    private static void menu() {
        Scanner input = new Scanner(System.in);

        int scelta = 0;

        System.out.println("Benvenuti nel test driver per il Sistema Gestione ZTL!");

        System.out.println("Seleziona ora inizio transito: ");
        int hhInzioCS = Integer.parseInt(input.nextLine());

        System.out.println("Seleziona ora fine transito: ");
        int hhFineCS = Integer.parseInt(input.nextLine());

        sistema = SistemaGestioneZTL.getIstanza(hhInzioCS, hhFineCS);
        utenti = new ArrayList<>();

        sistema.addTerminale("a", "CS");
        sistema.addTerminale("b", "RES");

        Utente u1 = new Utente("aaa");
        Utente u2 = new Utente("bbb");
        Utente u3 = new Utente("ccc");

        u1.setDispositivo(new Dispositivo(sistema));
        u2.setDispositivo(new Dispositivo(sistema));

        sistema.addResidente(u2.getID());

        utenti.add(u1);
        utenti.add(u2);
        utenti.add(u3);

        while (scelta != -1) {
            System.out.println("1. Aggiungi terminale");
            System.out.println("2. Rimuovi terminale");
            System.out.println("3. Aggiungi residente");
            System.out.println("4. Rimuovi residente");
            System.out.println("5. Aggiungi utente");
            System.out.println("6. Simula Accesso");
            System.out.println("7. Simula Uscita");
            System.out.println("8. Associa Dispositivo");
            System.out.println("9. Visualizza Sanzioni");
            System.out.println("-1. Esci");

            System.out.println("Scelta: ");

            scelta = Integer.parseInt(input.nextLine());

            switch (scelta) {
                case 1:
                    aggiungiTerminale();
                    break;
                case 2:
                    rimuoviTerminale();
                    break;
                case 3:
                    aggiungiResidente();
                    break;
                case 4:
                    rimuoviResidente();
                    break;
                case 5:
                    aggiungiUtente();
                    break;
                case 6:
                    simulaAccesso();
                    break;
                case 7:
                    simulaUscita();
                    break;
                case 8:
                    associaDispositivo();
                    break;
                case 9:
                    visualizzaSanzioni();
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Scelta invalida");
                    break;
            }
        }

        System.out.println("Grazie per aver usato il tester. Arrivederci!");
    }

    public static void aggiungiTerminale() {
        Scanner input = new Scanner(System.in);

        System.out.println("Immettere zona: ");
        String zona = input.nextLine();
        System.out.println("Immettere profilo: ");
        String profilo = input.nextLine();

        sistema.addTerminale(zona, profilo);
    }

    public static void rimuoviTerminale() {
        Scanner input = new Scanner(System.in);

        System.out.println("Immettere zona: ");
        String zona = input.nextLine();

        sistema.removeTerminale(zona);
    }

    public static void aggiungiResidente() {
        Scanner input = new Scanner(System.in);

        System.out.println("Immettere codice ID: ");
        int id = Integer.parseInt(input.nextLine());

        sistema.addResidente(id);
    }

    public static void rimuoviResidente() {
        Scanner input = new Scanner(System.in);

        System.out.println("Immettere codice ID: ");
        int id = Integer.parseInt(input.nextLine());

        sistema.removeResidente(id);
    }

    public static void aggiungiUtente() {
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci targa: ");
        String targa = input.nextLine();

        System.out.println("Aggiungere dispositivo? (y/n)");
        String scelta = input.nextLine();

        while (!scelta.equalsIgnoreCase("y") && !scelta.equalsIgnoreCase("n")) {
            System.out.println("Inserisci una valida opzione (y/n): ");
            scelta = input.nextLine();
        }

        if (scelta.equalsIgnoreCase("y")) {
            Utente utente = new Utente(targa);
            Dispositivo dispositivo = new Dispositivo(sistema);

            utente.setDispositivo(dispositivo);
            System.out.println("Dispositivo numero " + dispositivo.getID() + " impostato");
            utenti.add(utente);
        } else {
            utenti.add(new Utente(targa));
        }
    }

    public static void simulaAccesso() {
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci targa utente: ");
        String targa = input.nextLine();

        Utente utente = null;

        for (Utente u: utenti) {
            if (u.getTarga().equalsIgnoreCase(targa)) {
                utente = u;
            }
        }

        if (utente == null) {
            System.out.println("Nessun utente con targa " + targa);
            return;
        }

        System.out.println("Inserisci ora");
        int hh = Integer.parseInt(input.nextLine());

        System.out.println("Inserisci minuti");
        int mm = Integer.parseInt(input.nextLine());

        System.out.println("Inserisci zona: ");
        String zona = input.nextLine();

        utente.accedi(zona, hh, mm);
    }

    public static void simulaUscita() {
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci targa utente: ");
        String targa = input.nextLine();

        Utente utente = null;

        for (Utente u: utenti) {
            if (u.getTarga().equalsIgnoreCase(targa)) {
                utente = u;
            }
        }

        if (utente == null) {
            System.out.println("Nessun utente con targa " + targa);
            return;
        }

        System.out.println("Inserisci ora");
        int hh = Integer.parseInt(input.nextLine());

        System.out.println("Inserisci minuti");
        int mm = Integer.parseInt(input.nextLine());

        System.out.println("Inserisci zona: ");
        String zona = input.nextLine().trim();

        utente.esci(zona, hh, mm);
    }

    private static void associaDispositivo() {
        Scanner input = new Scanner(System.in);

        System.out.println("Lista di utenti senza dispositivo: ");
        for (Utente u: utenti) {
            if (u.getID() == -1) {
                System.out.println(u.getTarga());
            }
        }

        System.out.println("Inserire targa utente: ");
        String targa = input.nextLine();

        Utente utente = null;

        for (Utente u: utenti) {
            if (u.getTarga().equalsIgnoreCase(targa)) {
                utente = u;
            }
        }

        if (utente == null) {
            System.out.println("Targa non esistente");
            return;
        }

        utente.setDispositivo(new Dispositivo(sistema));

        System.out.println("Utente " + utente.getTarga() + " associato a dispositivo " + utente.getID());
    }

    private static void visualizzaSanzioni() {
        System.out.println("Sanzioni correntemente nel sistema");
        for (Sanzione s: sistema.getSanzioni()) {
            System.out.println("ID Utente: " + s.getIdUtente());
            System.out.println("Descrizione: " + s.getDescrizione());
            System.out.println("Ammontare: " + s.getAmmontare());
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
