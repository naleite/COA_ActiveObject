package async.afficheur;

import async.ObserverdeCapteurAsync;
import async.canal.Canal;
import async.capteur.Capteur;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Afficheur implements ObserverdeCapteurAsync {
    private Canal canal;
    private Future future;
    private Label label;

    public Afficheur(Canal c, Label label){
        this.label = label;
        this.canal=c;
    }

    @Override
    public void update(ObserverdeCapteurAsync subject) {


        future = subject.getValueFuture();
        try {
            System.out.println("wait.....");
            int value= (Integer) future.get();
            System.out.println("ok.....");
            Platform.runLater(() -> label.setText(Integer.toString(value)));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(Capteur subject) {

    }

    @Override
    public Future getValueFuture() {
        return null;
    }


    @Override
    public Future updatefuture(Capteur c) {
        return null;
    }


}
