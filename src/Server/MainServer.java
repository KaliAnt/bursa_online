package Server;


import Events.Event;
import Events.EventDispatcher;
import Events.EventType;
import Events.ListenerData;
import Filters.EventFilter;
import Filters.ItemFilter;
import Items.Demand;
import Items.Item;
import Items.Offer;
import Users.MainClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainServer {
    private Thread serverLoopThread;
    private static MainServer instance;
    private final EventDispatcher dispatcher = new EventDispatcher();
    private ItemManager itemManager = null;

    private MainServer() {
        itemManager = new ItemManager();
        serverLoopThread = new Thread(this::serverLoop);
        serverLoopThread.start();
    }

    public static MainServer getInstance(){
        if(instance == null){
            instance = new MainServer();
        }
        return instance;
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

    public void subscribeToEvents(EventFilter filter, MainClient client) {
        ListenerData listenerData = new ListenerData(client);
        listenerData.addFilter(filter);

        dispatcher.register(EventType.ITEM_APPEARED, listenerData);
        dispatcher.register(EventType.ITEM_CHANGED, listenerData);
        dispatcher.register(EventType.ITEM_DELETED, listenerData);
    }

    //to see who reads the offers/demands they had posted
    public void subscribeToOwnEvent(ItemFilter filter, MainClient client) {
        ListenerData listenerData = new ListenerData(client);
        listenerData.addFilter(filter);

        dispatcher.register(EventType.ITEM_READ, listenerData);
    }

    public void updateItem(Item oldItem, Item newItem){
        Event event = new Event(EventType.ITEM_CHANGED, oldItem);
        publishEvent(event);
        itemManager.updateItem(oldItem, newItem);
    }

    public void removeItem(Item item) {
        Event event = new Event(EventType.ITEM_DELETED, item);
        publishEvent(event);
        itemManager.removeItem(item);
    }

    public void addItem(Item item) {
        Event event = new Event(EventType.ITEM_APPEARED, item);
        publishEvent(event);
        System.out.println("Event created.");
        itemManager.addItem(item);
    }

    public ArrayList<Offer> getOffers() {
        ArrayList<Offer> offers = new ArrayList<Offer>(itemManager.getOffers());
        for (Offer offer:
             offers) {
            publishEvent(new Event(EventType.ITEM_READ, offer));
        }
        return offers;
    }

    public ArrayList<Demand> getDemands(MainClient client) {
        ArrayList<Demand> demands = new ArrayList<Demand>(itemManager.getDemands());
        for (Demand demand:
                demands) {
            demand.readByClient(client);
            publishEvent(new Event(EventType.ITEM_READ, demand));
        }
        return demands;
    }

    public Offer getOfferAtIntex(int i) {
        ArrayList<Offer> offers = new ArrayList<>(itemManager.getOffers());
        Offer offer = offers.get(i);
        publishEvent(new Event(EventType.ITEM_READ, offer));
        return offer;
    }

    public Demand getDemandAtIndex(int i) {
        ArrayList<Demand> demands = new ArrayList<>(itemManager.getDemands());
        Demand demand = demands.get(i);
        publishEvent(new Event(EventType.ITEM_READ, demand));
        return demand;
    }

    private void publishEvent(Event event) {
        dispatcher.publishEvent(event);
    }


}
