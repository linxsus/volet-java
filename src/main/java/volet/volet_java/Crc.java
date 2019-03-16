package volet.volet_java;

public class Crc {
	public static int calcul(int[] data,int ind) {
	    int result = 0;
		int retenue = 0;
		for ( byte i = 0 ; i < ind ;i ++ ) // pour chaque Byte
		{
			short octet_lu =(short) data[i];
			for ( byte j = 0 ; j < 16 ; j ++ ) // pour chaque bits !! ici il y a 16 pour la compatibilit� avec les int
			{
				result = (( ( octet_lu ^ retenue)  << 7 )   |  (result >> 1))& 0xff; // nouveau crc (le 0xff est pour faire un byte unsigned) 
				retenue = ((result>>7)^(result>>4)^(result>>3)^result)& 0xff; // retenue a xorer (dans bit0), autres bits quelconques (le 0xff est pour faire un byte unsigned)
				octet_lu >>= 1; // bit suivant
			}
		}
		return result;
	}
}
