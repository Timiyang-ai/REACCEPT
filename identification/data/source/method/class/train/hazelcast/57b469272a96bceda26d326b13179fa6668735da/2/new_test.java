    @Test
    public void readManyAsync_noFilter() throws Exception {
        serverRingbuffer.add("1");
        serverRingbuffer.add("2");
        serverRingbuffer.add("3");

        Future<ReadResultSet<String>> f = clientRingbuffer.readManyAsync(0, 3, 3, null).toCompletableFuture();

        ReadResultSet rs = f.get();
        assertInstanceOf(ReadResultSetImpl.class, rs);

        assertEquals(3, rs.readCount());
        assertEquals("1", rs.get(0));
        assertEquals("2", rs.get(1));
        assertEquals("3", rs.get(2));
    }