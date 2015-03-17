package async.capteur.strategie;

import async.capteur.Capteur;
import javafx.scene.control.Label;

/**
 *
 */
public interface AlgoDiffusion {

    void configure(int nbAfficheur, int newValue);

    public void execute();

    void setCapteur(Capteur c);

    int getValue();

    /**
     *
     * @return vrai si la diffusion est terminé, faux sinon
     */
    boolean isDone();

    /**
     * to display the version
     * @param label
     */
    void setLabel(Label label);

}
