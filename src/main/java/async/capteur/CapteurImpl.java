package async.capteur;

import async.Observer;
import async.canal.Canal;
import async.capteur.strategie.AlgoDiffusion;
import async.capteur.strategie.DiffusionAtomique;
import javafx.scene.control.Label;

import java.util.*;

/**
 * Created by naleite on 15/1/7.
 */
public class CapteurImpl implements Capteur {

    private int value;

    private List<Observer> observers=new ArrayList<>();

    private List<Canal> canals=new ArrayList<Canal>();

    private AlgoDiffusion algo;
    private Label label;


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
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        this.setValue(randomNum);
        System.out.println("timer: "+randomNum);
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

    @Override
    public void setLabel(Label l) {
        this.label = l;
    }

    @Override
    public Label getLabel() {
        return this.label;
    }

    @Override
    public void start() {
        Timer t = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //capteur.tick();
                System.out.println("timer...");
            }
        };
        t.schedule(task, 500);
    }
}
