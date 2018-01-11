import java.io.Serializable;
public class Information implements Serializable {
    public String Name;

    public String password;

    Information(String n,String p){
        Name=n;

        password=p;

    }
    Information(){
        Name=null;
    }
}
