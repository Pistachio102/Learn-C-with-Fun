import com.sun.org.apache.regexp.internal.REDebugCompiler;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
public class Main extends Application {
    public Socket s;
    public static NetworkUtil ns;

    private static Stage stage;
    static AnchorPane LogInPage, SignUpPage, SignUpSuccessPage, LogInSuccessPage, HomePage,
            AfterExamPage;
    //static Pane LogInPage;
    private static int numOfQuestion = 10;
    //static Pane AfterPracticePage,QuestionAddPage,AfterQuestionAddPage,StatisticsPage;
    private static LogINController login;
    private static String Name, ExamMode;
    ;

    public static int getNumOfQuestion() {
        return numOfQuestion;
    }

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

    public static void setNumOfQuestion(int n) {
        numOfQuestion = n;
    }

    public static void setExamMode(String mode) {
        ExamMode = mode;
    }

    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        StartPage ();
    }

    //page 1, contains login,sign up button
    public static void StartPage() throws IOException {

        ns = new NetworkUtil ("127.0.0.1", 33333);
        System.out.println ("client has created");
        FXMLLoader loader = new FXMLLoader ();

        loader.setLocation (Main.class.getResource ("Login1.fxml"));
        LogInPage = loader.load ();
        Scene scene = new Scene (LogInPage);
        stage.setScene (scene);
        stage.show ();


    }

    //signup page
    public static void SignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation (Main.class.getResource ("SignUpPage.fxml"));
        SignUpPage = loader.load ();
        Scene scene = new Scene (SignUpPage);
        stage.setScene (scene);
        stage.show ();

    }

    // page 3, if sign in is successful
    public static void SignUpSuccess() throws IOException {
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation (Main.class.getResource ("Menu.fxml"));
        SignUpSuccessPage = loader.load ();
        Scene scene = new Scene (SignUpSuccessPage);
        stage.setScene (scene);
        stage.show ();

    }

    public static void LogInSuccess() throws IOException {
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation (Main.class.getResource ("Menu.fxml"));
        LogInSuccessPage = loader.load ();
        Scene scene = new Scene (LogInSuccessPage);
        stage.setScene (scene);
        stage.show ();

    }

    //switches to the home page
    public static void HomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation (Main.class.getResource ("Menu.fxml"));
        HomePage = loader.load ();
        Scene scene = new Scene (HomePage);

        stage.setScene (scene);

        stage.show ();
    }

    public static void CloseProgram() throws IOException {
        ns.close ();
        stage.close ();
    }

    // practice page
    public static void PracticeQuestion() throws IOException {
        
        Questionpage q = new Questionpage ();
        q = (Questionpage) ns.read ();
        Date d = new Date ();
        //StartTime=d.getTime();
        // System.out.println("s "+StartTime);
        ArrayList<ToggleGroup> toggle = new ArrayList<> ();
        ArrayList<Label> QuestionsLabel = new ArrayList<> ();
        ArrayList<String> RightAnswers = new ArrayList<> ();
        ArrayList<RadioButton> OptionsLabel = new ArrayList<> ();
        ArrayList<RadioButton> QuesAnswered = new ArrayList<> ();
        ArrayList<String> QuestionString = new ArrayList<> ();
        ArrayList<String> OptionString = new ArrayList<> ();

        QuestionString = q.QuestionsList;
        RightAnswers = q.RightansList;
        OptionString = q.OptionsList;
        ArrayList<String> finalQuestionString = QuestionString;
        ArrayList<String> finalOptionString = OptionString;
        ArrayList<String> finalRightAnswers1 = RightAnswers;
        Vector<Integer> Clicked = new Vector<> ();


        /*
        Scanner Q = null,A=null;
        final int[] answered = {0};

        try{
            Q = new Scanner(new File(ExamMode+"Question.txt"));
            A = new Scanner(new File(ExamMode+"Answer.txt"));
        }catch(Exception e) {
            System.out.println("Error");
        }
        while(Q.hasNextLine()){
            QuestionString.add(Q.nextLine());
        }
        int i=1;
        while(A.hasNextLine()){
            if(i%5==0){
                String s= A.nextLine();
                RightAnswers.add(s);


            }
            else{
                String s= A.nextLine();
                OptionString.add(s);

            }
            i++;
        }
        */
        //in the uper portion of question page
        numOfQuestion = QuestionString.size ();
        VBox Box = new VBox (10);
        VBox ques = new VBox (10);
        final ScrollPane sp = new ScrollPane ();
        HBox QuestionNumber = new HBox (180);

        Label Number = new Label ("Number of Questions : " + QuestionString.size ());
        Number.setFont (Font.font ("Bodoni MT", 25));
        Label answeredQuestion = new Label ("Subject: GRE");
        Number.setFont (Font.font ("Bodoni MT", 20));
        answeredQuestion.setFont (Font.font ("Bodoni MT", 20));
        QuestionNumber.getChildren ().addAll (Number, answeredQuestion);
        QuestionNumber.setAlignment (Pos.BASELINE_LEFT);
        ques.setPadding (new Insets (10));
        Scene scene = new Scene (Box, 640, 480);
        stage.setScene (scene);
        stage.show ();
        HBox up = new HBox (70);
        Label practice = new Label ("Mode: " + ExamMode);
        practice.setFont (Font.font ("Bodoni MT", 25));
        Label name = new Label ("Name:" + Name);
        Button Refresh = new Button ("Refresh");
        //Label subject= new Label("Subject: "+Subject);
        name.setFont (Font.font ("Bodoni MT", 25));
        // subject.setFont(Font.font("Bodoni MT",25));
        up.getChildren ().addAll (practice, name, Refresh);
        HBox forButtons = new HBox (450);
        Button Back = new Button ("BACK");
        Button Show = new Button ("Show Answer");
        Button Finish = new Button ("Finish");
        forButtons.getChildren ().addAll (Show);

        Refresh.setDisable (true);
        //ques.getChildren().add(up);
        // ques.getChildren().addAll(QuestionNumber);
        Box.setPadding (new Insets (10));
        Box.getChildren ().addAll (up, QuestionNumber, sp, forButtons);
        VBox.setVgrow (sp, Priority.ALWAYS);

        //here comes the question part


        for (int j = 0; j < QuestionString.size (); j++) {
            Label QuesLine = new Label ();
            QuesLine.setText ((j + 1) + "." + QuestionString.get (j));

            QuestionsLabel.add (QuesLine);
        }
        for (int j = 0; j < QuestionsLabel.size (); j++) {
            ToggleGroup mygroup = new ToggleGroup ();
            toggle.add (mygroup);
        }
        System.out.println (OptionString.size ());
        for (int j = 0; j < OptionString.size (); j++) {

            RadioButton option = new RadioButton ();
            option.setText (OptionString.get (j));
            System.out.println (option.getText ());
            //System.out.println("j "+j);
            System.out.println (j / 4);
            option.setToggleGroup (toggle.get (j / 4));
            OptionsLabel.add (option);
            Clicked.add (j, 0);
        }

        for (int j = 0, k = 0; j < QuestionsLabel.size (); j++, k += 4) {
            ques.getChildren ().addAll (QuestionsLabel.get (j));
            ques.getChildren ().addAll (OptionsLabel.get (k));
            ques.getChildren ().addAll (OptionsLabel.get (k + 1));
            ques.getChildren ().addAll (OptionsLabel.get (k + 2));
            ques.getChildren ().addAll (OptionsLabel.get (k + 3));
        }

        sp.setVmax (440);
        sp.setPrefSize (440, 150);
        sp.setContent (ques);
        int[] answeredprev = new int[10000];
        int[] answered = new int[QuestionString.size ()];
        for (int j = 0; j < answered.length; j++) {
            answered[j] = 0;
        }
        for (int j = 0; j < OptionsLabel.size (); j++) {
            final int jj = j;
            System.out.println ("option selection" + jj);
            OptionsLabel.get (jj).setOnAction (e -> {
                System.out.println ("option selected" + jj);
                if (OptionsLabel.get (jj).isSelected () && answeredprev[jj / 4] == 0) {
                    answered[0]++;
                    Clicked.remove (jj);
                    Clicked.add (jj, 1);
                    answeredprev[jj / 4] = 1;

                }
                if (!OptionsLabel.get (jj).isSelected ()) {
                    answered[0]--;
                    Clicked.remove (jj);
                    Clicked.add (jj, 0);
                }

                //answeredQuestion.setText("Answered Questions : "+String.valueOf(answered[0]));
            });
        }
    }
}