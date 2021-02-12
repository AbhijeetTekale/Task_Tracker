package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
    @FXML private AnchorPane ApaneSignup,ApaneLogin;
    @FXML private TextField txtSignUser,txtUserl,txtPassl;
    @FXML private PasswordField txtSignPass,txtSignRepass;
    Connection conn;
    public void login(ActionEvent e){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root", "Abhijeet@123");
            String user = txtUserl.getText();
            String pass = txtPassl.getText();
            String sql = "SELECT pass from mydata where user = '"+user+"'";
            Statement s1 = conn.createStatement();
            ResultSet rs = s1.executeQuery(sql);
            if(rs.next())
            {
                String p1 = rs.getString(1);
                if(p1.equals(pass))
                {
                    JOptionPane.showMessageDialog(null,"Login success");
                    Parent p =  FXMLLoader.load(getClass().getResource("view1.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("User Dashboard");
                    stage.setScene(new Scene(p, 600, 400));
                    stage.show();
                    Stage tage =(Stage) txtIntro.getScene().getWindow();
                    tage.hide();

                }
                else
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
            }
            else
            JOptionPane.showMessageDialog(null,"Invalid username or password");
            conn.close();
        }catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null,e1);
        }

    }
    public void signup(ActionEvent e){
        if(!txtSignPass.getText().trim().equals(txtSignRepass.getText().trim()))
        {
            JOptionPane.showMessageDialog(null,"Passwords were not matching");
        }
        else
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata","root","Abhijeet@123");
                String user = txtSignUser.getText();
                String pass = txtSignPass.getText();
                String sql= "insert into mydata(user,pass) values('"+user+"','"+pass+"')";
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Sign up success Do login");
                doLogin(e);
                conn.close();

            }catch(Exception e1)
            {
                JOptionPane.showMessageDialog(null,e1);
            }
        }

    }
    public void doSignup(ActionEvent e){
        ApaneLogin.setVisible(false);
        ApaneSignup.setVisible(true);

    }
    public void doLogin(ActionEvent e){
        ApaneLogin.setVisible(true);
        ApaneSignup.setVisible(false);
    }
}

