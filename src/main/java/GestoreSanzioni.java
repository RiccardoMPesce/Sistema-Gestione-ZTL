import java.util.ArrayList;

public class GestoreSanzioni {
    private static GestoreSanzioni gestoreSanzioni;

    private SistemaGestioneZTL sistema;
    private ArrayList<Sanzione> sanzioni;

    private GestoreSanzioni(SistemaGestioneZTL sistema) {
        this.sistema = sistema;
        this.sanzioni = new ArrayList<>();
    }

    public static GestoreSanzioni getIstanza(SistemaGestioneZTL sistema) {
        if (gestoreSanzioni == null) {
            gestoreSanzioni = new GestoreSanzioni(sistema);
        }
        return gestoreSanzioni;
    }

    public void emettiSanzione(int idUtente, String descrizione) {
        float ammontare;

        if (descrizione.equals("TRANSITO IRREGOLARE")) {
            ammontare = 50.0f;
        } else if (descrizione.equals("TRANSITO EXTRA")) {
            ammontare = 30.0f;
        } else if (descrizione.equals("TRANSITO ECCESSIVO")) {
            ammontare = 15.0f;
        } else if (descrizione.equals("ACCESSO IRREGOLARE")) {
            ammontare = 10.0f;
        } else {
            System.out.println("Descrizione non valida. Annullo");
            return;
        }

        Sanzione sanzione = new Sanzione(idUtente, descrizione, ammontare);
        this.sanzioni.add(sanzione);
    }

    public ArrayList<Sanzione> getSanzioni() {
        return this.sanzioni;
    }
}
