import java.util.ArrayList;

public class Terminale {
    static int contatoreID = 0;

    private GestoreTerminali gestoreTerminali;

    private int id;
    private String zona;
    private String profilo;

    public Terminale(GestoreTerminali gestoreTerminali, String zona, String profilo) {
        this.id = ++contatoreID;

        this.gestoreTerminali = gestoreTerminali;

        this.zona = zona;
        this.profilo = profilo;
    }

    public int accedi(String targa) {
        this.gestoreTerminali.segnalaIngressoTarga(targa);
        return -1;
    }

    public int esci(String targa) {
        this.gestoreTerminali.segnalaUscitaTarga(targa);
        return -1;
    }

    int richiediAccesso(int idUtente, int hh, int mm) {
        RichiestaAccesso richiestaAccesso = new RichiestaAccesso(idUtente, this.profilo, hh, mm);
        return this.gestoreTerminali.inoltraRichiestaAccesso(richiestaAccesso);
    }

    int richiediUscita(int idUtente, int hh, int mm) {
        RichiestaUscita richiestaUscita = new RichiestaUscita(idUtente, hh, mm);
        return this.gestoreTerminali.inoltraRichiestaUscita(richiestaUscita);
    }

    public int getID() {
        return this.id;
    }

    public String getZona() {
        return zona;
    }

    public String getProfilo() {
        return profilo;
    }

    public void setProfilo(String profilo) {
        if (!profilo.equals("RES") && !profilo.equals("CS")) {
            System.out.println("Profilo non valido");
        } else {
            this.profilo = profilo;
        }
    }
}
