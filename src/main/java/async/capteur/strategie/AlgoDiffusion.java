package async.capteur.strategie;

import async.capteur.Capteur;

/**
 * Created by naleite on 15/1/7.
 */
public interface AlgoDiffusion {
    public void configure();
    public void execute();

    void setCapteur(Capteur c);
}
