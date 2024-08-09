@SafeVarargs
    public static void removeIfExists(ChannelPipeline pipeline, Class<? extends ChannelHandler>... handlers) {
        for (Class<? extends ChannelHandler> handler : handlers) {
            if (pipeline.get(handler) != null) {
                pipeline.remove(handler);
            }
        }
    }