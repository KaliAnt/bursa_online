package Events;


import Filters.EventFilter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventDispatcher {
    private final EventListenerManager eventListenerManager = new EventListenerManager();
    private final BlockingQueue<Event> eventsQueue = new LinkedBlockingQueue<>();
    private final Thread mainDispatchThread;

    public EventDispatcher() {
        mainDispatchThread = new Thread(this::dispatchLoop);
        this.mainDispatchThread.start();
    }

    public void register(EventType type, ListenerData data) {
        eventListenerManager.register(type, data);
    }

    public void unregister(EventType type, ListenerData data){
        eventListenerManager.register(type, data);
    }

    public void publishEvent() {

    }

    private void dispatch(ListenerData listenerData, Event event){
        EventListener listener = listenerData.getListener();
        //manage filters, apply filter on offer

    }



    private void dispatchLoop() {

    }

}
