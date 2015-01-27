package async.capteur.strategie;

import async.capteur.Capteur;

/**
 * Created by naleite on 15/1/7.
 */
public interface AlgoDiffusion {

    void configure(int nbAfficheur);

    public void execute();

    void setCapteur(Capteur c);

    /**
     *
     * @return vrai si la diffusion est terminé, faux sinon
     */
    boolean isDone();
}
