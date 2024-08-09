@Test
    public void testApplyDelegate() {
        Config config = Config.createConfig();
        config.setHighestProtocolVersion(ProtocolVersion.SSL2);
        config.setDefaultTransportHandlerType(TransportHandlerType.EAP_TLS);
        args = new String[2];
        args[0] = "-version";
        args[1] = "TLS12";
        jcommander.parse(args);
        assertTrue(config.getHighestProtocolVersion() == ProtocolVersion.SSL2);
        assertTrue(config.getDefaultTransportHandlerType() == TransportHandlerType.EAP_TLS);
        assertTrue(config.getConnectionEnd().getTransportHandlerType() == TransportHandlerType.EAP_TLS);
        delegate.applyDelegate(config);
        assertTrue(config.getHighestProtocolVersion() == ProtocolVersion.TLS12);
        assertTrue(config.getDefaultTransportHandlerType() == TransportHandlerType.TCP);
        assertTrue(config.getConnectionEnd().getTransportHandlerType() == TransportHandlerType.TCP);
    }