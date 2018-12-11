package Filters;

public abstract class EventFilter {
    private final FilterType filterType;
    Object object;

    public EventFilter(FilterType filterType, Object object) {
        this.filterType = filterType;

        this.object = object;
    }

}
