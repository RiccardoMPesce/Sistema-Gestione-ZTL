public class Dispositivo {
    private static int contatoreDispositivi = 0;

    private int id;
    private SistemaGestioneZTL sistema;

    public Dispositivo(SistemaGestioneZTL sistema) {
        this.id = ++contatoreDispositivi;
        this.sistema = sistema;
    }

    public int accedi(int idTerminale, int hh, int mm) {
        for (Terminale t : this.sistema.getTerminali()) {
            if (t.getID() == idTerminale) {
                return t.richiediAccesso(this.id, hh, mm);
            }
        }
        System.out.println("Nessun terminale con id " + idTerminale + " trovato.");
        return 0;
    }

    public int accedi(String zona, int hh, int mm) {
        for (Terminale t : this.sistema.getTerminali()) {
            if (t.getZona().equalsIgnoreCase(zona)) {
                return t.richiediAccesso(this.id, hh, mm);
            }
        }
        System.out.println("Nessun terminale in zona " + zona + " trovato.");
        return 0;
    }

    public int esci(int idTerminale, int hh, int mm) {
        for (Terminale t : this.sistema.getTerminali()) {
            if (t.getID() == idTerminale) {
                return t.richiediUscita(this.id, hh, mm);
            }
        }
        System.out.println("Nessun terminale con id " + idTerminale + " trovato.");
        return 0;
    }

    public int esci(String zona, int hh, int mm) {
        for (Terminale t : this.sistema.getTerminali()) {
            if (t.getZona().equalsIgnoreCase(zona)) {
                return t.richiediUscita(this.id, hh, mm);
            }
        }
        System.out.println("Nessun terminale in zona " + zona + " trovato.");
        return 0;
    }

    public int getID() {
        return this.id;
    }
}
