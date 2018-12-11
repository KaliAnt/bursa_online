package Events;

import java.util.*;

public class EventListenerManager {
    private final Object mutex = new Object();
    private Map<EventType, List<ListenerData>> listenerMap = new HashMap<>();

    public void register(EventType eventType, ListenerData listenerData) {
        synchronized (mutex){
            listenerMap.computeIfAbsent(eventType, elem -> new ArrayList<>()).add(listenerData);
        }
    }

    public void unregister(EventType eventType, ListenerData listenerData){
        synchronized (mutex) {
            if(mapContainsListener(eventType, listenerData)) {
                listenerMap.computeIfPresent(eventType, (type, list) -> {
                   list.remove(listenerData);
                   return list.isEmpty()?null:list;
                });
            }
        }
    }

    private boolean mapContainsListener(EventType type, ListenerData listener){
        return listenerMap.getOrDefault(type, Collections.emptyList()).stream().anyMatch(listener::equals);
    }
}
