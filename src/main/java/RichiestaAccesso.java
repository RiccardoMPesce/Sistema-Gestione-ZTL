public class RichiestaAccesso{
    private int idUtente;
    private String profiloTerminale;
    private String profiloUtente;
    private int hh;
    private int mm;

    public RichiestaAccesso(int idUtente, String profiloTerminale, int hh, int mm) {
        this.idUtente = idUtente;
        this.profiloTerminale = profiloTerminale;
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

    public String getProfiloTerminale() {
        return profiloTerminale;
    }

    public String getProfiloUtente() {
        return profiloUtente;
    }
}
