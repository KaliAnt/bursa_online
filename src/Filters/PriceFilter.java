package Filters;

import Events.Event;

import static Filters.FilterType.PRICE;

public class PriceFilter extends EventFilter{
    private int max;
    private int min;

    public PriceFilter(int min, int max){
        super(PRICE);
        this.min = min;
        this.max = max;
    }

    public boolean applyFilter(Event event) {
        int price = event.getItem().getPrice();
        if(price >= min && price <= max) {
            return true;
        }
        return false;
    }
}
