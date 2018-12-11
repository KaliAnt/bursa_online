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
}
