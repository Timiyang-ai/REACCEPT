public static DefaultFuture newFuture(Channel channel, Request request, int timeout, ExecutorService executor, CompletableFuture completableFuture) {
        final DefaultFuture future = new DefaultFuture(channel, request, timeout, executor, completableFuture);
        // timeout check
        timeoutCheck(future);
        return future;
    }