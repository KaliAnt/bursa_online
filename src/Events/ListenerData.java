package Events;

import Filters.EventFilter;

public class ListenerData {
    private EventListener listener;
    private EventFilter filter;

    public ListenerData(EventListener listener) {
        this.listener = listener;
    }

    public void addFilter(EventFilter filter) {
        this.filter = filter;
    }

    public EventListener getListener() {
        return listener;
    }

    public EventFilter getFilter() {
        return filter;
    }

    public boolean applyFilter(Event e) {
        return filter.applyFilter(e);
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
