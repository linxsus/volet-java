package volet.volet_java;


import java.io.InputStream;
import java.io.OutputStream;

public class Main {
	
	
	 public Main()
	    {
	        super();
	    }
	 
	    public static void main ( String[] args )
	    {
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
	    	   input.serialEvent(null);
	        }
	    }


}
