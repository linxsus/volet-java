package volet.volet_java;

import java.util.ArrayList;

public class FactoryXG {
	
	private ArrayList<Object> objects;
	
	public FactoryXG() {
		super();
		objects=new ArrayList<Object>();
	}
	
	public Volet newVolet(int relaisD,int relaisM,int entreeD,int entreeM,long tempsM,long tempsD) {
		// creation de l'object
		Volet volet=new Volet(this,relaisD,relaisM,entreeD,entreeM,tempsM,tempsD);
		// sauvegarde de l'object dans la base d'object.
		objects.add( volet);
		return volet;
	}

	private void add(Volet volet, Executable object) {
		// TODO Auto-generated method stub
	}

	public int getId(Object object) {
		// TODO Auto-generated method stub
		return objects.indexOf(object);
	}
 
}
