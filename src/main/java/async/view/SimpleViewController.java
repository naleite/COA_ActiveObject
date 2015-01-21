package async.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private ChoiceBox algoBox;

    @FXML
    private Label labelCapteur;

    @FXML
    private Label labelAff1;

    @FXML
    private Label labelAff2;




    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {

    }

    }

