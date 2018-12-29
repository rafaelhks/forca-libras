package Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rafae
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaFXML.fxml"));
        Scene scene = new Scene(root);
        //stage.getIcons().add(new Image(<yourclassname>.class.getResourceAsStream("icon.png")));
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Jogo da Forca em LIBRAS - Unespar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
