public boolean store(Tangle tangle, Snapshot initialSnapshot) throws Exception {

        if (initialSnapshot.hasSolidEntryPoint(hash)) {
            return false;
        }

        // We need to save approvees, tags, and other metadata that is used by
        // non-cached operations.
        List<Pair<Indexable, Persistable>> batch = getSaveBatch();
        if (exists(tangle, hash)) {
            return false;
        }
        tangle.saveBatch(batch);

        cachePut(tangle, this, hash);
        return true;
    }