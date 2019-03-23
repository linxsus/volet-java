package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayOutputStream;

import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import volet.volet_java.moc.FactoryXGMoc;
import volet.volet_java.moc.SerieMoc;

/**
 * 
 * pour le test de la class Lecture
 * 
 * !!!ATTENTION!!!!
 * on est tres dependant de serieMoc FactoryXG et FactoryXGMoc
 * 
 * @author phenom
 *
 */
class LectureTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@BeforeEach
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));

	    
	}

	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	// ici on recupere la sortie println et on la transforme en string
	String sortie() {
		return outContent.toString();
	}
	
	// on simule le fonctionement du thread
	void run(String temp) {
		FactoryXGMoc factory=new FactoryXGMoc();
        SerieMoc serie=(SerieMoc)factory.getSerie(temp); // on cree un port serie virtuel pour les test.
        serie.runLecture(); // c'est ici qu'est cree Lecture et on le lancer (a la base c'est un thread qui boucle)
	}
	
	@Test
	void testSerialEvent_1() {
		String temp="0 0 \r\n";
		run(temp);
		assertEquals("reset\r\n", sortie() );
	}

	@Test
	void testSerialEvent_2() {
		
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR+1;i++)
		{
			temp+="0 ";
		}
		temp+="\r\n";
		run(temp);
		assertEquals("message nok\r\n", outContent.toString());
		
	}
	
	@Test
	void testSerialEvent_3() {
		
		String temp="\r\n";
		run(temp);
		assertEquals("message nok\r\n", outContent.toString());
		
	}
}
