package volet.volet_java;

import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import volet.volet_java.var.Global;

public class Lecture  implements SerialPortEventListener 
{
	
	private InputStream in;
//	private byte[] buffer;
    private int temp;
    private MsgBin msgEnCours;
    private boolean valideInt;
    boolean limiteDepasse;
   // private FactoryXG factory;
    
	public Lecture ( FactoryXG factory,InputStream in )
	{
		//this.factory=factory;
		this.in = in;
		valideInt=false;
//		buffer = new byte[1024];
		msgEnCours=new MsgBin();
		limiteDepasse=false;
		try {
			factory.getSerie().getSerialPort().addEventListener(this);
		} catch (TooManyListenersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory.getSerie().getSerialPort().notifyOnDataAvailable(true);
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
			if (car>='0' && car<='9') // si c'est un nombre
			{
				temp=(10*temp)+(car-'0'); // calcul le nombre envoyer
				valideInt=true; // on valide le faite qu'il y ai bien un nombre
			}else if (car==' ' || car=='\r'){ //sinon si c'est un espace ou un retour chariot
				if (valideInt==true){ // si il y a eu un nombre
					if (msgEnCours.length()<Global.NB_MAX_VALEUR){ // si on a pas ateint la limite des valeur sur une ligne
						msgEnCours.ajout(temp); // on ajoute la valeur saisie au tableau
						// on reinitialise les variables
						temp=0;
						valideInt=false;
					}
					else{ // sinon on a depasser la limite
							limiteDepasse=true;
					}
				}
				if(car=='\r'){ // si c'est un retour chario
					if (msgEnCours.isValid() && !limiteDepasse) // si le crc est correcte
					{
						// TODO un println il faudrais le modifier 
						// en plus il serait utile de le metre dans un buffer.
						System.out.println(msgEnCours);
						
					}else{ // sinon le crc n'est pas correcte
						System.out.println("message nok");
						//System.out.println(msgEnCours);
					}
					msgEnCours=new MsgBin();// on recree une nouvelle lecture en cours

				}
			}
		}            
	}
}

