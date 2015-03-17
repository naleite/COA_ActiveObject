package async.capteur.strategie;

import async.Observer;
import async.canal.Canal;
import async.capteur.Capteur;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Iterator;

/**
 *
 */
public class DiffusionEpoque implements AlgoDiffusion {
    private Capteur capteur;
    int value;
    private Label label;

    public DiffusionEpoque(Label label)
    {
        this.setLabel(label);
    }

    @Override
    public void configure(int nbAfficheur, int newValue) {

        this.value = newValue;
        Platform.runLater(() -> label.setText(Integer.toString(this.value)));
    }

    @Override
    public void execute() {
        Iterator<Observer> obs=capteur.observerIterator();
        while(obs.hasNext()){
            ((Canal)obs.next()).updatefuture(capteur);
        }
    }

    @Override
    public void setCapteur(Capteur c) {
        this.capteur=c;
        value = this.capteur.getRealLastValue();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void setLabel(Label label) {
        this.label = label;
    }


    @Override
    public String toString(){
        return "Gestion par epoque";
    }
}
