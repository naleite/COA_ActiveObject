package async.afficheur;

import async.ObserverdeCapteur;
import async.canal.Canal;
import async.capteur.Capteur;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by naleite on 15/1/7.
 */
public class Afficheur implements ObserverdeCapteur {
    private Canal canal;
    private Future future;
    private Label label;

    public Afficheur(Canal c, Label label){
        this.label = label;
        this.canal=c;
    }

    @Override
    public void update(Capteur subject) {
        future = this.canal.getValueFuture();
        try {
            int value= (Integer) future.get(2000, TimeUnit.MILLISECONDS);
            Platform.runLater(() -> label.setText(Integer.toString(value)));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Future getValueFuture() {
        return null;
    }


}
