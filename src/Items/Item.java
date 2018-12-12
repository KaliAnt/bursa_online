package Items;

import Users.MainClient;

import java.util.Date;
import java.util.LinkedList;

public abstract class Item {
    private int totalViewers;
    private LinkedList<MainClient> viewers;

    private int price;
    private final Share shareType;
    private int amount;
    private Date date;

    Item(int price, Share shareType, int amount) {
        totalViewers = 0;
        this.price = price;
        this.shareType = shareType;
        this.amount = amount;
        date = new Date();
        viewers = new LinkedList<>();
    }

    void modifyDate(Date date) {
        this.date = date;
    }

    void setPrice(int price) {
        this.price = price;
    }

    void setAmount(int amount) {
        if(amount >0) {
            this.amount = amount;
        }
    }

    public int getPrice() {
        return price;
    }
    public Share getShareType() {
        return shareType;
    }
    public int getAmount() {
        return amount;
    }
    public Date getDate(){ return date; }

    public void modify(Date date, int amount, int price) {
        modifyDate(date);
        setAmount(amount);
        setPrice(price);
    }

    public boolean isEqual(Object obj) {
        if(obj instanceof Item) {
            Item item = (Item) obj;
            if(item.getAmount() == getAmount() &&
                    item.getPrice() == getPrice() &&
                    item.getDate().equals(getDate()) &&
                    item.getShareType() == getShareType()
            )
                return true;
        }
        return false;
    }

    public void readByClient(MainClient client) {
        viewers.add(client);
        totalViewers++;
    }

    public int getTotalViewers() {
        return totalViewers;
    }
}
