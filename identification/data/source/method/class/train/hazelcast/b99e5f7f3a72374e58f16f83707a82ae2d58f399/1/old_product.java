public static ListenerAdapter[] createListenerAdapters(EntryListener listener) {
        // We only care about these reference event types for backward compatibility.
        EntryEventType[] values = new EntryEventType[]{ADDED, REMOVED, EVICTED, UPDATED, EVICT_ALL, CLEAR_ALL};
        ListenerAdapter[] listenerAdapters = new ListenerAdapter[values.length];
        for (EntryEventType eventType : values) {
            listenerAdapters[eventType.ordinal()] = createListenerAdapter(eventType, listener);
        }
        return listenerAdapters;
    }