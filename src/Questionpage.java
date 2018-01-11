import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.io.Serializable;
import java.util.ArrayList;

public class Questionpage implements Serializable{
    ArrayList<String>RightansList=new ArrayList<> ();
    ArrayList<String>OptionsList= new ArrayList<> ();
    ArrayList<String>QuestionsList=new ArrayList<> ();
    Questionpage(ArrayList <String>Question,ArrayList <String>Right, ArrayList <String>Options){
        QuestionsList=Question;
        RightansList=Right;
        OptionsList=Options;
    }
    Questionpage(){

    }


}
