package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import volet.volet_java.moc.FactoryXGMoc;
import volet.volet_java.moc.SerieMoc;
import volet.volet_java.var.Global;
import volet.volet_java.var.MsgEnum;

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
	
	@DisplayName("pour reset")
	@Test
	void testSerialEvent_1() {		
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("0");
		assertEquals("0 0 \r\n", envoie());
		
	}

	@DisplayName("pour 255 1")
	@Test
	void testSerialEvent_2() {	
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("255 1 ");
		assertEquals("255 1 103 \r\n", envoie());
		
	}
	
	@DisplayName("pour 255t t1 verif que l'on ne prend que les chifre ")
	@Test
	void testSerialEvent_3() {
		
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("255t t1 ");
		assertEquals("255 1 103 \r\n",envoie());
		
	}
	
	@DisplayName("pour xavier 255t t1rr verif que l'on ne prend que les chifre ")
	@Test
	void testSerialEvent_4() {
		
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("xavier 255t t1rr");
		assertEquals("255 1 103 \r\n", envoie());
		
	}
	
	@DisplayName("verif que l'on ne prend bien Global.NB_MAX_VALEUR caractere")
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
	
	@DisplayName("verif que l'on a bien une erreur si on depasse le nb de caractere +1")
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
	
	@DisplayName("verif que l'on a bien une erreur si on depasse le nb de caractere +5")
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
	
	@DisplayName("verif que l'on peut accepter un carctere special")
	@Test
	// test tres leger mais je suis pas un specialiste 
	void testSerialEvent_8() {
		
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent("\r\n\\r\\n 1 \r\n\\r\\n 2");
		assertEquals("1 2 255 \r\n", envoie());
		
	}
	
	@DisplayName("verif pour un msgBin")
	@Test
	void testSerialEvent_9() {
		MsgBin msg=new MsgBin();
		msg.ajout(MsgEnum.ACTIVATION_SORTIE);
		msg.ajout(5);
		msg.ajoutCrc();
		Ecriture ecri=factory.getEcriture();
		ecri.serialEvent(msg);
		assertEquals("4 5 30 \r\n", envoie());
		
	}
}
