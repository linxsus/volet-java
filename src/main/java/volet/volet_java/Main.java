package volet.volet_java;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
	
	 private static Scanner sc;

	public Main()
	    {
	        super();
	    }
	 
	    public static void main ( String[] args )
	    {
	    	sc = new Scanner(System.in);
	    	InputStream in;
	    	OutputStream out;
	    	Serie serie=null;
	    try {
			serie=new Serie();
		} catch (Exception e) {
			e.printStackTrace();
		}
	       in=serie.getIn();
	       out=serie.getOut();
	      // SerialReader read=
	       new Lecture(in);
	       Ecriture input=new Ecriture(out);
	       while (true) {
	    	   String str = sc.nextLine();
	    	   input.serialEvent(str);
	        }
	    }


}
