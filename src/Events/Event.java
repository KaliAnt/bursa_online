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
            case ITEM_READ:
                return "An offer was read";
            case ITEM_CHANGED:
                return "An offer was changed";
            case ITEM_DELETED:
                return "An offer is not available anymore";
            case ITEM_APPEARED:
                return "An offer has appeared";
            default:
                return "Error";
        }
    }

    public Item getItem()
    {
        return item;
    }
    public EventType getType() { return type; }
}
