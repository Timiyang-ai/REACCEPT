    @Override
    protected void remove(String key, TObject value, long record) {
        ((AtomicOperation) store).remove(key, value, record);

    }