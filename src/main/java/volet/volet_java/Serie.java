package volet.volet_java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;




public class Serie {

	protected InputStream in;
	protected OutputStream out;
	protected SerialPort serialPort;

	
	/**
	 * @return the serialPort
	 */
	public SerialPort getSerialPort() {
		return serialPort;
	}


	public Serie (int portSpeed,String portName) throws NoSuchPortException, UnsupportedCommOperationException, IOException, TooManyListenersException, PortInUseException
	{
		in=null;
		out=null;
		serialPort=null;
		init(portSpeed,portName);
		
	}  
	
	protected void init(int portSpeed,String portName) throws NoSuchPortException, UnsupportedCommOperationException, IOException, TooManyListenersException, PortInUseException{
		// on recupere le port com si il exist sinon une erreur NoSuchPortException
				CommPortIdentifier portIdentifier=null;	
				portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
				// on demande le port com si selui ci est disponible sinon erreur PortInUseException
				CommPort commPort=null;
				commPort = portIdentifier.open(this.getClass().getName(),2000);
				
				// on ouvre le port com si cela echoue erreur UnsupportedCommOperationException
				serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(portSpeed,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
				// on ouvre les stream de comunication in et out si sela echou erreur IOException
				in = serialPort.getInputStream();
				out = serialPort.getOutputStream();
	}


	public InputStream getIn() {
		return in;
	}

	public OutputStream getOut() {
		return out;
	}
}


