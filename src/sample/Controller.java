package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.*;

public class Controller {

    @FXML
    private Text txtIntro;
    public void pressButton(ActionEvent e) throws Exception
    {
        Parent p =  FXMLLoader.load(getClass().getResource("view1.fxml"));
        Stage stage = new Stage();
        stage.setTitle("User Dashboard");
        stage.setScene(new Scene(p, 600, 400));
        stage.show();
        Stage tage =(Stage) txtIntro.getScene().getWindow();
        tage.hide();

    }
}

