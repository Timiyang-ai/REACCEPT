@Test
    public void noProxySelected() {
        // Arrange
        when(proxySelector.select(argThat(u -> u.getHost().equals(HOSTNAME))))
            .thenReturn(Collections.singletonList(PROXY));

        final WebSocketsProxyConnectionHandler handler = new WebSocketsProxyConnectionHandler(CONNECTION_ID, HOSTNAME,
            PROXY_CONFIGURATION);

        // Act and Assert
        Assertions.assertEquals(PROXY_ADDRESS.getHostName(), handler.getHostname());
        Assertions.assertEquals(PROXY_ADDRESS.getPort(), handler.getProtocolPort());
    }