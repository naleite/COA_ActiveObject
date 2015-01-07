package async;

import async.capteur.Capteur;

/**
 * Created by naleite on 15/1/7.
 */
public interface ObserverdeCapteur extends Observer<Capteur> {
    @Override
    void update(Capteur subject);
}
