import java.util.Objects;

public class Sanzione {
    private int idUtente;
    private String descrizione;
    private float ammontare;

    public Sanzione(int idUtente, String descrizione, float ammontare) {
        this.idUtente = idUtente;
        this.descrizione = descrizione;
        this.ammontare = ammontare;
    }

    public int getIdUtente() {
        return this.idUtente;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public float getAmmontare() {
        return this.ammontare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sanzione sanzione = (Sanzione) o;
        return this.idUtente == sanzione.idUtente &&
                Float.compare(sanzione.ammontare, this.ammontare) == 0 &&
                Objects.equals(this.descrizione, sanzione.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtente, descrizione, ammontare);
    }
}
