package async.capteur.strategie;

import async.capteur.Capteur;

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

}
