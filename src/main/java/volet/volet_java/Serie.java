package volet.volet_java;

import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;




public class Serie {
	
	private InputStream in;
	private OutputStream out;

	 Serie () throws Exception
	    {
	    	CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(Global.portName);
	        if ( portIdentifier.isCurrentlyOwned() )
	        {
	            System.out.println("Error: Port utiliser");
	        }
	        else
	        {
	            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
	            
	            if ( commPort instanceof SerialPort )
	            {
	                SerialPort serialPort = (SerialPort) commPort;
	                serialPort.setSerialPortParams(Global.portSpeed,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
	                
	                in = serialPort.getInputStream();
	                out = serialPort.getOutputStream();
	                
	                serialPort.addEventListener(new Lecture(in));
	                serialPort.notifyOnDataAvailable(true);
	                
	                
	            }
	            else
	            {
	                System.out.println("Error: Only serial ports are handled by this example.");
	            }
	        }     
	    }

	public InputStream getIn() {
		return in;
	}

	public OutputStream getOut() {
		return out;
	}
}


