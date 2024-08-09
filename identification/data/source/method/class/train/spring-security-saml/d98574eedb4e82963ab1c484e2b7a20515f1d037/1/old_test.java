@Test(expected = RuntimeException.class)
    public void testInitialize() {
        new JKSKeyManager(null, "xxx");
    }