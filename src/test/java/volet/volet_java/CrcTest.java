/**
 * 
 */
package volet.volet_java;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

/**
 * @author phenom
 *
 */
public class CrcTest {

	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@Test
	public void testCalcul_0() {
		int data[]= {0};
		int ind=1;
		assertEquals(0,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@Test
	public void testCalcul_1() {
		int data[]= {255};
		int ind=1;
		assertEquals(51,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@Test
	public void testCalcul_2() {
		int data[]= {128};
		int ind=1;
		assertEquals(7,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@Test
	public void testCalcul_3() {
		int data[]= {255,255,255,255,0};
		int ind=1;
		assertEquals(51,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@Test
	public void testCalcul_4() {
		int data[]= {1,1,1};
		int ind=3;
		assertEquals(78,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@Test
	public void testCalcul_5() {
		int data[]= {4,2,2};
		int ind=3;
		assertEquals(93,Crc.calcul(data, ind));
	}

	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@Test
	public void testCalcul_6() {
		int data[]= {4,2};
		int ind=2;
		assertEquals(152,Crc.calcul(data, ind));
	}

}
