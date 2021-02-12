package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import java.io.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Date;

public class View2 {
    @FXML
    Button btnCam,BtnSs;
    @FXML DatePicker dtPic;
    @FXML GridPane gp;
    public void showSs(javafx.event.ActionEvent actionEvent) {
        int n = numFiles("C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\Screenshots\\");
        System.out.print(n);
    }
    public void showCam(javafx.event.ActionEvent actionEvent) {
        int n = numFiles("C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\WebCam\\");
        System.out.print(n);
    }

    public void getDate(ActionEvent actionEvent) {
    }
    public int numFiles(String p){
        LocalDate d =  dtPic.getValue();
        int day = d.getDayOfMonth();
        String p1 = p + String.valueOf(day);
        File f = new File(p1);
       int count = 0;
        for (File file : f.listFiles()) {
            if (file.isFile()) {
                count++;
          }
       }
        return count;
    }

}
