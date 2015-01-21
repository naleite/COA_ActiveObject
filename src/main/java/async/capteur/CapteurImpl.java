package async.capteur;

import async.Observer;
import async.canal.Canal;
import async.capteur.strategie.AlgoDiffusion;
import async.capteur.strategie.DiffusionAtomique;

import java.util.*;

/**
 * Created by naleite on 15/1/7.
 */
public class CapteurImpl implements Capteur {

    private int value;

    private List<Observer> observers=new ArrayList<>();

    private List<Canal> canals=new ArrayList<>();

    private AlgoDiffusion algo;


    @Override
    public AlgoDiffusion getAlgo() {
        return algo;
    }

    @Override
    public void setAlgo(AlgoDiffusion algo) {
        this.algo = algo;
    }

    @Override
    public void setValue(int value){
        this.value=value;

    }
    
    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void tick() {
        Timer t = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };
        /**if(algo==null){
            algo=new DiffusionAtomique();
        }
        algo.execute();**/
    }

    @Override
    public void attach(Observer o) {

        if(!observers.contains(o)){
            observers.add(o);
        }
    }

    @Override
    public void detach(Observer o) {

        if(observers.contains(o)){
            observers.remove(o);
        }
    }

    @Override
    public Iterator<Observer> observerIterator(){
        return observers.iterator();
    }

    @Override
    public Iterator<Canal> canalIterator(){
        return canals.iterator();
    }

    @Override
    public void addCanal(Canal c){
        if(!canals.contains(c)){
            canals.add(c);
        }
    }

    @Override
    public void removeCanal(Canal c){
        if(canals.contains(c)){
            canals.remove(c);
        }
    }
}
