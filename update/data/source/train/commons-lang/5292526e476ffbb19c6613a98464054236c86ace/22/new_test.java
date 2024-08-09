@Test
    public void testInitialize() {
        final BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        checkInitialize(init);
    }