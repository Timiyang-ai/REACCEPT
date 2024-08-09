@Test
    public void test_createListenerAdapters() {
        TestEntryListener listener = new TestEntryListener();
        ListenerAdapter[] listenerAdapters = EntryListenerAdaptors.createListenerAdapters(listener);
        for (ListenerAdapter<?> listenerAdapter : listenerAdapters) {
            // just pass null to trigger corresponding listener method calls.
            listenerAdapter.onEvent(null);
        }

        String msg = "should be called exactly 1 times";
        assertEquals(msg, listener.entryAddedCalled, 1);
        assertEquals(msg, listener.entryEvictedCalled, 1);
        assertEquals(msg, listener.entryRemovedCalled, 1);
        assertEquals(msg, listener.entryUpdatedCalled, 1);
        assertEquals(msg, listener.mapClearedCalled, 1);
        assertEquals(msg, listener.mapEvictedCalled, 1);
    }