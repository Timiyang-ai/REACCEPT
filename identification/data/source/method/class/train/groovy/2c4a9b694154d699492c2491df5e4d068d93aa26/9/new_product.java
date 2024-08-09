@Override
    public void cleanUpNullReferences() {
        doWithWriteLock(c -> {
            c.cleanUpNullReferences();
            return null;
        });
    }