@Nonnull
  CompletableFuture<V> get(@Nonnull K key,
      @Nonnull Function<? super K, CompletableFuture<V>> mappingFunction);