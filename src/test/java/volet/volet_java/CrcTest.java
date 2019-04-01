/**
 * 
 */
package volet.volet_java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author phenom
 *
 */
public class CrcTest {

	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@DisplayName("pour 0")
	@Test
	public void testCalcul_0() {
		int data[]= {0};
		int ind=1;
		assertEquals(0,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@DisplayName("pour 255")
	@Test
	public void testCalcul_1() {
		int data[]= {255};
		int ind=1;
		assertEquals(51,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@DisplayName("pour 128")
	@Test
	public void testCalcul_2() {
		int data[]= {128};
		int ind=1;
		assertEquals(7,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@DisplayName("un indice inferieur a la taille du tableau")
	@Test
	public void testCalcul_3() {
		int data[]= {255,255,255,255,0};
		int ind=1;
		assertEquals(51,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@DisplayName("pour 1 1 1")
	@Test
	public void testCalcul_4() {
		int data[]= {1,1,1};
		int ind=3;
		assertEquals(78,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@DisplayName("pour 4 2 2")
	@Test
	public void testCalcul_5() {
		int data[]= {4,2,2};
		int ind=3;
		assertEquals(93,Crc.calcul(data, ind));
	}

	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@DisplayName("pour 4 2 ")
	@Test
	public void testCalcul_6() {
		int data[]= {4,2};
		int ind=2;
		assertEquals(152,Crc.calcul(data, ind));
	}

	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@DisplayName("indice superieur a la taille du data")
	@Test
	public void testCalcul_7() {
		int data[]= {4,2};
		int ind=3;
		assertEquals(152,Crc.calcul(data, ind));
	}
	
	/**
	 * Test method for {@link volet.volet_java.Crc#calcul(int[], int)}.
	 */
	@DisplayName("valeur a 65536 le arduino le prendra pour un 0")
	@Test
	public void testCalcul_8() {
		int data[]= {65536};
		int ind=1;
		assertEquals(0,Crc.calcul(data, ind));
	}
}
