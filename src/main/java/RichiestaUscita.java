public class RichiestaUscita {
    private int idUtente;
    private String profiloUtente;
    private int hh;
    private int mm;

    public RichiestaUscita(int idUtente, int hh, int mm) {
        this.idUtente = idUtente;
        this.hh = hh;
        this.mm = mm;
    }

    public void setProfiloUtente(String profiloUtente) {
        this.profiloUtente = profiloUtente;
    }

    public int getIDUtente() {
        return idUtente;
    }

    public int getHh() {
        return hh;
    }

    public int getMm() {
        return mm;
    }

    public String getProfiloUtente() {
        return profiloUtente;
    }
}
