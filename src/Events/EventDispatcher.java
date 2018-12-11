package Events;


import Filters.EventFilter;
import org.jetbrains.annotations.NotNull;

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

    public void publishEvent(Event event) {
        if(event != null) {
            eventsQueue.add(event);
        }
    }

    private void dispatch(ListenerData listenerData, Event event){
        EventListener listener = listenerData.getListener();
        //manage filters, apply filter on event
        if(listenerData.applyFilter(event)) {
            listener.handleEvent(event);
        }
    }

    private void dispatchLoop() {
        while(!Thread.currentThread().isInterrupted()){
            try{
                final Event event = eventsQueue.take();
                eventListenerManager.getListenersForEvent(event.getType())
                        .forEach(listenerData -> dispatch(listenerData, event));
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
