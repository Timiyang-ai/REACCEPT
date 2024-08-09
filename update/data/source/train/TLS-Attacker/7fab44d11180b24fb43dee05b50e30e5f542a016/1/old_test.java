@Test
    public void testApplyDelegate() {
        Config config = Config.createConfig();
        config.setHost(null);
        args = new String[2];
        args[0] = "-connect";
        args[1] = "123456";

        jcommander.parse(args);
        delegate.applyDelegate(config);
        assertTrue(config.getHost().equals("123456"));
        assertTrue(config.getConnectionEndType() == ConnectionEndType.CLIENT);
    }