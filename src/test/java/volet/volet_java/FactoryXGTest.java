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

import volet.volet_java.aCoder.Gestion;

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
		Volet volet=factory.newVolet(1,1,1,1,1L,1L);
		assertThat(volet , instanceOf(Volet.class));
		Volet volet1=factory.newVolet(1,1,1,1,1L,1L);
		assertThat(volet1 , instanceOf(Volet.class));
		assertNotEquals(volet, volet1);
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
		Serie serie=factory.getSerie();
		assertThat(serie, instanceOf(Serie.class));
		Serie serie1=factory.getSerie();
		assertEquals(serie, serie1);
	}

	@Disabled //a lancer qu'individuellement
	@Test
	void testNewLecture() {
		// attention il faut que le arduino soit brancher
		Lecture lecture=factory.getLecture();
		assertThat(lecture, instanceOf(Lecture.class));
		Lecture lecture1=factory.getLecture();
		assertEquals(lecture, lecture1);
	}

	@Disabled //a lancer qu'individuellement
	@Test
	void  testGetEcriture() {
		// attention il faut que le arduino soit brancher
		Ecriture ecriture=factory.getEcriture();
		assertThat(ecriture, instanceOf(Ecriture.class));
		Ecriture ecriture1=factory.getEcriture();
		assertEquals(ecriture, ecriture1);
	}
	
	@Test
	void testGetObjectDeType() {
		assertEquals(0, factory.getObjectDeType(Volet.class).size());
		factory.newVolet(1,1,1,1,1L,1L);
		factory.newVolet(1,1,1,1,1L,1L);
		factory.getGestion();
		assertEquals(2, factory.getObjectDeType(Volet.class).size());
	}
	
	@Test
	void testNewGestion() {
		//TODO a metre en place 
		assertThat(factory.getGestion(), instanceOf(Gestion.class));
	}
}
