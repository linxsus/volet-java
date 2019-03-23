package volet.volet_java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MsgEnumTest {

	@Test
	void testRechercheParMsgIn_0() {
		assertEquals(MsgEnum.rechercheParMsgIn(0),MsgEnum.RESET);
	}
	
	@Test
	void testRechercheParMsgIn_1() {
		assertEquals(MsgEnum.rechercheParMsgIn(255),null);
	}

	@Test
	void testToString() {
		assertEquals(MsgEnum.RESET.toString(),"reset");
	}

}
