@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "keys",
        args = {}
    )
    public void test_keys() throws IOException {
        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
        
        Set<SelectionKey> keySet = selector.keys();
        Set<SelectionKey> keySet2 = selector.keys();
        
        assertSame(keySet, keySet2);
        assertEquals(1,keySet.size());
        SelectionKey key2 = keySet.iterator().next();
        assertEquals(key,key2);

        // Any attempt to modify keys will cause UnsupportedOperationException
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        SelectionKey key3 = sc.register(selector, SelectionKey.OP_READ);
        try {
            keySet2.add(key3);
            fail("should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
        try {
            keySet2.remove(key3);
            fail("should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
        try {
            keySet2.clear();
            fail("should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }

        selector.close();
        try {
            selector.keys();
            fail("should throw ClosedSelectorException");
        } catch (ClosedSelectorException e) {
            // expected
        }
    }