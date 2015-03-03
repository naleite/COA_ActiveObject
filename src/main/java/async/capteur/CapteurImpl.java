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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class CapteurImpl implements Capteur {

    private int value;

    private List<Observer> observers=new ArrayList<>();

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
        algo.setCapteur(this);
    }

    @Override
    public void setValue(int value){
        if(algo.isDone())
        {
            this.value=value;
            algo.configure(this.observers.size(), value);

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
    public void start() {

    }


    @Override
    public Iterator<Observer> observerIterator(){
        return observers.iterator();
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
    public int getRealLastValue() {
        return value;
    }

    @Override
    public int numberOfObserver() {
        return this.observers.size();
    }
}
