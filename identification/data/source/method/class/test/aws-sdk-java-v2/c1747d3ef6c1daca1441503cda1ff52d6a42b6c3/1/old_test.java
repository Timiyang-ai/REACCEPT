    @Test
    public void removeIfExists() throws Exception {
        MockChannel channel = null;

        try {
            channel = new MockChannel();
            ChannelPipeline pipeline = channel.pipeline();
            pipeline.addLast(new ReadTimeoutHandler(1));
            pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));

            ChannelUtils.removeIfExists(pipeline, ReadTimeoutHandler.class, LoggingHandler.class);
            assertThat(pipeline.get(ReadTimeoutHandler.class)).isNull();
            assertThat(pipeline.get(LoggingHandler.class)).isNull();
        } finally {
            Optional.ofNullable(channel).ifPresent(Channel::close);
        }

    }