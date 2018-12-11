package Filters;

import Events.Event;
import Items.Item;

import static Filters.FilterType.ITEM_FILTER;

//to subscribe to a specific item
public class ItemFilter extends EventFilter {
    private final Item item;
    public ItemFilter(Item item) {
        super(ITEM_FILTER);
        this.item = item;
    }

    @Override
    public boolean applyFilter(Event event) {
        if(event.getItem().equals(item))
            return true;
        return false;
    }
}
