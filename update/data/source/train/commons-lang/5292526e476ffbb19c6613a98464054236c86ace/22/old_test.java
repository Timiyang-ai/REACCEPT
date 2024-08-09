@Test
    public void testInitialize() {
        BackgroundInitializerTestImpl init = new BackgroundInitializerTestImpl();
        init.start();
        checkInitialize(init);
    }