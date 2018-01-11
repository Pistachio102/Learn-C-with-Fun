import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class ScoreWrite implements Runnable {
    NetworkUtil NS;
    Thread t;
    String catagory;
    Score s=new Score();
    String s1,s2,s3,User;

    HashMap<String,NetworkUtil> clientList;
    ScoreWrite(String s, NetworkUtil ns, HashMap<String,NetworkUtil> clientList,String user){
        NS=ns;
        catagory=s;
        this.clientList=clientList;
        User=user;
        t=new Thread(this);
        t.start();
        this.s.score=0;
        //this.s.scoreHard=0;
       // this.s.scoreMedium=0;
    }
    @Override
    public void run() {
        int ans=Integer.parseInt(User);
        NetworkUtil value=NS;
        String name=null;
        for(Map.Entry entry: clientList.entrySet()){

            if(value.equals(entry.getValue())){
                name = (String) entry.getKey();
                break; //breaking because its one to one map
            }
        }

        BufferedReader fr= null;
        try {
            fr = new BufferedReader(new FileReader(name+"score.txt"));
            s1=fr.readLine();
           // s2=fr.readLine();
          //  s3=fr.readLine();
            s.score=Integer.parseInt(s1);
           // s.scoreMedium=Integer.parseInt(s2);
           // s.scoreHard=Integer.parseInt(s3);


                if(s.score< ans) {

                    s.score = ans;
                }

            PrintWriter bw=new PrintWriter(new File(name+"score.txt"));
            bw.println(s.score);
          //  bw.println(s.scoreMedium);
          //  bw.println(s.scoreHard);
            bw.close();
            System.out.println("writing from server scores");
            NS.write(s);
            System.out.println("writen from server scores");
        } catch (Exception e) {
            File file= new File(name+"score.txt");
            try {

                    if(s.score< ans){

                        s.score=ans;
                    }
            PrintWriter bw=new PrintWriter(file);


                bw.println(s.score);
              //  bw.println(s.score);
               // bw.println(s.scoreHard);

                bw.close();
                System.out.println("writing from server scores");
                NS.write(s);
                System.out.println("writn from server scores");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
