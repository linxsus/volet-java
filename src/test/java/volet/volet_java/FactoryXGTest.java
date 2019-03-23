package volet.volet_java;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedTransferQueue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FactoryXGTest {
	
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final static PrintStream originalOut = System.out;
	private final static PrintStream originalErr = System.err;
	private  FactoryXG factory;
	
	@BeforeAll
	static public void beforAll() 
	{
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));	
	}
	
	@AfterAll
	static public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	
	@BeforeEach
	public void before()
	{
		factory=new FactoryXG();
	}

	@Test
	@Tag("GetErreur")
	@DisplayName("test de recuperation de la liste erreur")
	
	void testGetErreur() {
		assertThat(factory.getErreur(), instanceOf(LinkedTransferQueue.class));
	}

	@Test
	void testNewVolet() {
		assertThat(factory.newVolet(1,1,1,1,1L,1L), instanceOf(Volet.class));
	}

	@Test
	void testGetId() {
		Volet volet=factory.newVolet(1,1,1,1,1L,1L);
		assertNotEquals(factory.getId(volet), -1);
	}

	@Disabled //a lancer qu'individuellement
	@Test
	void testNewSerie() {
		// attention il faut que le arduino soit brancher
		assertThat(factory.getSerie(), instanceOf(Serie.class));
	}

//	@Test
//	void testNewSerieIntString() {
//		// attention il faut que le arduino soit brancher
//		assertThat(factory.newSerie(9600,"com4"), instanceOf(Serie.class));
//	}

	@Disabled //a lancer qu'individuellement
	@Test
	void testNewLecture() {
		// attention il faut que le arduino soit brancher
		assertThat(factory.getLecture(), instanceOf(Lecture.class));
	}

	@Test
	void testNewGestion() {
		assertThat(factory.getGestion(), instanceOf(Gestion.class));
	}
}
