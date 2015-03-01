package async.canal;

import async.Observer;
import async.afficheur.Afficheur;
import async.capteur.Capteur;
import async.ObserverdeCapteurAsync;
import async.capteur.strategie.AlgoDiffusion;
import async.view.SimpleViewController;
import javafx.scene.control.Label;

import java.util.Iterator;
import java.util.Random;
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
            System.out.println("getValueFuture Canal#getValueFuture");
            return this.capteur.getValue();};

        int delay = 100 + (int)(Math.random()*400);
        return SimpleViewController.scheduledExecutor.schedule(c, delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public Future updatefuture(Capteur ca) {
        Callable<String> c = () -> {
            this.afficheur.update((ObserverdeCapteurAsync) this);
            System.out.println("executorService update Canal#updatefuture");
            return "ok";};
        int delay = 100 + (int)(Math.random()*400); //radom value between 500 and 1000
        return SimpleViewController.scheduledExecutor.schedule(c, delay, TimeUnit.MILLISECONDS);
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
    public int getRealLastValue() {
        return 0;
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



}
