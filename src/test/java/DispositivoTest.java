import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispositivoTest {
    SistemaGestioneZTL sistema;
    GestoreResidenti gestoreResidenti;

    @BeforeEach
    void setUp() {
        this.sistema = SistemaGestioneZTL.getIstanza(9, 21);
        this.gestoreResidenti = GestoreResidenti.getIstanza(this.sistema);

        this.sistema.addTerminale("a", "CS");
        this.sistema.addTerminale("b", "RES");
    }

    @AfterEach
    void tearDown() {
        this.sistema.distruggiIstanza();
        this.gestoreResidenti = null;
    }

    @Test
    void accedi() {
        Dispositivo d1 = new Dispositivo(this.sistema);
        Dispositivo d2 = new Dispositivo(this.sistema);
        Dispositivo d3 = new Dispositivo(this.sistema);
        Dispositivo d4 = new Dispositivo(this.sistema);
        Dispositivo d5 = new Dispositivo(this.sistema);
        Dispositivo d6 = new Dispositivo(this.sistema);
        Dispositivo d7 = new Dispositivo(this.sistema);

        this.sistema.addResidente(2);
        this.sistema.addResidente(6);

        int ret1 = d1.accedi("a", 8, 59);
        int ret2 = d2.accedi("a", 8, 59);
        int ret3 = d3.accedi("a", 9, 0);
        int ret4 = d4.accedi("a", 21, 0);
        int ret5 = d5.accedi("a", 21, 1);
        int ret6 = d6.accedi("b", 2, 59);
        int ret7 = d7.accedi("b", 11, 0);

        assertEquals(ret1, -1);
        assertEquals(ret2, d2.getID());
        assertEquals(ret3, d3.getID());
        assertEquals(ret4, d4.getID());
        assertEquals(ret5, -1);
        assertEquals(ret6, d6.getID());
        assertEquals(ret7, -1);

    }

    @Test
    void esci() {
        Dispositivo d8 = new Dispositivo(this.sistema);
        Dispositivo d9 = new Dispositivo(this.sistema);

        this.sistema.addResidente(9);

        d8.accedi("a", 9, 11);
        int ret11 = d8.esci("b", 10, 11);
        d8.accedi("a", 21, 0);
        int ret12 = d8.esci("a", 22, 1);
        d8.accedi("a", 21, 0);
        int ret13 = d8.esci("a", 22, 0);

        d9.accedi("b", 11, 0);
        int ret21 = d9.esci("a", 17, 0);

        assertEquals(d8.getID(), ret11);
        assertEquals(-1, ret12);
        assertEquals(d8.getID(), ret13);
        assertEquals(d9.getID(), ret21);
    }
}