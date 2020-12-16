import java.util.ArrayList;

public class GestoreResidenti {
    private static GestoreResidenti gestoreResidenti;
    private SistemaGestioneZTL sistema;

    private ArrayList<Integer> residenti;

    private GestoreResidenti(SistemaGestioneZTL sistema) {
        this.sistema = sistema;
        this.residenti = new ArrayList<>();
    }

    public static GestoreResidenti getIstanza(SistemaGestioneZTL sistema) {
        if (gestoreResidenti == null) {
            gestoreResidenti = new GestoreResidenti(sistema);
        }
        return gestoreResidenti;
    }

    public ArrayList<Integer> getResidenti() {
        return this.residenti;
    }

    public void addResidente(int id) {
        if (this.residenti.contains(id)) {
            System.out.println("Residente gia esistente.");
        } else {
            this.residenti.add(id);
        }
    }

    public void removeResidente(int id) {
        if (this.residenti.contains(id)) {
            this.residenti.remove(id);
        } else {
            System.out.println("Residente non esistente.");
        }
    }
}
