@TestTargetNew(
        level = TestLevel.PARTIAL_COMPLETE,
        notes = "",
        method = "register",
        args = {java.nio.channels.Selector.class, int.class, java.lang.Object.class}
    )
    public void test_register_LSelectorILObject() throws IOException {
        assertFalse(testChannel.isRegistered());
        Selector acceptSelector1 = SelectorProvider.provider().openSelector();
        Selector acceptSelector2 = new MockAbstractSelector(SelectorProvider
                .provider());
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        SelectionKey acceptKey = sc.register(acceptSelector1,
                SelectionKey.OP_READ, null);
        assertNotNull(acceptKey);
        assertTrue(acceptKey.isValid());
        assertSame(sc, acceptKey.channel());

        //test that sc.register invokes Selector.register()
        acceptKey = sc.register(acceptSelector2, SelectionKey.OP_READ, null);
        assertTrue(((MockAbstractSelector)acceptSelector2).isRegisterCalled);

        // Regression test to ensure acceptance of a selector with empty
        // interest set.
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, 0);
        selector.close();
        channel.close();
    }