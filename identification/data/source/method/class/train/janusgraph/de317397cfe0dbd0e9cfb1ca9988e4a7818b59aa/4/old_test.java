    public void close() throws BackendException {
        for (int i = 0; i < CONCURRENCY; i++) {
            idAuthorities[i].close();
            manager[i].close();
        }
    }