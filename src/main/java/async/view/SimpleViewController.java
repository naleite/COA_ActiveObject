package async.view;

import async.afficheur.Afficheur;
import async.canal.Canal;
import async.capteur.Capteur;
import async.capteur.CapteurImpl;
import async.capteur.strategie.AlgoDiffusion;
import async.capteur.strategie.DiffusionAtomique;
import async.capteur.strategie.DiffusionSeq;
import async.capteur.strategie.DiffustionEpoque;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by josian on 08/10/14.
 */
public class SimpleViewController implements Initializable {


    //Ensemble des éléments de la vue, récupérer depuis ../ressources/Scene.fxml
    @FXML
    private Button buttonFXStart;

    @FXML
    private ChoiceBox algoBox;

    @FXML
    private Label labelCapteur;

    @FXML
    private Label labelAff1;

    @FXML
    private Label labelAff2;

    @FXML
    private Label labelAff3;

    @FXML
    private Label labelAff4;

    private Capteur capteur;

    public static ScheduledExecutorService scheduledExecutor = new ScheduledThreadPoolExecutor(2) ;

    private List<AlgoDiffusion> algos=new ArrayList<>();
    private AlgoDiffusion algoAtom=new DiffusionAtomique();
    private AlgoDiffusion algoSeq=new DiffusionSeq();
    private AlgoDiffusion algoEpo=new DiffustionEpoque();

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        //gestion ChoiceBox
        algos.add(algoAtom);
        algos.add(algoSeq);
        algos.add(algoEpo);
        algoBox.getItems().addAll(algos);
        algoBox.setValue(algoBox.getItems().get(0));
        buttonFXStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleStart();
            }
        });

        //GESTION CAPTEUR
        capteur = new CapteurImpl();
        capteur.setLabel(labelCapteur);

        Canal c1 = new Canal(capteur);
        Canal c2 = new Canal(capteur);
        Canal c3 = new Canal(capteur);
        Canal c4 = new Canal(capteur);

        capteur.attach(c1);
        capteur.attach(c2);
        capteur.attach(c3);
        capteur.attach(c4);

        capteur.addCanal(c1);
        capteur.addCanal(c2);
        capteur.addCanal(c3);
        capteur.addCanal(c4);

        Afficheur aff1 = new Afficheur(c1, labelAff1);
        Afficheur aff2 = new Afficheur(c2, labelAff2);
        Afficheur aff3 = new Afficheur(c2, labelAff3);
        Afficheur aff4 = new Afficheur(c2, labelAff4);

        c1.setAfficheur(aff1);
        c2.setAfficheur(aff2);
        c3.setAfficheur(aff3);
        c4.setAfficheur(aff4);
    }

    private void handleStart(){
        AlgoDiffusion testAlgo=(AlgoDiffusion)algoBox.getValue();
        testAlgo.setCapteur(capteur);
        capteur.setAlgo(testAlgo);
        System.out.println("start pressed:"+testAlgo);

        capteur.start();
        //testAlgo.execute();
    }

}

