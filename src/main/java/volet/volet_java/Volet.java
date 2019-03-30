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


	/**
	 *  utile pour indiquer a l'object a quelle factory (contexte) il appartient
	 */
	FactoryXG factory;




	/**
	 * constructeur
	 * 
	 * @param relaisD N° du relais pour une descente
	 * @param relaisM N° du relais pour une monter
	 * @param entreeD N° de l'entree qui fait une demande de descente
	 * @param entreeM N° de l'entree qui fait une demande de monter 
	 * @param tempsM temps en ms pour faire une monter
	 * @param tempsD temps en ms pour faire une descente
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
	 * permet de positionner le volet a un % expl setPosyion(50); met le volet a mis hauteur. 
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		//TODO il faut bouger le volet en fonction de la position voulue
		this.position = position;
	}

	/**
	 * descend le volet pendant (temp) ms
	 * @param temp temps en ms
	 */
	protected void monter(long temp) {
		stop();
		//TODO
		//activation du relais monter
		//creation d'un timer pour stop sur ce volet dans temp
		etat=1;
	}

	/**
	 * descend le volet pendant (temp) ms
	 * @param temp temps en ms
	 */
	protected void descendre(long temp) {
		stop();
		//TODO
		//activation du relais descente
		//creation d'un timer pour stop sur ce volet dans temp
		etat=2;
	}

	/**
	 * retourn le n° du relais  sur le arduino pour faire une descente
	 * @return the relaisD
	 */
	public int getRelaisD() {
		return relaisD;
	}

	/**
	 * retourn le n° du relais  sur le arduino pour faire une monter
	 * @return the relaisM
	 */
	public int getRelaisM() {
		return relaisM;
	}

	/**
	 * retourne le N° de l'entree sur le arduino pour lancer une descente
	 * @return the entreeD
	 */
	public int getEntreeD() {
		return entreeD;
	}

	/**
	 * retourne le N° de l'entree sur le arduino pour lancer une monter
	 * @return the entreeM
	 */
	public int getEntreeM() {
		return entreeM;
	}

	/**
	 * retourne le temps en ms pour une monter complete du volet
	 * @return the tempsM
	 */
	public long getTempsM() {
		return tempsM;
	}

	/**
	 * retourne le temps en ms pour une descente complete du volet
	 * @return the tempsD
	 */
	public long getTempsD() {
		return tempsD;
	}

	/**
	 * retourn la potion du volet en pourcentage 
	 * expl si le volet est a mis hauteur on retourn 50
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * retourn l'id de l'object
	 * @return l'id 
	 */
	public int getID() {
		return factory.getId(this);
	}


	/**
	 * @return the etat
	 */
	public int getEtat() {
		return etat;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Volet [relaisD=" + relaisD + ", relaisM=" + relaisM + ", entreeD=" + entreeD + ", entreeM=" + entreeM
				+ ", tempsM=" + tempsM + ", tempsD=" + tempsD + ", position=" + position + "]";
	}
}
