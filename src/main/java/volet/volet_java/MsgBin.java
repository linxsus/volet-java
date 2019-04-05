package volet.volet_java;

import volet.volet_java.var.Global;
import volet.volet_java.var.MsgEnum;



//TODO gestion du crc optionel lors de l'ajout
// revoir la fonction ajout string 
/**
 * class pour formater et imprimer les message de comunication avec le arduino
 * 
 * @author xavier
 *
 */
public class MsgBin {
	
    /**
     *  nb d'element dans data
     */
    private int length;
    
    /**
     * le data 
     */
    private int data[];
    /**
     * le crc
     */
    private int crc;
    
    /**
     * le constructeur
     */
    MsgBin(){
    	length=0;
    	crc=0;
    	data=new int[Global.NB_MAX_VALEUR];
    }
    
	/**
	 * verifie si le crc est corecte pour le message
	 * @return true si on a un bon crc false sinon
	 */
	public boolean isValid() {
		if (length>0) {
	    return Crc.calcul(data, length)==crc;// le ind-1 de Crc.calcul est pour ne pas prendre en compte le crc dans le message
		}
		return false;
	}
	
	/**
	 * retourn le nb d'element 
	 * @return le nb d'element
	 */
	public int length() { 
		return length;
	}

	/**
	 * retourne le tableau de data 
	 * @return le tableau de data
	 */
	public int[] getData() {
		return data;
	}
	
	/**
	 * ajout un int au data
	 * @param data un int a jouter au data
	 */
	public boolean ajout(int data) {
		// le -1 sert pour le crc
		if (length<this.data.length-1) {
		this.data[length] = data;
		length++;
		return true;
		}
		return false;
	}
	
	// TODO test non fait
	/**
	 * ajout un msgEnum au data 
	 * doit etre le 1er data ajouter
	 * @param msg msgEnum a ajouter
	 */
	public boolean ajout(MsgEnum msg) {
		if (length==0) { // on verifie qu'on est bien sur le 1er data
		  return ajout(msg.toInt());
		}
		return false;
	}
	
	
	/**
	 * ajoute le crc au message
	 */
	public void ajoutCrc() {
		crc=Crc.calcul(data,length);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str;
		// on commance toujours par un MsgEnum
		str=MsgEnum.rechercheParMsgIn(data[0]).toString();
		for (int i=1;i<length;i++) {
			str+=data[i]+" ";
		}
		return str;
	}
	
	/**
	 * retorune le message au format arduino
	 * @return string au format arduino
	 */
	public String toStringData() {

		String str="";
		// pour chaque data present
		for (int i=0;i<length;i++) {
			str+=data[i]+" ";
		}
		// on rajoute le crc et le retour chariot
		str+=crc+" \r\n";
		return str;
	}
	
	/**
	 * fonction pour formater un string
	 * 
	 * 
	 * @param str message a formater
	 */
	public void ajout(String str,boolean enableCrc) {
		boolean limiteDepasse=false;
		//int icar=-1; // caractere en cours
		int temp=0; // nb en cours
		boolean valideInt=false;
		
		// on formate le message
		str+=" "; // utile pour bien prendre en compte la derniere valeur
		// pour chaque caractere 
		for(int i=0;i<str.length();i++){ 			
			char car=str.charAt(i);
			if (car>='0' && car<='9') // si c'est un nombre
			{
				temp=(10*temp)+(car-'0'); // calcul le nombre envoyer
				valideInt=true; // on valide le faite qu'il y ai bien un nombre
			}else { //sinon
				if (valideInt==true){ // si il y a eu un nombre
					if (length<Global.NB_MAX_VALEUR-1){ // si on a pas ateint la limite des valeur sur une ligne
						ajout(temp); // on ajoute la valeur saisie au tableau
						// on reinitialise les variables
						temp=0;
						valideInt=false;
					}
					else{ // sinon on a depasser la limite
						if (!limiteDepasse) { // on affiche le message qu'une seul fois
							// TODO ici une sortie sur le perifierique standard ce n'est pas tres jolie. 
							// pensser a modifier les test testSerialEvent_5() a testSerialEvent_7() si on change.
							System.out.println("limite des valeur sur une ligne depasser ");
							limiteDepasse=true;
						}
					}
				}
			}
		} 
	}
}
