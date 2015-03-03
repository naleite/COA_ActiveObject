package async.capteur.strategie;

import async.Observer;
import async.canal.Canal;
import async.capteur.Capteur;

import java.util.Iterator;

/**
 *
 */
public class DiffusionEpoque implements AlgoDiffusion {
    private Capteur capteur;
    int value;

    @Override
    public void configure(int nbAfficheur, int newValue) {
        this.value = newValue;
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
    public String toString(){
        return "Gestion par epoque";
    }
}
