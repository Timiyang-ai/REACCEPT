@Test
    public void testShutdown() {
        final CorfuMsgHandler handler = mock(CorfuMsgHandler.class);
        final ExecutorService executor = mock(ExecutorService.class);

        AbstractServer server = new AbstractServer() {
            @Override
            public CorfuMsgHandler getHandler() {
                return handler;
            }

            @Override
            public boolean isServerReadyToHandleMsg(CorfuMsg msg) {
                return getState() == ServerState.READY;
            }

            @Override
            public ExecutorService getExecutor() {
                return executor;
            }
        };

        server.shutdown();

        server.handleMessage(
                new CorfuMsg(CorfuMsgType.WRITE),
                mock(ChannelHandlerContext.class),
                mock(IServerRouter.class)
        );

        verify(handler, times(0));
    }