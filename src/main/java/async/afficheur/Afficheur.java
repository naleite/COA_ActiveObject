package async.afficheur;

import async.ObserverdeCapteur;
import async.canal.Canal;
import async.capteur.Capteur;

/**
 * Created by naleite on 15/1/7.
 */
public class Afficheur implements ObserverdeCapteur {
    private Canal canal;

    public Afficheur(Canal c){
        this.canal=c;
    }
    @Override
    public void update(Capteur subject) {

        this.canal.getValue();
    }
}
