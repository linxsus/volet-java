package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import volet.volet_java.var.Global;

class MsgBinTest {

	@Test
	void testIsValid_0() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(0);
		msgBin.ajout(0);
		assertEquals(true,msgBin.isValid());
	}

	@Test
	void testIsValid_1() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(10);
		msgBin.ajout(0);
		assertEquals(false,msgBin.isValid());
	}
	
	@Test
	void testIsValid_2() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(4);
		msgBin.ajout(2);
		msgBin.ajout(2);
		msgBin.ajout(93);
		assertEquals(true,msgBin.isValid());
	}
	
	@Test
	void testGetInd() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(4);
		msgBin.ajout(2);
		msgBin.ajout(2);
		msgBin.ajout(93);
		assertEquals(4,msgBin.length());
	}

	@Test
	void testGetData() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(4);
		msgBin.ajout(2);
		msgBin.ajout(2);
		int[] ind=new int[Global.NB_MAX_VALEUR];
		ind[0]=4;
		ind[1]=2;
		ind[2]=2;
		assertEquals(Arrays.toString(ind),Arrays.toString(msgBin.getData()));
	}

	@Test
	void testToString() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(4);
		msgBin.ajout(2);
		msgBin.ajout(152);
		assertEquals("activation de la sortie 2 ",msgBin.toString());
	}

	@Test
	void testToStringData() {
	MsgBin msgBin=new MsgBin();
	msgBin.ajout(4);
	msgBin.ajout(2);
	msgBin.ajout(152);
	assertEquals("4 2 152 \r\n",msgBin.toStringData());
	}

}
