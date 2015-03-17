package async.capteur.strategie;

import async.Observer;
import async.canal.Canal;
import async.capteur.Capteur;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 */
public class DiffusionAtomique implements AlgoDiffusion {
    private Label label;
    private int nbAfficheur; //nb afficheur
    private Capteur capteur;
    private int value;
    private List<Future<Integer>> listFutur = new ArrayList<Future<Integer>>();

    public DiffusionAtomique() {
        listFutur = new ArrayList<Future<Integer>>();
    }

    public DiffusionAtomique(Label l) {
        this();
        this.setLabel(l);
    }

    @Override
    public void configure(int nbAfficheur, int newValue) {
        this.nbAfficheur  = nbAfficheur;
        this.value = newValue;
        Platform.runLater(() -> label.setText(Integer.toString(this.value)));
    }

    @Override
    public void execute() {

        Iterator<Observer> obs=capteur.observerIterator();
        listFutur.clear();
        while(obs.hasNext()){
            Future<Integer> f1 = ((Canal) obs.next()).updatefuture(capteur);
            listFutur.add(f1);
        }
    }

    @Override
    public void setCapteur(Capteur c){

        this.capteur=c;
        this.value = this.capteur.getRealLastValue();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isDone() {
        boolean res = true;
        int currentFinishedFuture = 0;

        if(listFutur.size() != nbAfficheur)
        {
            return false;
        }
        Iterator<Future<Integer>> iter = listFutur.iterator();
        while(iter.hasNext()) {
            Future<Integer> currFuture = iter.next();
            if(currFuture.isDone())
            {
                currentFinishedFuture++;
            }
            if(!currFuture.isDone())
            {
                res = false;
                break;
            }
        }
        System.out.println("ALGO IS DONE = "+res +" "+currentFinishedFuture+"/"+listFutur.size());
        return res;
    }

    @Override
    public void setLabel(Label label) {
        this.label = label;
    }


    @Override
    public String toString(){
        return "Diffustion Atomique";
    }
}
