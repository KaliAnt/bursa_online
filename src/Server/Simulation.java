package Server;

import Filters.ItemFilter;
import Items.Demand;
import Items.Offer;
import Items.Share;
import Users.MainClient;

import java.util.ArrayList;

public class Simulation {
    private ArrayList<MainClient> clients = new ArrayList<>();
    private MainServer s;

    private MainClient a;
    private MainClient b;
    private MainClient c;
    private MainClient d;

    public void simulate() {
        a = new MainClient();
        b = new MainClient();
        c = new MainClient();
        d = new MainClient();

        Thread clientA = new Thread(this::simulateBehaviour);
        Thread clientB = new Thread(this::simulateBehaviour);
        Thread clientC = new Thread(this::simulateBehaviour);
        Thread clientD = new Thread(this::simulateBehaviour);

        MainServer s = MainServer.getInstance();

        //clientA.start();
       // clientB.start();
        //clientC.start();
        //clientD.start();

        firstHardcodedTest();
    }

    private void simulateBehaviour() {

    }

    private void firstHardcodedTest() {
        Offer offerA = new Offer(a,200, Share.GOOGLE, 2);
        a.proposeOffer(offerA);
        b.proposeDemand(new Demand(a,300, Share.MICROSOFT, 1));
        c.proposeDemand(new Demand(a,400, Share.FACEBOOK, 9));
        d.proposeDemand(new Demand(a,500, Share.APPLE, 3));

        a.subscribeToOwnEvent(new ItemFilter(offerA));
        c.subscribeToEvents(new ItemFilter(offerA));
        b.buy(MainServer.getInstance().getOfferAtIntex(0));
    }

}
