import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestoreAccessiTest {
    SistemaGestioneZTL sistema;
    GestoreAccessi gestoreAccessi;

    @BeforeEach
    void setUp() {
        this.sistema = SistemaGestioneZTL.getIstanza(9, 21);
        this.gestoreAccessi = GestoreAccessi.getIstanza(this.sistema);
    }

    @Test
    void controllaIntervallo() {
        boolean ci1 = gestoreAccessi.controllaIntervallo(9, 0);
        boolean ci2 = gestoreAccessi.controllaIntervallo(8, 59);
        boolean ci3 = gestoreAccessi.controllaIntervallo(21, 0);
        boolean ci4 = gestoreAccessi.controllaIntervallo(21, 1);

        assertTrue(ci1);
        assertTrue(ci3);
        assertFalse(ci2);
        assertFalse(ci4);
    }

    @Test
    void controllaInfrazioneUscita() {
        Accesso a1 = new Accesso(0, 9, 0);
        Accesso a2 = new Accesso(0, 9, 0);
        Accesso a3 = new Accesso(0, 9, 0);
        Accesso a4 = new Accesso(0, 21, 0);
        Accesso a5 = new Accesso(0, 21, 0);
        Accesso a6 = new Accesso(0, 21, 0);

        a1.setOrarioUscita(10, 0);
        a2.setOrarioUscita(10, 1);
        a3.setOrarioUscita(9, 59);
        a4.setOrarioUscita(22, 0);
        a5.setOrarioUscita(22, 1);
        a6.setOrarioUscita(21, 59);

        boolean ci1 = gestoreAccessi.controllaInfrazioneUscita(a1);
        boolean ci2 = gestoreAccessi.controllaInfrazioneUscita(a2);
        boolean ci3 = gestoreAccessi.controllaInfrazioneUscita(a3);
        boolean ci4 = gestoreAccessi.controllaInfrazioneUscita(a4);
        boolean ci5 = gestoreAccessi.controllaInfrazioneUscita(a5);
        boolean ci6 = gestoreAccessi.controllaInfrazioneUscita(a6);

        assertFalse(ci1);
        assertFalse(ci3);
        assertFalse(ci4);
        assertFalse(ci6);
        assertTrue(ci2);
        assertTrue(ci5);
    }

    @AfterEach
    void tearDown() {
        this.sistema.distruggiIstanza();
        this.gestoreAccessi = null;
    }
}