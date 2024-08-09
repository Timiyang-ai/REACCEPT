public static ListenerAdapter[] createListenerAdapters(EntryListener listener) {
        EntryEventType[] values = new EntryEventType[]{ADDED, REMOVED, EVICTED, EXPIRED, UPDATED, EVICT_ALL, CLEAR_ALL};
        ListenerAdapter[] listenerAdapters = new ListenerAdapter[values.length];
        for (EntryEventType eventType : values) {
            listenerAdapters[eventType.ordinal()] = createListenerAdapter(eventType, listener);
        }
        return listenerAdapters;
    }