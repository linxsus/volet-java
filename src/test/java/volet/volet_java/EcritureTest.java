package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import volet.volet_java.moc.FactoryXGMoc;
import volet.volet_java.moc.SerieMoc;

class EcritureTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private  FactoryXGMoc factory;
	
	@BeforeEach
	public void setUpStreams() {
		factory=new FactoryXGMoc();
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    
	}

	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	String envoie() {
		return ((SerieMoc)factory.getSerie()).envoie();
	}
	
	
	@Test
	void testSerialEvent_1() {		
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("0");
		assertEquals("0 0 \r\n", envoie());
		
	}

	@Test
	void testSerialEvent_2() {	
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("255 1 ");
		assertEquals("255 1 103 \r\n", envoie());
		
	}
	
	@Test
	void testSerialEvent_3() {
		
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("255t t1 ");
		assertEquals("255 1 103 \r\n",envoie());
		
	}
	
	@Test
	void testSerialEvent_4() {
		
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("xavier 255t t1rr");
		assertEquals("255 1 103 \r\n", envoie());
		
	}
	
	@Test
	void testSerialEvent_5() {
		
		Ecriture ecri=factory.getEcriture();
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR-1;i++)
		{
			temp+="0 ";
		}
		ecri.serialEvent(temp);
		assertEquals(temp+"0 \r\n", envoie());
		
	}
	
	@Test
	void testSerialEvent_6() {
		
		Ecriture ecri=factory.getEcriture();
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR;i++)
		{
			temp+="0 ";
		}
		ecri.serialEvent(temp);
		assertEquals("limite des valeur sur une ligne depasser \r\n", outContent.toString());
		
	}
	
	@Test
	void testSerialEvent_7() {
		
		Ecriture ecri=factory.getEcriture();
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR+5;i++)
		{
			temp+="0 ";
		}
		ecri.serialEvent(temp);
		assertEquals("limite des valeur sur une ligne depasser \r\n", outContent.toString());
		
	}
	
	@Test
	void testSerialEvent_8() {
		
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("/r 0");
		assertEquals("0 0 \r\n", envoie());
		
	}
}
