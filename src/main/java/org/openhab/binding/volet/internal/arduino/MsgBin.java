package org.openhab.binding.volet.internal.arduino;

//TODO gestion du crc optionel lors de l'ajout
// revoir la fonction ajout string
/**
 * class pour formater et imprimer les message de comunication avec le arduino
 *
 * @author xavier
 *
 */
class MsgBin {

    /**
     * nb d'element dans data
     */
    private int length;

    /**
     * le data
     */
    private int data[];
    /**
     * le crc
     */
    private int crc;

    /**
     * le constructeur
     */
    public MsgBin() {
        length = 0;
        crc = 0;
        data = new int[Global.NB_MAX_VALEUR];
    }

    /**
     * verifie si le crc est corecte pour le message
     *
     * @return true si on a un bon crc false sinon
     */
    public boolean isValid() {
        if (length > 0) {
            return Crc.calcul(data, length) == crc;// le ind-1 de Crc.calcul est pour ne pas prendre en compte le crc
                                                   // dans le message
        }
        return false;
    }

    /**
     * retourn le nb d'element
     *
     * @return le nb d'element
     */
    public int length() {
        return length;
    }

    /**
     * retourne le tableau de data
     *
     * @return le tableau de data
     */
    public int[] getData() {
        data[length] = crc;
        return data;
    }

    /**
     * ajout un int au data
     *
     * @param data un int a jouter au data
     */
    synchronized public boolean ajout(int data) {
        return ajout(data, true);
    }

    /**
     * ajout un int au data
     *
     * @param data un int a jouter au data
     */
    synchronized public boolean ajout(int data, boolean enableCrc) {
        int limite = enableCrc ? Global.NB_MAX_VALEUR - 1 : Global.NB_MAX_VALEUR;
        if (length < limite) {
            this.data[length] = data;
            length++;
            if (enableCrc) {
                setCrc();
            }
            return true;
        }
        return false;
    }

    // TODO test non fait
    /**
     * ajout un msgEnum au data
     * doit etre le 1er data ajouter
     *
     * @param msg msgEnum a ajouter
     */
    synchronized public boolean ajout(MsgEnum msg) {
        if (length == 0) { // on verifie qu'on est bien sur le 1er data
            return ajout(msg.toInt());
        }
        return false;
    }

    /**
     * fonction pour formater un string et l'ajouter au data
     *
     * le string doit etre du style "XX XX XX" si enableCrc est a true
     * ou du style "xx xx xx crc" si enableCrc est false
     *
     * @param str message a formater
     *
     */
    synchronized public boolean ajout(String str) {
        return ajout(str, true);
    }

    /**
     * fonction pour formater un string et l'ajouter au data
     *
     * le string doit etre du style "XX XX XX" si enableCrc est a true
     * ou du style "xx xx xx crc" si enableCrc est false
     *
     * @param str       message a formater
     * @param enableCrc a true le crc est caluler automatiquement a false le dernier ajout est le crc
     */
    synchronized public boolean ajout(String str, boolean enableCrc) {
        boolean ok = true;
        // int icar=-1; // caractere en cours
        int temp = 0; // nb en cours
        boolean valideInt = false;
        int limite = enableCrc ? Global.NB_MAX_VALEUR - 1 : Global.NB_MAX_VALEUR;
        // on formate le message
        str += " "; // utile pour bien prendre en compte la derniere valeur
        // pour chaque caractere
        for (int i = 0; i < str.length(); i++) {
            char car = str.charAt(i);
            if (car >= '0' && car <= '9') // si c'est un nombre
            {
                temp = (10 * temp) + (car - '0'); // calcul le nombre envoyer
                valideInt = true; // on valide le faite qu'il y ai bien un nombre
            } else { // sinon
                if (valideInt == true) { // si il y a eu un nombre
                    if (length < limite) { // si on a pas ateint la limite des valeur sur une ligne
                        ok &= ajout(temp, enableCrc); // on ajoute la valeur saisie au tableau et si on n'a pas reussit
                                                      // passe ok a false
                        // on reinitialise les variables
                        temp = 0;
                        valideInt = false;
                    } else { // sinon on a depasser la limite
                        ok = false;
                    }
                }
            }
        }
        if (enableCrc) {
            setCrc();
        } else {
            if (length > 0) {
                length--;
                crc = data[length];
            }
        }
        return ok;
    }

    /**
     * calcul et joute le crc au message
     */
    synchronized public void setCrc() {
        crc = Crc.calcul(data, length);
    }

    /**
     * ajoute le crc au message
     *
     * @param crc
     */
    synchronized public void setCrc(int crc) {
        this.crc = crc;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String str;
        // on commance toujours par un MsgEnum
        str = MsgEnum.rechercheParMsgIn(data[0]).toString();
        for (int i = 1; i < length; i++) {
            str += data[i] + " ";
        }
        return str;
    }

    /**
     * retourne le message au format arduino
     *
     * @return string au format arduino
     */
    public String toStringData() {

        String str = "";
        // pour chaque data present
        for (int i = 0; i < length; i++) {
            str += data[i] + " ";
        }
        // on rajoute le crc et le retour chariot
        str += crc + " \r\n";
        return str;
    }
}
