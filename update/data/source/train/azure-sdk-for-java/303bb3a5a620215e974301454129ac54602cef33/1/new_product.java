@ParameterizedTest
    @MethodSource("getProxyConfigurations")
    public void noProxySelected(ProxyOptions configuration) {
        // Arrange
        final String hostname = "foo.eventhubs.azure.com";
        when(proxySelector.select(argThat(u -> u.getHost().equals(hostname))))
            .thenReturn(Collections.singletonList(PROXY));

        // Act
        final ConnectionHandler handler = provider.createConnectionHandler(CONNECTION_ID, hostname,
            AmqpTransportType.AMQP_WEB_SOCKETS, configuration, PRODUCT, CLIENT_VERSION);

        // Act and Assert
        Assertions.assertEquals(PROXY_ADDRESS.getHostName(), handler.getHostname());
        Assertions.assertEquals(PROXY_ADDRESS.getPort(), handler.getProtocolPort());
    }