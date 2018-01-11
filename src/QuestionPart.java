import java.util.HashMap;


public class QuestionPart implements Runnable {
    HashMap<String, NetworkUtil> clientList;
    NetworkUtil NS;

    Thread t;

    QuestionPart( HashMap<String, NetworkUtil> clientList, NetworkUtil NS) {

        this.clientList = clientList;
        this.NS = NS;
        t = new Thread (this);

        t.start ();
    }


    @Override
    public void run() {
        while (true) {
            if (NS.s.isClosed ()) {
                break;
            }
            String examMode = NS.read ().toString ();
            System.out.println (examMode + " server ");


            if (examMode.equals ("Basic")) {
                System.out.println ("Easy server selected");
                try {
                    new QuizPart ("Basic", clientList, NS).t.join ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            } else if (examMode.equals ("Loop")) {
                System.out.println ("Medium server selected");
                try {
                    new QuizPart ("Loop", clientList, NS).t.join ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }

        }
    }
}