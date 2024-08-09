    @Test
    public void netListeningTest() throws Exception {
        NetListening response = Mockito.mock(NetListening.class);
        Mockito.when(mockWeb3j.netListening()).thenReturn(request);
        Mockito.when(request.send()).thenReturn(response);
        Mockito.when(response.isListening()).thenReturn(true);

        Exchange exchange = createExchangeWithBodyAndHeader(null, OPERATION, Web3jConstants.NET_LISTENING);
        template.send(exchange);
        Boolean body = exchange.getIn().getBody(Boolean.class);
        assertTrue(body);
    }