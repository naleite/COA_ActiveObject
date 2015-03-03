package async.capteur;

import async.Observer;
import async.Subject;
import async.canal.Canal;
import async.capteur.strategie.AlgoDiffusion;
import javafx.scene.control.Label;

import java.util.Iterator;

/**
 *
 */
public interface Capteur extends Subject {

    AlgoDiffusion getAlgo();

    void setAlgo(AlgoDiffusion algo);

    void setValue(int value);

    public int getValue();

    public void tick();

    Iterator<Observer> observerIterator();

    void setLabel(Label l);

    Label getLabel();

    int getRealLastValue();//return the last observ value

    int numberOfObserver();
}
