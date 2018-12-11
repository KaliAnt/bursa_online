package Items;

import Users.Seller;

import java.util.Date;


public class Offer extends Item{
    private final Seller seller;

    public Offer(Seller seller, int price, Share shareType, int amount) {
        super(price, shareType, amount);
        this.seller = seller;
    }

    public Seller getSeller(){
        return seller;
    }
}
