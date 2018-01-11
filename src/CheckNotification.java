import javafx.scene.control.Button;

/**
 * Created by Admin_8_1 on 08-Jun-16.
 */
public class CheckNotification implements Runnable {
    NetworkUtil NS;
    Main m;
    Button refresh;
    Thread t;

    Object o;
    Object oprev;
    boolean b;
    CheckNotification(Button a, NetworkUtil ns,boolean b){
        NS=ns;
        refresh=a;
        t=new Thread(this);
        t.start();
        oprev=null;
        this.b=b;

    }
    @Override
    public void run() {
        while(!b) {
            o = NS.read();
            System.out.println("run e dhukse");
            if (o instanceof QuestionSt && !o.equals(oprev)) {
                System.out.println("checking e asche");
                oprev=o;
                refresh.setDisable(false);
            }
        }
    }
    public Object returnQuestion(){
        return o;
    }

}
