import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuController {
    private Main main;
    ObservableList<String> Topic= FXCollections.observableArrayList ("Basic","I/O","Loop","Array","fucntions","String","Pointer");
    NetworkUtil NS=Main.ns;
    @FXML
    private Button basic;
    @FXML
    private Button loop;
    @FXML
    private Button array;
    @FXML
    private Button string;
    @FXML
    private Button io;
    @FXML
    private Button logout;
    @FXML
    private Button  start;
    @FXML

    public void LogOutAction() throws IOException {
        NS=Main.ns;
        NS.write("logOut");
        Main.StartPage();
    }


public void basicAction() throws IOException {
    NS=Main.ns;
        Main.setNumOfQuestion(10);
        Main.setExamMode("Basic");
    Main.PracticeQuestion ();
    }


    }






