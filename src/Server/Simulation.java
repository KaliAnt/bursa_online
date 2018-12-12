package Server;

import Users.MainClient;

import java.util.ArrayList;

public class Simulation {
    private ArrayList<MainClient> clients = new ArrayList<>();
    private MainServer s;

    public void simulate() {
        MainClient a = new MainClient();
        MainClient b = new MainClient();
        MainClient c = new MainClient();
        MainClient d = new MainClient();

        Thread clientA = new Thread(this::simulateBehaviour);
        Thread clientB = new Thread(this::simulateBehaviour);
        Thread clientC = new Thread(this::simulateBehaviour);
        Thread clientD = new Thread(this::simulateBehaviour);

        MainServer s = MainServer.getInstance();

        clientA.start();
        clientB.start();
        clientC.start();
        clientD.start();
    }

    private void simulateBehaviour() {

    }

}
