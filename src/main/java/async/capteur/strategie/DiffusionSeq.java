package async.capteur.strategie;

import async.canal.Canal;
import async.capteur.Capteur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Future;

/**
 * Created by naleite on 15/1/7.
 */
public class DiffusionSeq implements AlgoDiffusion {
    private int nbAfficheur; //nb afficheur
    private int value;
    private Capteur capteur;
    private ArrayList<Integer> valueCopie; //tableau des valeurs non envoyées
    private int currentDemandeGetValue;

    public DiffusionSeq() {
        valueCopie = new ArrayList<Integer>();
    }

    @Override
    public void configure(int nbAfficheur, int newValue) {
        this.nbAfficheur = nbAfficheur;
        valueCopie.add(newValue);
    }

    @Override
    public void execute() {
        Iterator<Canal> canals=capteur.canalIterator();
        while(canals.hasNext()){
            canals.next().updatefuture(capteur);
        }

    }

    @Override
    public void setCapteur(Capteur c) {
        valueCopie = new ArrayList<Integer>();
        currentDemandeGetValue = 0;
        this.capteur = c;
    }

    @Override
    public int getValue() {
        ++currentDemandeGetValue;

        int v = this.valueCopie.get(0);
        if(currentDemandeGetValue == nbAfficheur)
        {
            currentDemandeGetValue = 0;
            this.valueCopie.remove(0);
        }
        return  v;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void clear() {
        currentDemandeGetValue = 0;
        valueCopie.clear();
        valueCopie.add(capteur.getRealLastValue());
    }

    @Override
    public String toString(){
        return "Diffustion Sequentielle";
    }
}
