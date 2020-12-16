public class Utente {
    private String targa;
    private Dispositivo dispositivo;

    public Utente(String targa) {
        this.targa = targa;
        this.dispositivo = null;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    void accedi(int idTerminale, int hh, int mm) {
        int codiceRitorno = 0;

        if (this.dispositivo == null) {
            for (Terminale t: SistemaGestioneZTL.getIstanza().getTerminali()) {
                if (t.getID() == idTerminale) {
                    codiceRitorno = t.accedi(this.targa);
                    break;
                }
            }
        } else {
            codiceRitorno = this.dispositivo.accedi(idTerminale, hh, mm);
        }

        System.out.println("Utente " + this.targa + " entrato con codice " + codiceRitorno);
    }

    public void accedi(String zona, int hh, int mm) {
        int codiceRitorno = 0;

        if (this.dispositivo == null) {
            for (Terminale t: SistemaGestioneZTL.getIstanza().getTerminali()) {
                if (t.getZona().equalsIgnoreCase(zona)) {
                    codiceRitorno = t.accedi(this.targa);
                    break;
                }
            }
        } else {
            codiceRitorno = this.dispositivo.accedi(zona, hh, mm);
        }

        System.out.println("Utente " + this.targa + " entrato con codice " + codiceRitorno);
    }

    public void esci(int idTerminale, int hh, int mm) {
        int codiceRitorno = 0;

        if (this.dispositivo == null) {
            for (Terminale t: SistemaGestioneZTL.getIstanza().getTerminali()) {
                if (t.getID() == idTerminale) {
                    codiceRitorno = t.esci(this.targa);
                    break;
                }
            }
        } else {
            codiceRitorno = this.dispositivo.esci(idTerminale, hh, mm);
        }

        System.out.println("Utente " + this.targa + " uscito con codice " + codiceRitorno);
    }

    public void esci(String zona, int hh, int mm) {
        int codiceRitorno = 0;

        if (this.dispositivo == null) {
            for (Terminale t: SistemaGestioneZTL.getIstanza().getTerminali()) {
                if (t.getZona().equalsIgnoreCase(zona)) {
                    codiceRitorno = t.esci(this.targa);
                    break;
                }
            }
        } else {
            codiceRitorno = this.dispositivo.esci(zona, hh, mm);
        }

        System.out.println("Utente " + this.targa + " uscito con codice " + codiceRitorno);
    }

    public int getID() {
        if (this.dispositivo == null) {
            return -1;
        } else {
            return this.dispositivo.getID();
        }
    }

    public String getTarga() {
        return this.targa;
    }
}
