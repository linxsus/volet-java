package volet.volet_java;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * class pour l'ecriture vers le arduino
 * -ajout du crc
 * -mise en forme
 *
 * @author phenom
 *
 */
public class Ecriture extends Thread {

    /**
     * le out du port serie ou on envie les messages
     */
    private OutputStream outSerie;
    LinkedTransferQueue<MsgBin> outQueue;

    /**
     * le constructeur
     * 
     * @param outQueue
     * 
     * @param serie
     * @param outQueue
     */
    public Ecriture(Serie serie, LinkedTransferQueue<MsgBin> outQueue) {
        // permet de nomer le thread
        super("ecriture");
        this.outQueue = outQueue;
        this.outSerie = serie.getOut();
    }

    /**
     * fonction pour formater un string en msgBin et l'envoyer au arduino
     * 
     * 
     * @param str message a envoyer
     */
    // cette fonction ne devrait pas forcement etre ici
    // plus dans msgBin ou dans un class a part.
    public void serialEvent(String str) {
        MsgBin msgEnCours = new MsgBin();
        msgEnCours.ajout(str);
        // on envoie le message
        serialEvent(msgEnCours);
    }

    /**
     * envoie du msgBin au arduino.
     * 
     * @param msgBin
     */
    public void serialEvent(MsgBin msgBin) {

        // on transforme le tout en string data
        String msg = msgBin.toStringData();

        // on envoie le message vers le arduino
        for (int i = 0; i < msg.length(); i++) {
            try {
                this.outSerie.write(msg.charAt(i));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                // gerer l'interuption break.
                e.printStackTrace();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        while (true) {
            try {
                serialEvent(outQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
                break; // sort de la boucle while
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        try {
            outSerie.close(); // Fermeture du flux si l'interruption n'a pas fonctionné.
        } catch (IOException e) {
        }
    }
}
