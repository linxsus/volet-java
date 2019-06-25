package org.openhab.binding.volet.internal.volet;

public class VoletBidon {
    /*
     * variable
     * poolDesChoseAfaire (fonction) fifo
     * Gestionaire
     * poolecritureArduino
     * Thing
     *
     *
     * fonction activationRelais(rel){
     * ecriture sur poolecritureArduino
     * creation du message a verifier
     * ajouter le message au gestionaire
     * }
     *
     * next(){
     * si il a des chose a faire dans poolDesChoseAfaire {
     * executer la 1er fonction de poolDesChoseAfaire
     * }
     * }
     *
     * a chaque fonction stop ou monter il y a une fonction privee realStop et une stop
     *
     * realstop(){
     * ajouter (desactiver relais(Monter))
     * ajouter (desactiver relais(Desendre))
     * }
     *
     * stop(){
     * realstop();
     * mise a jour de thing
     * mise a jour des variable interne a volet
     * next();
     * }
     *
     * il faut aussi des fonction de mise a jour des valeur interne
     *
     *
     */

}
