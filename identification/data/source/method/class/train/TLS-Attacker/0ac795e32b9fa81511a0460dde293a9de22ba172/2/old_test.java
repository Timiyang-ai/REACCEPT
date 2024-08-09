@Test
    public void testApplyDelegate() {
        Config config = Config.createConfig();
        config.clearConnectionEnds();
        args = new String[2];
        args[0] = "-port";
        args[1] = "1234";
        jcommander.parse(args);
        delegate.applyDelegate(config);
        ConnectionEnd newConEnd = config.getConnectionEnd();
        assertNotNull(newConEnd);
        assertTrue(newConEnd.getPort() == 1234);
        assertTrue(newConEnd.getConnectionEndType() == ConnectionEndType.SERVER);
    }