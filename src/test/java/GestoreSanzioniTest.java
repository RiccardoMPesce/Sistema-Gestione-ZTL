import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestoreSanzioniTest {
    SistemaGestioneZTL sistema;
    GestoreSanzioni gestoreSanzioni;

    @BeforeEach
    void setUp() {
        this.sistema = SistemaGestioneZTL.getIstanza(9, 21);
        this.gestoreSanzioni = GestoreSanzioni.getIstanza(this.sistema);
    }

    @AfterEach
    void tearDown() {
        this.sistema.distruggiIstanza();
    }

    @Test
    void emettiSanzione() {
        Sanzione sanzione1 = new Sanzione(0, "TRANSITO IRREGOLARE", 50.0f);
        Sanzione sanzione2 = new Sanzione(0, "TRANSITO EXTRA", 30.0f);
        Sanzione sanzione3 = new Sanzione(0, "TRANSITO ECCESSIVO", 15.0f);
        Sanzione sanzione4 = new Sanzione(0, "ACCESSO IRREGOLARE", 10.0f);

        this.gestoreSanzioni.emettiSanzione(0, "TRANSITO IRREGOLARE");
        this.gestoreSanzioni.emettiSanzione(0, "TRANSITO EXTRA");
        this.gestoreSanzioni.emettiSanzione(0, "TRANSITO ECCESSIVO");
        this.gestoreSanzioni.emettiSanzione(0, "ACCESSO IRREGOLARE");

        assertTrue(this.gestoreSanzioni.getSanzioni().contains(sanzione1));
        assertTrue(this.gestoreSanzioni.getSanzioni().contains(sanzione2));
        assertTrue(this.gestoreSanzioni.getSanzioni().contains(sanzione3));
        assertTrue(this.gestoreSanzioni.getSanzioni().contains(sanzione4));
    }
}