package org.openhab.binding.volet.internal.arduino;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import gnu.io.NRSerialPort;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

public class Serie {

    protected InputStream in;
    protected OutputStream out;
    protected NRSerialPort serialPort;

    /**
     * @return the serialPort
     */
    public NRSerialPort getSerialPort() {
        return serialPort;
    }

    public Serie(int portSpeed, String portName) throws NoSuchPortException, UnsupportedCommOperationException,
            IOException, TooManyListenersException, PortInUseException {
        in = null;
        out = null;
        serialPort = null;
        init(portSpeed, portName);

    }

    protected void init(int portSpeed, String portName) throws NoSuchPortException, UnsupportedCommOperationException,
            IOException, TooManyListenersException, PortInUseException {
        // on recupere le port com si il exist sinon une erreur NoSuchPortException
        serialPort = new NRSerialPort(portName, portSpeed);
        serialPort.connect();

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
