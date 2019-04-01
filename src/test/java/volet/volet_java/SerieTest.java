package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import volet.volet_java.var.Global;


/**
 * attention il faut que le arduino soit brancher pour que les test fonctionne
 * 
 * @author phenom
 *
 */
class SerieTest {
	
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final static PrintStream originalOut = System.out;
	private final static PrintStream originalErr = System.err;
	private static Serie serie;

	@BeforeAll
	static public void beforAll() 
	{
		
	    FactoryXG factory=new FactoryXG();
		try {
			serie=new Serie(factory,Global.portSpeed,Global.portName);
		} catch (Exception e) {
			e.printStackTrace();
			fail(" verrifier que l'arduino est bien brancher sur le port "+Global.portName+" avant toute chose");
			
		}
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	
	}
	
	
	@AfterAll
	static public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Test
	void testGetIn()  {
		assertNotEquals(serie.getIn(), null);
	}

	@Test
	void testGetOut()  {
		assertNotEquals(serie.getOut(), null);
	}
	
	@Test
	void testgetSerialPort()  {
		assertNotEquals(serie.getSerialPort(), null);
	}
	
	@Test
	void testExceptionNoSuchPort()  {
		 Assertions.assertThrows(NoSuchPortException.class, () -> {
			 new Serie(null,Global.portSpeed,"com10");;
		    });
	}
	
	@Test
	void testExceptionPortInUseException()  {
		 Assertions.assertThrows(PortInUseException.class, () -> {
			 new Serie(null,Global.portSpeed,Global.portName);;
		    });
	}

}
