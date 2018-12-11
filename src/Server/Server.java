package Server;

import Items.*;
import Events.*;
import java.util.LinkedList;


public class Server {
    private static Server instance;
    private LinkedList<Offer> offerList;
    private final EventDispatcher dispatcher = new EventDispatcher();
    private Thread serverLoopThread;

    private Server() {
        offerList = new LinkedList<>();
        serverLoopThread = new Thread(this::serverLoop);
        serverLoopThread.start();
    }

    public static Server getInstance(){
        if(instance == null){
            instance = new Server();
        }
        return instance;
    }

    public void simulate() {

    }



    private void serverLoop(){
        while(!Thread.currentThread().isInterrupted()){
            try{

            }catch(Exception e){
                Thread.currentThread().interrupt();
                break;
            }

        }
    }

}
