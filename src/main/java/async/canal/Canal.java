package async.canal;

import async.Observer;
import async.afficheur.Afficheur;
import async.capteur.Capteur;
import async.ObserverdeCapteurAsync;
import async.capteur.strategie.AlgoDiffusion;
import async.view.SimpleViewController;
import javafx.scene.control.Label;

import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Created by naleite on 15/1/7.
 */
public class Canal implements ObserverdeCapteurAsync, Capteur {

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

    @Override
    public Future<Integer> getValueFuture() {
        Callable<Integer> c = () -> {
            System.out.println("getValueFuture called");
            return this.capteur.getValue();};
        return SimpleViewController.scheduledExecutor.schedule(c, 2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public Future updatefuture(Capteur ca) {
        Callable<String> c = () -> {
            this.afficheur.update((ObserverdeCapteurAsync) this);
            System.out.println("executorService update Canal");
            return "ok";};
        return SimpleViewController.scheduledExecutor.schedule(c, 2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void update(ObserverdeCapteurAsync subject) {
        System.out.println("Not executed");
        this.afficheur.update((ObserverdeCapteurAsync) this);
    }


    @Override
    public int getValue(){
        return capteur.getValue();
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

       this.afficheur.update(subject);//methode sync
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
