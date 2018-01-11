import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin_8_1 on 03-Jun-16.
 */
public class InformationCheck implements Runnable {
    Information in;
    NetworkUtil NS;
    Thread t;
    HashMap<String,NetworkUtil> clientList;
    InformationCheck(Information in,NetworkUtil ns,HashMap <String,NetworkUtil> clientList){
        this.in=in;
        NS=ns;
        this.clientList=clientList;
        t= new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        while(true){
            System.out.println(NS.s.isConnected()+"is connected");
            if(NS.s.isClosed() ){
                System.out.println("socket is closed");
                break;
            }
            BufferedReader fr= null;
            try {
                fr = new BufferedReader(new FileReader(in.Name+".txt"));
                String UserName=fr.readLine();
                //String UserEmail=fr.readLine();
                String UserPassword=fr.readLine();
                String mode=fr.readLine();

                fr.close();
                if( in.Name.equals(UserName)  && in.password.equals(UserPassword)){
                    NS.write(UserName);
                    //System.out.println(mode+ "Server");
                    UserName=NS.read().toString();

                        clientList.put(in.Name,NS);
                        System.out.println("added in hash map");
                        printHashMap();
                    QuestionPart q=new QuestionPart(clientList,NS);
                    q.t.join ();
                        //new QuestionPart(clientList,NS).t.join();


                    if(mode.equals("exit")){
                        break;
                    }
                    else if(mode.equals("logOut")){
                        t.stop();
                        break;

                    }


                }
                    //if(!m.t.isAlive()) break;


                else{
                    NS.write("2");
                    break;
                }
            } catch (FileNotFoundException e) {
                NS.write("0");
                break;
            } catch (IOException e) {
                //e.printStackTrace();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }
    public void printHashMap(){

        Set set = clientList.entrySet();

        Iterator i = set.iterator();
        System.out.println("Current User--");
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.println(me.getKey() + " : ");
        }


    }
}

