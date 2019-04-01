package volet.volet_java.moc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import volet.volet_java.FactoryXG;
import volet.volet_java.Serie;

public class SerieMoc extends Serie {
	
	private String message;
	private FactoryXGMoc factory;

	public SerieMoc(FactoryXGMoc factory,String messageIn) throws NoSuchPortException,
			UnsupportedCommOperationException, IOException, TooManyListenersException, PortInUseException {
		super(factory, 0, null);
		byte[] data = messageIn.getBytes();
        in = new ByteArrayInputStream(data);
        message=messageIn;
        this.factory=factory;
	}

	protected void init(int portSpeed,String portName) {
	   out = new ByteArrayOutputStream();
	}

	/**
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
	
	public void runLecture() {
		 for (int i=0;i<message.length();i++) {
		        factory.getLecture().serialEvent(null);
		        }	
	
	}
}
