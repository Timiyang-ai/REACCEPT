public static DefaultFuture newFuture(Channel channel, Request request, int timeout) {
        final DefaultFuture defaultFuture = new DefaultFuture(channel, request, timeout);
        timeoutCheck(defaultFuture);
        return defaultFuture;
    }