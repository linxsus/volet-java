package volet.volet_java;


import java.util.Scanner;

public class Main {

	private static Scanner sc;
	private static FactoryXG factory;
	public Main()
	{
		super();
	}

	public static void main ( String[] args )
	{
		factory=new FactoryXG();
		sc = new Scanner(System.in);
		factory.getLecture();
		Ecriture out=factory.getEcriture();
		while (true) {
			String str = sc.nextLine();
			out.serialEvent(str);
		}
	}


}
