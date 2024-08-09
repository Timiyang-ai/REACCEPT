@SafeVarargs
    public static void removeIfExists(ChannelPipeline pipeline, Class<? extends ChannelHandler>... handlers) {
        for (Class<? extends ChannelHandler> handler : handlers) {
            if (pipeline.get(handler) != null) {
                try {
                    pipeline.remove(handler);
                } catch (NoSuchElementException exception) {
                    // There could still be race condition when channel gets
                    // closed right after removeIfExists is invoked. Ignoring
                    // NoSuchElementException for that edge case.
                }
            }
        }
    }