package Items;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ThreadSafe
public class ConcurrentTransactionList<T extends Item> {
    @GuardedBy("this")
    private final CopyOnWriteArrayList<T> myList;
    private final List<T> unmodifiableList;

    public ConcurrentTransactionList(CopyOnWriteArrayList<T> set) {
        myList = set;
        unmodifiableList = Collections.unmodifiableList(myList);
    }

    public synchronized void add(T p) {
        myList.add(p);
    }

    public List<T> getProposalList() {
        return unmodifiableList;
    }

    public synchronized void remove(Item transaction){
        for (T p : myList) {
            if (p.equals(transaction)) myList.remove(p);
        }
    }
}