package Items;

import Users.Buyer;

public class Demand extends Item {

    private final Buyer buyer;
    public Demand(Buyer buyer, int price, Share shareType, int amount) {
        super(price, shareType, amount);
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Demand) {
            Demand demand = (Demand) obj;
            if(demand.getBuyer().getId() == buyer.getId())
                return super.isEqual(obj);
        }
        return false;
    }
}
