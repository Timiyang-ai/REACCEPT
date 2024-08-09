    @Test
    public void onOutOfMemory() {
        OutOfMemoryError oome = new OutOfMemoryError();
        OutOfMemoryHandler handler = mock(OutOfMemoryHandler.class);
        when(handler.shouldHandle(oome)).thenReturn(Boolean.TRUE);

        HazelcastInstance hz1 = mock(HazelcastInstance.class);

        OutOfMemoryErrorDispatcher.registerServer(hz1);
        OutOfMemoryErrorDispatcher.setServerHandler(handler);

        HazelcastInstance[] registeredInstances = OutOfMemoryErrorDispatcher.current();

        OutOfMemoryErrorDispatcher.onOutOfMemory(oome);

        //make sure the handler is called
        verify(handler).onOutOfMemory(oome, registeredInstances);
        //make sure that the registered instances are removed.
        assertArrayEquals(new HazelcastInstance[]{}, OutOfMemoryErrorDispatcher.current());
    }