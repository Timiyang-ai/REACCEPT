    @Test
    public void listStreamsInScope() throws Exception {
        // list stream in scope
        store.createScope("Scope").get();
        store.createStream("Scope", stream1, configuration1, System.currentTimeMillis(), null, executor).get();
        store.setState("Scope", stream1, State.ACTIVE, null, executor).get();
        store.createStream("Scope", stream2, configuration2, System.currentTimeMillis(), null, executor).get();
        store.setState("Scope", stream2, State.ACTIVE, null, executor).get();
        Map<String, StreamConfiguration> streamInScope = store.listStreamsInScope("Scope").get();
        assertEquals("List streams in scope", 2, streamInScope.size());
        assertTrue("List streams in scope", streamInScope.containsKey(stream1));
        assertTrue("List streams in scope", streamInScope.containsKey(stream2));

        // List streams in non-existent scope 'Scope1'
        try {
            store.listStreamsInScope("Scope1").join();
        } catch (StoreException se) {
            assertTrue("List streams in non-existent scope Scope1",
                    se instanceof StoreException.DataNotFoundException);
        } catch (CompletionException ce) {
            assertTrue("List streams in non-existent scope Scope1",
                    Exceptions.unwrap(ce) instanceof StoreException.DataNotFoundException);
        }
    }