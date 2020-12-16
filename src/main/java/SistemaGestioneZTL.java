import java.util.ArrayList;

public class SistemaGestioneZTL {
    private static SistemaGestioneZTL sistema;

    private GestoreAccessi gestoreAccessi;
    private GestoreTerminali gestoreTerminali;
    private GestoreResidenti gestoreResidenti;
    private GestoreSanzioni gestoreSanzioni;

    private SistemaGestioneZTL(int hhInizio, int hhFine) {
        this.gestoreAccessi = GestoreAccessi.getIstanza(this, hhInizio, hhFine);
        this.gestoreTerminali = GestoreTerminali.getIstanza(this);
        this.gestoreResidenti = GestoreResidenti.getIstanza(this);
        this.gestoreSanzioni = GestoreSanzioni.getIstanza(this);

        hhInizio = (hhInizio < 0 || hhInizio > 24) ? 0 : hhInizio;
        hhFine = (hhFine < 0 || hhFine > 24) ? 24 : hhFine;
    }

    public static SistemaGestioneZTL getIstanza(int hhInizio, int hhFine) {
        if (sistema == null) {
            sistema = new SistemaGestioneZTL(hhInizio, hhFine);
        }
        return sistema;
    }

    public static SistemaGestioneZTL getIstanza() {
        if (sistema == null) {
            sistema = new SistemaGestioneZTL(9, 21);
        }
        return sistema;
    }

    public void distruggiIstanza() {
        sistema = null;
    }

    public void notificaInfrazione(String tipo) {
        System.out.println("Commessa infrazione: " + tipo);
    }

    public int registraAccesso(RichiestaAccesso richiestaAccesso) {
        if (this.getResidenti().contains(richiestaAccesso.getIDUtente())) {
            richiestaAccesso.setProfiloUtente("RES");
        } else {
            richiestaAccesso.setProfiloUtente("CS");
        }
        return this.gestoreAccessi.registraIngresso(richiestaAccesso);
    }

    public int registraUscita(RichiestaUscita richiestaUscita) {
        if (this.getResidenti().contains(richiestaUscita.getIDUtente())) {
            richiestaUscita.setProfiloUtente("RES");
        } else {
            richiestaUscita.setProfiloUtente("CS");
        }
        return this.gestoreAccessi.registraUscita(richiestaUscita);
    }

    public void addResidente(int id) {
        this.gestoreResidenti.addResidente(id);
    }

    public void addTerminale(String zona, String profilo) {
        this.gestoreTerminali.addTerminale(zona, profilo);
    }

    public ArrayList<Terminale> getTerminali() {
        return this.gestoreTerminali.getTerminali();
    }

    public void removeTerminale(int id) {
        this.gestoreTerminali.removeTerminale(id);
    }

    public void removeTerminale(String zona) {
        this.gestoreTerminali.removeTerminale(zona);
    }

    public void removeResidente(int id) {
        this.gestoreResidenti.removeResidente(id);
    }

    public ArrayList<Integer> getResidenti() {
        return this.gestoreResidenti.getResidenti();
    }

    public void emettiSanzione(int idUtente, String descrizione) {
        this.gestoreSanzioni.emettiSanzione(idUtente, descrizione);
    }

    public void notificaIngressoTarga(String targa) {
        System.out.println("Vettura con targa " + targa + " entrata.");
    }

    public void notificaUscitaTarga(String targa) {
        System.out.println("Vettura con targa " + targa + " uscita.");
    }

    public ArrayList<Sanzione> getSanzioni() {
        return this.gestoreSanzioni.getSanzioni();
    }
}
