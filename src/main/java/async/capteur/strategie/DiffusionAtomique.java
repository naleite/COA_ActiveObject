package async.capteur.strategie;

import async.canal.Canal;
import async.capteur.Capteur;

import java.util.Iterator;

/**
 * Created by naleite on 15/1/7.
 */
public class DiffusionAtomique implements AlgoDiffusion {

    private Capteur capteur;

    @Override
    public void configure() {

    }

    @Override
    public void execute() {

        Iterator<Canal> canals=capteur.canalIterator();
        while(canals.hasNext()){
            canals.next().update(capteur);
        }
    }

    @Override
    public void setCapteur(Capteur c){
        this.capteur=c;
    }
}
