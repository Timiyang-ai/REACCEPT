@Override
   public ListenableFuture<Void> removeBlob(final String container, final String key) {
      if (getContainerToBlobs().containsKey(container)) {
         getContainerToBlobs().get(container).remove(key);
      }
      return immediateFuture(null);
   }