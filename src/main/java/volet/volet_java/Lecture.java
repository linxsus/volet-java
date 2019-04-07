package volet.volet_java;

import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;
import java.util.concurrent.LinkedTransferQueue;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class Lecture  implements SerialPortEventListener 
{

	private InputStream in;
	private String msgEnCours;
	private LinkedTransferQueue<MsgBin> out;

	public Lecture ( Serie serie, LinkedTransferQueue<MsgBin> out)
	{
		this.in = serie.getIn();
		this.out=out;
		msgEnCours="";
	}

	// le SerialPortEvent arg0 sert a etre compatible avec l'interface SerialPortEventListener 
	public void serialEvent(SerialPortEvent arg0) { 
		int icar=-1;
		try {
			icar = in.read();
		} catch (IOException e1) {
			//TODO gerer l'exception si il y a une erreur de lecture 
			//exemple deconnexion de l'arduino
			e1.printStackTrace();
		} // on regarde lit le dernier caractere saisie
		if (icar!=-1){ // si il y a bien un caractere
			//System.out.print((char) icar);
			char car=(char) icar;
			msgEnCours+=car;
			if(car=='\r'){ // si c'est un retour chario
				boolean ok=true;
				MsgBin msgBin=new MsgBin();
				ok&=msgBin.ajout(msgEnCours,false); 
				ok&=msgBin.isValid();
				if (ok) {
					out.offer(msgBin);
					//TODO ici pour faire des test dans un premier temps
					System.out.println(msgBin);
					
				}
				else{ // sinon le crc n'est pas correcte
					System.out.println("message nok");
					//System.out.println(msgEnCours);
				}
				msgEnCours="";// on recree une nouvelle lecture en cours
			}
		}
	}            
}

