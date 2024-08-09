@Test
    public void noProxySelected() {
        // Arrange
        when(proxySelector.select(argThat(u -> u.getHost().equals(HOSTNAME))))
            .thenReturn(Collections.singletonList(PROXY));

        final WebSocketsProxyConnectionHandler handler = new WebSocketsProxyConnectionHandler(CONNECTION_ID, HOSTNAME,
            PROXY_CONFIGURATION);

        // Act and Assert
        Assert.assertEquals(PROXY_ADDRESS.getHostName(), handler.getHostname());
        Assert.assertEquals(PROXY_ADDRESS.getPort(), handler.getProtocolPort());
    }