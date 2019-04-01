package volet.volet_java;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.LinkedTransferQueue;

import gnu.io.SerialPortEventListener;
import volet.volet_java.aCoder.Gestion;
import volet.volet_java.var.Global;

/**
 * factory
 * 
 * a utiliser pour la creation d'object
 * :factory.newVolet(...)
 * 
 * permet de gerer la liste des object cree
 * -on peut avoir la liste d'un type d'objects
 * : ArrayList<Volet> volets=factory.objectDeType(Volet.class);
 * -un newXXX d'un object "unique" renvoie l'object existant. 
 * 
 * @author phenom
 *
 */
/**
 * @author phenom
 *
 */
public class FactoryXG {

	/**
	 * liste des objects cree
	 */
	protected ArrayList<Object> objects;
	/**
	 * liste des erreur
	 */
	// utilistaton d'un LinkedTransferQueue pour etre thread et gerer le bloquage d'un thread
	protected LinkedTransferQueue<Exception> erreur;


	/**
	 *  creation du factory	  
	 *  
	 */
	public FactoryXG() {
		super();
		objects=new ArrayList<Object>();
		erreur= new LinkedTransferQueue<Exception>();
	}

	/**
	 * creation d'un object Volet
	 * 
	 @param relaisD N° du relais sur arduino a activer pour faire descendre le volet
	 * @param relaisM N° du relais sur arduino a activer pour faire monter le volet
	 * @param entreeD entree sur le arduino pour demander la descente
	 * @param entreeM entree sur le arduino pour demander la monter
	 * @param tempsM temps pour que le volet monter
	 * @param tempsD temps pour que le volet descende
	 * @return retourne l'object cree
	 */
	public Volet newVolet(int relaisD,int relaisM,int entreeD,int entreeM,long tempsM,long tempsD) {
		// creation de l'object
		Volet volet=new Volet(this,relaisD,relaisM,entreeD,entreeM,tempsM,tempsD);
		// sauvegarde de l'object dans la base d'object.
		objects.add( volet);
		return volet;
	}

	/**
	 * creation d'un nouvelle object Serie avec les parametre par default
	 * object unique (si il a deja ete cree on renvoie l'object existant).
	 * @return l'object cree
	 */
	public Serie getSerie() {
		// TODO object unique
		// creation de l'object
		return getSerie(Global.portSpeed,Global.portName);
	}

	//TODO comentaitre
	public Lecture getLecture() {
		// c'est un object unique
		// si il existe on le recupere 
		Lecture lecture=ifExiste(Lecture.class);
		// sinon on le cree
		if (lecture==null) {
			// on recupere le inputstream du port serie
			InputStream in=getSerie().getIn();
			// on cree l'object avec le on l'joute a la base
			lecture=new Lecture(this,in);
			objects.add(lecture);
		}
		return lecture;
	}

	//TODO comentaitre
		public Ecriture getEcriture() {
			// c'est un object unique
			// si il existe on le recupere 
			Ecriture ecriture=ifExiste(Ecriture.class);
			// sinon on le cree
			if (ecriture==null) {
				// on recupere le inputstream du port serie
				OutputStream out=getSerie().getOut();
				// on cree l'object avec le on l'joute a la base
				ecriture=new Ecriture(this,out);
				objects.add(ecriture);
			}
			return ecriture;
		}
	
	//TODO comentaire
	public Gestion getGestion() {
		Gestion gestion=new Gestion(this);
		objects.add(gestion);
		return gestion;
	}



	/**
	 * retourn l'id de l'object 
	 * @param object object d'on on veut l'id
	 * @return id de l'object
	 */
	public int getId(Object object) {
		return objects.indexOf(object);
	}

	/**
	 * recupere la liste erreur
	 * 
	 * une erreur bloquante doit etre ajouter par un  erreur.transfer();
	 * une erreur non bloquante doit etre ajouter par un  erreur.offer();
	 * 
	 * @return the erreur (LinkedTransferQueue)
	 */
	public LinkedTransferQueue<Exception> getErreur() {
		return erreur;
	}


	/**
	 * retourne la liste des object cree par le factory qui sont du type fourni en parametre (*.class)
	 * 
	 * expl d'utilisation
	 * ArrayList<Volet> volets=factory.objectDeType(Volet.class);
	 * 
	 * 
	 * @param type Nom d'une instance avec un .class a la fin
	 * @return un ArrayList du type fourni en parametre de la fonction.
	 */
	// suppresion de l'erreur de conversion car on fait une verification avant.
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> getObjectDeType(Class<T> type){
		ArrayList<T> resultat=new ArrayList<T>();
		for (Object item : objects) {
			if (type.isInstance(item))
			{
				resultat.add(((T)item));
			}
		}
		return resultat;
	};

	/**
	 * fonction qui verifie si un object de type (fornit en parametre) existe dans la base 
	 * si c'est le cas renvoie le premier object de cette liste 
	 * sinon renvoie null
	 * 
	 * cette fonction est utiliser pour des object que l'on veut unique 
	 * 
	 * @param type Nom d'une instance avec un .class a la fin
	 * @return un object du type fourni en parametre de la fonction ou null.
	 */
	public <T> T ifExiste(Class<T> type) {
		ArrayList<T> object=getObjectDeType(type);
		if (object.isEmpty())
		{
			return null;
		}
		return object.get(0);
	}


	/**
	 *  creation d'un nouvelle object Serie avec les parametre fourni en parametere
	 * @param portSpeed vitesse du port serie (9600 par default)
	 * @param portName nom du port serie (exp: com4 pour windows, /dev/ttyusb0 pour  
	 * @return l'object cree
	 */
	protected Serie getSerie (int portSpeed,String portName)
	{
		// c'est un object unique
		// si il existe on le recupere 
		Serie serie=ifExiste(Serie.class); 
		// sinon on le cree
		if (serie==null) {
			try {
				serie = new Serie(this,portSpeed,portName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// sauvegarde de l'object dans la base d'object.
			objects.add( serie);
		}
		return serie;
	
	}
}
