import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogINController  {
    private Main main;
    private static String Name="User";
    public  static NetworkUtil NS=Main.ns;

    @FXML
    private TextField LogInName;
    @FXML
    private PasswordField LogInPassword;
    @FXML
    private Label WelcomeName;
    public  String getName() {
        return Name;
    }
    //go to sign up page
    public void SignAction(ActionEvent event) throws IOException {
        NS=Main.ns;
        System.out.println("Signing in");
        NS.write("Sign");
        main.SignUpPage();
        System.out.println("Noting");
    }
    public void LogInAction(ActionEvent event) throws IOException {
        NS=Main.ns;
        NS.write("Log");
        String name=LogInName.getText();
        String password= LogInPassword.getText();
        System.out.println(name+" "+" "+password);
        Information information=new Information(name,password);

        NS.write(information);
        String s=NS.read().toString();

            System.out.println("Log in Successful");

            Main.setName(name);
            NS.write(Main.getName());
            //Welcome.setText("Welcome "+name);
            // WelcomeUser.setText("Afrina ");

            main.LogInSuccess();

         if(s.equals("0")){
            reset();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Username does not exist");
            alert.setHeaderText("Log In failed");
            alert.setContentText("The username you provided does not  exist.");
            alert.showAndWait();
            System.out.println("No account in this name ");
        }
        else if(s.equals("2")){
            reset();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong information");
            alert.setHeaderText("Log In failed");
            alert.setContentText("The information you provided is wrong.");
            alert.showAndWait();
            System.out.println("No account in this name ");



        }
}
    public void reset() {
        LogInName.setText(null);

        LogInPassword.setText(null);
    }
    public void exitAction(ActionEvent event) throws IOException {
        NS.write("exit");
        Main.ns.close();
        Main.CloseProgram();
    }
}
