package async.view;

import async.afficheur.Afficheur;
import async.canal.Canal;
import async.capteur.Capteur;
import async.capteur.CapteurImpl;
import async.capteur.strategie.AlgoDiffusion;
import async.capteur.strategie.DiffusionAtomique;
import async.capteur.strategie.DiffusionSeq;
import async.capteur.strategie.DiffusionEpoque;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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


    public static ScheduledExecutorService scheduledExecutor ;

    private List<AlgoDiffusion> algos=new ArrayList<>();
    private AlgoDiffusion algoAtom=new DiffusionAtomique();
    private AlgoDiffusion algoSeq=new DiffusionSeq();
    private AlgoDiffusion algoEpo=new DiffusionEpoque();

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {

        scheduledExecutor = new ScheduledThreadPoolExecutor(20);

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

        algoBox.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                        switch (newValue.intValue()) {

                            case 0:
                                System.out.println("*** Algo Atomique");
                                capteur.setAlgo(new DiffusionAtomique());
                                break;
                            case 1:
                                System.out.println("*** Algo Sequentielle");
                                capteur.setAlgo(new DiffusionSeq());
                                break;
                            case 2:
                                System.out.println("*** Algo Epoque");
                                capteur.setAlgo(new DiffusionEpoque());
                                break;
                            default:
                                break;
                        }
                    }
                }
        );


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

        Afficheur aff1 = new Afficheur(c1, labelAff1);
        Afficheur aff2 = new Afficheur(c2, labelAff2);
        Afficheur aff3 = new Afficheur(c2, labelAff3);
        Afficheur aff4 = new Afficheur(c2, labelAff4);

        c1.setAfficheur(aff1);
        c2.setAfficheur(aff2);
        c3.setAfficheur(aff3);
        c4.setAfficheur(aff4);

        capteur.setAlgo(new DiffusionAtomique());
    }


    private void handleStart(){
        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
        ex.scheduleAtFixedRate(
                ()->capteur.tick(),0, 1000, TimeUnit.MILLISECONDS
        );
        buttonFXStart.setDisable(true);

    }
}

