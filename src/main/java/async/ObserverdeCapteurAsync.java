package async;

import async.capteur.Capteur;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by naleite on 15/1/7.
 */
public interface ObserverdeCapteurAsync extends Observer<Capteur> {
    @Override
    void update(Capteur subject);

    Future getValueFuture();

    Future updatefuture(Capteur c);

    void update(ObserverdeCapteurAsync subject);
}
