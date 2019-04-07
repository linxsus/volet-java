package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TooManyListenersException;
import java.util.concurrent.LinkedTransferQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import volet.volet_java.mock.FactoryXGMock;
import volet.volet_java.mock.SerieMock;
import volet.volet_java.var.Global;

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
	private LinkedTransferQueue<MsgBin> out;
	private SerieMock serie;
	
	@BeforeEach
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    out=new LinkedTransferQueue<MsgBin>();	    
	}

	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	// ici on recupere la sortie println et on la transforme en string
	String sortie() {
		return out.peek().toString();
	}
	
	// on simule le fonctionement du thread
	void run(String temp) {
		
        serie=null;
		try {
			serie = new SerieMock(temp);
		} catch (NoSuchPortException | UnsupportedCommOperationException | IOException | TooManyListenersException
				| PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // on cree un port serie virtuel pour les test.
        Lecture lecture=new Lecture(serie,out);
        serie.runLecture(lecture); // c'est ici qu'est cree Lecture et on le lancer (a la base c'est un thread qui boucle)
	}
	
	@Test
	void testSerialEvent_1() {
		String temp="0 0 \r\n";
		run(temp);
		assertEquals("reset", sortie() );
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
