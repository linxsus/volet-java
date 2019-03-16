package volet.volet_java;

public class FactoryXG {
	public Volet newVolet(int relaisD,int relaisM,int entreeD,int entreeM,long tempsM,long tempsD) {
		//recuperation de l'id object
		Volet volet=new Volet(this,relaisD,relaisM,entreeD,entreeM,tempsM,tempsD);
		// sauvegarde de l'object dans la base d'object.
		return volet;
	}

	private void add(Volet volet, Executable object) {
		// TODO Auto-generated method stub
	}
 
}
