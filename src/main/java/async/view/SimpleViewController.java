package async.view;

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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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


    private List<AlgoDiffusion> algos=new ArrayList<>();
    private AlgoDiffusion algoAtom=new DiffusionAtomique();
    private AlgoDiffusion algoSeq=new DiffusionSeq();
    private AlgoDiffusion algoEpo=new DiffustionEpoque();

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {

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
    }

    private void handleStart(){
        AlgoDiffusion testAlgo=(AlgoDiffusion)algoBox.getValue();
        System.out.println("start pressed:"+testAlgo);
        testAlgo.execute();
    }

}

