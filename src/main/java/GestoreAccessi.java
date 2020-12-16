import java.util.ArrayList;

public class GestoreAccessi{
    private SistemaGestioneZTL sistema;

    static final int DEFAULT_HH_ACCESSO = 9;
    static final int DEFAULT_HH_USCITA = 21;

    private static GestoreAccessi gestoreAccessi;

    private int hhInizioCS;
    private int hhFineCS;

    private ArrayList<Accesso> accessiCorrenti;
    private ArrayList<Accesso> storicoAccessi;

    private GestoreAccessi(SistemaGestioneZTL sistema, int hhInizioCS, int hhFineCS) {
        this.sistema = sistema;

        this.hhInizioCS = hhInizioCS;
        this.hhFineCS = hhFineCS;

        this.accessiCorrenti = new ArrayList<>();
        this.storicoAccessi = new ArrayList<>();
    }

    public static GestoreAccessi getIstanza(SistemaGestioneZTL sistema, int hhInizioCS, int hhFineCS) {
        if (gestoreAccessi == null) {
            gestoreAccessi = new GestoreAccessi(sistema, hhInizioCS, hhFineCS);
        }
        return gestoreAccessi;
    }

    public static GestoreAccessi getIstanza(SistemaGestioneZTL sistema) {
        if (gestoreAccessi == null) {
            gestoreAccessi = new GestoreAccessi(sistema, DEFAULT_HH_ACCESSO, DEFAULT_HH_USCITA);
        }
        return gestoreAccessi;
    }

    public ArrayList<Accesso> getStoricoAccessi() {
        return this.storicoAccessi;
    }

    public ArrayList<Accesso> getAccessiCorrenti() {
        return this.accessiCorrenti;
    }

    public int controllaNumeroAccessi(int idUtente) {
        int count = 0;

        for (Accesso a : this.storicoAccessi) {
            if (a.getIDUtente() == idUtente) {
                count++;
            }
        }

        return count;
    }

    public boolean controllaIntervallo(int hh, int mm) {
        if (hh >= this.hhInizioCS && (hh < this.hhFineCS || (hh == this.hhFineCS && mm == 0))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean controllaInfrazioneUscita(Accesso accesso) {
        int hhAccesso = accesso.getHhAccesso();
        int mmAccesso = accesso.getMmAccesso();
        int hhUscita = accesso.getHhUscita();
        int mmUscita = accesso.getMmUscita();

        int durataMinuti = (hhUscita - hhAccesso) * 60 + (mmUscita - mmAccesso);

        return durataMinuti > 60;
    }

    public void setOrarioUscita(Accesso accesso, int hh, int mm) {
        return;
    }

    public int registraIngresso(RichiestaAccesso richiestaAccesso) {
        int idUtente = richiestaAccesso.getIDUtente();

        for (Accesso a: this.accessiCorrenti) {
            if (a.getIDUtente() == idUtente) {
                System.out.println("Utente gia in transito");
                return 0;
            }
        }

        String profiloTerminale = richiestaAccesso.getProfiloTerminale();
        String profiloUtente = richiestaAccesso.getProfiloUtente();
        int hh = richiestaAccesso.getHh();
        int mm = richiestaAccesso.getMm();

        int codiceRitorno = idUtente;

        Accesso nuovoAccesso = new Accesso(idUtente, hh, mm);

        if (profiloUtente.equals("CS")) {
            if (profiloTerminale.equals("RES")) {
                this.sistema.emettiSanzione(idUtente, "ACCESSO IRREGOLARE");
                codiceRitorno = -1;
            }

            if (!this.controllaIntervallo(hh, mm)) {
                this.sistema.emettiSanzione(idUtente, "TRANSITO IRREGOLARE");
                codiceRitorno = -1;
            }

            if (this.controllaNumeroAccessi(idUtente) > 0) {
                this.sistema.emettiSanzione(idUtente, "TRANSITO EXTRA");
                codiceRitorno = -1;
            }
        }

        this.accessiCorrenti.add(nuovoAccesso);

        return codiceRitorno;
    }

    public int registraUscita(RichiestaUscita richiestaUscita) {
        int idUtente = richiestaUscita.getIDUtente();

        boolean dentro = false;

        String profiloUtente = richiestaUscita.getProfiloUtente();
        int hh = richiestaUscita.getHh();
        int mm = richiestaUscita.getMm();

        int codiceRitorno;
        Accesso accessoCorrente = null;

        for (Accesso a: this.accessiCorrenti) {
            if (a.getIDUtente() == idUtente) {
                accessoCorrente = a;
                break;
            }
        }

        if (accessoCorrente == null) {
            System.out.println("Nessun accesso trovato.");
            codiceRitorno = 0;
        } else {
            accessoCorrente.setOrarioUscita(hh, mm);
            if (profiloUtente.equals("RES")) {
                codiceRitorno = idUtente;
            } else {
                if (this.controllaInfrazioneUscita(accessoCorrente)) {
                    this.sistema.emettiSanzione(idUtente, "TRANSITO ECCESSIVO");
                    codiceRitorno = -1;
                } else {
                    codiceRitorno = idUtente;
                }
            }

            this.accessiCorrenti.remove(accessoCorrente);
            this.storicoAccessi.add(accessoCorrente);
        }

        return codiceRitorno;
    }
}
