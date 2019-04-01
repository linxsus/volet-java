package volet.volet_java;

import volet.volet_java.var.Global;
import volet.volet_java.var.MsgEnum;

public class MsgBin {
    private int ind;
    private int data[];
    
    MsgBin(){
    	ind=0;
    	data=new int[Global.NB_MAX_VALEUR];
    }
    
	public boolean isValid() {
		if (ind>0) {
	    return Crc.calcul(data, ind-1)==data[ind-1];// le ind-1 de Crc.calcul est pour ne pas prendre en compte le crc dans le message
		}
		return false;
	}
	
	public int getInd() { 
		return ind;
	}

	public int[] getData() {
		return data;
	}
	
	public void ajout(int data) {
		if (ind<Global.NB_MAX_VALEUR-1) {
		this.data[ind] = data;
		}
		ind++;
	}
	
	// TODO test non fait
	public void ajout(MsgEnum msg) {
		ajout(msg.toInt());
	}
	
	
	// TODO test non fait
	public void ajoutCrc() {
		ajout(Crc.calcul(data,ind));
	}
	
	public String toString() {
		String str;
		str=MsgEnum.rechercheParMsgIn(data[0]).toString();
		for (int i=1;i<ind-1;i++) {
			str+=data[i]+" ";
		}
		//str+="\r\n";
		return str;
	}
	
	public String toStringData() {
		
		String str="";
		for (int i=0;i<ind;i++) {
			str+=data[i]+" ";
		}
		str+="\r\n";
		return str;
	}
}
