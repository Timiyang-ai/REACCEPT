@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "selectedKeys",
        args = {}
    )
    public void test_selectedKeys() throws IOException {
        SocketChannel sc = SocketChannel.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        try {
            int count = 0;
            sc.connect(LOCAL_ADDRESS);
            count = blockingSelect(SelectType.NULL, 0);
            assertEquals(1, count);
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Set<SelectionKey> selectedKeys2 = selector.selectedKeys();
            assertSame(selectedKeys, selectedKeys2);

            assertEquals(1, selectedKeys.size());
            assertEquals(ssc.keyFor(selector), selectedKeys.iterator().next());
            // add one key into selectedKeys
            try {
                selectedKeys.add(ssc.keyFor(selector));
                fail("Should throw UnsupportedOperationException");
            } catch (UnsupportedOperationException e) {
                // expected
            }

            // no exception should be thrown
            selectedKeys.clear();

            Set<SelectionKey> selectedKeys3 = selector.selectedKeys();
            assertSame(selectedKeys, selectedKeys3);

            ssc.keyFor(selector).cancel();
            assertEquals(0, selectedKeys.size());
            selector.close();
            try {
                selector.selectedKeys();
                fail("should throw ClosedSelectorException");
            } catch (ClosedSelectorException e) {
                // expected
            }
        } finally {
            sc.close();
        }
    }