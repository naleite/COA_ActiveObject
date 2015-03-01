package async.capteur;

import async.Observer;
import async.Subject;
import async.canal.Canal;
import async.capteur.strategie.AlgoDiffusion;
import javafx.scene.control.Label;

import java.util.Iterator;

/**
 * Created by naleite on 15/1/7.
 */
public interface Capteur extends Subject {

    AlgoDiffusion getAlgo();

    void setAlgo(AlgoDiffusion algo);

    void setValue(int value);

    public int getValue();

    public void tick();

    Iterator<Observer> observerIterator();

    Iterator<Canal> canalIterator();

    void addCanal(Canal c);

    void removeCanal(Canal c);

    void setLabel(Label l);

    Label getLabel();

    void start();

    int getRealLastValue();//return the last observ value
}
