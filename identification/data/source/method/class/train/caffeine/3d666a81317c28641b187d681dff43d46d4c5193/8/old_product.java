public void awaitSynchronous() {
    List<CompletableFuture<Void>> futures = pending.get();
    try {
      CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[futures.size()])).join();
    } catch (CompletionException e) {
      // ignored
    } finally {
      futures.clear();
    }
  }