package volet.volet_java.mock;

import volet.volet_java.FactoryXG;
import volet.volet_java.Serie;

public class FactoryXGMock extends FactoryXG {

    /**
     * creation d'un nouvelle object qui simule la classe Serie
     * permet de faire des test sans arduino
     * 
     * @param message message que l'arduino devrait envoyer
     * @return l'object cree
     */
    public Serie getSerie(String message) {
        // c'est un object unique
        // si il existe on le recupere
        Serie serie = ifExiste(Serie.class);
        // sinon on le cree
        if (serie == null) {
            try {
                serie = new SerieMock(message);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // sauvegarde de l'object dans la base d'object.
            objects.add(serie);
        }
        return serie;

    }

    /**
     * creation d'un nouvelle object qui simule la classe Serie
     * permet de faire des test sans arduino
     * 
     * @param portSpeed pas utiliser
     * @param portName  pas utiliser
     * @return l'object cree
     */
    @Override
    protected Serie getSerie(int portSpeed, String portName) {
        return getSerie("");

    }

}
