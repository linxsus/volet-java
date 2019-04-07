package volet.volet_java;


import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class Main {

	private static Scanner sc;
	private static FactorySerie factory;
	public Main()
	{
		super();
	}

	public static void main ( String[] args )
	{
		factory=new FactorySerie(9600,"COM4");
		sc = new Scanner(System.in);
		// TODO a recree corectement
		LinkedTransferQueue<MsgBin> out=factory.getOut();
		while (true) {
			String str = sc.nextLine();
			MsgBin msg=new MsgBin();
			msg.ajout(str);
			out.offer(msg);
		}
	}
	

}
