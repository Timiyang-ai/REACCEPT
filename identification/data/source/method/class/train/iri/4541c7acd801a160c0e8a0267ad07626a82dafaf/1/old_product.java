public boolean store(Tangle tangle, Snapshot initialSnapshot) throws Exception {
        if (initialSnapshot.hasSolidEntryPoint(hash) || exists(tangle, hash)) {
            return false;
        }

        List<Pair<Indexable, Persistable>> batch = getSaveBatch();
        if (exists(tangle, hash)) {
            return false;
        }
        return tangle.saveBatch(batch);
    }