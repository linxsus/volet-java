package org.openhab.binding.volet.internal.arduino;

public class Gestionaire {
    /**
     * variable
     * -lectureSerie
     * -tampon
     * -valeurAttendue (valeur,volet)
     *
     */

    /**
     * boucle principal{
     * si (valeurAtendue n'est pas vide){
     * lectureSerie=lecture sur poolLectureSerie (lecture avec un temps et si temps depasser erreur)
     * si (lectureSerie==valeurAttendue.valeur){
     * volet.next();
     * }sinon{
     * ajout a tampon de lectureSerie (si tampon depasse une limite erreur)
     * }
     * }sinon{
     * si (tampon n'est pas vide){
     * executer la valeur de tampon
     * } sinon{
     * lecture sur poolLectureSerie et execution
     * }
     * }
     * }
     */
}
