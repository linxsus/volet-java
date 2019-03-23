package volet.volet_java;


public class Volet {

	/**
	 * N� du relais sur arduino a activer pour faire descendre le volet
	 */
	private int relaisD;
	/**
	 * N� du relais sur arduino a activer pour faire descendre le volet
	 */
	private int relaisM;
	
	/**
	 * entree sur le arduino pour demander la descente
	 */
	private int entreeD;
	
	/**
	 * entree sur le arduino pour demander la monter
	 */
	private int entreeM;
	
	/**
	 * temps pour que le volet descende
	 */
	private long tempsM;
	/**
	 *  temps pour que le volet monte
	 */
	private long tempsD;
	/**
	 *  position du volet 0= en bas 100=en haut
	 */
	private int position;
	/**
	 * 0= arret ,1= monter , 2= descente
	 */
	private int etat; 
    FactoryXG factory; 
	
	
	 /**
	 * @param a
	 * @param b
	 * @return
	 */
	private int pourcentage(int a,int b){
	        //TODO
		return 0;
	    }

	/**
	 * constructeur
	 * 
	 * @param factory factory utile pour la gestion des object.
	 * @param relaisD N° du relais sur arduino a activer pour faire descendre le volet
	 * @param relaisM N° du relais sur arduino a activer pour faire monter le volet
	 * @param entreeD entree sur le arduino pour demander la descente
	 * @param entreeM entree sur le arduino pour demander la monter
	 * @param tempsM temps pour que le volet monter
	 * @param tempsD temps pour que le volet descende
	 */
	public Volet(FactoryXG factory,int relaisD,int relaisM,int entreeD,int entreeM,long tempsM,long tempsD) { // TODO rajouter le factory
		super();
		this.factory=factory;
		this.relaisD=relaisD;
		this.relaisM=relaisM;
		this.entreeD=entreeD;
		this.entreeM=entreeM;
		this.tempsD=tempsD;
		this.tempsM =tempsM;
		etat=0;
		// TODO attention ici possible pb de securiter et on n'attend pas que le volet soit reelement en bas donc pas tres fiable il faudra trouver mieux
		descendre();//je descend le volet pour connaitre sa position
		position=0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Volet [relaisD=" + relaisD + ", relaisM=" + relaisM + ", entreeD=" + entreeD + ", entreeM=" + entreeM
				+ ", tempsM=" + tempsM + ", tempsD=" + tempsD + ", position=" + position + "]";
	}

	/**
	 *  monte le volet tout en haut ou jusqu'a un stop
	 */
	public void monter() {
		monter(tempsM*2); // un *2 pour etre sure que le volet sera bien monter 
	}
	;
	
	/**
	 * descend le volet tout en bas ou jusqu'a un stop
	 */
	public void descendre() {
		descendre(tempsD*2);// un *2 pour etre sure que le volet sera bien descendu
	}

	/**
	 * arrete la monter ou la descente du volet
	 */
	public void stop() {
		//TODO
		//desactivation des 2 relais
		//desactivation de tout timer stop sur ce volet
		etat=0;
	}

	
	/**
	 * descend le volet pendant (temp) ms
	 * @param temp
	 */
	protected void monter(long temp) {
		//TODO
		//desactivation de tout timer stop sur ce volet
		//desactivation du relais descente
		//activation du relais monter
		//creation d'un timer pour stop sur ce volet dans temp
		etat=1;
	}
	
	/**
	 * descend le volet pendant (temp) ms
	 * @param temp
	 */
	public void descendre(long temp) {
		//TODO
		//desactivation de tout timer stop sur ce volet
		//desactivation du relais monter
		//activation du relais descente
		//creation d'un timer pour stop sur ce volet dans temp
		etat=2;
	}
	
	/**
	 * 
	 * @return the relaisD
	 */
	public int getRelaisD() {
		return relaisD;
	}

	/**
	 * @return the relaisM
	 */
	public int getRelaisM() {
		return relaisM;
	}

	/**
	 * @return the entreeD
	 */
	public int getEntreeD() {
		return entreeD;
	}

	/**
	 * @return the entreeM
	 */
	public int getEntreeM() {
		return entreeM;
	}

	/**
	 * @return the tempsM
	 */
	public long getTempsM() {
		return tempsM;
	}

	/**
	 * @return the tempsD
	 */
	public long getTempsD() {
		return tempsD;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		//TODO il faut bouger le volet en fonction de la position voulue
		this.position = position;
	}
	;
}

