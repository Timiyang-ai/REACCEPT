@Override public final V localPeek(K key,
        CachePeekMode[] peekModes)
        throws IgniteCheckedException {
        A.notNull(key, "key");

        if (keyCheck)
            validateCacheKey(key);

        ctx.checkSecurity(SecurityPermission.CACHE_READ);

        PeekModes modes = parsePeekModes(peekModes, false);

        KeyCacheObject cacheKey = ctx.toCacheKeyObject(key);

        CacheObject cacheVal = null;

        if (!ctx.isLocal()) {
            AffinityTopologyVersion topVer = ctx.affinity().affinityTopologyVersion();

            int part = ctx.affinity().partition(cacheKey);

            boolean nearKey;

            if (!(modes.near && modes.primary && modes.backup)) {
                boolean keyPrimary = ctx.affinity().primaryByPartition(ctx.localNode(), part, topVer);

                if (keyPrimary) {
                    if (!modes.primary)
                        return null;

                    nearKey = false;
                }
                else {
                    boolean keyBackup = ctx.affinity().partitionBelongs(ctx.localNode(), part, topVer);

                    if (keyBackup) {
                        if (!modes.backup)
                            return null;

                        nearKey = false;
                    }
                    else {
                        if (!modes.near)
                            return null;

                        nearKey = true;

                        // Swap and offheap are disabled for near cache.
                        modes.offheap = false;
                    }
                }
            }
            else {
                nearKey = !ctx.affinity().partitionBelongs(ctx.localNode(), part, topVer);

                if (nearKey) {
                    // Swap and offheap are disabled for near cache.
                    modes.offheap = false;
                }
            }

            if (nearKey && !ctx.isNear())
                return null;

            GridCacheEntryEx e;
            GridCacheContext ctx0;

            while (true) {
                if (nearKey)
                    e = peekEx(key);
                else {
                    ctx0 = ctx.isNear() ? ctx.near().dht().context() : ctx;
                    e = modes.offheap ? ctx0.cache().entryEx(key) : ctx0.cache().peekEx(key);
                }

                if (e != null) {
                    ctx.shared().database().checkpointReadLock();

                    try {
                        cacheVal = ctx.mvccEnabled()
                            ? e.mvccPeek(modes.heap && !modes.offheap)
                            : e.peek(modes.heap, modes.offheap, topVer, null);
                    }
                    catch (GridCacheEntryRemovedException ignore) {
                        if (log.isDebugEnabled())
                            log.debug("Got removed entry during 'peek': " + key);

                        continue;
                    }
                    finally {
                        e.touch();

                        ctx.shared().database().checkpointReadUnlock();
                    }
                }

                break;
            }
        }
        else {
            while (true) {
                try {
                    cacheVal = localCachePeek0(cacheKey, modes.heap, modes.offheap);

                    break;
                }
                catch (GridCacheEntryRemovedException ignore) {
                    if (log.isDebugEnabled())
                        log.debug("Got removed entry during 'peek': " + key);

                    // continue
                }
            }
        }

        Object val = ctx.unwrapBinaryIfNeeded(cacheVal, ctx.keepBinary(), false);

        return (V)val;
    }