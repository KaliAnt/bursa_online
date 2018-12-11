package Filters;

import Events.Event;

public abstract class EventFilter {
    private final FilterType filterType;

    public EventFilter(FilterType filterType) {
        this.filterType = filterType;
    }

    public abstract boolean applyFilter(Event event);

}
