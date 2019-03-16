package volet.volet_java;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import gnu.io.SerialPortEvent;

public class Ecriture {
	
	private OutputStream out;
//	private byte[] buffer;
    private int temp;
    private MsgBin msgEnCours;
    private boolean valideInt;
    private Scanner sc;
    
	public Ecriture ( OutputStream out )
	{
		this.out = out;
		valideInt=false;
		//buffer = new byte[1024];
		msgEnCours=new MsgBin();
		sc=new Scanner(System.in);
	}
	public void serialEvent(SerialPortEvent arg0) {
		int icar=-1;
		String str = sc.nextLine();
		str+=" ";
		for(int i=0;i<str.length();i++){ // si il y a bien un caractere
			icar = str.charAt(i);
			//System.out.print((char) icar);
			char car=(char) icar;
			if (car>='0' && car<='9') // si c'est un nombre
			{
				temp=(10*temp)+(car-'0'); // calcul le nombre envoyer
				valideInt=true; // on valide le faite qu'il y ai bien un nombre
			}else { //sinon si c'est un espace ou un retour chariot
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
			}
		} 
		msgEnCours.ajout(Crc.calcul(msgEnCours.getData(), msgEnCours.getInd())); // on ajoute le crc
		String msg=msgEnCours.toString();
		System.out.println(Msg.rechercheParMsgIn(msgEnCours));
		System.out.println(msg);
		for(int i=0;i<msg.length();i++){
            try {
				this.out.write(msg.charAt(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }    
		msgEnCours=new MsgBin();// on recree une nouvelle lecture en cours
	}
}
