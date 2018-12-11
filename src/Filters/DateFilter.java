package Filters;

import Events.Event;

import java.util.Date;

import static Filters.FilterType.DATE;

public class DateFilter extends EventFilter {
    private final Date date;
    public DateFilter(Date date){
        super(DATE);
        this.date = date;
    }

    @Override
    public boolean applyFilter(Event event) {
        if(event.getItem().getDate().after(date))
            return true;
        return false;
    }
}
