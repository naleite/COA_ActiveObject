package async.afficheur;

import async.ObserverdeCapteur;
import async.canal.Canal;
import async.capteur.Capteur;
import javafx.scene.control.Label;

/**
 * Created by naleite on 15/1/7.
 */
public class Afficheur implements ObserverdeCapteur {
    private Canal canal;
    private Label label;

    public Afficheur(Canal c, Label label){
        this.label = label;
        this.canal=c;
    }

    @Override
    public void update(Capteur subject) {
        int v = this.canal.getValue();
        label.setText(Integer.toString(v));
    }
}
