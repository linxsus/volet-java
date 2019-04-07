package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import volet.volet_java.var.Global;
import volet.volet_java.var.MsgEnum;

class MsgBinTest {

	@DisplayName("pour 1 avec crc ajouter manuellement")
	@Test
	void testIsValid_0() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(1,false);
		assertEquals(false,msgBin.isValid());
		msgBin.setCrc(90);
		assertEquals(true,msgBin.isValid());
	}

	@DisplayName("pour 10 avec crc ajouter automatiquement")
	@Test
	void testIsValid_1() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(10);
		assertEquals(true,msgBin.isValid());
	}
	
	@DisplayName("pour 4 2  avec crc calculer automatiquement")
	@Test
	void testIsValid_2() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(4);
		msgBin.ajout(2);
		msgBin.setCrc();
		assertEquals(true,msgBin.isValid());
	}
	
	@DisplayName("verif de la fonction isValide() avec l'insersion du mauvais Crc")
	@Test
	void testIsValid_3() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(5);
		msgBin.setCrc(5);
		assertEquals(false, msgBin.isValid());
	}
	
	@DisplayName("verif de la fonction isValide() avec aucun message")
	@Test
	void testIsValid_4() {
		MsgBin msgBin=new MsgBin();
		assertEquals(false, msgBin.isValid());
	}

	@DisplayName("verif de la validiter de la longuer de data lenght()")
	@Test
	void testGetInd() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(4);
		msgBin.ajout(2);
		msgBin.ajout(2);
		msgBin.setCrc(93);
		assertEquals(3,msgBin.length());
	}

	@DisplayName("verif de la fonction getData()")
	@Test
	void testGetData() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(4,false);
		msgBin.ajout(2,false);
		msgBin.setCrc(2);
		int[] ind=new int[Global.NB_MAX_VALEUR];
		ind[0]=4;
		ind[1]=2;
		ind[2]=2;
		assertEquals(Arrays.toString(ind),Arrays.toString(msgBin.getData()));
		msgBin.ajout(2);
		assertEquals(true,msgBin.isValid());		
	}

	@DisplayName("verif de la fonction toString()")
	@Test
	void testToString() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(4);
		msgBin.ajout(2);
		msgBin.setCrc(152);
		assertEquals("activation de la sortie 2 ",msgBin.toString());
	}

	@DisplayName("verif de la fonction toStringData()")
	@Test
	void testToStringData() {
	MsgBin msgBin=new MsgBin();
	msgBin.ajout(4);
	msgBin.ajout(2);
	msgBin.setCrc(152);
	assertEquals("4 2 152 \r\n",msgBin.toStringData());
	}

	@DisplayName("verif de la fonction ajout(String) pour 0")
	@Test
	void testAjoutString1() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout("0");
		assertEquals("0 0 \r\n",msgBin.toStringData());
	}
	
	@DisplayName("verif de la fonction ajout(String) pour 255 1")
	@Test
	void testAjoutString2() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout("255 1");
		assertEquals("255 1 103 \r\n",msgBin.toStringData());
	}
	
	
	@DisplayName("verif de la fonction ajout(String) pour 255t t1 verif que l'on ne prend que les chifre")
	@Test
	void testAjoutString3() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout("255t t1");
		assertEquals("255 1 103 \r\n",msgBin.toStringData());
	}
	
	@DisplayName("verif de la fonction ajout(String) pour xavier 255t t1rr verif que l'on ne prend que les chifre")
	@Test
	void testAjoutString4() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout("xavier 255t t1rr");
		assertEquals("255 1 103 \r\n",msgBin.toStringData());
	}
	
	@DisplayName("verif de la fonction ajout(String) prend bien Global.NB_MAX_VALEUR-1 caractere")
	@Test
	void testAjoutString5() {
		MsgBin msgBin=new MsgBin();
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR-1;i++)
		{
			temp+="1 ";
		}
		assertEquals(true, msgBin.ajout(temp));
		//assertEquals(temp+" 75 \r\n", msgBin.toStringData());
		assertEquals(false, msgBin.ajout(0));
	}
	
	@DisplayName("verif de la fonction ajout(String) ne prend bien que Global.NB_MAX_VALEUR-1 caractere")
	@Test
	void testAjoutString6() {
		MsgBin msgBin=new MsgBin();
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR;i++)
		{
			temp+="0 ";
		}
		assertEquals(false, msgBin.ajout(temp));
	}
	
	@DisplayName("verif de la fonction ajout(String,false) prend bien Global.NB_MAX_VALEUR caractere")
	@Test
	void testAjoutStringBool1() {
		MsgBin msgBin=new MsgBin();
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR;i++)
		{
			temp+="1 ";
		}
		assertEquals(true, msgBin.ajout(temp,false));
		assertEquals(temp+"\r\n", msgBin.toStringData());
		assertEquals(false, msgBin.ajout(0));
	}
	
	@DisplayName("verif de la fonction ajout(String,false) ne prend bien que Global.NB_MAX_VALEUR caractere")
	@Test
	void testAjoutStringBool2() {
		MsgBin msgBin=new MsgBin();
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR+1;i++)
		{
			temp+="0 ";
		}
		assertEquals(false, msgBin.ajout(temp));
	}
	
	@DisplayName("verif de la fonction ajout(String,false) avec rien")
	@Test
	void testAjoutStringBool3() {
		MsgBin msgBin=new MsgBin();
		String temp="\r\n";
		assertEquals(true, msgBin.ajout(temp,false));
	}
	
	@DisplayName("verif de la fonction ajout(int) prend bien Global.NB_MAX_VALEUR-1 caractere")
	@Test
	void testAjoutInt1() {
		MsgBin msgBin=new MsgBin();
		String temp="";
		for (int i=0;i<Global.NB_MAX_VALEUR-1;i++)
		{
			temp+="0 ";
			msgBin.ajout(0);
		}
		
		assertEquals(temp+"0 \r\n", msgBin.toStringData());
		assertEquals(false, msgBin.ajout(0));
	}
	
	
	@DisplayName("verif de la fonction ajout(MsgEnum) sur 1er data")
	@Test
	void testAjoutMsgEnum1() {
		MsgBin msgBin=new MsgBin();
		assertEquals(true,msgBin.ajout(MsgEnum.MEMOIRE_RESTANTE));
		assertEquals("7 134 \r\n", msgBin.toStringData());
	}
	
	@DisplayName("verif de la fonction ajout(MsgEnum) sur 2eme data")
	@Test
	void testAjoutMsgEnum2() {
		MsgBin msgBin=new MsgBin();
		msgBin.ajout(1);
		assertEquals(false,msgBin.ajout(MsgEnum.MEMOIRE_RESTANTE));
	}
}
