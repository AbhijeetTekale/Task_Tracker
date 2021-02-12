package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import java.awt.image.BufferedImage;
import java.io.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.time.LocalDate;


public class View2 {
    @FXML
    Button btnCam,BtnSs;
    @FXML DatePicker dtPic;
    private int day=0;
    @FXML TilePane TlPane;
    @FXML
    AnchorPane Apane;
    File images[];
    public void showSs(javafx.event.ActionEvent actionEvent) throws IOException {
        getDate();
        File f1 = new File("C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\Screenshots\\"+day);
        if(f1!=null)
        {
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if( name.endsWith(".png"))
                        return true;
                    else
                        return false;
                }
            };
            images = f1.listFiles(filter);
        }try {
            createElements();
        }catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"No Images Available");
        }
    }
    public void showCam(javafx.event.ActionEvent actionEvent) {
        getDate();
        File f1 = new File("C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\WebCam\\"+day);
        if(f1!=null)
        {
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if( name.endsWith(".png"))
                        return true;
                    else
                        return false;
                }
            };
            images = f1.listFiles(filter);
        }
        createElements();
    }

    public void getDate() {
        try {
            LocalDate d = dtPic.getValue();
            day = d.getDayOfMonth();
            this.day = day;
        }
        catch(NullPointerException e)
        {
            JOptionPane.showMessageDialog(null,"Please select date first");
        }
    }
    private int count=0;
    private  void createElements(){
        TlPane.getChildren().clear();
        count=0;
        for(int i=0;i<images.length;i++) {
            TlPane.getChildren().add(createPage(count));
            count++;
        }
    }
    public VBox createPage(int index)
    {
        ImageView imageView = new ImageView();
        File file = images[index];
        try{
            BufferedImage bufferedImage  = ImageIO.read(file);
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            imageView.setFitWidth(250);
            imageView.setFitHeight(150);
            imageView.setSmooth(true);
            imageView.setCache(true);

        }catch(Exception e)
        {

        }
        VBox vBox = new VBox();
        vBox.getChildren().add(imageView);
        vBox.setSpacing(10);
        imageView = null;
        return vBox;
    }
}
