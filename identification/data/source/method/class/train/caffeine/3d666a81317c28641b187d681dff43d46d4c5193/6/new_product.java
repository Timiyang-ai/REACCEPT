public void awaitSynchronous() {
    List<CompletableFuture<Void>> futures = pending.get();
    try {
      if (!futures.isEmpty()) {
        CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[futures.size()])).join();
      }
    } catch (CompletionException e) {
      logger.log(Level.WARNING, null, e);
    } finally {
      futures.clear();
    }
  }