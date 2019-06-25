package org.openhab.binding.volet.internal.arduino;

import java.io.IOException;
import java.util.TooManyListenersException;
import java.util.concurrent.LinkedTransferQueue;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

/**
 * classe qui permet de cree et de gerre la communication serie.
 * 
 * @author phenom
 *
 */
public class FactorySerie {
    /**
     * object serie
     */
    protected Serie serie;
    /**
     * object qui gere le thread lecture
     */
    protected Lecture lecture;
    /**
     * object qui gere le thread ecriture
     */
    protected Ecriture ecriture;
    /**
     * vitesse d'ecriture sur le port serie
     */
    protected int portSpeed;
    /**
     * port com utiliser
     */
    protected String portName;

    /**
     * liste des element a envoyer sur le port serie
     * 
     * * une erreur bloquante doit etre ajouter par un erreur.transfer();
     * une erreur non bloquante doit etre ajouter par un erreur.offer();
     * 
     */
    protected LinkedTransferQueue<MsgBin> in;
    /**
     * liste des element recue sur le port serie
     */
    protected LinkedTransferQueue<MsgBin> out;

    /**
     * cree un gestionaire du port serie portname a la vitesse portSpeed
     * 
     * @param portSpeed vitesse du port serie exp 9600
     * @param portName  nom du port serie exp com4 ou /dev/usb01
     */
    public FactorySerie(int portSpeed, String portName) {
        this.portSpeed = portSpeed;
        this.portName = portName;
        in = new LinkedTransferQueue<MsgBin>();
        out = new LinkedTransferQueue<MsgBin>();
        serie = null;
        lecture = null;
        ecriture = null;
        init();
    }

    /**
     * fonction de creation des objects utile au factory
     * dans une fonction pour pouvoir cree un mock
     */
    protected void init() {
        // creation de Serie
        initSerie();
        // creation de lecture
        initLecture();
        // creation de ecriture
        initEcriture();

    }

    protected void initEcriture() {
        // creation du thread ecriture.
        ecriture = new Ecriture(serie, out);
        ecriture.start();
    }

    /**
     * cree l'object serie
     */
    protected void initSerie() {
        try {
            serie = new Serie(portSpeed, portName);
        } catch (NoSuchPortException | UnsupportedCommOperationException | IOException | TooManyListenersException
                | PortInUseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void initLecture() {
        // ici car je voudrais bien cree un thread
        lecture = new Lecture(serie, in);
        try {
            serie.getSerialPort().addEventListener(lecture);
        } catch (TooManyListenersException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        serie.getSerialPort().notifyOnDataAvailable(true);
    }

    /**
     * @return the portSpeed
     */
    public int getPortSpeed() {
        return portSpeed;
    }

    /**
     * @param portSpeed the portSpeed to set
     */
    public void setPortSpeed(int portSpeed) {
        this.portSpeed = portSpeed;
    }

    /**
     * @return the portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * @param portName the portName to set
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

    /**
     * @return the in
     */
    public LinkedTransferQueue<MsgBin> getIn() {
        return in;
    }

    /**
     * @return the out
     */
    public LinkedTransferQueue<MsgBin> getOut() {
        return out;
    }

    /**
     * @return true si le reset a fonctionner
     */
    public boolean reset() {
        return true;
    }
}
