import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class NetworkUtil {
     Socket s;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    NetworkUtil(Socket s) throws IOException {
        this.s=s;
        ois=new ObjectInputStream(s.getInputStream());
        oos=new ObjectOutputStream(s.getOutputStream());
    }
    NetworkUtil(String str, int port) throws IOException {
        s=new Socket(str,port);

        oos=new ObjectOutputStream(s.getOutputStream());
        ois=new ObjectInputStream(s.getInputStream());
    }
    public Object read(){
        Object o=null;
        try {
            o=ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }
    public void write(Object o){
        try {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() throws IOException {

        oos.close();
        ois.close();
        s.close();
    }


}
