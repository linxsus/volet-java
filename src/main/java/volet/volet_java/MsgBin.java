package volet.volet_java;

public class MsgBin {
    private int ind;
    private int data[];
    
    MsgBin(){
    	ind=0;
    	data=new int[Global.NB_MAX_VALEUR];
    }
    
	public boolean isValid() {
	    return Crc.calcul(data, ind-1)==data[ind-1];// le ind-1 de Crc.calcul est pour ne pas prendre en compte le crc dans le message
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
	
	public String toString() {
		String str="";
		for (int i=0;i<ind;i++) {
			str+=data[i]+" ";
		}
		str+="\r\n";
		return str;
	}
	
}
