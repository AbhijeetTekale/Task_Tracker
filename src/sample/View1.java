package sample;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Date;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;


public class View1 {
    @FXML
    Text TxtMin,TxtSecond;
    @FXML ImageView img1;
    @FXML
    Button BtnStop,btnPause;
    Timer timer;
    int sec=-1,min=0;
    public void newCllicked(ActionEvent actionEvent) {
        simpletimer();
        timer.start();
    }
    public void simpletimer()
    {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                sec++;
                if(sec==60)
                {
                    min++;
                    sec = 0;
                }
                TxtMin.setText(""+min);
                TxtSecond.setText(""+sec);
                if(min%5==0&&sec==0)
                {
                    captureImage();
                    captureWebcam();
                }
            }
        });
    }

    private void captureWebcam() {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        try{
            grabber.start();
            opencv_core.IplImage img3 = grabber.grab();
            String a;
            if(img3 != null)
            {
                Date d = new Date();
                long ms = d.getTime();
                int date = d.getDate();
                String dir = "C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\WebCam\\" + date;
                File file1 = new File(dir);
                if(file1.isDirectory())
                {
                    a = "C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\WebCam\\" + date + "\\IMG" + String.valueOf(ms) + ".png";
                    cvSaveImage(a, img3);
                }
                else
                {
                    file1.mkdir();
                    a = "C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\WebCam\\" + date + "\\IMG" + String.valueOf(ms) + ".png";
                    cvSaveImage(a, img3);
                }


            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void captureImage() {
        String a;
        BufferedImage img;
        int date=1;
        try {
            Robot robot = new Robot();
            Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            img = robot.createScreenCapture(rec);
            Image image = SwingFXUtils.toFXImage(img,null);
            img1.setImage(image);
            Date d = new Date();
            long ms = d.getTime();
            date = d.getDate();
            String dir ="C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\Screenshots\\"+date;
            File file1 = new File(dir);
            if(file1.isDirectory())
            {
                a = "C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\Screenshots\\"+date+"\\Screenshots" + String.valueOf(ms) + ".png";
                ImageIO.write(img, "png", new File(a));
            }
            else
            {
                file1.mkdir();
                a = "C:\\Users\\Abhijeet_\\IdeaProjects\\Task_tracker\\Images\\Screenshots\\"+date+"\\Screenshots" + String.valueOf(ms) + ".png";
                ImageIO.write(img, "png", new File(a));
            }

        }
        catch (Exception e) {
            System.out.print(e.toString());
        }

    }
    public void pressedPause(ActionEvent e)
    {
        if(timer.isRunning())
        {
            timer.stop();
            btnPause.setText("Resume");
        }
        else
        {
            timer.restart();
            btnPause.setText("Pause");

        }
    }
    public void pressedStop(ActionEvent e)
    {
        timer.stop();
        captureWebcam();
        captureImage();
        Stage s  =(Stage) TxtMin.getScene().getWindow();
        s.close();
    }
    public void getActivity(ActionEvent actionEvent) throws IOException {
        Parent p =  FXMLLoader.load(getClass().getResource("view2.fxml"));
        Stage stage = new Stage();
        stage.setTitle("History");
        stage.setScene(new Scene(p, 1000, 600));
        stage.show();
        Stage tage =(Stage) btnPause.getScene().getWindow();
        tage.hide();
    }
}
