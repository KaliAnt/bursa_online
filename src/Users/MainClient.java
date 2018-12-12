package Users;

import Events.Event;
import Events.EventListener;
import Filters.EventFilter;
import Filters.ItemFilter;
import Items.Demand;
import Items.Offer;
import Items.Share;
import Server.MainServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainClient extends EventListener implements Buyer, Seller {
    public static int counter;
    public final String id;
    private int walletBalance;
    private boolean wantsToTrade;

    private final HashMap<Share, Integer> shareInventory;

    private ArrayList<Offer> offers = new ArrayList<>();
    private ArrayList<Demand> demands = new ArrayList<>();

    public MainClient() {
        counter++;
        this.id = String.valueOf(counter);
        this.shareInventory = new HashMap<>();
        this.walletBalance = 0;
        System.out.println("Created Client: " + id);
    }

    @Override
    public void handleEvent(Event event) {
        System.out.print("Event for Client " + id + event.getEventType());
    }

    //SUBSCRIBE
    public void subscribeToEvents(EventFilter filter) {
        MainServer.getInstance().subscribeToEvents(filter, this);
    }

    public void subscribeToOwnEvent(ItemFilter filter) {
        MainServer.getInstance().subscribeToOwnEvent(filter, this);
    }

    private void generateInventory() {
        Random invGenRandom = new Random();
        int iterations = invGenRandom.nextInt(9) + 1;
        for (int i = 0; i < iterations; i++) {
            addSharesToInventory(Share.values()[invGenRandom.nextInt(Share.values().length)], (invGenRandom.nextInt(4) + 1) * 100);
            increaseWalletBalanceBy((invGenRandom.nextInt(4) + 1) * 100);
        }
    }

    @Override
    public void addSharesToInventory(Share shareType, int amount) {
        shareInventory.put(shareType, shareInventory.getOrDefault(shareType, 0) + amount);
    }


    public void removeSharesFromInventory(Share shareType, int amount) {
        if (shareInventory.getOrDefault(shareType, 0) > amount) {
            shareInventory.put(shareType, shareInventory.getOrDefault(shareType, 0) - amount);
        }
    }

    //wallet stuff
    public void decreaseWalletBalanceBy(int amount) {
        if (walletBalance - amount >= 0) {
            walletBalance -= amount;
        } else {
            //here the client is out of money
            walletBalance = 0;
            wantsToTrade = false;
        }
    }

    @Override
    public void increaseWalletBalanceBy(int amount) {
        if (amount > 0)
            walletBalance += amount;
    }

    //actions
    public void proposeOffer(int price, Share shareType, int amount) {
        Offer o = new Offer(this, price, shareType, amount);
        MainServer.getInstance().addItem(o);
        offers.add(o);
        removeSharesFromInventory(shareType, amount); //remove the shares, since they are up for sale
    }

    public void proposeDemand(int price, Share shareType, int amount) {
        Demand d = new Demand(this, price, shareType, amount);
        MainServer.getInstance().addItem(d);
        demands.add(d);
        decreaseWalletBalanceBy(price * amount);
    }

    public void buy(Offer o) {
        // this loses money and gains shares
        decreaseWalletBalanceBy(o.getPrice() * o.getAmount());
        addSharesToInventory(o.getShareType(), o.getAmount());
        MainServer.getInstance().removeItem(o);

        // the other party gains money
        o.getSeller().increaseWalletBalanceBy(o.getPrice() * o.getAmount());
    }

    public void sell(Demand d) {
        // this gains money and loses shares
        increaseWalletBalanceBy(d.getPrice() * d.getAmount());
        removeSharesFromInventory(d.getShareType(), d.getAmount());
        MainServer.getInstance().removeItem(d);

        // the other party gains shares
        d.getBuyer().addSharesToInventory(d.getShareType(), d.getAmount());
    }

    @Override
    public String getId() {
        return id;
    }
}
