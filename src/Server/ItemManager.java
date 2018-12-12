package Server;

import Items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

//lists of all items, manages every offer, demand
public class ItemManager {
    private final ConcurrentTransactionList<Offer> offerList;
    private final ConcurrentTransactionList<Demand> demandList;

    private Semaphore offerSemaphore = new Semaphore(1, true);
    private Semaphore demandSemaphore = new Semaphore(1, true);

    public ItemManager() {
        offerList = new ConcurrentTransactionList<Offer>(new CopyOnWriteArrayList<Offer>());
        demandList = new ConcurrentTransactionList<Demand>(new CopyOnWriteArrayList<Demand>());
    }

    //add/remove Offer/Demand
    public void removeItem(Item item) {
        try {
            if(item instanceof Demand) {
                demandSemaphore.acquire();
                demandList.remove(item);
            }
            else if(item instanceof Offer) {
                offerSemaphore.acquire();
                offerList.remove(item);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(item instanceof Demand)
                demandSemaphore.release();
            else if(item instanceof Offer) {
                offerSemaphore.release();
            }
        }
    }

    public void addItem(Item item) {
        try {
            if(item instanceof Demand) {
                demandSemaphore.acquire();
                demandList.add((Demand) item);
            }
            else if(item instanceof Offer) {
                offerSemaphore.acquire();
                offerList.add((Offer) item);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(item instanceof Demand)
                demandSemaphore.release();
            else if(item instanceof Offer) {
                offerSemaphore.release();
            }
        }
    }

    public void updateItem(Item oldItem, Item newItem) {

    }

    public List<Offer> getOffers() {
        return offerList.getProposalList();
    }

    public List<Demand> getDemands() {
        return demandList.getProposalList();
    }

}
