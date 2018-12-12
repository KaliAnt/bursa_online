package Events;

import Filters.EventFilter;

import java.util.ArrayList;

public class ListenerData {
    private EventListener listener;
    private ArrayList<EventFilter> filters;

    public ListenerData(EventListener listener) {
        this.listener = listener;
    }

    public void addFilter(EventFilter filter) {
        this.filters.add(filter);
    }

    public EventListener getListener() {
        return listener;
    }

    public ArrayList getFilter() {
        return filters;
    }

    public boolean applyFilter(Event e) {
        for (EventFilter filter:
             filters) {
            if (!filter.applyFilter(e))
                return false;
        }
        return true;
    }


    @Override
    public boolean equals(Object object) {
        if(object instanceof ListenerData) {
            EventListener eventListener = (EventListener) object;
            return eventListener == listener;
        }
        return false;
    }

}
