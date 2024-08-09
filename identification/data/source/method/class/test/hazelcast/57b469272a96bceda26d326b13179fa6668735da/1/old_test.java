    @Test
    public void addAsync() throws Exception {
        Future<Long> f = clientRingbuffer.addAsync("foo", OVERWRITE).toCompletableFuture();
        Long result = f.get();

        assertEquals(new Long(serverRingbuffer.headSequence()), result);
        assertEquals("foo", serverRingbuffer.readOne(0));
        assertEquals(0, serverRingbuffer.headSequence());
        assertEquals(0, serverRingbuffer.tailSequence());
    }