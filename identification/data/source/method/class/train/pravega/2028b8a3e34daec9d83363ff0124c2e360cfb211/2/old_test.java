    @Test(timeout = 10000)
    public void compareAndSetAttribute() {
        UUID attributeId = SegmentAttribute.RevisionStreamClientMark.getValue();
        Segment segment = new Segment("scope", "testRetry", 4);
        PravegaNodeUri endpoint = new PravegaNodeUri("localhost", 0);
        MockConnectionFactoryImpl cf = new MockConnectionFactoryImpl();
        MockController controller = new MockController(endpoint.getEndpoint(), endpoint.getPort(), cf, true);
        ClientConnection connection = mock(ClientConnection.class);
        cf.provideConnection(endpoint, connection);
        @Cleanup
        SegmentMetadataClientImpl client = new SegmentMetadataClientImpl(segment, controller, cf, "");
        client.getConnection();
        ReplyProcessor processor = cf.getProcessor(endpoint);
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                WireCommands.UpdateSegmentAttribute request = invocation.getArgument(0);
                processor.process(new SegmentAttributeUpdated(request.getRequestId(), true));
                return null;
            }
        }).when(connection).sendAsync(any(WireCommands.UpdateSegmentAttribute.class), Mockito.any(ClientConnection.CompletedCallback.class));
        assertTrue(client.compareAndSetAttribute(SegmentAttribute.RevisionStreamClientMark, -1234, 1234));
    }