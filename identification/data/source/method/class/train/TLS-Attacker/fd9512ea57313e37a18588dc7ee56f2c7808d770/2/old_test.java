@Test
    public void testApplyDelegate() {
        Config config = Config.createConfig();
        config.setHighestProtocolVersion(ProtocolVersion.SSL2);
        config.setTransportHandlerType(TransportHandlerType.EAP_TLS);
        args = new String[2];
        args[0] = "-version";
        args[1] = "TLS12";
        jcommander.parse(args);
        delegate.applyDelegate(config);
        assertTrue(config.getHighestProtocolVersion() == ProtocolVersion.TLS12);
        assertTrue(config.getTransportHandlerType() == TransportHandlerType.TCP);
    }