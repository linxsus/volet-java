package volet.volet_java.mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import volet.volet_java.Lecture;
import volet.volet_java.Serie;
/**
 * Mock pour simuler une connexion au arduino.
 * utile pour les test de lecture et ecriture
 * 
 */
public class SerieMock extends Serie {
	
	private String message;
	/**
	 * createur avec comme parametere le message que l'on veut lire sur le in du getIn()
	 * 
	 * @param messageIn message que l'on est veut lire sur le in du getIn()
	 * @throws NoSuchPortException
	 * @throws UnsupportedCommOperationException
	 * @throws IOException
	 * @throws TooManyListenersException
	 * @throws PortInUseException
	 */
	public SerieMock(String messageIn) throws NoSuchPortException,
			UnsupportedCommOperationException, IOException, TooManyListenersException, PortInUseException {
		super( 0, null);
		byte[] data = messageIn.getBytes();
        in = new ByteArrayInputStream(data);
        message=messageIn;
	}

	/* (non-Javadoc)
	 * @see volet.volet_java.Serie#init(int, java.lang.String)
	 */
	protected void init(int portSpeed,String portName) {
	   out = new ByteArrayOutputStream();
	}

	/**
	 * 
	 * @return the serialPort
	 */
	public SerialPort getSerialPort() {
		return new SerialPortMoc();
	}
	
	

	public InputStream getIn() {
		return in;
	}

	public OutputStream getOut() {
		return out;
	}
	
	public String envoie() {
		return new String(((ByteArrayOutputStream)out).toByteArray());
	}
	
	public void runLecture(Lecture lecture) {
		 for (int i=0;i<message.length();i++) {
		        lecture.serialEvent(null);
		        }	
	
	}
}
