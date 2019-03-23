package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcritureTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

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

	@Test
	void testSerialEvent_1() {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Ecriture ecri=new Ecriture(baos);
		ecri.serialEvent("0");
		byte[] byteArray = baos.toByteArray();
		assertEquals("0 0 \r\n", new String(byteArray));
		
	}

	@Test
	void testSerialEvent_2() {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Ecriture ecri=new Ecriture(baos);
		ecri.serialEvent("255 1 ");
		byte[] byteArray = baos.toByteArray();
		assertEquals("255 1 103 \r\n", new String(byteArray));
		
	}
	
	@Test
	void testSerialEvent_3() {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Ecriture ecri=new Ecriture(baos);
		ecri.serialEvent("255t t1 ");
		byte[] byteArray = baos.toByteArray();
		assertEquals("255 1 103 \r\n", new String(byteArray));
		
	}
	
	@Test
	void testSerialEvent_4() {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Ecriture ecri=new Ecriture(baos);
		ecri.serialEvent("xavier 255t t1rr");
		byte[] byteArray = baos.toByteArray();
		assertEquals("255 1 103 \r\n", new String(byteArray));
		
	}
	
	@Test
	void testSerialEvent_5() {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Ecriture ecri=new Ecriture(baos);
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR-1;i++)
		{
			temp+="0 ";
		}
		ecri.serialEvent(temp);
		byte[] byteArray = baos.toByteArray();
		assertEquals(temp+"0 \r\n", new String(byteArray));
		
	}
	
	@Test
	void testSerialEvent_6() {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Ecriture ecri=new Ecriture(baos);
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
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Ecriture ecri=new Ecriture(baos);
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
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Ecriture ecri=new Ecriture(baos);
		ecri.serialEvent("/r 0");
		byte[] byteArray = baos.toByteArray();
		assertEquals("0 0 \r\n", new String(byteArray));
		
	}
}
