package Events;

import Filters.EventFilter;
import Items.Offer;

import java.util.ArrayList;
import java.util.List;

public class ListenerData {
    private EventListener listener;
    private ArrayList<EventFilter> filters;

    public ListenerData(EventListener listener) {
        this.listener = listener;
        this.filters = new ArrayList<EventFilter>();
    }

    public ArrayList<Offer> addFilter() {
        // TODO: 11-Dec-18
        return null;
    }

    public EventListener getListener() {
        return listener;
    }

    public ArrayList<EventFilter> getFilters() {
        return (ArrayList<EventFilter>) filters;
    }

    public boolean equals(Object object) {
        if(object instanceof ListenerData) {
            EventListener eventListener = (EventListener) object;
            return eventListener == listener;
        }
        return false;
    }

}
