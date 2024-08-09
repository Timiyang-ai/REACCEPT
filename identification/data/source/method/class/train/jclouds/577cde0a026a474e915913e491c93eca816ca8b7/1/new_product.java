@Override
   public ListenableFuture<Void> removeBlob(final String container, final String key) {
      storageStrategy.removeBlob(container, key);
      return immediateFuture(null);
   }