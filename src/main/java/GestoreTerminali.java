import java.util.ArrayList;

public class GestoreTerminali {
    private static GestoreTerminali gestoreTerminali;

    private SistemaGestioneZTL sistema;
    private ArrayList<Terminale> terminali;

    private GestoreTerminali(SistemaGestioneZTL sistema) {
        this.sistema = sistema;
        this.terminali = new ArrayList<>();
    }

    public static GestoreTerminali getIstanza(SistemaGestioneZTL sistema) {
        if (gestoreTerminali == null) {
            gestoreTerminali = new GestoreTerminali(sistema);
        }
        return gestoreTerminali;
    }

    public int inoltraRichiestaAccesso(RichiestaAccesso richiestaAccesso) {
        return this.sistema.registraAccesso(richiestaAccesso);
    }

    public int inoltraRichiestaUscita(RichiestaUscita richiestaUscita) {
        return this.sistema.registraUscita(richiestaUscita);
    }

    public ArrayList<Terminale> getTerminali() {
        return this.terminali;
    }

    public void addTerminale(String zona, String profilo) {
        if (!profilo.equalsIgnoreCase("RES") && !profilo.equalsIgnoreCase("CS")) {
            System.out.println("Profilo non corretto");
        }

        if (this.terminali.size() == 0 && !profilo.equals("CS")) {
            System.out.println("Errore. Il primo terminale deve essere CS. Annullo.");
            return;
        }

        Terminale terminale = null;

        for (Terminale t : this.terminali) {
            if (t.getZona().equalsIgnoreCase(zona)) {
                terminale = t;
                break;
            }
        }

        if (terminale != null) {
            if (terminale.getProfilo().equalsIgnoreCase(profilo)) {
                System.out.println("Terminale gia esistente");
            } else if (terminale.getProfilo().equalsIgnoreCase("RES") && profilo.equals("CS")) {
                terminale.setProfilo("CS");
                System.out.println("Terminale RES trasformato in Terminale CS");
            } else if (terminale.getProfilo().equalsIgnoreCase("CS") && profilo.equals("RES")){
                boolean esiste = false;

                for (Terminale t : this.terminali) {
                    if (terminale.getID() != t.getID() && t.getProfilo().equals("CS")) {
                        esiste = true;
                        break;
                    }
                }

                if (esiste) {
                    terminale.setProfilo("RES");
                } else {
                    System.out.println("Non esiste altro terminale CS. Annullo l'operazione.");
                    return;
                }
            } else {
                System.out.println("Errore. Annullo l'operazione.");
            }
        } else {
            this.terminali.add(new Terminale(this, zona, profilo));
        }
    }

    public void removeTerminale(int id) {
        for (Terminale t : this.terminali) {
            if (t.getID() == id) {
                if (t.getProfilo() == "CS") {
                    boolean unico = true;
                    for (Terminale terminale: this.terminali) {
                        if (terminale.getProfilo().equalsIgnoreCase("CS") && terminale.getID() != t.getID()) {
                                unico = false;
                        }
                    }
                    if (unico == true) {
                        System.out.println("Unico terminale CS attivo");
                        return;
                    }
                }
                System.out.println("Terminale " + t.getID() + " rimosso.");
                terminali.remove(t);
                break;
            }
        }

        System.out.println("Nessun terminale con id " + id);
    }

    public void removeTerminale(String zona) {
        for (Terminale t : this.terminali) {
            if (t.getZona().equals(zona)) {
                System.out.println("Terminale " + zona + " rimosso.");
                terminali.remove(t);
                break;
            }
        }
    }

    void segnalaIngressoTarga(String targa) {
        this.sistema.notificaIngressoTarga(targa);
    }

    void segnalaUscitaTarga(String targa) {
        this.sistema.notificaUscitaTarga(targa);
    }
}
