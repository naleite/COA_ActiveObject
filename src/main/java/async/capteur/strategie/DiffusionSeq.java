package async.capteur.strategie;

import async.Observer;
import async.canal.Canal;
import async.capteur.Capteur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Future;

/**
 *
 */
public class DiffusionSeq implements AlgoDiffusion {
    private int nbAfficheur; //nb afficheur
    private Capteur capteur;
    private ArrayList<Integer> valueCopie; //tableau des valeurs non envoyées
    private int currentDemandeGetValue;

    int lastValue;//derniere valeur avant que valueCopie soit vide
                    //utile lorsqu'on a changé de stratégie

    public DiffusionSeq() {
        currentDemandeGetValue = 0;
        valueCopie = new ArrayList<Integer>();
    }

    @Override
    public void configure(int nbAfficheur, int newValue) {
        this.nbAfficheur = nbAfficheur;
        valueCopie.add(newValue);
        lastValue = newValue;
        System.out.print("valueCopie = ");
        for(int i=0;i<valueCopie.size();i++) {
            System.out.print(" " + valueCopie.get(i)+",");
        }
        System.out.println("\n");
    }

    @Override
    public void execute() {
        Iterator<Observer> canals=capteur.observerIterator();
        while(canals.hasNext()){
            (( Canal)canals.next()).updatefuture(capteur);
        }

    }

    @Override
    public void setCapteur(Capteur c) {
        this.capteur = c;
        nbAfficheur = capteur.numberOfObserver();
    }

    @Override
    public int getValue() {
        currentDemandeGetValue = currentDemandeGetValue + 1;
        System.out.println("currentgetdemand =" + currentDemandeGetValue + "/" + nbAfficheur);

        if(!valueCopie.isEmpty()) {
            int v = this.valueCopie.get(0);
            if (currentDemandeGetValue >= nbAfficheur) {
                currentDemandeGetValue = 0;
                this.valueCopie.remove(0);
            }
            return v;
        }
        else return lastValue;

    }


    @Override
    public boolean isDone() {
        return true;
    }



    @Override
    public String toString(){
        return "Diffustion Sequentielle";
    }
}
