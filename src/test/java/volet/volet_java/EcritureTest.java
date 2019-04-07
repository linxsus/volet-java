package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TooManyListenersException;
import java.util.concurrent.LinkedTransferQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import volet.volet_java.mock.FactoryXGMock;
import volet.volet_java.mock.SerieMock;
import volet.volet_java.var.Global;
import volet.volet_java.var.MsgEnum;

class EcritureTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private  SerieMock serie;
	private LinkedTransferQueue<MsgBin> in;
	
	@BeforeEach
	public void setUpStreams() {
		try {
			serie=new SerieMock("");
		} catch (NoSuchPortException | UnsupportedCommOperationException | IOException | TooManyListenersException
				| PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    in=new LinkedTransferQueue<MsgBin>();
	    
	}

	
	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	String envoie() {
		return (serie.envoie());
	}
	
	@DisplayName("pour reset")
	@Test
	void testSerialEvent_1() {		
		Ecriture ecri=new Ecriture(serie,in);
		ecri.serialEvent("0");
		assertEquals("0 0 \r\n", envoie());
		
	}

	
	@DisplayName("verif que l'on ne prend bien Global.NB_MAX_VALEUR caractere")
	@Test
	void testSerialEvent_5() {
		
		Ecriture ecri=new Ecriture(serie,in);
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR-1;i++)
		{
			temp+="0 ";
		}
		ecri.serialEvent(temp);
		assertEquals(temp+"0 \r\n", envoie());
		
	}
	
	
	@DisplayName("verif que l'on peut accepter un carctere special")
	@Test
	// test tres leger mais je suis pas un specialiste 
	void testSerialEvent_8() {
		
		Ecriture ecri=new Ecriture(serie,in);
		ecri.serialEvent("\r\n\\r\\n 1 \r\n\\r\\n 2");
		assertEquals("1 2 255 \r\n", envoie());
		
	}
	
	@DisplayName("verif pour un msgBin")
	@Test
	void testSerialEvent_9() {
		MsgBin msg=new MsgBin();
		msg.ajout(MsgEnum.ACTIVATION_SORTIE);
		msg.ajout(5);
		msg.setCrc();
		Ecriture ecri=new Ecriture(serie,in);
		ecri.serialEvent(msg);
		assertEquals("4 5 30 \r\n", envoie());
		
	}
}
