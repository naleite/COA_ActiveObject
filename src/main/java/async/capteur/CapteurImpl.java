package async.capteur;

import async.Observer;
import async.canal.Canal;
import async.capteur.strategie.AlgoDiffusion;
import async.capteur.strategie.DiffusionAtomique;
import async.view.SimpleViewController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by naleite on 15/1/7.
 */
public class CapteurImpl implements Capteur {

    private int value;

    private List<Observer> observers=new ArrayList<>();

    private List<Canal> canals=new ArrayList<Canal>();

    private AlgoDiffusion algo;

    private Label label;

    public int tickValue =0;

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
        if(algo.isDone())
        {
            this.value=value;
            algo.configure(this.canals.size(), value);

            algo.execute();
        }
        else
        {
            System.out.println("CapteurImpl#notFinishedAlgo");
        }
    }
    
    @Override
    public int getValue() {

        return algo.getValue();
    }

    @Override
    public void tick() {
        //Random rand = new Random();
        //int randomNum = rand.nextInt(100);
        int ti =++tickValue;
        this.setValue(ti);
        try {
            Platform.runLater(() -> getLabel().setText(String.valueOf(ti)));
        } catch (Exception e) {}
        System.out.println("timer: " + ti);
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
        //MyTimer timer = new MyTimer();
        //timer.scheduleAtFixedRate(() -> this.tick(), 0, 200);
        SimpleViewController.scheduledExecutor.scheduleAtFixedRate(
                ()->this.tick(),0, 1000, TimeUnit.MILLISECONDS
        );
    }
}
