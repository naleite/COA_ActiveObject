package async.view; /**
 * Created by naleite on 08/10/14.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFX extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        //Normalement lancé par le Controller apres avoir instancier tous les elements
        stage.setTitle("COA");

        Parent root;
        FXMLLoader fxmlloader = new FXMLLoader();
        root = fxmlloader.load(getClass().getClassLoader().getResource("ihmv1.fxml"));

        Scene scene = new Scene(root, 600,400);
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setScene(scene);


        stage.setOnCloseRequest(event -> {
            try {

                Platform.exit();
                stop();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stage.show();

    }
}
