package volet.volet_java.mock;

import java.util.Random;
import java.util.concurrent.LinkedTransferQueue;

import volet.volet_java.FactoryXG;

public class GestionErreurMoc extends Thread {

	private LinkedTransferQueue<Exception> erreur;
	private FactoryXG factory;

	public GestionErreurMoc(FactoryXG factory){
		this.factory=factory;
		erreur=factory.getErreur();
	}

	public void run(){
		//NoSuchPortException
	}      

}
