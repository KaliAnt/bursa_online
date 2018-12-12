package Server;

import Filters.ItemFilter;
import Filters.PriceFilter;
import Items.Demand;
import Items.Offer;
import Items.Share;
import Users.MainClient;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

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

        Thread clientA = new Thread(this::simulationA);
        Thread clientB = new Thread(this::simulationB);
        Thread clientC = new Thread(this::simulationC);
        Thread clientD = new Thread(this::simulationD);

        MainServer s = MainServer.getInstance();

        clientA.start();
        clientB.start();
        clientC.start();
        clientD.start();

    }

    private void firstHardcodedTest() {
        Offer offerA = new Offer(a,200, Share.GOOGLE, 2);
        a.proposeOffer(offerA);
        b.proposeDemand(new Demand(a,300, Share.MICROSOFT, 1));
        c.proposeDemand(new Demand(a,400, Share.FACEBOOK, 9));
        d.proposeDemand(new Demand(a,500, Share.APPLE, 3));

        a.subscribeToReadEvents(new ItemFilter(offerA));
        c.subscribeToEvents(new ItemFilter(offerA));
        b.buy(MainServer.getInstance().getOfferAtIntex(0));
    }

    private void simulationA(){
        try {
            for (int i = 0; i < 5; i++) {
                Offer offer = new Offer(a, 20 * i, Share.GOOGLE, i);
                a.proposeOffer(offer);
                a.subscribeToReadEvents(new ItemFilter(offer));
                sleep(1000);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Client A is done.");
    }

    private void simulationB(){
        try {
            for (int i = 0; i < 5; i++) {
                Demand demand = new Demand(a, 20 * i, Share.GOOGLE, i);
                b.proposeDemand(demand);
                b.subscribeToReadEvents(new ItemFilter(demand));
                sleep(800);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Client B is done.");
    }

    private void simulationC(){
        Random random = new Random();
        int bound = 5;
        try {
            for (int i = 0; i < 20; i++) {
                if((random.nextInt(100) % 5) == 0) {
                    c.buy(MainServer.getInstance().getOfferAtIntex(random.nextInt(bound)));
                    bound--;
                    System.out.println("Client C bought an item.");
                }
                sleep(2000);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Client C is done.");
    }

    private void simulationD(){
        Random r = new Random();

        try {
            d.subscribeToEvents(new PriceFilter(10, 200));
            for (int i = 0; i < 20; i++) {
                MainServer.getInstance().getOfferAtIntex(r.nextInt(20));
                sleep(500);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Client D is done.");
    }

}
