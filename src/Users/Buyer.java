package Users;

import Items.Share;

public interface Buyer {
    void addSharesToInventory(Share shareType, int amount);
    String getId();
}

