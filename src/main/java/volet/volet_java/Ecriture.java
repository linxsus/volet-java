package volet.volet_java;

import java.io.IOException;
import java.io.OutputStream;


public class Ecriture {
	
	private OutputStream out;
	private FactoryXG factory;
    
	public Ecriture ( FactoryXG factory,OutputStream out )
	{
		this.factory=factory;
		this.out = out;
	}
	
	public void serialEvent(String str) {
		boolean limiteDepasse=false;
		int icar=-1;
		MsgBin msgEnCours=new MsgBin();
		int temp=0;
		boolean valideInt=false;
		str+=" "; // utile pour bien prendre en compte la derniere valeur
		for(int i=0;i<str.length();i++){ // si il y a bien un caractere
			icar = str.charAt(i);
			//System.out.print((char) icar);
			//System.out.print('t');
			char car=(char) icar;
			if (car>='0' && car<='9') // si c'est un nombre
			{
				temp=(10*temp)+(car-'0'); // calcul le nombre envoyer
				valideInt=true; // on valide le faite qu'il y ai bien un nombre
			}else { //sinon si c'est un espace
				if (valideInt==true){ // si il y a eu un nombre
					if (msgEnCours.getInd()<Global.NB_MAX_VALEUR-1){ // si on a pas ateint la limite des valeur sur une ligne
						msgEnCours.ajout(temp); // on ajoute la valeur saisie au tableau
						// on reinitialise les variables
						temp=0;
						valideInt=false;
					}
					else{ // sinon on a depasser la limite
						if (!limiteDepasse) {
							// TODO ici une sortie sur le perifierique standard ce n'est pas tres jolie. 
							// pensser a modifier les test testSerialEvent_5() a testSerialEvent_7() si on change.
							System.out.println("limite des valeur sur une ligne depasser ");
							limiteDepasse=true;
						}
					}
				}
			}
		} 
		msgEnCours.ajout(Crc.calcul(msgEnCours.getData(), msgEnCours.getInd())); // on ajoute le crc
		String msg=msgEnCours.toStringData(); // on transforme le tout en string
		
		//System.out.println(Msg.rechercheParMsgIn(msgEnCours)); // ici pour des test a modifier
		//System.out.println(msg); // 
		
		// on envoie le message vers le arduino
		for(int i=0;i<msg.length();i++){
            try {
				this.out.write(msg.charAt(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }    
	}
}
