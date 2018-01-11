import javafx.application.Application;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

public class ControllerSignUp  {
    private Main main;
    public NetworkUtil NS=Main.ns;
    private String name;
    private String password;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField PassWord;

    @FXML
    private PasswordField Confirm;
    @FXML
    private Label WelcomeUser;

    public String getName() {
        return name;
    }

    //action of sign up button

    public void SignAction(ActionEvent event ) throws IOException {
        NS = Main.ns;
        name = UserName.getText();
        password=PassWord.getText();   String confirm=Confirm.getText();
        System.out.println("n "+name+" p "+password);
        Information information= new Information(name,password);

        if(!password.equals(confirm)){
            System.out.println("ERROR");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("password doesn't match");
            alert.setHeaderText("Sign Up failed");
            alert.setContentText("Confirm your Password");
            alert.showAndWait();
            UserName.setText(null);
            PassWord.setText(null);
            Confirm.setText(null);
        }
        else {
            // creating new file for information
            NS = Main.ns;
            System.out.println("writing " + information.Name);
            NS.write(information);
            System.out.println("written " + information.Name);
            String check = NS.read().toString();
            if (check.equals("0")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Username already exists");
                alert.setHeaderText("Sign In failed");
                alert.setContentText("The username you provided already exists.");
                alert.showAndWait();
                UserName.setText(null);
                PassWord.setText(null);
                Confirm.setText(null);
            }
            else{
                Main.setName(name);
                WelcomeUser=new Label("Welcome "+name);
                NS.write(Main.getName ());
                Main.SignUpSuccess();
            }
        }}

        //action of back button
        public void BackAction() throws IOException {
            try {
                // NS.write(new Information());
                NS.close();
                Main.ns.close();
                //NS.s.close();
                Main.StartPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //action of exit button
        public void exitAction(ActionEvent event) throws IOException {
            Main.ns.close();
            Main.CloseProgram();
        }




    }

