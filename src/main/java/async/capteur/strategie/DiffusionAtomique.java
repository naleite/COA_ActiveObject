package async.capteur.strategie;

import async.canal.Canal;
import async.capteur.Capteur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 */
public class DiffusionAtomique implements AlgoDiffusion {
    private int nbAfficheur; //nb afficheur
    private Capteur capteur;
    private int value;
    private boolean execute_algo;
    private List<Future<Integer>> listFutur = new ArrayList<Future<Integer>>();

    public DiffusionAtomique() {
        listFutur = new ArrayList<Future<Integer>>();
        execute_algo = false;
    }

    @Override
    public void configure(int nbAfficheur, int newValue) {
        this.nbAfficheur  = nbAfficheur;
        this.value = newValue;
    }

    @Override
    public void execute() {

        Iterator<Canal> canals=capteur.canalIterator();
        listFutur.clear();
        while(canals.hasNext()){
            Future<Integer> f1 = canals.next().updatefuture(capteur);
            listFutur.add(f1);
        }
    }

    @Override
    public void setCapteur(Capteur c){
        this.capteur=c;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isDone() {
        boolean res = true;
        int currentFinishedFuture = 0;
        if(execute_algo) {
            execute_algo = false;
            System.out.println("changing algorithm");
            return true; // si on vient de changer de strategie
                        //evite de tester si tous les obs sont notifiés
        }
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
    public void clear() {
        listFutur.clear();
        value = capteur.getRealLastValue();
        execute_algo = true;
    }

    @Override
    public String toString(){
        return "Diffustion Atomique";
    }
}
