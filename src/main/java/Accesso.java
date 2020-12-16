public class Accesso {
    private int idUtente;

    private int hhIngresso;
    private int mmIngresso;
    private int hhUscita;
    private int mmUscita;

    public Accesso(int id, int hh, int mm) {
        this.idUtente = id;
        this.hhIngresso = hh;
        this.mmIngresso = mm;
    }

    void setOrarioUscita(int hh, int mm) {
        this.hhUscita = hh;
        this.mmUscita = mm;
    }

    public int getHhAccesso() {
        return this.hhIngresso;
    }

    public int getMmAccesso() {
        return this.mmIngresso;
    }

    public int getHhUscita() {
        return this.hhUscita;
    }

    public int getMmUscita() {
        return this.mmUscita;
    }

    public int getIDUtente() {
        return this.idUtente;
    }
}
