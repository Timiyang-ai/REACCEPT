@Test
    public void testApplyDelegate() {
        Config config = Config.createConfig();
        int expectedDefaultTimeout = 390121;
        config.getDefaultServerConnection().setTimeout(expectedDefaultTimeout);
        args = new String[2];
        args[0] = "-port";
        args[1] = "1234";
        jcommander.parse(args);
        delegate.applyDelegate(config);
        AliasedConnection actual = config.getDefaultServerConnection();
        assertNotNull(actual);
        assertThat(actual.getPort(), equalTo(1234));
        assertThat(actual.getLocalConnectionEndType(), equalTo(ConnectionEndType.SERVER));
        assertThat(actual.getTimeout(), equalTo(expectedDefaultTimeout));

    }