@Nonnull
    public CompletableFuture<MCMapConfig> getMapConfig(String map) {
        checkNotNull(map);

        return doGetMapConfig(null, map);
    }