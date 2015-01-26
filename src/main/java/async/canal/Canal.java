package async.canal;

import async.Observer;
import async.afficheur.Afficheur;
import async.capteur.Capteur;
import async.ObserverdeCapteur;
import async.capteur.strategie.AlgoDiffusion;
import async.view.SimpleViewController;
import javafx.scene.control.Label;

import java.util.Iterator;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by naleite on 15/1/7.
 */
public class Canal implements ObserverdeCapteur, Capteur {

    private Capteur capteur;
    private Afficheur afficheur;
    private AlgoDiffusion algo;

    public Canal(Capteur capteur){
        this.capteur=capteur;
    }


    public void setAfficheur(Afficheur afficheur){
        this.afficheur=afficheur;
    }

    @Override
    public AlgoDiffusion getAlgo() {
        return algo;
    }

    @Override
    public void setAlgo(AlgoDiffusion algo) {
        this.algo = algo;
    }

    @Override
    public void setValue(int value) {

    }

    public ScheduledFuture getValue() {
        Callable<Integer> c = () -> {return this.capteur.getValue();};
        return SimpleViewController.scheduledExecutor.schedule(c, 500, TimeUnit.MILLISECONDS);
    }


    @Override
    public void tick() {

    }

    @Override
    public Iterator<Observer> observerIterator() {
        return null;
    }

    @Override
    public Iterator<Canal> canalIterator() {
        return null;
    }

    @Override
    public void addCanal(Canal c) {

    }

    @Override
    public void removeCanal(Canal c) {

    }

    @Override
    public void setLabel(Label l) {
        this.capteur.setLabel(l);
    }

    @Override
    public Label getLabel() {
        return this.capteur.getLabel();
    }

    @Override
    public void start() {

    }

    @Override
    public void attach(Observer o) {
        this.capteur.attach(o);
    }

    @Override
    public void detach(Observer o) {
        this.capteur.detach(o);
    }

    @Override
    public void update(Capteur subject) {
        this.afficheur.update(subject);
    }


    class MyCallable implements Callable
    {
        Runnable r;

        MyCallable(final Runnable r)
        {
            this.r = r;
        }

        @Override
        public Object call() throws Exception {
            return r;
        }
    }
}
