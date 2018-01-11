import javafx.scene.control.RadioButton;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;

public class QuizPart implements Runnable {

    HashMap<String,NetworkUtil> clientList;
    NetworkUtil NS;
    Thread t;
    String catagory;
    Questionpage q;
    ArrayList<String> RightAnswers= new ArrayList<>();

    ArrayList<String> QuestionString= new ArrayList<>();
    ArrayList<String> OptionString= new ArrayList<>();
    QuizPart(String s,HashMap<String,NetworkUtil> clientList, NetworkUtil NS){
        catagory=s;
        this.clientList=clientList;
        this.NS=NS;
        t=new Thread(this);

        t.start();
    }
    public void run() {
        while (true) {
            if (NS.s.isClosed()) {
                //break;
            }
            Scanner Q = null, A = null;


            try {
                Q = new Scanner(new File(catagory +"Ques.txt"));
                A = new Scanner(new File(catagory +"Ans.txt"));
            } catch (Exception e) {
                System.out.println("Error");
            }
            while (Q.hasNextLine()) {
                QuestionString.add(Q.nextLine());
            }
            int i = 1;
            while (A.hasNextLine()) {
                if (i % 5 == 0) {
                    String s = A.nextLine();
                    RightAnswers.add(s);


                } else {
                    String s = A.nextLine();
                    OptionString.add(s);

                }
                i++;
            }
            q=new Questionpage (QuestionString,RightAnswers,OptionString);







            }}}
