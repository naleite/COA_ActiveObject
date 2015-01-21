package async.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by josian on 08/10/14.
 */
public class SimpleViewController implements Initializable {


    //Ensemble des éléments de la vue, récupérer depuis ../ressources/Scene.fxml
    @FXML
    private Button buttonFXStart;

    @FXML
    private Button buttonFXStop;

    @FXML
    private Button buttonFXInc;

    @FXML
    private Button buttonFXDec;

    @FXML
    private Slider sliderFX; //le slider pour modifier le tempo

    @FXML
    private Label labelFX; //afficheur du tempo

    @FXML
    private Circle circleFXLed1; //la led 1

    @FXML
    private Circle circleFXLed2;//la led 2

    @FXML
    public ToggleButton toggleSound; //le bouton sound


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {

    }

    }

