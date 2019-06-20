package volet.volet_java;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedTransferQueue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import volet.volet_java.var.Global;

/**
 * test a faire en independant
 *
 * il faut que le arduino soit brancher
 *
 * renseigner le port com et la vitesse dans @BeforeEach
 *
 * @author phenom
 *
 */
@Disabled("test a realiser avec arduino")
class FactorySerieTest {

    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final static PrintStream originalOut = System.out;
    private final static PrintStream originalErr = System.err;
    private static FactorySerie factorySerie;

    @BeforeAll
    public static void setUpStreams() {
        factorySerie = new FactorySerie(9600, Global.portName);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @DisplayName("verif que la vitesse est bien renseigner a 9600")
    @Test
    void testGetPortSpeed() {
        assertEquals(9600, factorySerie.getPortSpeed());
    }

    @Disabled("test a realiser avec changement de vitesse sur le arduino")
    @DisplayName("verif qu'un changement de vitesse est bien pris en compt")
    @Test
    void testSetPortSpeed() {
        factorySerie.setPortSpeed(1200);
        assertEquals(1200, factorySerie.getPortSpeed());
    }

    @DisplayName("verif que le port com est bien mis sur com4")
    @Test
    void testGetPortName() {
        assertEquals("COM3", factorySerie.getPortName());
    }

    @Disabled("test a realiser avec changement de port com physique")
    @DisplayName("verif qu'un changement de port com est bien pris en compt")
    @Test
    void testSetPortName() {
        factorySerie.setPortName("com5");
        assertEquals("com5", factorySerie.getPortName());
    }

    @DisplayName("verif qu'un LinkedTransferQueue est bien cree pour l'entree 'in' ")
    @Test
    void testGetIn() {
        assertThat(factorySerie.getIn(), instanceOf(LinkedTransferQueue.class));
    }

    @DisplayName("verif qu'un LinkedTransferQueue est bien cree pour la sortie 'out' ")
    @Test
    void testGetOut() {
        assertThat(factorySerie.getOut(), instanceOf(LinkedTransferQueue.class));
    }

    @Test
    void testReset() {
        fail("Not yet implemented");
    }

}
