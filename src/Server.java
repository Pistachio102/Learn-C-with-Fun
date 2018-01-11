import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by Admin_8_1 on 03-Jun-16.
 */
public class Server {
    ServerSocket ss;
    static int num=0;

    HashMap <String,NetworkUtil> clientList;
    Server(int port) throws IOException, InterruptedException {
        ss=new ServerSocket(port);
        System.out.println("server has started");
        clientList = new HashMap<String,NetworkUtil>();
        while(true){
            Socket s=ss.accept();
            System.out.println("client has joined");
            NetworkUtil ns= new NetworkUtil(s);
            //clientList.put(++num,ns);
            new LogInCheck(ns,clientList,++num);
        }
    }

    public static void main(String[] args) throws Exception {
        new Server(33333);

    }

}
