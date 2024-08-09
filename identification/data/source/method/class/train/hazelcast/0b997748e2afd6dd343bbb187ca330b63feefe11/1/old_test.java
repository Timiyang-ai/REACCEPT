    @Test
    public void add() throws Exception {
        clientRingbuffer.add("foo");
        assertEquals("foo", serverRingbuffer.readOne(0));
    }