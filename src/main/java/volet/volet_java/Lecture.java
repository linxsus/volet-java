package volet.volet_java;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class Lecture  implements SerialPortEventListener 
{
	
	private InputStream in;
//	private byte[] buffer;
    private int temp;
    private MsgBin msgEnCours;
    private boolean valideInt;
    
	public Lecture ( InputStream in )
	{
		this.in = in;
		valideInt=false;
//		buffer = new byte[1024];
		msgEnCours=new MsgBin();
	}

	public void serialEvent(SerialPortEvent arg0) {
		int icar=-1;
		try {
			icar = in.read();
		} catch (IOException e1) {
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
					if (msgEnCours.getInd()<Global.NB_MAX_VALEUR){ // si on a pas ateint la limite des valeur sur une ligne
						msgEnCours.ajout(temp); // on ajoute la valeur saisie au tableau
						// on reinitialise les variables
						temp=0;
						valideInt=false;
					}
					else{ // sinon on a depasser la limite
						System.out.println("limite des valeur sur une ligne depasser ");
					}
				}
				if(car=='\r'){ // si c'est un retour chario
					if (msgEnCours.isValid()) // si le crc est correcte
					{
						System.out.println(Msg.rechercheParMsgIn(msgEnCours));
						msgEnCours=new MsgBin();// on recree une nouvelle lecture en cours
					}else{ // sinon le crc n'est pas correcte
						System.out.println("message nok");
						System.out.println(msgEnCours);
						msgEnCours=new MsgBin();
					}

				}
			}
		}            
	}
}

