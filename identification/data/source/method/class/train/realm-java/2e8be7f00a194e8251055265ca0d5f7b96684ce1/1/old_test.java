@Test
    public void foreach_shouldRemoveWeakRefs() {
        TestObserverPair pair = new TestObserverPair(ONE, new TestListener());
        observerPairs.add(pair);
        assertEquals(1, observerPairs.size());
        observerPairs.foreach(new ObserverPairList.Callback<TestObserverPair>() {
            @Override
            public void onCalled(TestObserverPair pair, Object observer) {
                // There is no guaranteed way to release the WeakReference,
                // just clear it.
                pair.observerRef.clear();
            }
        });
        assertEquals(1, observerPairs.size());

        observerPairs.foreach(new ObserverPairList.Callback<TestObserverPair>() {
            @Override
            public void onCalled(TestObserverPair pair, Object observer) {
                fail();
            }
        });
        assertEquals(0, observerPairs.size());
    }