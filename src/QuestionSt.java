import java.io.Serializable;
public class QuestionSt implements Serializable {
    String qs;
    String op1;
    String op2;
    String op3;
    String op4;
    String ans;
    String mode;
    QuestionSt(String q,String o1,String o2,String o3,String o4,String a){
        qs=q;
        op1=o1;
        op2=o2;
        op3=o3;
        op4=o4;
        ans=a;
    }
    QuestionSt(){

    }

}
