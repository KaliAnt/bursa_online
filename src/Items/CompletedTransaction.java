package Items;

import Users.Buyer;
import Users.Seller;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.jetbrains.annotations.NotNull;

@Immutable
public class CompletedTransaction extends Item {
    private final String buyerId;
    private final String sellerId;

    public CompletedTransaction(@NotNull Buyer buyer, int price, Share shareType, int amount, Seller seller) {
        super(price, shareType, amount);
        this.buyerId = buyer.getId();
        this.sellerId = seller.getId();
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }
}

