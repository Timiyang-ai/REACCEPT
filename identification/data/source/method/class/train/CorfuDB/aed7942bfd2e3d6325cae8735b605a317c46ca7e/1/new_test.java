@Test
    public void testShutdown() {
        final HandlerMethods handler = mock(HandlerMethods.class);
        final ExecutorService executor = mock(ExecutorService.class);

        AbstractServer server = new AbstractServer() {
            @Override
            public HandlerMethods getHandler() {
                return handler;
            }

            @Override
            public boolean isServerReadyToHandleMsg(CorfuMsg msg) {
                return getState() == ServerState.READY;
            }
        };

        server.shutdown();

        server.handleMessage(
                new CorfuMsg(CorfuMsgType.WRITE),
                mock(ChannelHandlerContext.class),
                mock(IServerRouter.class)
        );

        verify(handler, times(0)).handle(any(), any(), any());
    }