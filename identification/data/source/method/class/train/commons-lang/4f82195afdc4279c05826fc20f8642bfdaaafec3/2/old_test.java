@Test
    public void testInitialize() throws Exception {
        final TestCallable call = new TestCallable();
        final CallableBackgroundInitializer<Integer> init = new CallableBackgroundInitializer<Integer>(
                call);
        assertEquals("Wrong result", RESULT, init.initialize());
        assertEquals("Wrong number of invocations", 1, call.callCount);
    }