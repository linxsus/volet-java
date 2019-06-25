package org.openhab.binding.volet.internal.arduino;


public enum MsgEnum {
	//Objets directement construits
	MSG_TEST(3000, "test"),
	MSG_CREATION_COMUNICATION(3001, "debug creation Comunication"),
	MSG_DESTRUCTION_COMUNICATION(3002, "debug destruction Comunication"),
	MSG_INIT_START(3003, "debug init start"),
	MSG_INIT_FIN(3004, "debug init ok"),
	MSG_CREATION_SORTIE(3005, "debug creation Sortie"),
	MSG_DESTRUCTION_SORTIE(3006, "debug destruction Sortie "),
	MSG_ECRITURE_SORTIE(3007, "debug ecriture  Sortie "),
	MSG_CREATION_ENTREE(3008, "debug creation Entree avec un nb de 74HC595 :  "),
	MSG_DESTRUCTION_ENTREE(3009, "debug destruction Entree "),
	MSG_CREATION_PROM(3010, "debug creation Prom"),
	MSG_DESTRUCTION_PROM(3011, "debug destruction Prom "),
	MSG_ECRITURE_ES(3012, "debug eciture dans la prom de la ligne "),
	MSG_ECRITURE_ADRESSE(3013, "debug eciture dans la prom a l'adresse "),
	// message important
	MSG_ACTIVATION_SORTIE(1000, "activation  Sortie "),
	MSG_DESACTIVATION_SORTIE(1001, "desactivation  Sortie "),
	MSG_LECTURE_SORTIE(1002, "lecture  Sortie [numero du 74HC165, valeur du 74HC165] :"),
	MSG_LECTURE_ENTREE(1003, "lecture  Entree [numero du 74HC595, valeur du 74HC595] :"),
	MSG_ACTIVATION_ENTREE(1004, "activation de l'entree :"),
	MSG_DESACTIVATION_ENTREE(1005, "desactivation de l'entree :"),
	MSG_NB_74HC165(1006, "Nombre de 74hc165 :"),
	MSG_NB_74HC595(1007, "Nombre de 74hc595 :"),
	MSG_AFFICHAGE_ES(1008, "contenu de la prom [ x x ... ] n� de ligne  crc"),
	MSG_MEMOIRE_RESTANTE(1009, "memoire restante  [xx xx crc] "),
	// message erreur
	MSG_RESET(2000, "reset"),
	MSG_MSG_INCONNUE(2001, "message inconnue"),
	MSG_CRC_NOK(2002, " le crc n'est pas correcte il devrait etre "),	
	// message action
	RESET (0,"reset"),
	LEC_PROM (1,"lecture de la prom"),
	LEC_SORTIE (2,"lecture de la sortie"),
	LEC_ENTREE (3,"lecture des entree"),
	ACTIVATION_SORTIE (4,"activation de la sortie "),
	DESACTIVATION_SORTIE (5,"desactivation de la sortie "),
	ECRITURE_PROM (6,"ecriture de la prom"),
	MEMOIRE_RESTANTE (7,"memoire restante");


	private int intMsg = 0;
	private String stringMsg = "";


	//Constructeur
	MsgEnum(int intMsg, String stringMsg){
		this.intMsg = intMsg;
		this.stringMsg = stringMsg;
	}

	public static MsgEnum rechercheParMsgIn(int i){
		
		for(MsgEnum v : values()){
			if( v.intMsg==i){
				return v;
			}
		}
		return null;
	}

	public String toString(){
		return stringMsg;
	}
	
	public int toInt(){
		return intMsg;
	}
}




