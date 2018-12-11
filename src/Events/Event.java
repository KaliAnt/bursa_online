package Events;

import Items.Item;
import Items.Offer;

public class Event {
    private final Item item;
    private final EventType type;

    public Event(EventType type, Item item) {
        this.item  = item;
        this.type = type;
    }

    public String getEventType() {
        switch(type) {
            case OFFER_READ:
                return "An offer was read";
            case OFFER_CHANGED:
                return "An offer was changed";
            case OFFER_DELETED:
                return "An offer is not available anymore";
            case OFFER_APPEARED:
                return "An offer has appeared";
            default:
                return "Error";
        }
    }

    public Item getItem()
    {
        return item;
    }

}
