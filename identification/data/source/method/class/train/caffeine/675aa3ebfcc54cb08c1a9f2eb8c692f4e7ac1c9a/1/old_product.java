static boolean isReady(@Nullable CompletableFuture<?> future) {
    return (future != null) && future.isDone() && !future.isCompletedExceptionally();
  }