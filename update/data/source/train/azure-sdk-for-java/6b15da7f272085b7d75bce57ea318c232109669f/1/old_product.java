@Theory
    public void noProxySelected(@FromDataPoints("configurations") ProxyConfiguration configuration) {
        // Arrange
        final String hostname = "foo.eventhubs.azure.com";
        when(proxySelector.select(argThat(u -> u.getHost().equals(hostname))))
            .thenReturn(Collections.singletonList(PROXY));

        // Act
        final ConnectionHandler handler = provider.createConnectionHandler(CONNECTION_ID, hostname,
            TransportType.AMQP_WEB_SOCKETS, configuration);

        // Act and Assert
        Assert.assertEquals(PROXY_ADDRESS.getHostName(), handler.getHostname());
        Assert.assertEquals(PROXY_ADDRESS.getPort(), handler.getProtocolPort());
    }