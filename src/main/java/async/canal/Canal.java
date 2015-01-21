package async.canal;

import async.Observer;
import async.afficheur.Afficheur;
import async.capteur.Capteur;
import async.ObserverdeCapteur;
import async.capteur.strategie.AlgoDiffusion;

import java.util.Iterator;
import java.util.Timer;

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

    @Override
    public int getValue() {
        return this.capteur.getValue();
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
    public void attach(Observer o) {

    }

    @Override
    public void detach(Observer o) {

    }

    @Override
    public void update(Capteur subject) {
        this.afficheur.update(subject);
    }
}
