package volet.volet_java.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import gnu.io.NRSerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class SerialPortMoc extends NRSerialPort {

    public SerialPortMoc(String port, int baud) {
        super(port, baud); // TODO Auto-generated constructor stub
    }

    public void setOutputBufferSize(int arg0) {
        // TODO Auto-generated method stub

    }

    public void setInputBufferSize(int arg0) {
        // TODO Auto-generated method stub

    }

    public boolean isReceiveTimeoutEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isReceiveThresholdEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isReceiveFramingEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    public int getReceiveTimeout() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getReceiveThreshold() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getReceiveFramingByte() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public OutputStream getOutputStream() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getOutputBufferSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public InputStream getInputStream() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getInputBufferSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void enableReceiveTimeout(int arg0) throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub

    }

    public void enableReceiveThreshold(int arg0) throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub

    }

    public void enableReceiveFraming(int arg0) throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub

    }

    public void disableReceiveTimeout() {
        // TODO Auto-generated method stub

    }

    public void disableReceiveThreshold() {
        // TODO Auto-generated method stub

    }

    public void disableReceiveFraming() {
        // TODO Auto-generated method stub

    }

    public boolean setUARTType(String arg0, boolean arg1) throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    public void setSerialPortParams(int arg0, int arg1, int arg2, int arg3) throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub

    }

    public void setRTS(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public boolean setParityErrorChar(byte arg0) throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean setLowLatency() throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    public void setFlowControlMode(int arg0) throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub

    }

    public boolean setEndOfInputChar(byte arg0) throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean setDivisor(int arg0) throws UnsupportedCommOperationException, IOException {
        // TODO Auto-generated method stub
        return false;
    }

    public void setDTR(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public boolean setCallOutHangup(boolean arg0) throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean setBaudBase(int arg0) throws UnsupportedCommOperationException, IOException {
        // TODO Auto-generated method stub
        return false;
    }

    public void sendBreak(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeEventListener() {
        // TODO Auto-generated method stub

    }

    public void notifyOnRingIndicator(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public void notifyOnParityError(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public void notifyOnOverrunError(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public void notifyOnOutputEmpty(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public void notifyOnFramingError(boolean arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyOnDataAvailable(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public void notifyOnDSR(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public void notifyOnCarrierDetect(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public void notifyOnCTS(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public void notifyOnBreakInterrupt(boolean arg0) {
        // TODO Auto-generated method stub

    }

    public boolean isRTS() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isRI() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isDTR() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isDSR() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isCTS() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isCD() {
        // TODO Auto-generated method stub
        return false;
    }

    public String getUARTType() throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return null;
    }

    public int getStopBits() {
        // TODO Auto-generated method stub
        return 0;
    }

    public byte getParityErrorChar() throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getParity() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean getLowLatency() throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    public int getFlowControlMode() {
        // TODO Auto-generated method stub
        return 0;
    }

    public byte getEndOfInputChar() throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getDivisor() throws UnsupportedCommOperationException, IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getDataBits() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean getCallOutHangup() throws UnsupportedCommOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    public int getBaudRate() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getBaudBase() throws UnsupportedCommOperationException, IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void addEventListener(SerialPortEventListener arg0) throws TooManyListenersException {
        // TODO Auto-generated method stub

    }

}
