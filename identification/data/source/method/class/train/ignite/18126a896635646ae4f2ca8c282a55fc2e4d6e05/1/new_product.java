public <K, V, T> IgniteInternalFuture<GridCacheReturn> invokeAsync(
        GridCacheContext cacheCtx,
        @Nullable AffinityTopologyVersion entryTopVer,
        @Nullable Map<? extends K, ? extends EntryProcessor<K, V, Object>> map,
        Object... invokeArgs
    ) {
        return (IgniteInternalFuture<GridCacheReturn>)putAllAsync0(cacheCtx,
            entryTopVer,
            null,
            map,
            invokeArgs,
            null,
            true);
    }