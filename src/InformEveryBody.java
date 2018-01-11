import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin_8_1 on 06-Jun-16.
 */
public class InformEveryBody implements Runnable {
    Thread t;
    HashMap<String,NetworkUtil> clientList;
    QuestionSt add;
    NetworkUtil NS;
    InformEveryBody(HashMap<String,NetworkUtil> clientList,QuestionSt add,NetworkUtil ns){
        this.clientList=clientList;
        this.add=add;
        NS=ns;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        if(NS.s.isClosed()){
            System.out.println("socket is closed");
            // break;
        }
        printHashMap();

    }
    public void printHashMap(){

        Set set = clientList.entrySet();

        System.out.println("Size server"+clientList.size());
        Iterator i = set.iterator();
        System.out.println("Current User--");
        NetworkUtil ns;
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.println(me.getKey() + " : ");
            ns= (NetworkUtil) me.getValue();
            System.out.println("server socket "+ns.s.getLocalSocketAddress());
            if(!ns.s.isClosed()) ns.write(add);
            System.out.println("inform kora hoye gese "+ add.qs);
            //ns.write(add);

        }


    }
}

