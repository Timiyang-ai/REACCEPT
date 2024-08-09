@Override
  public <R> CompletableFuture<R> submit(Operation<R> operation) {
    if (!open)
      throw new IllegalStateException("protocol not open");
    return context.submit(operation);
  }